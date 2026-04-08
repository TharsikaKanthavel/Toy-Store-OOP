/**
 * JavaScript file for toy management functionality
 * Handles fetching, filtering, sorting, and managing toys through the API
 */

// Global variables
let allToys = [];
let filteredToys = [];
let currentCategory = null;
let currentMinAge = null;
let currentSearchQuery = null;
let currentSortMethod = null;

// Wait for document to be fully loaded
document.addEventListener('DOMContentLoaded', function() {
    console.log('Toys page initialized');

    // Initialize popovers for toy details
    initPopovers();

    // Get query parameters
    const urlParams = new URLSearchParams(window.location.search);
    currentCategory = urlParams.get('category');
    currentMinAge = urlParams.get('minAge');
    currentSearchQuery = urlParams.get('search');

    // Load toys with initial filters
    loadToys();

    // Initialize filter event listeners
    initFilterListeners();

    // Initialize sort dropdown
    initSortDropdown();

    // Initialize category filters
    initCategoryFilters();

    // Initialize age group filters
    initAgeGroupFilters();

    // Add event listener for add toy button
    const addToyBtn = document.getElementById('addToyBtn');
    if (addToyBtn) {
        addToyBtn.addEventListener('click', function() {
            window.location.href = '/toys/new';
        });
    }
});

/**
 * Load toys from the API with optional filters
 */
function loadToys() {
    // Show loading spinner
    const toysContainer = document.getElementById('toysContainer');
    if (toysContainer) {
        toysContainer.innerHTML = '<div class="loading-spinner"></div>';
    }

    // Build query string for filters
    let queryParams = [];
    if (currentCategory) queryParams.push(`category=${currentCategory}`);
    if (currentMinAge) queryParams.push(`minAge=${currentMinAge}`);
    if (currentSearchQuery) queryParams.push(`search=${encodeURIComponent(currentSearchQuery)}`);
    if (currentSortMethod === 'age') queryParams.push('sort=age');

    const queryString = queryParams.length > 0 ? '?' + queryParams.join('&') : '';

    // Fetch toys from API
    fetch(`/api/toys${queryString}`)
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(toys => {
            // Store original and filtered toys
            allToys = toys;
            filteredToys = [...toys];

            // Display toys
            displayToys(filteredToys);

            // Apply UI updates for filters
            updateFilterUI();
        })
        .catch(error => {
            console.error('Error fetching toys:', error);
            if (toysContainer) {
                toysContainer.innerHTML = `
                    <div class="empty-state">
                        <div class="empty-state-icon">⚠️</div>
                        <div class="empty-state-text">Error loading toys. Please try again later.</div>
                        <button class="btn btn-primary" onclick="loadToys()">Retry</button>
                    </div>
                `;
            }
        });
}

/**
 * Display toys in the UI
 * @param {Array} toys - Array of toy objects to display
 */
function displayToys(toys) {
    const toysContainer = document.getElementById('toysContainer');
    if (!toysContainer) return;

    if (toys.length === 0) {
        // Show empty state
        toysContainer.innerHTML = `
            <div class="empty-state">
                <div class="empty-state-icon">🧸</div>
                <div class="empty-state-text">No toys found matching your criteria.</div>
                <button class="btn btn-primary" onclick="resetFilters()">Reset Filters</button>
            </div>
        `;
        return;
    }

    // Generate HTML for toys
    let toysHTML = '';
    toys.forEach(toy => {
        toysHTML += createToyCard(toy);
    });

    // Update container
    toysContainer.innerHTML = toysHTML;

    // Initialize action buttons
    initToyActions();

    // Initialize popovers for toy details
    initPopovers();
}


/**
 * Create HTML for a toy card
 * @param {Object} toy - Toy object
 * @returns {string} HTML for the toy card
 */
