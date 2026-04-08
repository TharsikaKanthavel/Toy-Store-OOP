<!DOCTYPE html
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Your Cart - Toy Wonderland</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/addtocart.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<header>
  <div class="header-container">
    <a href="homepage.html" class="logo">
      <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscaleg" alt="Toy Wonderland"> Toy Wonderland
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
        <li><a href="${pageContext.request.contextPath}/accountpage.jsp">Account</a></li>
      </ul>
    </nav>
  </div>
</header>

<main>
  <h1 class="page-title">Your Shopping Cart</h1>

  <div class="cart-container">
    <div class="cart-items">
      <!-- Cart Item 1 -->
      <div class="cart-item">
        <img src="https://m.media-amazon.com/images/I/81RtjTcZ0xL._AC_UL480_FMwebp_QL65_.jpg" alt="Magic Building Blocks" class="cart-item-image">
        <div class="cart-item-details">
          <h3 class="cart-item-name">Magic Building Blocks Set</h3>
          <div class="cart-item-price">Rs.4000</div>
          <div class="cart-item-quantity">
            <button class="quantity-btn">-</button>
            <input type="number" value="1" min="1" class="quantity-input">
            <button class="quantity-btn">+</button>
          </div>
          <button class="remove-item">
            <i class="fas fa-trash"></i> Remove
          </button>
        </div>
      </div>

      <!-- Cart Item 2 -->
      <div class="cart-item">
        <img src="https://m.media-amazon.com/images/I/91uvKS4iOcL._AC_UL480_FMwebp_QL65_.jpg" alt="Solar System Puzzle" class="cart-item-image">
        <div class="cart-item-details">
          <h3 class="cart-item-name">Solar System Puzzle</h3>
          <div class="cart-item-price">Rs.6000</div>
          <div class="cart-item-quantity">
            <button class="quantity-btn">-</button>
            <input type="number" value="2" min="1" class="quantity-input">
            <button class="quantity-btn">+</button>
          </div>
          <button class="remove-item">
            <i class="fas fa-trash"></i> Remove
          </button>
        </div>
      </div>

      <!-- Cart Item 3 -->
      <div class="cart-item">
        <img src="https://m.media-amazon.com/images/I/81FQjVWP-sL._AC_UL480_FMwebp_QL65_.jpg" alt="Cuddly Bear" class="cart-item-image">
        <div class="cart-item-details">
          <h3 class="cart-item-name">Cuddly Bear</h3>
          <div class="cart-item-price">Rs.2500</div>
          <div class="cart-item-quantity">
            <button class="quantity-btn">-</button>
            <input type="number" value="1" min="1" class="quantity-input">
            <button class="quantity-btn">+</button>
          </div>
          <button class="remove-item">
            <i class="fas fa-trash"></i> Remove
          </button>
        </div>
      </div>

      <!-- Empty Cart State (hidden by default) -->
      <!-- <div class="empty-cart">
          <div class="empty-cart-icon">
              <i class="fas fa-shopping-cart"></i>
          </div>
          <p class="empty-cart-message">Your cart is empty</p>
          <a href="/products" class="btn">Start Shopping</a>
      </div> -->
    </div>

    <div class="cart-summary">
      <h2 class="summary-title">Order Summary</h2>
      <div class="summary-row">
        <span class="summary-label">Subtotal (3 items)</span>
        <span class="summary-value">Rs.12500</span>
      </div>
      <div class="summary-row">
        <span class="summary-label">Shipping</span>
        <span class="summary-value">Rs.300</span>
      </div>
      <div class="summary-row">
        <span class="summary-label">Tax</span>
        <span class="summary-value">Rs.600</span>
      </div>
      <div class="summary-row total-row">
        <span class="total-label">Total</span>
        <span class="total-value">Rs.13400</span>
      </div>
      <button class="checkout-btn" href="${pageContext.request.contextPath}/payment.jsp"> Proceed to Checkout</button>

      <a href="${pageContext.request.contextPath}/product.jsp" class="continue-shopping">
        <i class="fas fa-arrow-left"></i> Continue Shopping
      </a>
    </div>
  </div>
</main>

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
  document.querySelector('.checkout-btn').addEventListener('click', function(e) {
    // Only prevent default if you need to do validation first
    e.preventDefault();

    // Perform any validation
    const cartItems = document.querySelectorAll('.cart-item');
    if (cartItems.length === 0) {
      alert('Your cart is empty! Please add some items before checkout.');
      return;
    }

    // Then redirect
    window.location.href = this.getAttribute('href');
  });
</script>
</body>
</html>