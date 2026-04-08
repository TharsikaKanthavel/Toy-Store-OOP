<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Toy Wonderland - Forgot Password</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css"> <!-- Reusing the same stylesheet -->
  <style>
    .instructions {
      color: #666;
      text-align: center;
      margin-bottom: 20px;
      font-size: 0.9em;
    }
  </style>
</head>
<body>
<div class="login-container">
  <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscale" alt="Toy Wonderland Logo" class="logo">
  <h1>Reset Your Password</h1>

  <img src="https://img.drz.lazcdn.com/static/lk/p/8b54cead8a08ff997ae988de03fbb8f2.jpg_400x400q75.avif" alt="Teddy Bear" class="toy-decoration toy1">
  <img src="https://m.media-amazon.com/images/I/61OowkNVFqL._AC_UL480_FMwebp_QL65_.jpg" alt="Rubber Duck" class="toy-decoration toy2">

  <p class="instructions">Enter your email address and we'll send you a link to reset your password.</p>

  <form action="/forgot-password" method="POST">
    <div class="input-group">
      <label for="email">Email</label>
      <input type="email" id="email" name="email" placeholder="your@email.com" required>
    </div>

    <button type="submit" class="btn">Send Reset Link</button>
  </form>

  <div class="links">
    <a href="${pageContext.request.contextPath}/login.jsp">Back to Login</a>
    <a href="${pageContext.request.contextPath}/signup.jsp">Create Account</a>
  </div>
</div>
</body>
</html>