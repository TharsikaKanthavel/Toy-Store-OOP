<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Order Processing - Toy Wonderland</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ordertracking.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<header>
  <div class="header-container">
    <a href="/" class="logo">
      <img src="https://example.com/logo.png" alt="Toy Wonderland"> Toy Wonderland
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
            <span class="cart-count">0</span>
          </a>
        </li>
        <li><a href="${pageContext.request.contextPath}/accountpage.jsp">Account</a></li>
      </ul>
    </nav>
  </div>
</header>

<main>
  <div class="order-processing-container">
    <div class="processing-icon">
      <i class="fas fa-box-open"></i>
    </div>
    <h1 class="order-title">Your Order Is Being Processed!</h1>
    <div class="order-number">Order #TW-2025-1008</div>

    <div class="progress-steps">
      <div class="progress-bar"></div>

      <div class="step completed">
        <div class="step-icon">
          <i class="fas fa-check"></i>
        </div>
        <div class="step-label">Order Placed</div>
      </div>

      <div class="step active">
        <div class="step-icon">2</div>
        <div class="step-label">Processing</div>
      </div>

      <div class="step">
        <div class="step-icon">3</div>
        <div class="step-label">Shipped</div>
      </div>

      <div class="step">
        <div class="step-icon">4</div>
        <div class="step-label">Delivered</div>
      </div>
    </div>

    <p style="color: #666; margin-bottom: 30px; line-height: 1.6;">
      Thank you for your order! We're preparing your toys with care and will notify you when they're on their way.
      You'll receive a confirmation email with all the details shortly.
    </p>

    <div class="order-details">
      <h3 class="details-title">Order Summary</h3>

      <div class="detail-row">
        <div class="detail-label">Order Date:</div>
        <div class="detail-value">March 20, 2025</div>
      </div>

      <div class="detail-row">
        <div class="detail-label">Items:</div>
        <div class="detail-value">
          Magic Building Blocks (1), Solar System Puzzle (1), Cuddly Bear (1)
        </div>
      </div>

      <div class="detail-row">
        <div class="detail-label">Total Amount:</div>
        <div class="detail-value">Rs.3450</div>
      </div>

      <div class="detail-row">
        <div class="detail-label">Payment Method:</div>
        <div class="detail-value">Credit Card (•••• •••• •••• 4242)</div>
      </div>

      <div class="detail-row">
        <div class="detail-label">Shipping Address:</div>
        <div class="detail-value">
          kantharmadam jaffna srilanka
        </div>
      </div>

      <div class="estimated-delivery">
        <div class="delivery-icon">
          <i class="fas fa-truck"></i>
        </div>
        <div class="delivery-text">
          <strong>Estimated Delivery:</strong> March 25-27, 2025
        </div>
      </div>
    </div>

    <div class="action-buttons">
      <a href="${pageContext.request.contextPath}/product.jsp" class="btn btn-secondary">
        <i class="fas fa-shopping-bag"></i> Continue Shopping
      </a>
      <a href="${pageContext.request.contextPath}/customer.jsp" class="btn btn-primary">
        <i class="fas fa-clipboard-list"></i> View Order Details
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
      &copy; 2025 Toy Wonderland. All rights reserved.
    </div>
  </div>
</footer>

<script>
  // This would be connected to real order status in a live application
  // For demo purposes, we'll simulate progress after 3 seconds
  setTimeout(function() {
    document.querySelector('.progress-bar').style.width = '66%';
    document.querySelector('.step:nth-child(3)').classList.add('active');
    document.querySelector('.step:nth-child(2)').classList.remove('active');
    document.querySelector('.step:nth-child(2)').classList.add('completed');
  }, 3000);
</script>
</body>
</html>