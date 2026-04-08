/**
 * Main JavaScript file for the Toy Store Web Application
 * Handles global UI functionality, animations, and common interactions
 */

// Wait for document to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    console.log('Toy Store Web App initialized');

    // Initialize Bootstrap tooltips
    var tooltipTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="tooltip"]'));
    var tooltipList = tooltipTriggerList.map(function(tooltipTriggerEl) {
        return new bootstrap.Tooltip(tooltipTriggerEl);
    });

    // Initialize Bootstrap popovers
    var popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    var popoverList = popoverTriggerList.map(function(popoverTriggerEl) {
        return new bootstrap.Popover(popoverTriggerEl);
    });

    // Add fade-in animation to elements with .fade-in class
    const fadeElements = document.querySelectorAll('.fade-in');
    if (fadeElements.length > 0) {
        fadeElements.forEach((element, index) => {
            // Apply progressive delay for cascade effect
            element.style.animationDelay = `${index * 0.1}s`;
        });
    }

    // Handle mobile navigation toggling
    const navbarToggler = document.querySelector('.navbar-toggler');
    if (navbarToggler) {
        navbarToggler.addEventListener('click', function() {
            document.body.classList.toggle('nav-open');
        });
    }

    // Handle search form submission
    const searchForm = document.querySelector('#searchForm');
    if (searchForm) {
        searchForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const searchInput = this.querySelector('input[name="search"]');
            const searchQuery = searchInput.value.trim();

            if (searchQuery) {
                // If on toys page, filter toys directly
                if (window.filterToys) {
                    window.filterToys(searchQuery);
                } else {
                    // Otherwise redirect to toys page with search query
                    window.location.href = `/toys?search=${encodeURIComponent(searchQuery)}`;
                }
            }
        });
    }

    // Back to top button functionality
    const backToTopBtn = document.querySelector('#backToTop');
    if (backToTopBtn) {
        window.addEventListener('scroll', function() {
            if (window.pageYOffset > 300) {
                backToTopBtn.classList.add('show');
            } else {
                backToTopBtn.classList.remove('show');
            }
        });

        backToTopBtn.addEventListener('click', function() {
            window.scrollTo({
                top: 0,
                behavior: 'smooth'
            });
        });
    }

    // Age group filtering in hero section
    const heroAgeLinks = document.querySelectorAll('.hero-age-link');
    if (heroAgeLinks.length > 0) {
        heroAgeLinks.forEach(link => {
            link.addEventListener('click', function(event) {
                event.preventDefault();
                const minAge = this.getAttribute('data-age');
                window.location.href = `/toys?minAge=${minAge}`;
            });
        });
    }

    // Category cards linking
    const categoryCards = document.querySelectorAll('.category-card');
    if (categoryCards.length > 0) {
        categoryCards.forEach(card => {
            card.addEventListener('click', function() {
                const category = this.getAttribute('data-category');
                window.location.href = `/toys?category=${category}`;
            });
        });
    }

    // Initialize toast notifications
    window.showToast = function(message, type = 'success') {
        // Create toast container if it doesn't exist
        let toastContainer = document.querySelector('.toast-container');
        if (!toastContainer) {
            toastContainer = document.createElement('div');
            toastContainer.className = 'toast-container';
            document.body.appendChild(toastContainer);
        }

        // Create toast element
        const toast = document.createElement('div');
        toast.className = `toast toast-${type} show`;
        toast.setAttribute('role', 'alert');
        toast.setAttribute('aria-live', 'assertive');
        toast.setAttribute('aria-atomic', 'true');

        toast.innerHTML = `
            <div class="toast-header">
                <strong class="me-auto">${type === 'success' ? 'Success' : 'Error'}</strong>
                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
            </div>
            <div class="toast-body">
                ${message}
            </div>
        `;

        // Add toast to container
        toastContainer.appendChild(toast);

        // Auto-remove after 5 seconds
        setTimeout(() => {
            toast.remove();
        }, 5000);

        // Handle close button
        const closeBtn = toast.querySelector('.btn-close');
        if (closeBtn) {
            closeBtn.addEventListener('click', function() {
                toast.remove();
            });
        }
    };

    // Handle confirmation dialogs
    window.confirmAction = function(message, callback) {
        if (confirm(message)) {
            callback();
        }
    };

    // Handle quantity display styling
    const quantityElements = document.querySelectorAll('.toy-quantity');
    if (quantityElements.length > 0) {
        quantityElements.forEach(element => {
            const quantity = parseInt(element.getAttribute('data-quantity'));
            if (quantity < 5) {
                element.classList.add('low');
            }
        });
    }

    // Handle hero animation effects
    const hero = document.querySelector('.hero');
    if (hero) {
        window.addEventListener('scroll', function() {
            const scrollPosition = window.pageYOffset;
            const heroHeight = hero.offsetHeight;

            if (scrollPosition < heroHeight) {
                // Parallax effect
                const heroContent = hero.querySelector('.hero-content');
                heroContent.style.transform = `translateY(${scrollPosition * 0.2}px)`;

                // Opacity fade effect
                const opacity = 1 - (scrollPosition / heroHeight) * 1.5;
                heroContent.style.opacity = Math.max(opacity, 0);
            }
        });
    }
});

// Utility function to format price
function formatPrice(price) {
    return '$' + parseFloat(price).toFixed(2);
}

// Utility function to get age group label
function getAgeGroupLabel(ageGroup) {
    if (ageGroup <= 2) return '0-2 years';
    if (ageGroup <= 5) return '3-5 years';
    if (ageGroup <= 8) return '6-8 years';
    if (ageGroup <= 12) return '9-12 years';
    return '13+ years';
}

// Utility function to get age group class
function getAgeGroupClass(ageGroup) {
    if (ageGroup <= 2) return 'age-0-2';
    if (ageGroup <= 5) return 'age-3-5';
    if (ageGroup <= 8) return 'age-6-8';
    if (ageGroup <= 12) return 'age-9-12';
    return 'age-13-plus';
}

// Utility function to truncate text
function truncateText(text, maxLength) {
    if (text.length <= maxLength) return text;
    return text.slice(0, maxLength) + '...';
}