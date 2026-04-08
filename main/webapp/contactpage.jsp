
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Contact Us - Toy Wonderland</title>

  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/contactpage.css">

  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<header>
  <div class="header-container">
    <a href="${pageContext.request.contextPath}/homepage.jsp" class="logo">
      <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscaleg" alt="Toy Wonderland"> Toy Wonderland
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
        <li><a href="${pageContext.request.contextPath}/accountpage.jsp">Account</a></li>
      </ul>
    </nav>
  </div>
</header>

<main>
  <h1 class="page-title">Get In Touch With Us</h1>

  <div class="contact-container">
    <div class="contact-info">
      <h2 class="info-title">Our Information</h2>

      <div class="info-item">
        <div class="info-icon">
          <i class="fas fa-map-marker-alt"></i>
        </div>
        <div class="info-content">
          <h3>Our Store</h3>
          <p>Kanthar madam<br>jaffna<br>Sri Lanka</p>
        </div>
      </div>

      <div class="info-item">
        <div class="info-icon">
          <i class="fas fa-phone-alt"></i>
        </div>
        <div class="info-content">
          <h3>Phone Number</h3>
          <p><a href="tel:+18005551234">(+94) 0772078909</a></p>
        </div>
      </div>

      <div class="info-item">
        <div class="info-icon">
          <i class="fas fa-envelope"></i>
        </div>
        <div class="info-content">
          <h3>Email Address</h3>
          <p><a href="mailto:hello@toywonderland.com">hello@toywonderland.com</a></p>
        </div>
      </div>

      <div class="store-hours">
        <h3>Store Hours</h3>
        <table class="hours-table">
          <tr>
            <td>Monday - Friday</td>
            <td>9:00 AM - 7:00 PM</td>
          </tr>
          <tr>
            <td>Saturday</td>
            <td>10:00 AM - 6:00 PM</td>
          </tr>
          <tr>
            <td>Sunday</td>
            <td>11:00 AM - 5:00 PM</td>
          </tr>
        </table>
      </div>

      <div class="map-container">
        <iframe src="https://maps.app.goo.gl/zkuet4omnx9Uujg48" allowfullscreen="" loading="lazy"></iframe>
      </div>
    </div>

    <div class="contact-form">
      <h2 class="form-title">Send Us a Message</h2>
      <form action="/contact" method="POST">
        <div class="form-group">
          <label for="name">Your Name</label>
          <input type="text" id="name" name="name" class="form-control" required>
        </div>

        <div class="form-group">
          <label for="email">Email Address</label>
          <input type="email" id="email" name="email" class="form-control" required>
        </div>

        <div class="form-group">
          <label for="subject">Subject</label>
          <input type="text" id="subject" name="subject" class="form-control" required>
        </div>

        <div class="form-group">
          <label for="message">Your Message</label>
          <textarea id="message" name="message" class="form-control" required></textarea>
        </div>

        <button type="submit" class="submit-btn">Send Message</button>
      </form>
    </div>
  </div>
</main>

<footer>
  <div class="footer-container">
    <div class="footer-links">
      <a href="${pageContext.request.contextPath}//about.jsp">About Us</a>
      <a href="${pageContext.request.contextPath}//contact.jsp">Contact</a>
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