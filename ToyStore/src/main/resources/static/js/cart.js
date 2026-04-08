/**
 * Cart functionality for ToyLand Online Store
 */
document.addEventListener('DOMContentLoaded', function() {
    console.log('Cart JS loaded');

    // Set up cart controls
    setupCartControls();

    // Initialize "Add to Cart" buttons
    setupAddToCartButtons();

    // Setup cart in session storage if not exists
    if (!sessionStorage.getItem('cart')) {
        sessionStorage.setItem('cart', JSON.stringify({
            items: [],
            totalCount: 0,
            totalPrice: 0
        }));
    }
});

/**
 * Setup "Add to Cart" buttons
 */
function setupAddToCartButtons() {
    // Regular add to cart buttons (product listings)
    const addToCartButtons = document.querySelectorAll('.add-to-cart-btn');
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            const toyName = this.getAttribute('data-toy-name');

            addToCart(toyId);
            showToast(`${toyName} added to cart!`);
        });
    });

    // Product detail page "Add to Cart" button
    const detailAddToCartBtn = document.getElementById('detail-add-to-cart-btn');
    if (detailAddToCartBtn) {
        detailAddToCartBtn.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            const toyName = this.getAttribute('data-toy-name');
            const quantity = parseInt(document.getElementById('quantity').value) || 1;

            addToCart(toyId, quantity);
            showToast(`${toyName} added to cart!`);
        });
    }
}

/**
 * Set up cart controls (increase, decrease, remove)
 */
function setupCartControls() {
    // Quantity increase buttons
    const increaseButtons = document.querySelectorAll('.quantity-increase');
    increaseButtons.forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            const quantityInput = document.getElementById(`quantity-${toyId}`);
            let currentQuantity = parseInt(quantityInput.value);
            const maxQuantity = parseInt(quantityInput.getAttribute('max')) || 100;

            if (currentQuantity < maxQuantity) {
                updateCartItemQuantity(toyId, currentQuantity + 1);
                quantityInput.value = currentQuantity + 1;
                updateCartTotals();
            }
        });
    });

    // Quantity decrease buttons
    const decreaseButtons = document.querySelectorAll('.quantity-decrease');
    decreaseButtons.forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            const quantityInput = document.getElementById(`quantity-${toyId}`);
            let currentQuantity = parseInt(quantityInput.value);

            if (currentQuantity > 1) {
                updateCartItemQuantity(toyId, currentQuantity - 1);
                quantityInput.value = currentQuantity - 1;
                updateCartTotals();
            }
        });
    });

    // Quantity input fields
    const quantityInputs = document.querySelectorAll('.cart-quantity-input');
    quantityInputs.forEach(input => {
        input.addEventListener('change', function() {
            const toyId = this.getAttribute('data-toy-id');
            let newQuantity = parseInt(this.value) || 1;
            const maxQuantity = parseInt(this.getAttribute('max')) || 100;

            // Ensure quantity is between 1 and max
            newQuantity = Math.min(Math.max(newQuantity, 1), maxQuantity);
            this.value = newQuantity;

            updateCartItemQuantity(toyId, newQuantity);
            updateCartTotals();
        });
    });

    // Remove item buttons
    const removeButtons = document.querySelectorAll('.remove-item-btn');
    removeButtons.forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            const confirmRemove = confirm('Are you sure you want to remove this item from your cart?');

            if (confirmRemove) {
                removeCartItem(toyId);
                // Remove the cart item element from DOM
                const cartItemElement = document.getElementById(`cart-item-${toyId}`);
                if (cartItemElement) {
                    cartItemElement.remove();
                }
                updateCartTotals();

                // If cart is empty, show empty state
                const cartItems = document.querySelectorAll('.cart-item');
                if (cartItems.length === 0) {
                    const cartContainer = document.querySelector('.cart-items-container');
                    if (cartContainer) {
                        cartContainer.innerHTML = `
                            <div class="text-center py-5">
                                <i class="bi bi-cart-x text-muted" style="font-size: 4rem;"></i>
                                <h3 class="mt-3">Your cart is empty</h3>
                                <p class="text-muted">Looks like you haven't added any toys to your cart yet.</p>
                                <a href="/toys" class="btn btn-primary mt-3">Continue Shopping</a>
                            </div>
                        `;
                    }
                }
            }
        });
    });

    // Clear cart button
    const clearCartBtn = document.getElementById('clear-cart-btn');
    if (clearCartBtn) {
        clearCartBtn.addEventListener('click', function() {
            const confirmClear = confirm('Are you sure you want to clear your entire cart?');

            if (confirmClear) {
                clearCart();

                // Update the UI to show empty cart
                const cartContainer = document.querySelector('.cart-items-container');
                if (cartContainer) {
                    cartContainer.innerHTML = `
                        <div class="text-center py-5">
                            <i class="bi bi-cart-x text-muted" style="font-size: 4rem;"></i>
                            <h3 class="mt-3">Your cart is empty</h3>
                            <p class="text-muted">Looks like you haven't added any toys to your cart yet.</p>
                            <a href="/toys" class="btn btn-primary mt-3">Continue Shopping</a>
                        </div>
                    `;
                }

                // Update summary section
                updateCartTotals();
            }
        });
    }
}

