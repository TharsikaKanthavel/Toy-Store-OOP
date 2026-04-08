// Form Validation
document.addEventListener('DOMContentLoaded', function() {
    // Validate toy forms
    const toyForms = document.querySelectorAll('.toy-form');
    toyForms.forEach(form => {
        form.addEventListener('submit', function(e) {
            const price = parseFloat(form.querySelector('#price').value);
            const quantity = parseInt(form.querySelector('#quantity').value);

            if (isNaN(price) || price <= 0) {
                alert('Please enter a valid price (greater than 0)');
                e.preventDefault();
            }

            if (isNaN(quantity) || quantity < 0) {
                alert('Please enter a valid quantity (positive number)');
                e.preventDefault();
            }
        });
    });

    // Search functionality
    const searchForm = document.querySelector('#searchForm');
    if (searchForm) {
        searchForm.addEventListener('submit', function(e) {
            const searchTerm = document.querySelector('#searchTerm').value.trim();
            if (searchTerm === '') {
                e.preventDefault();
                alert('Please enter a search term');
            }
        });
    }
});