function createToyCard(toy) {
    return `
        <div class="col-lg-4 col-md-6 mb-4 fade-in">
            <div class="toy-card">
                <img src="${toy.imageUrl}" class="card-img-top" alt="${toy.name}">
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

/**
 * Format toy details for popover
 * @param {Object} toy - Toy object
 * @returns {string} HTML content for popover
 */
function formatToyDetails(toy) {
    // Escape HTML characters to prevent XSS
    const escapedDescription = toy.description
        .replace(/&/g, '&amp;')
        .replace(/</g, '&lt;')
        .replace(/>/g, '&gt;')
        .replace(/"/g, '&quot;')
        .replace(/'/g, '&#039;');

    return `
        <div class="popover-content">
            <p><strong>Description:</strong> ${escapedDescription}</p>
            <p><strong>Age Group:</strong> ${getAgeGroupLabel(toy.ageGroup)}</p>
            <p><strong>Price:</strong> ${formatPrice(toy.price)}</p>
            <p><strong>Category:</strong> ${toy.category}</p>
            <p><strong>Quantity:</strong> ${toy.quantity} in stock</p>
            <div class="mt-2">
                <a href="/toys/edit/${toy.id}" class="btn btn-sm btn-secondary">Edit</a>
            </div>
        </div>
    `;
}

/**
 * Initialize Bootstrap popovers
 */
function initPopovers() {
    const popoverTriggerList = [].slice.call(document.querySelectorAll('[data-bs-toggle="popover"]'));
    popoverTriggerList.forEach(function(popoverTriggerEl) {
        new bootstrap.Popover(popoverTriggerEl, {
            html: true,
            trigger: 'focus'
        });
    });
}

/**
 * Initialize toy action buttons (edit, delete)
 */
function initToyActions() {
    // Edit toy buttons
    const editButtons = document.querySelectorAll('.edit-toy-btn');
    editButtons.forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            window.location.href = `/toys/edit/${toyId}`;
        });
    });

    // Delete toy buttons
    const deleteButtons = document.querySelectorAll('.delete-toy-btn');
    deleteButtons.forEach(button => {
        button.addEventListener('click', function() {
            const toyId = this.getAttribute('data-toy-id');
            const toyName = getToyNameById(toyId);

            window.confirmAction(`Are you sure you want to delete "${toyName}"?`, function() {
                deleteToy(toyId);
            });
        });
    });
}

/**
 * Get the name of a toy by its ID
 * @param {string} toyId - ID of the toy
 * @returns {string} Name of the toy
 */
function getToyNameById(toyId) {
    const toy = allToys.find(t => t.id === toyId);
    return toy ? toy.name : 'this toy';
}

/**
 * Delete a toy via the API
 * @param {string} toyId - ID of the toy to delete
 */
function deleteToy(toyId) {
    fetch(`/api/toys/${toyId}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Failed to delete toy');
            }

            // Remove toy from arrays
            allToys = allToys.filter(toy => toy.id !== toyId);
            filteredToys = filteredToys.filter(toy => toy.id !== toyId);

            // Update UI
            displayToys(filteredToys);

            // Show success message
            window.showToast('Toy deleted successfully', 'success');
        })
        .catch(error => {
            console.error('Error deleting toy:', error);
            window.showToast('Error deleting toy. Please try again.', 'error');
        });
}

/**
 * Initialize filter event listeners
 */
function initFilterListeners() {
    // Search form
    const searchForm = document.getElementById('searchForm');
    if (searchForm) {
        searchForm.addEventListener('submit', function(event) {
            event.preventDefault();
            const searchInput = this.querySelector('input[name="search"]');
            filterToys(searchInput.value.trim());
        });
    }

    // Clear filters button
    const clearFiltersBtn = document.getElementById('clearFiltersBtn');
    if (clearFiltersBtn) {
        clearFiltersBtn.addEventListener('click', resetFilters);
    }
}

/**
 * Filter toys by search query
 * @param {string} query - Search query
 */
function filterToys(query) {
    currentSearchQuery = query;

    // Update URL with new search parameter
    const url = new URL(window.location);
    if (query) {
        url.searchParams.set('search', query);
    } else {
        url.searchParams.delete('search');
    }
    window.history.pushState({}, '', url);

    // Reload toys with new filter
    loadToys();
}

/**
 * Reset all filters
 */
function resetFilters() {
    currentCategory = null;
    currentMinAge = null;
    currentSearchQuery = null;
    currentSortMethod = null;

    // Clear URL parameters
    window.history.pushState({}, '', '/toys');

    // Reset UI
    const searchInput = document.querySelector('input[name="search"]');
    if (searchInput) {
        searchInput.value = '';
    }

    // Reset category filters
    const categoryCheckboxes = document.querySelectorAll('.category-filter');
    categoryCheckboxes.forEach(checkbox => {
        checkbox.checked = false;
    });

    // Reset age group filters
    const ageGroupCheckboxes = document.querySelectorAll('.age-filter');
    ageGroupCheckboxes.forEach(checkbox => {
        checkbox.checked = false;
    });

    // Reset sort dropdown
    const sortDropdown = document.getElementById('sortDropdown');
    if (sortDropdown) {
        sortDropdown.value = 'default';
    }

    // Reload toys with no filters
    loadToys();
}

/**
 * Initialize sort dropdown
 */
function initSortDropdown() {
    const sortDropdown = document.getElementById('sortDropdown');
    if (sortDropdown) {
        sortDropdown.addEventListener('change', function() {
            currentSortMethod = this.value;

            if (currentSortMethod === 'default') {
                currentSortMethod = null;
            }

            loadToys();
        });
    }
}

/**
 * Initialize category filter checkboxes
 */
function initCategoryFilters() {
    const categoryCheckboxes = document.querySelectorAll('.category-filter');
    categoryCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            if (this.checked) {
                // Uncheck other category checkboxes
                categoryCheckboxes.forEach(cb => {
                    if (cb !== this) cb.checked = false;
                });

                currentCategory = this.value;
            } else {
                currentCategory = null;
            }

            loadToys();
        });
    });
}

