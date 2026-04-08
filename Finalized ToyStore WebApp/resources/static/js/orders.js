document.addEventListener('DOMContentLoaded', function () {
    console.log('orders.js loaded, loading orders');
    loadOrders();
});

function loadOrders() {
    console.log('Loading orders');
    fetch('/orders/api/orders', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
        .then(response => {
            console.log('Orders fetch status:', response.status);
            if (!response.ok) throw new Error('Failed to load orders');
            return response.json();
        })
        .then(orders => {
            console.log('Orders:', orders);
            const ordersList = document.getElementById('ordersList');
            ordersList.innerHTML = '';
            if (!orders || orders.length === 0) {
                ordersList.innerHTML = '<p>No orders found.</p>';
                return;
            }
            orders.forEach(order => {
                const orderDiv = document.createElement('div');
                orderDiv.className = 'list-group-item mb-4';
                orderDiv.innerHTML = `
                    <h5>Order #${order.orderId} - <small>${new Date(order.orderDate).toLocaleString()}</small></h5>
                    <p><strong>Total: </strong>$${order.totalPrice.toFixed(2)}</p>
                    <h6>Items:</h6>
                    <ul class="list-group">
                    </ul>
                `;
                const itemsList = orderDiv.querySelector('.list-group');
                order.cartItems.forEach(item => {
                    fetch(`/api/toys/${item.toyId}`)
                        .then(response => response.json())
                        .then(toy => {
                            itemsList.innerHTML += `
                                <li class="list-group-item">
                                    ${toy.name} - Quantity: ${item.quantity} - Price: $${item.price.toFixed(2)}
                                </li>
                            `;
                        });
                });
                ordersList.appendChild(orderDiv);
            });
        })
        .catch(error => {
            console.error('Error loading orders:', error);
            window.showToast('Failed to load orders: ' + error.message, 'error');
        });
}