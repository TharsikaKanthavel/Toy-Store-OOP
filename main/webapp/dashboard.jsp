<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>My Dashboard - Toy Wonderland</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/dashboard.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<header>
  <div class="header-container">
    <a href="/" class="logo">
      <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscale" alt="Toy Wonderland"> Toy Wonderland
    </a>
    <nav>
      <ul>
        <li><a href="${pageContext.request.contextPath}/homepage.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/product.jsp">Shop</a></li>
        <li><a href="${pageContext.request.contextPath}/about.jsp">About</a></li>
        <li><a href="${pageContext.request.contextPath}/contactpage.jsp" class="active">Contact</a></li>
        <li>
          <a href="${pageContext.request.contextPath}/addtocart.jsp" class="cart-icon">
            <i class="fas fa-shopping-cart"></i>
            <span class="cart-count">3</span>
          </a>
        </li>
        <li><a href="${pageContext.request.contextPath}/accountpage.jsp" class="active">Account</a></li>
      </ul>
    </nav>
  </div>
</header>

<div class="dashboard-container">
  <aside class="sidebar">
    <div class="user-profile">
      <img src="https://randomuser.me/api/portraits/women/44.jpg" alt="User Avatar" class="user-avatar">
      <h3 class="user-name">Alex Johnson</h3>
      <p class="user-email">alex@example.com</p>
    </div>

    <ul class="dashboard-menu">
      <li><a href="#" class="active"><i class="fas fa-tachometer-alt"></i> Dashboard</a></li>
      <li><a href="${pageContext.request.contextPath}/ordertracking.jsp"><i class="fas fa-shopping-bag"></i> Orders</a></li>
      <li><a href="${pageContext.request.contextPath}/payment.jsp"><i class="fab fa-paypal"></i> Payment</a></li>
      <li><a href="#"><i class="fas fa-map-marker-alt"></i> Addresses</a></li>
      <li><a href="${pageContext.request.contextPath}/accountpage.jsp"><i class="fas fa-user"></i> Account Details</a></li>
      <li><a href="${pageContext.request.contextPath}/rewards.jsp"><i class="fas fa-gift"></i> Rewards</a></li>
      <li><a href="#"><i class="fas fa-sign-out-alt"></i> Logout</a></li>
    </ul>
  </aside>

  <main class="main-content">
    <div class="dashboard-header">
      <div>
        <h1 class="dashboard-title">My Dashboard</h1>
        <p class="welcome-message">Welcome back, Alex! Here's what's happening with your account.</p>
      </div>
    </div>

    <div class="dashboard-cards">
      <div class="dashboard-card">
        <h3 class="card-title"><i class="fas fa-shopping-bag"></i> Total Orders</h3>
        <p class="card-value">12</p>
      </div>

      <div class="dashboard-card">
        <h3 class="card-title"><i class="fas fa-heart"></i> Wishlist Items</h3>
        <p class="card-value">5</p>
      </div>

      <div class="dashboard-card">
        <h3 class="card-title"><i class="fas fa-star"></i> Reward Points</h3>
        <p class="card-value">1,250</p>
      </div>

      <div class="dashboard-card">
        <h3 class="card-title"><i class="fas fa-tag"></i> Active Coupons</h3>
        <p class="card-value">3</p>
      </div>
    </div>

    <div class="recent-orders">
      <h2 class="section-title">Recent Orders</h2>
      <table class="orders-table">
        <thead>
        <tr>
          <th>Order #</th>
          <th>Date</th>
          <th>Items</th>
          <th>Total</th>
          <th>Status</th>
          <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>#TW-2025-107</td>
          <td>mar 27, 2025</td>
          <td>3 items</td>
          <td>Rs.11000</td>
          <td><span class="order-status status-delivered">Delivered</span></td>
          <td><a href="#" class="view-order">View</a></td>
        </tr>
        <tr>
          <td>#TW-2025-107</td>
          <td>mar 27, 2025</td>
          <td>2 items</td>
          <td>Rs.12500</td>
          <td><span class="order-status status-shipped">Shipped</span></td>
          <td><a href="#" class="view-order">View</a></td>
        </tr>
        <tr>
          <td>#TW-2025-107</td>
          <td>mar 27, 2025</td>
          <td>5 items</td>
          <td>Rs.5500</td>
          <td><span class="order-status status-processing">Processing</span></td>
          <td><a href="#" class="view-order">View</a></td>
        </tr>
        </tbody>
      </table>
    </div>

    <div class="wishlist">
      <h2 class="section-title">Your Wishlist</h2>
      <div class="wishlist-grid">
        <div class="wishlist-item">
          <button class="remove-wishlist"><i class="fas fa-times"></i></button>
          <img src="https://m.media-amazon.com/images/I/81717vG5DHL._AC_UL480_FMwebp_QL65_.jpg" alt="Magic Building Blocks">
          <h3>Magic Building Blocks</h3>
          <p class="price">Rs.5500</p>
        </div>

        <div class="wishlist-item">
          <button class="remove-wishlist"><i class="fas fa-times"></i></button>
          <img src="https://m.media-amazon.com/images/I/913EGhkVMOL._AC_UL480_FMwebp_QL65_.jpg" alt="Adventure Dollhouse">
          <h3>Adventure Dollhouse</h3>
          <p class="price">Rs.5500</p>
        </div>

        <div class="wishlist-item">
          <button class="remove-wishlist"><i class="fas fa-times"></i></button>
          <img src="https://m.media-amazon.com/images/I/91uLmkoueKL._AC_UL480_FMwebp_QL65_.jpg" alt="Solar System Puzzle">
          <h3>Solar System Puzzle</h3>
          <p class="price">Rs.5500</p>
        </div>

        <div class="wishlist-item">
          <button class="remove-wishlist"><i class="fas fa-times"></i></button>
          <img src="https://m.media-amazon.com/images/I/919irgWNrhL._AC_UL480_FMwebp_QL65_.jpg" alt="Race Car Track Set">
          <h3>Race Car Track Set</h3>
          <p class="price">Rs.5500</p>
        </div>

        <div class="wishlist-item">
          <button class="remove-wishlist"><i class="fas fa-times"></i></button>
          <img src="https://m.media-amazon.com/images/I/71WQ5VmYl1L._AC_UL480_FMwebp_QL65_.jpg" alt="Cuddly Bear">
          <h3>Cuddly Bear</h3>
          <p class="price">Rs.5500</p>
        </div>
      </div>
    </div>
  </main>
</div>

<footer>
  <div class="footer-container">
    <div class="footer-links">
      <a href="/about">About Us</a>
      <a href="/contact">Contact</a>
      <a href="/faq">FAQ</a>
      <a href="/shipping">Shipping & Returns</a>
      <a href="/privacy">Privacy Policy</a>
      <a href="/terms">Terms of Service</a>
    </div>
    <div class="copyright">
      &copy; 2023 Toy Wonderland. All rights reserved.
    </div>
  </div>
</footer>

<script>
  // Simple functionality for the dashboard
  document.querySelectorAll('.remove-wishlist').forEach(button => {
    button.addEventListener('click', function() {
      const wishlistItem = this.closest('.wishlist-item');
      wishlistItem.remove();

      // In a real app, you would update the wishlist count here
      // and make an API call to remove from the server
    });
  });
</script>
</body>
</html>