/**
 * Initialize age group filter checkboxes
 */
function initAgeGroupFilters() {
    const ageGroupCheckboxes = document.querySelectorAll('.age-filter');
    ageGroupCheckboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            if (this.checked) {
                // Uncheck other age group checkboxes
                ageGroupCheckboxes.forEach(cb => {
                    if (cb !== this) cb.checked = false;
                });

                currentMinAge = parseInt(this.value);
            } else {
                currentMinAge = null;
            }

            loadToys();
        });
    });
}

/**
 * Update filter UI to match current filters
 */
function updateFilterUI() {
    // Update category checkboxes
    if (currentCategory) {
        const categoryCheckbox = document.querySelector(`.category-filter[value="${currentCategory}"]`);
        if (categoryCheckbox) {
            categoryCheckbox.checked = true;
        }
    }

    // Update age group checkboxes
    if (currentMinAge) {
        const ageGroupCheckbox = document.querySelector(`.age-filter[value="${currentMinAge}"]`);
        if (ageGroupCheckbox) {
            ageGroupCheckbox.checked = true;
        }
    }

    // Update search input
    if (currentSearchQuery) {
        const searchInput = document.querySelector('input[name="search"]');
        if (searchInput) {
            searchInput.value = currentSearchQuery;
        }
    }

    // Update sort dropdown
    if (currentSortMethod) {
        const sortDropdown = document.getElementById('sortDropdown');
        if (sortDropdown) {
            sortDropdown.value = currentSortMethod;
        }
    }

    // Update active filters summary
    updateActiveFiltersSummary();
}

/**
 * Update active filters summary display
 */
function updateActiveFiltersSummary() {
    const filtersContainer = document.getElementById('activeFilters');
    if (!filtersContainer) return;

    let filtersHtml = '';

    if (currentCategory) {
        filtersHtml += `<span class="badge bg-primary me-2 mb-2">Category: ${currentCategory} <i class="bi bi-x-circle" onclick="removeFilter('category')"></i></span>`;
    }

    if (currentMinAge) {
        filtersHtml += `<span class="badge bg-primary me-2 mb-2">Age: ${currentMinAge}+ years <i class="bi bi-x-circle" onclick="removeFilter('age')"></i></span>`;
    }

    if (currentSearchQuery) {
        filtersHtml += `<span class="badge bg-primary me-2 mb-2">Search: "${currentSearchQuery}" <i class="bi bi-x-circle" onclick="removeFilter('search')"></i></span>`;
    }

    if (currentSortMethod) {
        const sortLabel = currentSortMethod === 'age' ? 'Age (Low to High)' : currentSortMethod;
        filtersHtml += `<span class="badge bg-secondary me-2 mb-2">Sort: ${sortLabel} <i class="bi bi-x-circle" onclick="removeFilter('sort')"></i></span>`;
    }

    if (filtersHtml) {
        filtersContainer.innerHTML = `
            <div class="mb-3">
                <h6 class="mb-2">Active Filters:</h6>
                ${filtersHtml}
            </div>
        `;
        filtersContainer.style.display = 'block';
    } else {
        filtersContainer.style.display = 'none';
    }
}

/**
 * Remove a specific filter
 * @param {string} filterType - Type of filter to remove (category, age, search, sort)
 */
function removeFilter(filterType) {
    switch (filterType) {
        case 'category':
            currentCategory = null;
            // Uncheck category checkboxes
            document.querySelectorAll('.category-filter').forEach(cb => {
                cb.checked = false;
            });
            break;
        case 'age':
            currentMinAge = null;
            // Uncheck age group checkboxes
            document.querySelectorAll('.age-filter').forEach(cb => {
                cb.checked = false;
            });
            break;
        case 'search':
            currentSearchQuery = null;
            // Clear search input
            const searchInput = document.querySelector('input[name="search"]');
            if (searchInput) {
                searchInput.value = '';
            }
            break;
        case 'sort':
            currentSortMethod = null;
            // Reset sort dropdown
            const sortDropdown = document.getElementById('sortDropdown');
            if (sortDropdown) {
                sortDropdown.value = 'default';
            }
            break;
    }

    // Update URL
    const url = new URL(window.location);
    if (filterType === 'category') url.searchParams.delete('category');
    if (filterType === 'age') url.searchParams.delete('minAge');
    if (filterType === 'search') url.searchParams.delete('search');
    if (filterType === 'sort') url.searchParams.delete('sort');
    window.history.pushState({}, '', url);

    // Reload toys with updated filters
    loadToys();
}

// Expose the filterToys function globally for search form
window.filterToys = filterToys;
window.resetFilters = resetFilters;
window.removeFilter = removeFilter;