/**
 * Add an item to the cart
 */
function addToCart(toyId, quantity = 1) {
    // First try API call to server
    fetch('/cart/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `toyId=${toyId}&quantity=${quantity}`
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                console.log('Item added to cart via API');
                updateCartBadge();
            } else {
                console.error('Failed to add item to cart via API:', data.message);
                // Fall back to session storage if API fails
                addToCartSessionStorage(toyId, quantity);
            }
        })
        .catch(error => {
            console.error('Error adding item to cart via API:', error);
            // Fall back to session storage
            addToCartSessionStorage(toyId, quantity);
        });
}

/**
 * Fallback to add item to cart using session storage
 */
function addToCartSessionStorage(toyId, quantity = 1) {
    try {
        // Get existing cart or create new one
        let cart = JSON.parse(sessionStorage.getItem('cart')) || { items: [], totalCount: 0, totalPrice: 0 };

        // Check if item already exists in cart
        const existingItemIndex = cart.items.findIndex(item => item.toyId === toyId);

        if (existingItemIndex >= 0) {
            // Update quantity if item exists
            cart.items[existingItemIndex].quantity += quantity;
        } else {
            // Add new item if it doesn't exist
            // Fetch toy details via API
            fetch(`/toys/${toyId}`)
                .then(response => {
                    // If we can't get toy details, use minimal info
                    const newItem = {
                        toyId: toyId,
                        quantity: quantity,
                        price: 0, // Will be updated when user visits cart page
                        name: 'Unknown Item' // Will be updated when user visits cart page
                    };

                    cart.items.push(newItem);

                    // Update total count
                    cart.totalCount = (cart.totalCount || 0) + quantity;

                    // Save updated cart
                    sessionStorage.setItem('cart', JSON.stringify(cart));

                    // Update cart badge
                    updateCartBadge();
                })
                .catch(error => {
                    console.error('Error fetching toy details:', error);
                    // Still add the item with minimal info
                    const newItem = {
                        toyId: toyId,
                        quantity: quantity,
                        price: 0,
                        name: 'Unknown Item'
                    };

                    cart.items.push(newItem);

                    // Update total count
                    cart.totalCount = (cart.totalCount || 0) + quantity;

                    // Save updated cart
                    sessionStorage.setItem('cart', JSON.stringify(cart));

                    // Update cart badge
                    updateCartBadge();
                });
        }
    } catch (e) {
        console.error('Error adding item to cart via session storage:', e);
    }
}

/**
 * Update cart item quantity
 */
function updateCartItemQuantity(toyId, quantity) {
    // First try API call to server
    fetch('/cart/update', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `toyId=${toyId}&quantity=${quantity}`
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                console.log('Cart item updated via API');

                // Update item price
                const itemTotalElement = document.getElementById(`item-total-${toyId}`);
                if (itemTotalElement && data.itemTotal) {
                    itemTotalElement.textContent = '$' + data.itemTotal.toFixed(2);
                }

                // Update cart totals in UI
                updateCartUITotals(data.cartTotal, data.cartSize);
            } else {
                console.error('Failed to update cart item via API');
                // Fall back to session storage
                updateCartItemSessionStorage(toyId, quantity);
            }
        })
        .catch(error => {
            console.error('Error updating cart item via API:', error);
            // Fall back to session storage
            updateCartItemSessionStorage(toyId, quantity);
        });
}

/**
 * Fallback to update cart item using session storage
 */
function updateCartItemSessionStorage(toyId, quantity) {
    try {
        // Get existing cart
        let cart = JSON.parse(sessionStorage.getItem('cart'));

        if (!cart || !cart.items) {
            console.error('Cart not found in session storage');
            return;
        }

        // Find the item
        const itemIndex = cart.items.findIndex(item => item.toyId === toyId);

        if (itemIndex >= 0) {
            // Calculate quantity difference
            const oldQuantity = cart.items[itemIndex].quantity;
            const quantityDiff = quantity - oldQuantity;

            // Update item quantity
            cart.items[itemIndex].quantity = quantity;

            // Update total count
            cart.totalCount = (cart.totalCount || 0) + quantityDiff;

            // Save updated cart
            sessionStorage.setItem('cart', JSON.stringify(cart));

            // Update cart badge
            updateCartBadge();
        }
    } catch (e) {
        console.error('Error updating cart item via session storage:', e);
    }
}

/**
 * Remove an item from the cart
 */
function removeCartItem(toyId) {
    // First try API call to server
    fetch('/cart/remove', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `toyId=${toyId}`
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                console.log('Item removed from cart via API');

                // Update cart totals in UI
                updateCartUITotals(data.cartTotal, data.cartSize);
            } else {
                console.error('Failed to remove item from cart via API');
                // Fall back to session storage
                removeCartItemSessionStorage(toyId);
            }
        })
        .catch(error => {
            console.error('Error removing item from cart via API:', error);
            // Fall back to session storage
            removeCartItemSessionStorage(toyId);
        });
}

