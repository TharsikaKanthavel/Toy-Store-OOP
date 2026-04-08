<!DOCTYPE html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Toy Wonderland - Order Management</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/customer.css">
</head>
<body>
<header class="admin-header">
  <div class="logo">
    <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscale" alt="Toy Wonderland Logo">
    <h1>Toy Wonderland Admin</h1>
  </div>
  <nav class="admin-nav">
    <a href="#"><i class="fas fa-bell"></i></a>
    <a href="#"><i class="fas fa-user-circle"></i> Admin</a>
    <a href="#"><i class="fas fa-sign-out-alt"></i></a>
  </nav>
</header>

<div class="admin-container">
  <aside class="sidebar">
    <h3>Dashboard</h3>
    <ul class="sidebar-menu">
      <li><a href="#"><i class="fas fa-tachometer-alt"></i> Overview</a></li>
      <li><a href="#"><i class="fas fa-plus-circle"></i> Add Product</a></li>
      <li><a href="#"><i class="fas fa-box-open"></i> Products</a></li>
      <li><a href="#"><i class="fas fa-list-alt"></i> Categories</a></li>
      <li><a href="#"><i class="fas fa-tags"></i> Inventory</a></li>
      <li><a href="#" class="active"><i class="fas fa-shopping-cart"></i> Orders</a></li>
      <li><a href="#"><i class="fas fa-users"></i> Customers</a></li>
      <li><a href="#"><i class="fas fa-chart-line"></i> Reports</a></li>
      <li><a href="#"><i class="fas fa-cog"></i> Settings</a></li>
    </ul>
  </aside>

  <main class="main-content">
    <div class="page-header">
      <h2><i class="fas fa-shopping-cart"></i> Order Management</h2>
      <button class="btn"><i class="fas fa-file-export"></i> Export Orders</button>
    </div>

    <div class="filters">
      <div class="filter-group">
        <label>Status</label>
        <select>
          <option value="">All Statuses</option>
          <option value="pending">Pending</option>
          <option value="processing">Processing</option>
          <option value="shipped">Shipped</option>
          <option value="delivered">Delivered</option>
          <option value="cancelled">Cancelled</option>
        </select>
      </div>

      <div class="filter-group">
        <label>Date Range</label>
        <select>
          <option value="">All Time</option>
          <option value="today">Today</option>
          <option value="week">This Week</option>
          <option value="month">This Month</option>
          <option value="custom">Custom Range</option>
        </select>
      </div>

      <div class="filter-group">
        <label>Payment</label>
        <select>
          <option value="">All Payments</option>
          <option value="paid">Paid</option>
          <option value="unpaid">Unpaid</option>
          <option value="refunded">Refunded</option>
        </select>
      </div>

      <div class="search-box">
        <i class="fas fa-search"></i>
        <input type="text" placeholder="Search orders...">
      </div>
    </div>

    <div class="table-responsive">
      <table class="orders-table">
        <thead>
        <tr>
          <th>Order ID</th>
          <th>Customer</th>
          <th>Date</th>
          <th>Amount</th>
          <th>Status</th>
          <th>Payment</th>
          <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>#TW-1001</td>
          <td>John Smith</td>
          <td>May 15, 2023</td>
          <td>$59.99</td>
          <td><span class="status-badge status-pending">Pending</span></td>
          <td><span class="status-badge status-processing">Paid</span></td>
          <td>
            <div class="order-actions">
              <button class="btn btn-sm btn-info" onclick="viewOrderDetails('TW-1001')"><i class="fas fa-eye"></i></button>
              <button class="btn btn-sm btn-success"><i class="fas fa-check"></i></button>
              <button class="btn btn-sm btn-warning"><i class="fas fa-truck"></i></button>
              <button class="btn btn-sm btn-danger"><i class="fas fa-times"></i></button>
            </div>
          </td>
        </tr>
        <tr>
          <td>#TW-1002</td>
          <td>Emily Johnson</td>
          <td>May 14, 2023</td>
          <td>$129.95</td>
          <td><span class="status-badge status-processing">Processing</span></td>
          <td><span class="status-badge status-processing">Paid</span></td>
          <td>
            <div class="order-actions">
              <button class="btn btn-sm btn-info" onclick="viewOrderDetails('TW-1002')"><i class="fas fa-eye"></i></button>
              <button class="btn btn-sm btn-success"><i class="fas fa-check"></i></button>
              <button class="btn btn-sm btn-warning"><i class="fas fa-truck"></i></button>
              <button class="btn btn-sm btn-danger"><i class="fas fa-times"></i></button>
            </div>
          </td>
        </tr>
        <tr>
          <td>#TW-1003</td>
          <td>Michael Brown</td>
          <td>May 12, 2023</td>
          <td>$34.50</td>
          <td><span class="status-badge status-shipped">Shipped</span></td>
          <td><span class="status-badge status-processing">Paid</span></td>
          <td>
            <div class="order-actions">
              <button class="btn btn-sm btn-info" onclick="viewOrderDetails('TW-1003')"><i class="fas fa-eye"></i></button>
              <button class="btn btn-sm btn-success"><i class="fas fa-check"></i></button>
              <button class="btn btn-sm btn-warning"><i class="fas fa-truck"></i></button>
              <button class="btn btn-sm btn-danger"><i class="fas fa-times"></i></button>
            </div>
          </td>
        </tr>
        <tr>
          <td>#TW-1004</td>
          <td>Sarah Wilson</td>
          <td>May 10, 2023</td>
          <td>$87.25</td>
          <td><span class="status-badge status-delivered">Delivered</span></td>
          <td><span class="status-badge status-processing">Paid</span></td>
          <td>
            <div class="order-actions">
              <button class="btn btn-sm btn-info" onclick="viewOrderDetails('TW-1004')"><i class="fas fa-eye"></i></button>
              <button class="btn btn-sm btn-success"><i class="fas fa-check"></i></button>
              <button class="btn btn-sm btn-warning"><i class="fas fa-truck"></i></button>
              <button class="btn btn-sm btn-danger"><i class="fas fa-times"></i></button>
            </div>
          </td>
        </tr>
        <tr>
          <td>#TW-1005</td>
          <td>David Lee</td>
          <td>May 8, 2023</td>
          <td>$45.99</td>
          <td><span class="status-badge status-cancelled">Cancelled</span></td>
          <td><span class="status-badge status-cancelled">Refunded</span></td>
          <td>
            <div class="order-actions">
              <button class="btn btn-sm btn-info" onclick="viewOrderDetails('TW-1005')"><i class="fas fa-eye"></i></button>
              <button class="btn btn-sm btn-success"><i class="fas fa-check"></i></button>
              <button class="btn btn-sm btn-warning"><i class="fas fa-truck"></i></button>
              <button class="btn btn-sm btn-danger"><i class="fas fa-times"></i></button>
            </div>
          </td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="pagination">
      <button disabled><i class="fas fa-angle-left"></i></button>
      <button class="active">1</button>
      <button>2</button>
      <button>3</button>
      <button><i class="fas fa-angle-right"></i></button>
    </div>

    <img src="https://img.drz.lazcdn.com/static/lk/p/8b54cead8a08ff997ae988de03fbb8f2.jpg_400x400q75.avif" alt="Teddy Bear" class="toy-decoration toy1">
    <img src="https://m.media-amazon.com/images/I/61OowkNVFqL._AC_UL480_FMwebp_QL65_.jpg" alt="Rubber Duck" class="toy-decoration toy2">
  </main>
