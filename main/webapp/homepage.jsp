<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Toy Wonderland - Home</title>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500;600;700&display=swap" rel="stylesheet">
  <!-- Your CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/homepage.css">
</head>
<body>
<header>
  <div class="header-container">
    <a href="${pageContext.request.contextPath}/toys/home" class="logo">
      <img src="https://via.placeholder.com/50x50" alt="Toy Wonderland"> Toy Wonderland
    </a>
    <nav>
      <ul>
        <li><a href="${pageContext.request.contextPath}/home" class="active">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/product.jsp">Shop</a></li>
        <li><a href="${pageContext.request.contextPath}/about.jsp">About</a></li>
        <li><a href="${pageContext.request.contextPath}/contactpage.jsp">Contact</a></li>
        <li>
          <a href="${pageContext.request.contextPath}/addtocart.jsp" class="cart-icon">
            <i class="fas fa-shopping-cart"></i>
            <span class="cart-count">${cartCount}</span>
          </a>
        </li>
        <li><a href="${pageContext.request.contextPath}/accountpage.jsp">Account</a></li>
      </ul>
    </nav>
  </div>
</header>



<section class="hero">
  <div class="hero-content">
    <h1>Discover the Magic of Playtime</h1>
    <p>Explore our collection of fun, educational, and imaginative toys that spark creativity and joy in children of all ages.</p>
    <a href="${pageContext.request.contextPath}/product.jsp" class="btn">Shop Now</a>
    <a href="${pageContext.request.contextPath}/about.jsp" class="btn btn-secondary">Learn More</a>
  </div>
</section>

<section class="featured-categories">
  <h2 class="section-title">Popular Categories</h2>
  <div class="categories-grid">
    <div class="category-card">
      <img src="https://m.media-amazon.com/images/I/718X2Q4lMYL._AC_UL480_FMwebp_QL65_.jpg" alt="Building Blocks" class="category-image">
      <div class="category-info">
        <h3 class="category-name">Building Blocks</h3>
        <a href="${pageContext.request.contextPath}/product1.jsp" class="category-btn">Explore</a>
      </div>
    </div>

    <div class="category-card">
      <img src="https://m.media-amazon.com/images/I/81cCgb-TIpL._AC_UL480_FMwebp_QL65_.jpg" alt="Dolls & Figures" class="category-image">
      <div class="category-info">
        <h3 class="category-name">Dolls & Figures</h3>
        <a href="${pageContext.request.contextPath}/product2.jsp" class="category-btn">Explore</a>
      </div>
    </div>

    <div class="category-card">
      <img src="https://m.media-amazon.com/images/I/81Zn0SA5oRL._AC_UL480_FMwebp_QL65_.jpg" alt="Educational Toys" class="category-image">
      <div class="category-info">
        <h3 class="category-name">Educational Toys</h3>
        <a href="${pageContext.request.contextPath}/product2.jsp" class="category-btn">Explore</a>
      </div>
    </div>

    <div class="category-card">
      <img src="https://m.media-amazon.com/images/I/61C9E-oAybL._AC_UL320_.jpg" alt="Outdoor Fun" class="category-image">
      <div class="category-info">
        <h3 class="category-name">Outdoor Fun</h3>
        <a href="${pageContext.request.contextPath}/product3.jsp" class="category-btn">Explore</a>
      </div>
    </div>
  </div>
</section>

<section class="banner">
  <h2>Free Shipping on Orders Over $50!</h2>
  <p>Hurry, offer valid for a limited time only. Give the gift of play without the extra cost.</p>
  <a href="${pageContext.request.contextPath}/product.jsp" class="btn">Start Shopping</a>
</section>

<section class="testimonials">
  <div class="testimonials-container">
    <h2 class="section-title">What Parents Say</h2>
    <div class="testimonial-grid">
      <div class="testimonial-card">
        <p class="testimonial-text">"My kids absolutely love the building blocks set! It keeps them engaged for hours and helps develop their creativity. The quality is outstanding."</p>
        <div class="testimonial-author">
          <img src="https://example.com/parent1.jpg" alt="Sarah J." class="author-avatar">
          <div class="author-info">
            <h4>Sarah J.</h4>
            <p>Mom of two</p>
          </div>
        </div>
      </div>

      <div class="testimonial-card">
        <p class="testimonial-text">"The educational toys from Toy Wonderland have made learning so much fun for my daughter. She doesn't even realize she's learning while playing!"</p>
        <div class="testimonial-author">
          <img src="https://example.com/parent2.jpg" alt="Michael T." class="author-avatar">
          <div class="author-info">
            <h4>Michael T.</h4>
            <p>Dad of one</p>
          </div>
        </div>
      </div>

      <div class="testimonial-card">
        <p class="testimonial-text">"I'm so impressed with the customer service and quality of toys. Every purchase has been a hit with my grandchildren. Highly recommended!"</p>
        <div class="testimonial-author">
          <img src="https://example.com/parent3.jpg" alt="Linda K." class="author-avatar">
          <div class="author-info">
            <h4>Linda K.</h4>
            <p>Grandmother</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</section>

<section class="newsletter">
  <div class="newsletter-container">
    <h2 class="section-title">Join Our Toy Club!</h2>
    <p>Subscribe to our newsletter for exclusive offers, toy news, and parenting tips.</p>
    <form class="newsletter-form">
      <input type="email" placeholder="Your email address" class="newsletter-input" required>
      <button type="submit" class="newsletter-btn">Subscribe</button>
    </form>
  </div>
</section>

<footer>
  <div class="footer-container">
    <div class="footer-links">
      <a href="${pageContext.request.contextPath}/about.jsp">About Us</a>
      <a href="${pageContext.request.contextPath}/contactpage.jsp">Contact</a>
      <a href="${pageContext.request.contextPath}/about.jsp">FAQ</a>
      <a href="${pageContext.request.contextPath}/payment.jsp">Shipping & Returns</a>
      <a href="${pageContext.request.contextPath}/about.jsp">Privacy Policy</a>
      <a href="${pageContext.request.contextPath}/about.jsp">Terms of Service</a>
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