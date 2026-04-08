document.addEventListener('DOMContentLoaded', function () {
    console.log('cart.js loaded at:', new Date().toISOString());
    loadCart();
});

function loadCart() {
    fetch('/cart/api/cart', {
        method: 'GET',
        credentials: 'include'
    })
        .then(response => {
            if (!response.ok) throw new Error('Network response was not ok');
            return response.json();
        })
        .then(cart => {
            const cartItemsDiv = document.getElementById('cartItems');
            cartItemsDiv.innerHTML = '';
            if (Object.keys(cart).length === 0) {
                cartItemsDiv.innerHTML = '<p class="text-center">Your cart is empty.</p>';
                return;
            }
            Object.entries(cart).forEach(([toyId, item]) => {
                const div = document.createElement('div');
                div.className = 'col-md-4 mb-3';
                div.innerHTML = `
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title">${item.toyId}</h5>
                        <p class="card-text">Quantity: ${item.quantity}</p>
                        <p class="card-text">Price: $${item.price.toFixed(2)}</p>
                        <button class="btn btn-danger remove-from-cart-btn" data-toy-id="${toyId}">Remove</button>
                    </div>
                </div>
            `;
                cartItemsDiv.appendChild(div);
            });
            initCartActions();
        })
        .catch(error => {
            console.error('Error loading cart:', error);
            document.getElementById('cartItems').innerHTML = '<p class="text-center">Error loading cart.</p>';
        });
}

function initCartActions() {
    document.querySelectorAll('.remove-from-cart-btn').forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            console.log('Remove from Cart clicked for toyId:', toyId);
            removeFromCart(toyId);
        });
    });
}

function removeFromCart(toyId) {
    fetch('/cart/remove', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `toyId=${encodeURIComponent(toyId)}`,
        credentials: 'include'
    })
        .then(response => {
            if (!response.ok) throw new Error('Failed to remove item');
            return response.text();
        })
        .then(message => {
            console.log('Remove response:', message);
            window.showToast(message, 'success');
            loadCart(); // Reload cart after removal
        })
        .catch(error => {
            console.error('Error removing from cart:', error);
            window.showToast(error.message, 'error');
        });
}

window.showToast = window.showToast || function(message, type) {
    const toast = document.createElement('div');
    toast.className = `toast align-items-center text-bg-${type === 'error' ? 'danger' : 'success'} border-0`;
    toast.role = 'alert';
    toast.innerHTML = `
        <div class="d-flex">
            <div class="toast-body">${message}</div>
            <button type="button" class="btn-close btn-close-white me-2 m-auto" data-bs-dismiss="toast" aria-label="Close"></button>
        </div>
    `;
    document.body.appendChild(toast);
    const bsToast = new bootstrap.Toast(toast);
    bsToast.show();
    setTimeout(() => bsToast.hide(), 3000);
};