</div>

<!-- Order Details Modal -->
<div class="order-details-modal" id="orderDetailsModal">
  <div class="modal-content">
    <div class="modal-header">
      <h3>Order Details - #<span id="modalOrderId">TW-1001</span></h3>
      <button class="close-modal" onclick="closeModal()">&times;</button>
    </div>
    <div class="modal-body">
      <div class="order-info-grid">
        <div class="info-card">
          <h4>Customer Information</h4>
          <p><strong>Name:</strong> <span id="customerName">John Smith</span></p>
          <p><strong>Email:</strong> <span id="customerEmail">john.smith@example.com</span></p>
          <p><strong>Phone:</strong> <span id="customerPhone">(555) 123-4567</span></p>
        </div>

        <div class="info-card">
          <h4>Order Information</h4>
          <p><strong>Date:</strong> <span id="orderDate">May 15, 2023 10:30 AM</span></p>
          <p><strong>Status:</strong> <span id="orderStatus" class="status-badge status-pending">Pending</span></p>
          <p><strong>Payment:</strong> <span id="orderPayment" class="status-badge status-processing">Paid</span></p>
        </div>

        <div class="info-card">
          <h4>Shipping Address</h4>
          <p id="shippingAddress">
            123 Toy Street<br>
            Playville, PV 12345<br>
            United States
          </p>
        </div>

        <div class="info-card">
          <h4>Billing Address</h4>
          <p id="billingAddress">
            123 Toy Street<br>
            Playville, PV 12345<br>
            United States
          </p>
        </div>
      </div>

      <h4>Order Items</h4>
      <table class="products-table">
        <thead>
        <tr>
          <th>Product</th>
          <th>Price</th>
          <th>Quantity</th>
          <th>Total</th>
        </tr>
        </thead>
        <tbody id="orderItems">
        <tr>
          <td>
            <div style="display: flex; align-items: center; gap: 10px;">
              <img src="https://m.media-amazon.com/images/I/71JQd+Zh3RL._AC_UL480_FMwebp_QL65_.jpg" alt="Remote Control Car" class="product-img">
              <div>
                <strong>Remote Control Car</strong><br>
                <small>SKU: TW-RC-001</small>
              </div>
            </div>
          </td>
          <td>$29.99</td>
          <td>1</td>
          <td>$29.99</td>
        </tr>
        <tr>
          <td>
            <div style="display: flex; align-items: center; gap: 10px;">
              <img src="https://m.media-amazon.com/images/I/71hUzFG7mIL._AC_UL480_FMwebp_QL65_.jpg" alt="Stuffed Animal" class="product-img">
              <div>
                <strong>Stuffed Animal</strong><br>
                <small>SKU: TW-SA-005</small>
              </div>
            </div>
          </td>
          <td>$19.99</td>
          <td>1</td>
          <td>$19.99</td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
          <td colspan="3" style="text-align: right;"><strong>Subtotal:</strong></td>
          <td>$49.98</td>
        </tr>
        <tr>
          <td colspan="3" style="text-align: right;"><strong>Shipping:</strong></td>
          <td>$10.00</td>
        </tr>
        <tr>
          <td colspan="3" style="text-align: right;"><strong>Tax:</strong></td>
          <td>$0.00</td>
        </tr>
        <tr>
          <td colspan="3" style="text-align: right;"><strong>Total:</strong></td>
          <td>$59.98</td>
        </tr>
        </tfoot>
      </table>

      <div style="margin-top: 2rem;">
        <h4>Order Notes</h4>
        <div style="background-color: #f9f9f9; padding: 1rem; border-radius: 5px;">
          <p><em>Please gift wrap both items separately.</em></p>
        </div>
      </div>
    </div>
    <div class="modal-footer">
      <button class="btn btn-secondary" onclick="closeModal()">Close</button>
      <button class="btn btn-success"><i class="fas fa-check"></i> Mark as Completed</button>
      <button class="btn btn-warning"><i class="fas fa-truck"></i> Update Shipping</button>
      <button class="btn"><i class="fas fa-print"></i> Print Invoice</button>
    </div>
  </div>
</div>

<script>
  // View order details modal
  function viewOrderDetails(orderId) {
    // In a real application, you would fetch order details from your backend
    // Here we're just simulating with the first order's data
    document.getElementById('modalOrderId').textContent = orderId;

    // Show the modal
    document.getElementById('orderDetailsModal').style.display = 'flex';
    document.body.style.overflow = 'hidden';
  }

  // Close modal
  function closeModal() {
    document.getElementById('orderDetailsModal').style.display = 'none';
    document.body.style.overflow = 'auto';
  }

  // Close modal when clicking outside
  window.onclick = function(event) {
    const modal = document.getElementById('orderDetailsModal');
    if (event.target == modal) {
      closeModal();
    }
  }

  // In a real application, you would have:
  // - AJAX calls to fetch order data
  // - Functions to update order status
  // - Export functionality
  // - Pagination controls
  // - Search and filter implementations
</script>
</body>
</html>