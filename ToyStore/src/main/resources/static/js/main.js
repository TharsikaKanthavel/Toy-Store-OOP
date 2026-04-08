/**
 * Main JavaScript for ToyLand Online Store
 */
document.addEventListener('DOMContentLoaded', function() {
    console.log('Main JS loaded');

    // Initialize elements that require JavaScript
    initializeComponents();

    // Update cart badge on page load
    updateCartBadge();
});

/**
 * Initialize all interactive components
 */
function initializeComponents() {
    // Initialize tooltips
    const tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    tooltipTriggerList.map(function (tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Check if we're on the products page and initialize filters if so
    if (document.getElementById('filter-form')) {
        initializeFilters();
    }
}

/**
 * Initialize filter functionality on product listing page
 */
function initializeFilters() {
    // Filter functionality
    const filterForm = document.getElementById('filter-form');
    const categoryInput = document.getElementById('category-input');
    const ageGroupInput = document.getElementById('age-group-input');
    const sortInput = document.getElementById('sort-input');

    // Apply filters button
    const applyFiltersBtn = document.getElementById('apply-filters');
    if (applyFiltersBtn) {
        applyFiltersBtn.addEventListener('click', function() {
            const selectedCategory = document.querySelector('.filter-category:checked');
            if (selectedCategory) {
                categoryInput.value = selectedCategory.value;
            } else {
                categoryInput.value = '';
            }

            const selectedAgeGroup = document.querySelector('.filter-age-group:checked');
            if (selectedAgeGroup) {
                ageGroupInput.value = selectedAgeGroup.value;
            } else {
                ageGroupInput.value = '';
            }

            filterForm.submit();
        });
    }

    // Clear filters button
    const clearFiltersBtn = document.getElementById('clear-filters');
    if (clearFiltersBtn) {
        clearFiltersBtn.addEventListener('click', function() {
            const categoryInputs = document.querySelectorAll('.filter-category');
            const ageGroupInputs = document.querySelectorAll('.filter-age-group');

            categoryInputs.forEach(input => input.checked = false);
            ageGroupInputs.forEach(input => input.checked = false);

            categoryInput.value = '';
            ageGroupInput.value = '';
            sortInput.value = '';

            filterForm.submit();
        });
    }

    // Sort options
    const sortOptions = document.querySelectorAll('.sort-option');
    sortOptions.forEach(option => {
        option.addEventListener('click', function(e) {
            e.preventDefault();
            sortInput.value = this.getAttribute('data-sort');
            filterForm.submit();
        });
    });
}

/**
 * Update the cart badge count
 */
function updateCartBadge() {
    try {
        // Try to get cart from session storage
        let cart = JSON.parse(sessionStorage.getItem('cart')) || { items: [], totalCount: 0 };

        // Update the badge with the total item count
        const cartBadge = document.getElementById('cart-badge');
        if (cartBadge) {
            cartBadge.innerText = cart.totalCount || 0;
        }
    } catch (e) {
        console.error('Error updating cart badge:', e);
    }
}