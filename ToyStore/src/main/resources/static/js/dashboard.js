/**
 * Dashboard JavaScript for ToyLand Admin
 */
document.addEventListener('DOMContentLoaded', function() {
    console.log('Dashboard JS loaded');

    // Render charts
    renderCharts();

    // Setup restock functionality
    setupRestockFunctionality();

    // Animate dashboard cards on load
    animateDashboardCards();

    // Add flash message timeout
    const flashMessage = document.querySelector('.alert-success');
    if (flashMessage) {
        setTimeout(function() {
            flashMessage.classList.add('fade');
            setTimeout(function() {
                flashMessage.style.display = 'none';
            }, 500);
        }, 5000);
    }
});

/**
 * Animate dashboard cards on load
 */
function animateDashboardCards() {
    const cards = document.querySelectorAll('.dashboard-card');
    cards.forEach((card, index) => {
        setTimeout(() => {
            card.classList.add('zoom-in');
            card.style.opacity = 1;
        }, index * 100);
    });
}

/**
 * Render charts for dashboard
 */
function renderCharts() {
    renderCategoryChart();
    renderAgeGroupChart();
    renderInventorySummaryChart();
}

/**
 * Render category chart
 */
function renderCategoryChart() {
    const chartCanvas = document.getElementById('categoryChart');
    if (!chartCanvas) return;

    // Get data from hidden spans
    const dataElements = document.querySelectorAll('#category-chart-data span');
    const labels = [];
    const productCounts = [];
    const stockCounts = [];

    dataElements.forEach(element => {
        labels.push(element.getAttribute('data-label'));
        productCounts.push(parseInt(element.getAttribute('data-count')));
        stockCounts.push(parseInt(element.getAttribute('data-stock')));
    });

    // Create chart
    new Chart(chartCanvas, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Product Count',
                    backgroundColor: '#4e73df',
                    data: productCounts,
                    borderRadius: 5,
                    maxBarThickness: 25
                },
                {
                    label: 'Stock Count',
                    backgroundColor: '#1cc88a',
                    data: stockCounts,
                    borderRadius: 5,
                    maxBarThickness: 25
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'top',
                },
                tooltip: {
                    backgroundColor: 'rgba(0, 0, 0, 0.8)',
                    titleFont: {
                        size: 14,
                        weight: 'bold'
                    },
                    bodyFont: {
                        size: 13
                    },
                    padding: 12,
                    cornerRadius: 8
                }
            },
            scales: {
                x: {
                    grid: {
                        display: false
                    }
                },
                y: {
                    beginAtZero: true,
                    grid: {
                        color: 'rgba(0, 0, 0, 0.05)'
                    }
                }
            },
            animation: {
                duration: 1500,
                easing: 'easeOutQuart'
            }
        }
    });
}

/**
 * Render age group chart
 */
function renderAgeGroupChart() {
    const chartCanvas = document.getElementById('ageGroupChart');
    if (!chartCanvas) return;

    // Get data from hidden spans
    const dataElements = document.querySelectorAll('#age-group-chart-data span');
    const labels = [];
    const productCounts = [];
    const stockCounts = [];

    dataElements.forEach(element => {
        labels.push(element.getAttribute('data-label'));
        productCounts.push(parseInt(element.getAttribute('data-count')));
        stockCounts.push(parseInt(element.getAttribute('data-stock')));
    });

    // Create chart
    new Chart(chartCanvas, {
        type: 'bar',
        data: {
            labels: labels,
            datasets: [
                {
                    label: 'Product Count',
                    backgroundColor: '#4e73df',
                    data: productCounts,
                    borderRadius: 5,
                    maxBarThickness: 25
                },
                {
                    label: 'Stock Count',
                    backgroundColor: '#1cc88a',
                    data: stockCounts,
                    borderRadius: 5,
                    maxBarThickness: 25
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'top',
                },
                tooltip: {
                    backgroundColor: 'rgba(0, 0, 0, 0.8)',
                    titleFont: {
                        size: 14,
                        weight: 'bold'
                    },
                    bodyFont: {
                        size: 13
                    },
                    padding: 12,
                    cornerRadius: 8
                }
            },
            scales: {
                x: {
                    grid: {
                        display: false
                    }
                },
                y: {
                    beginAtZero: true,
                    grid: {
                        color: 'rgba(0, 0, 0, 0.05)'
                    }
                }
            },
            animation: {
                delay: 500,
                duration: 1500,
                easing: 'easeOutQuart'
            }
        }
    });
}

/**
 * Render inventory summary chart (pie chart)
 */
