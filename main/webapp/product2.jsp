<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Toy Wonderland - Our Products</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/product.css">
</head>
<body>
<header>
  <div class="header-container">
    <a href="${pageContext.request.contextPath}/homepage.jsp" class="logo">
      <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscale" alt="Toy Wonderland"> Toy Wonderland
    </a>
    <nav>
      <ul>
        <li><a href="${pageContext.request.contextPath}/homepage.jsp">Home</a></li>
        <li><a href="${pageContext.request.contextPath}/product.jsp" class="active">Shop</a></li>
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
  <h1 class="page-title">Our Wonderful Toys</h1>

  <div class="filters">
    <div class="search-box">
      <i class="fas fa-search search-icon"></i>
      <input type="text" placeholder="Search for toys...">
    </div>

    <div class="category-filter">
      <select>
        <option value="">All Categories</option>
        <option value="building">Building Blocks</option>
        <option value="dolls">Dolls & Action Figures</option>
        <option value="educational">Educational Toys</option>
        <option value="outdoor">Outdoor Toys</option>
        <option value="puzzles">Puzzles & Games</option>
        <option value="vehicles">Toy Vehicles</option>
        <option value="soft">Soft Toys</option>
      </select>
    </div>
  </div>

  <div class="products-grid">
    <!-- Product 1 -->
    <div class="product-card">
      <span class="product-badge">Bestseller</span>
      <img src="https://m.media-amazon.com/images/I/71a9rjUxSeL._AC_UL320_.jpg" alt="Magic Building Blocks" class="product-image">
      <div class="product-info">
        <div class="product-category">Dolls & Figures</div>
        <h3 class="product-name">Princess Girl Mini Dolls, Small Princess Dolls, Little Girl Miniature Dolls</h3>
        <div class="rating">
          <div class="stars">
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star-half-alt"></i>
          </div>
          <span class="review-count">(128)</span>
        </div>
        <div class="product-price">
          <span class="current-price">Rs.7500</span>
          <span class="original-price">Rs.12000</span>
        </div>
        <span class="age-badge">3+ years</span>
        <button class="add-to-cart">Add to Cart</button>
      </div>
    </div>

    <!-- Product 2 -->
    <div class="product-card">
      <img src="https://m.media-amazon.com/images/I/81sJpAcYTJL._AC_UL320_.jpg" alt="Adventure Dollhouse" class="product-image">
      <div class="product-info">
        <div class="product-category">Dolls & Figures</div>
        <h3 class="product-name">Mattel Disney Princess Toys, Small Doll Party Set</h3>
        <div class="rating">
          <div class="stars">
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="far fa-star"></i>
          </div>
          <span class="review-count">(76)</span>
        </div>
        <div class="product-price">
          <span class="current-price">Rs.5500</span>
        </div>
        <span class="age-badge">4+ years</span>
        <button class="add-to-cart">Add to Cart</button>
      </div>
    </div>

    <!-- Product 3 -->
    <div class="product-card">
      <span class="product-badge">Sale</span>
      <img src="https://m.media-amazon.com/images/I/71juRZdBhGL._AC_UL320_.jpg" alt="Solar System Puzzle" class="product-image">
      <div class="product-info">
        <div class="product-category">Dolls & Figures</div>
        <h3 class="product-name">Mini Baby Dolls, Adorable 4 inch Realistic Baby Dolls </h3>
        <div class="rating">
          <div class="stars">
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
          </div>
          <span class="review-count">(215)</span>
        </div>
        <div class="product-price">
          <span class="current-price">Rs.2000</span>
          <span class="original-price">Rs.3000</span>
        </div>
        <span class="age-badge">5+ years</span>
        <button class="add-to-cart">Add to Cart</button>
      </div>
    </div>

    <!-- Product 4 -->
    <div class="product-card">
      <img src="https://m.media-amazon.com/images/I/819oIGHhllL._AC_UL320_.jpg" alt="Race Car Track Set" class="product-image">
      <div class="product-info">
        <div class="product-category">Dolls & Figures</div>
        <h3 class="product-name">Mini Baby Dolls, Adorable 4 inch Realistic Baby Dolls </h3>
        <div class="rating">
          <div class="stars">
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
          </div>
          <span class="review-count">(189)</span>
        </div>
        <div class="product-price">
          <span class="current-price">Rs.6000</span>
        </div>
        <span class="age-badge">6+ years</span>
        <button class="add-to-cart">Add to Cart</button>
      </div>
    </div>

    <!-- Product 5 -->
    <div class="product-card">
      <img src="https://m.media-amazon.com/images/I/91DkA1tuA4L._AC_UL320_.jpg" alt="Cuddly Bear" class="product-image">
      <div class="product-info">
        <div class="product-category">Dolls & Figures</div>
        <h3 class="product-name">Dollhouse Set of Mini People Figures Dollhouse Dolls</h3>
        <div class="rating">
          <div class="stars">
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star-half-alt"></i>
          </div>
          <span class="review-count">(312)</span>
        </div>
        <div class="product-price">
          <span class="current-price">Rs.3000</span>
        </div>
        <span class="age-badge">0+ years</span>
        <button class="add-to-cart">Add to Cart</button>
      </div>
    </div>

    <!-- Product 6 -->
    <div class="product-card">
      <span class="product-badge">New</span>
      <img src="https://m.media-amazon.com/images/I/818eWk-MtBL._AC_UL320_.jpg" alt="Outdoor Explorer Kit" class="product-image">
      <div class="product-info">
        <div class="product-category">Dolls & Figures</div>
        <h3 class="product-name">Dollhouse with 4 Princesses, 4 Unicorns, Dog, Furniture and Accessories </h3>
        <div class="rating">
          <div class="stars">
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="fas fa-star"></i>
            <i class="far fa-star"></i>
          </div>
          <span class="review-count">(42)</span>
        </div>
        <div class="product-price">
          <span class="current-price">Rs.4500</span>
        </div>
        <span class="age-badge">5+ years</span>
        <button class="add-to-cart">Add to Cart</button>
      </div>
    </div>
  </div>

  <div class="pagination">
    <a href="${pageContext.request.contextPath}/product1.jsp">&laquo;</a>
    <a href="${pageContext.request.contextPath}/product.jsp" >1</a>
    <a href="${pageContext.request.contextPath}/product1.jsp">2</a>
    <a href="${pageContext.request.contextPath}/product2.jsp" class="active">3</a>

    <a href="${pageContext.request.contextPath}/product3.jsp">&raquo;</a>
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
</body>
</html>