<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>My Account - Toy Wonderland</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/accountpage.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<header>
  <div class="container header-content">
    <a href="${pageContext.request.contextPath}/homepage.jsp" class="logo">
      <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscale" alt="Toy Wonderland"> Toy Wonderland
    </a>
    <nav>
      <ul>
        <li><a href="${pageContext.request.contextPath}/homepage.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/product.jsp">Shop</a></li>
        <li><a href="${pageContext.request.contextPath}/about.jsp">About</a></li>
        <li><a href="${pageContext.request.contextPath}/contactpage.jsp">Contact</a></li>
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

<section class="account-section">
  <div class="container account-container">
    <div class="account-sidebar">
      <div class="profile-card">
        <img src="https://randomuser.me/api/portraits/women/44.jpg" alt="User Avatar" class="avatar">
        <h3>Welcome, Sarah!</h3>
        <p>Member since January 2022</p>
      </div>
      <nav class="account-menu">
        <ul>
          <li><a href="${pageContext.request.contextPath}/dashboard.jsp" class="active"><i class="fas fa-user"></i> Dashboard</a></li>
          <li><a href="/account/orders"><i class="fas fa-box-open"></i> My Orders</a></li>
          <li><a href="/account/wishlist"><i class="fas fa-heart"></i> Wishlist</a></li>
          <li><a href="/account/addresses"><i class="fas fa-map-marker-alt"></i> Addresses</a></li>
          <li><a href="/account/payment"><i class="fas fa-credit-card"></i> Payment Methods</a></li>
          <li><a href="/account/settings"><i class="fas fa-cog"></i> Account Settings</a></li>
          <li><a href="/logout"><i class="fas fa-sign-out-alt"></i> Log Out</a></li>
        </ul>
      </nav>
    </div>

    <div class="account-content">
      <div class="content-header">
        <h2>Account Dashboard</h2>
        <a href="${pageContext.request.contextPath}/product.jsp" class="btn">Continue Shopping</a>
      </div>

      <div class="dashboard-cards">
        <div class="dashboard-card">
          <div class="card-icon">
            <i class="fas fa-box-open"></i>
          </div>
          <div class="card-content">
            <h3>Recent Orders</h3>
            <p>You have 2 recent orders</p>
            <a href="${pageContext.request.contextPath}/addtocart.jsp" class="btn-small">View Orders</a>
          </div>
        </div>

        <div class="dashboard-card">
          <div class="card-icon">
            <i class="fas fa-heart"></i>
          </div>
          <div class="card-content">
            <h3>Wishlist</h3>
            <p>5 items in your wishlist</p>
            <a href="${pageContext.request.contextPath}/addtocart.jsp" class="btn-small">View Wishlist</a>
          </div>
        </div>

        <div class="dashboard-card">
          <div class="card-icon">
            <i class="fas fa-map-marker-alt"></i>
          </div>
          <div class="card-content">
            <h3>Default Address</h3>
            <p>kantharmadam jaffna srilanka</p>
            <a href="/account/addresses" class="btn-small">Manage Addresses</a>
          </div>
        </div>
      </div>

      <div class="recent-orders">
        <h3>Recent Orders</h3>
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
            <td>#TW-2025-101</td>
            <td>mar 10, 2025</td>
            <td>2 items</td>
            <td>Rs.9000</td>
            <td><span class="status status-shipped">Shipped</span></td>
            <td><a href="/account/orders/7890" class="btn-small">View</a></td>
          </tr>
          <tr>
            <td>#TW-2025-107</td>
            <td>mar 27, 2025</td>
            <td>2 items</td>
            <td>Rs.14000</td>
            <td><span class="status status-delivered">Delivered</span></td>
            <td><a href="/account/orders/6789" class="btn-small">View</a></td>
          </tr>
          </tbody>
        </table>
        <a href="/account/orders" class="btn">View All Orders</a>
      </div>

      <div class="recommended-products">
        <h3>Recommended For You</h3>
        <div class="products-grid">
          <div class="product-card">
            <img src="https://m.media-amazon.com/images/I/81717vG5DHL._AC_UL480_FMwebp_QL65_.jpg" alt="Building Blocks Set" class="product-img">
            <h4>Deluxe Building Blocks</h4>
            <p class="price">Rs.1400</p>
            <button class="btn-small">Add to Cart</button>
          </div>
          <div class="product-card">
            <img src="https://m.media-amazon.com/images/I/81kLUWeCQcL._AC_UL480_FMwebp_QL65_.jpg" alt="Educational Robot" class="product-img">
            <h4>STEM Learning Robot</h4>
            <p class="price">Rs.11100</p>
            <button class="btn-small">Add to Cart</button>
          </div>
          <div class="product-card">
            <img src="https://m.media-amazon.com/images/I/71hcC9pYpxL._AC_UL480_FMwebp_QL65_.jpg" alt="Art Set" class="product-img">
            <h4>Creative Art  <br>    Set</h4>
            <p class="price">Rs.2000</p>
            <button class="btn-small">Add to Cart</button>
          </div>
          <div class="product-card">
            <img src="https://m.media-amazon.com/images/I/81sXzB5l7jL._AC_UL480_FMwebp_QL65_.jpg" alt="Puzzle Game" class="product-img">
            <h4>3D Puzzle   <br>    Game</h4>
            <p class="price">Rs.1500</p>
            <button class="btn-small">Add to Cart</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<footer>
  <div class="container footer-content">
    <div class="footer-links">
      <a href="/about">About Us</a>
      <a href="/contact">Contact</a>
      <a href="/faq">FAQ</a>
      <a href="/shipping">Shipping & Returns</a>
      <a href="/privacy">Privacy Policy</a>
      <a href="/terms">Terms of Service</a>
    </div>
    <div class="social-links">
      <a href="#"><i class="fab fa-facebook-f"></i></a>
      <a href="#"><i class="fab fa-instagram"></i></a>
      <a href="#"><i class="fab fa-twitter"></i></a>
      <a href="#"><i class="fab fa-pinterest-p"></i></a>
    </div>
    <div class="copyright">
      &copy; 2023 Toy Wonderland. All rights reserved.
    </div>
  </div>
</footer>
</body>
</html>