/**
 * Fallback to remove cart item using session storage
 */
function removeCartItemSessionStorage(toyId) {
    try {
        // Get existing cart
        let cart = JSON.parse(sessionStorage.getItem('cart'));

        if (!cart || !cart.items) {
            console.error('Cart not found in session storage');
            return;
        }

        // Find the item
        const itemIndex = cart.items.findIndex(item => item.toyId === toyId);

        if (itemIndex >= 0) {
            // Subtract item quantity from total count
            cart.totalCount = (cart.totalCount || 0) - cart.items[itemIndex].quantity;

            // Remove the item
            cart.items.splice(itemIndex, 1);

            // Save updated cart
            sessionStorage.setItem('cart', JSON.stringify(cart));

            // Update cart badge
            updateCartBadge();
        }
    } catch (e) {
        console.error('Error removing cart item via session storage:', e);
    }
}

/**
 * Clear the cart
 */
function clearCart() {
    // First try API call to server
    fetch('/cart/clear', {
        method: 'POST'
    })
        .then(response => response.json())
        .then(data => {
            if (data.success) {
                console.log('Cart cleared via API');
            } else {
                console.error('Failed to clear cart via API');
                // Fall back to session storage
                clearCartSessionStorage();
            }
        })
        .catch(error => {
            console.error('Error clearing cart via API:', error);
            // Fall back to session storage
            clearCartSessionStorage();
        });

    // Also clear session storage cart as a fallback
    clearCartSessionStorage();
}

/**
 * Fallback to clear cart using session storage
 */
function clearCartSessionStorage() {
    try {
        // Reset cart to empty state
        const emptyCart = { items: [], totalCount: 0, totalPrice: 0 };
        sessionStorage.setItem('cart', JSON.stringify(emptyCart));

        // Update cart badge
        updateCartBadge();
    } catch (e) {
        console.error('Error clearing cart via session storage:', e);
    }
}

/**
 * Update cart totals in UI
 */
function updateCartUITotals(total, count) {
    // Update subtotal
    const subtotalElement = document.getElementById('cart-subtotal');
    if (subtotalElement && total !== undefined) {
        subtotalElement.textContent = '$' + total.toFixed(2);
    }

    // Update total
    const totalElement = document.getElementById('cart-total');
    if (totalElement && total !== undefined) {
        totalElement.textContent = '$' + total.toFixed(2);
    }

    // Update cart badge
    const cartBadge = document.getElementById('cart-badge');
    if (cartBadge && count !== undefined) {
        cartBadge.textContent = count;
    }
}

/**
 * Recalculate and update cart totals from the current page content
 */
function updateCartTotals() {
    try {
        // Calculate totals from visible cart items
        let subtotal = 0;
        let itemCount = 0;

        const priceElements = document.querySelectorAll('[id^="item-total-"]');
        priceElements.forEach(element => {
            const price = parseFloat(element.textContent.replace('$', '')) || 0;
            subtotal += price;
        });

        const quantityInputs = document.querySelectorAll('.cart-quantity-input');
        quantityInputs.forEach(input => {
            itemCount += parseInt(input.value) || 0;
        });

        // Update the UI
        updateCartUITotals(subtotal, itemCount);

        // Update session storage cart
        let cart = JSON.parse(sessionStorage.getItem('cart')) || { items: [], totalCount: 0, totalPrice: 0 };
        cart.totalCount = itemCount;
        cart.totalPrice = subtotal;
        sessionStorage.setItem('cart', JSON.stringify(cart));
    } catch (e) {
        console.error('Error updating cart totals:', e);
    }
}

/**
 * Display a toast notification
 */
function showToast(message, isError = false) {
    // Create toast if it doesn't exist
    let toastContainer = document.querySelector('.toast-container');
    if (!toastContainer) {
        toastContainer = document.createElement('div');
        toastContainer.className = 'toast-container position-fixed bottom-0 end-0 p-3';
        document.body.appendChild(toastContainer);
    }

    // Create a unique ID for this toast
    const toastId = 'toast-' + Date.now();

    // Create toast HTML
    const toastHtml = `
        <div id="${toastId}" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
            <div class="toast-header">
                <strong class="me-auto">ToyLand</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body ${isError ? 'text-danger' : ''}">
                ${message}
            </div>
        </div>
    `;

    // Append toast to container
    toastContainer.innerHTML += toastHtml;

    // Initialize and show the toast
    const toastElement = document.getElementById(toastId);
    const toast = new bootstrap.Toast(toastElement, {
        delay: 3000,
        autohide: true
    });
    toast.show();

    // Remove toast after it's hidden
    toastElement.addEventListener('hidden.bs.toast', function() {
        toastElement.remove();
    });
}