function renderInventorySummaryChart() {
    const chartCanvas = document.getElementById('inventorySummaryChart');
    if (!chartCanvas) return;

    // Get data from hidden spans
    const dataElements = document.querySelectorAll('#category-chart-data span');
    const labels = [];
    const stockCounts = [];

    dataElements.forEach(element => {
        labels.push(element.getAttribute('data-label'));
        stockCounts.push(parseInt(element.getAttribute('data-stock')));
    });

    // Create color array
    const colors = [
        '#4e73df', '#1cc88a', '#36b9cc', '#f6c23e', '#e74a3b',
        '#5a5c69', '#6f42c1', '#fd7e14', '#20c9a6', '#858796'
    ];

    // Create chart
    new Chart(chartCanvas, {
        type: 'doughnut',
        data: {
            labels: labels,
            datasets: [
                {
                    data: stockCounts,
                    backgroundColor: colors.slice(0, labels.length),
                    hoverBackgroundColor: colors.slice(0, labels.length).map(color => {
                        return pSBC(0.1, color); // Lighten color slightly on hover
                    }),
                    hoverBorderColor: "rgba(234, 236, 244, 1)",
                    borderWidth: 2,
                    borderColor: '#ffffff'
                }
            ]
        },
        options: {
            responsive: true,
            maintainAspectRatio: false,
            plugins: {
                legend: {
                    position: 'right',
                    labels: {
                        usePointStyle: true,
                        padding: 20,
                        font: {
                            size: 12
                        }
                    }
                },
                tooltip: {
                    backgroundColor: 'rgba(0, 0, 0, 0.8)',
                    titleFont: {
                        size: 14,
                        weight: 'bold'
                    },
                    bodyFont: {
                        size: 13
                    },
                    padding: 12,
                    cornerRadius: 8,
                    callbacks: {
                        label: function(context) {
                            const label = context.label || '';
                            const value = context.raw || 0;
                            const total = context.dataset.data.reduce((a, b) => a + b, 0);
                            const percentage = Math.round((value / total) * 100);
                            return `${label}: ${value} units (${percentage}%)`;
                        }
                    }
                }
            },
            cutout: '65%',
            animation: {
                animateRotate: true,
                animateScale: true,
                duration: 2000,
                delay: 1000,
                easing: 'easeOutQuart'
            }
        }
    });
}

/**
 * Setup restock button functionality
 */
function setupRestockFunctionality() {
    const restockButtons = document.querySelectorAll('.restock-btn');

    restockButtons.forEach(button => {
        button.addEventListener('click', function(e) {
            // Add loading spinner while request is processing
            const originalContent = this.innerHTML;
            this.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Restocking...';
            this.disabled = true;

            // Form will submit and page will refresh with server-side response
        });
    });

    // Setup restock all button
    const restockAllButton = document.querySelector('#restock-all-btn');
    if (restockAllButton) {
        restockAllButton.addEventListener('click', function(e) {
            if (confirm('Are you sure you want to restock all low stock items?')) {
                // Add loading spinner while request is processing
                const originalContent = this.innerHTML;
                this.innerHTML = '<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Restocking All...';
                this.disabled = true;

                // Link will redirect to server-side restock endpoint
            } else {
                e.preventDefault();
            }
        });
    }
}

// Function to shade/blend/brighten colors
// Usage: pSBC(0.5, "#F00") // 50% lighter red
// Credit: https://github.com/PimpTrizkit/PJs/wiki/12.-Shade,-Blend-and-Convert-a-Web-Color-(pSBC.js)
const pSBC = (p, c0, c1, l) => {
    let r, g, b, a, P, f, t, h, i = parseInt, m = Math.round, z = typeof (c1) == "string";
    if (typeof (p) != "number" || p < -1 || p > 1 || typeof (c0) != "string" || (c0[0] != 'r' && c0[0] != '#') || (c1 && !z)) return null;
    h = c0.length > 9, h = a = h ? c0.substring(7, c0.length - 1) : c0.substring(7, c0.length), h = h ? 2 : 1;
    if (!h) return null;
    c0 = c0.slice(0, 7);
    P = p < 0, f = P ? p * -1 : p, t = P ? 0 : 255 * f, P = P ? 255 : 0;
    if (!c1) {
        r = i("0x" + c0.substring(1, 3)) * (1 - f) + t;
        g = i("0x" + c0.substring(3, 5)) * (1 - f) + t;
        b = i("0x" + c0.substring(5, 7)) * (1 - f) + t;
    } else {
        r = m((i("0x" + c0.substring(1, 3)) * (1 - f) + (i("0x" + c1.substring(1, 3))) * f));
        g = m((i("0x" + c0.substring(3, 5)) * (1 - f) + (i("0x" + c1.substring(3, 5))) * f));
        b = m((i("0x" + c0.substring(5, 7)) * (1 - f) + (i("0x" + c1.substring(5, 7))) * f));
    }
    return "#" + (0x1000000 + (r < 255 ? r < 1 ? 0 : r : 255) * 0x10000 + (g < 255 ? g < 1 ? 0 : g : 255) * 0x100 + (b < 255 ? b < 1 ? 0 : b : 255)).toString(16).slice(1) + (a && c0.slice(7, c0.length));
};