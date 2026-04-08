document.addEventListener('DOMContentLoaded', function () {
    console.log('toys.js loaded at:', new Date().toISOString(), 'calling loadToys');
    loadToys();
    initToyActions();
});

function loadToys() {
    console.log('loadToys called');
    const toysContainer = document.getElementById('toysContainer');
    if (!toysContainer) {
        console.error('toysContainer not found in DOM');
        return;
    }
    toysContainer.innerHTML = '<div class="loading-spinner"></div>';

    // Extract query parameters from URL
    const urlParams = new URLSearchParams(window.location.search);
    const categories = urlParams.get('category') ? urlParams.get('category').split(',') : [];
    const ages = urlParams.get('minAge') ? urlParams.get('minAge').split(',') : [];
    const sort = urlParams.get('sort') || 'default';

    const url = `/api/toys?category=${categories.join(',')}&minAge=${ages.join(',')}&sort=${sort}`;
    console.log('Fetching toys from:', url);

    fetch(url, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            console.log('Fetch response status:', response.status);
            if (!response.ok) throw new Error(`Network error: ${response.status}`);
            return response.json();
        })
        .then(toys => {
            console.log('Toys received:', toys);
            toysContainer.innerHTML = '';
            if (toys.length === 0) {
                toysContainer.innerHTML = '<p>No toys found.</p>';
                return;
            }
            toys.forEach(toy => {
                const cardHtml = createToyCard(toy);
                console.log('Generated card HTML for toy', toy.id, ':', cardHtml);
                toysContainer.innerHTML += cardHtml;
            });
            initToyActions();
            const popoverTriggerList = document.querySelectorAll('[data-bs-toggle="popover"]');
            [...popoverTriggerList].forEach(popoverTriggerEl => new bootstrap.Popover(popoverTriggerEl));
        })
        .catch(error => {
            console.error('Error loading toys:', error);
            toysContainer.innerHTML = '<p>Error loading toys: ' + error.message + '</p>';
            window.showToast('Failed to load toys: ' + error.message, 'error');
        });
}

function createToyCard(toy) {
    console.log('Creating card for toy:', toy);
    return `
        <div class="col-lg-4 col-md-6 mb-4 fade-in">
            <div class="toy-card">
                <img src="${toy.imageUrl || 'https://via.placeholder.com/150'}" class="card-img-top" alt="${toy.name}">
                <div class="card-body">
                    <div class="category">${toy.category}</div>
                    <h5 class="card-title">${toy.name}</h5>
                    <span class="age-group ${getAgeGroupClass(toy.ageGroup)}">${getAgeGroupLabel(toy.ageGroup)}</span>
                    <p class="card-text">${truncateText(toy.description, 80)}</p>
                    <div class="price">${formatPrice(toy.price)}</div>
                    <div class="toy-quantity" data-quantity="${toy.quantity}">
                        ${toy.quantity < 5 ? 'Only ' : ''}${toy.quantity} in stock
                    </div>
                    <div class="d-flex justify-content-between align-items-center">
                        <button class="btn btn-primary view-details-btn" data-toy-id="${toy.id}" 
                                data-bs-toggle="popover" data-bs-placement="top" 
                                data-bs-content="${formatToyDetails(toy)}">
                            View Details
                        </button>
                        <button class="btn btn-success add-to-cart-btn" data-toy-id="${toy.id}" data-quantity="1">
                            Add to Cart
                        </button>
                    </div>
                </div>
                <div class="action-btn-container">
                    <button class="btn btn-edit btn-action edit-toy-btn" data-toy-id="${toy.id}" title="Edit">
                        <i class="bi bi-pencil"></i>
                    </button>
                    <button class="btn btn-delete btn-action delete-toy-btn" data-toy-id="${toy.id}" title="Delete">
                        <i class="bi bi-trash"></i>
                    </button>
                </div>
            </div>
        </div>
    `;
}

function initToyActions() {
    console.log('Initializing toy actions');
    document.querySelectorAll('.edit-toy-btn').forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            console.log('Edit toy:', toyId);
        });
    });
    document.querySelectorAll('.delete-toy-btn').forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            console.log('Delete toy:', toyId);
        });
    });

    const addToCartButtons = document.querySelectorAll('.add-to-cart-btn');
    console.log('Found', addToCartButtons.length, 'add-to-cart buttons:', addToCartButtons);
    addToCartButtons.forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            const quantity = parseInt(this.getAttribute('data-quantity') || 1);
            console.log('Add to Cart clicked for toyId:', toyId, 'with quantity:', quantity);
            addToCart(toyId, quantity);
        });
    });

    checkUserLoginStatus();
}

function addToCart(toyId, quantity) {
    console.log('Adding to cart:', toyId, 'Quantity:', quantity);
    fetch('/cart/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: `toyId=${encodeURIComponent(toyId)}&quantity=${quantity}`
    })
        .then(response => {
            console.log('Cart response status:', response.status);
            if (!response.ok) {
                return response.text().then(text => {
                    throw new Error(`Failed to add to cart: ${text} (Status: ${response.status})`);
                });
            }
            return response.text();
        })
        .then(message => {
            console.log('Cart response:', message);
            window.showToast(message, 'success');
        })
        .catch(error => {
            console.error('Error adding to cart:', error);
            window.showToast(error.message, 'error');
        });
}

function checkUserLoginStatus() {
    console.log('Checking user login status at:', new Date().toISOString());
    fetch('/user/check', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            console.log('Login check response status:', response.status);
            if (!response.ok) {
                console.warn('Login check failed, defaulting to logged in for debugging');
                return { isLoggedIn: true };
            }
            return response.json();
        })
        .then(data => {
            console.log('User login status:', data);
            if (!data.isLoggedIn) {
                console.log('User not logged in, hiding Add to Cart buttons');
                document.querySelectorAll('.add-to-cart-btn').forEach(button => {
                    button.style.display = 'none';
                });
            } else {
                console.log('User logged in, ensuring Add to Cart buttons are visible');
                document.querySelectorAll('.add-to-cart-btn').forEach(button => {
                    button.style.display = 'block';
                });
            }
        })
        .catch(error => {
            console.error('Error checking login status:', error);
            console.log('Error occurred, defaulting to logged in for debugging');
            document.querySelectorAll('.add-to-cart-btn').forEach(button => {
                button.style.display = 'block';
            });
        });
}

function getAgeGroupClass(ageGroup) {
    return ageGroup >= 0 && ageGroup <= 12 ? 'age-' + ageGroup : 'age-13';
}

function getAgeGroupLabel(ageGroup) {
    const ageGroups = {0: '0-2', 3: '3-5', 6: '6-8', 9: '9-12', 13: '13+'};
    return ageGroups[ageGroup] || '13+';
}

function truncateText(text, maxLength) {
    return text.length > maxLength ? text.substring(0, maxLength) + '...' : text;
}

function formatPrice(price) {
    return `$${price.toFixed(2)}`;
}

function formatToyDetails(toy) {
    return `Category: ${toy.category}<br>Age Group: ${getAgeGroupLabel(toy.ageGroup)}<br>Price: ${formatPrice(toy.price)}<br>Quantity: ${toy.quantity}`;
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