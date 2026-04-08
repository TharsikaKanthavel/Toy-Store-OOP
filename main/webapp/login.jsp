<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Toy Wonderland - Login</title>
  <!-- Font Awesome -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
  <!-- Google Fonts -->
  <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&family=Poppins:wght@300;400;500;600&display=swap" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css2?family=Fredoka+One&display=swap" rel="stylesheet">
  <!-- Your CSS -->
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/login.css">
</head>
<body>
<div class="login-container">
  <div class="login-box">
    <div class="logo-container">
      <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscale" alt="Toy Wonderland Logo" class="logo">
      <h1>Welcome Back!</h1>
      <p>Ready for more playtime adventures?</p>
    </div>

    <form action="${pageContext.request.contextPath}/toys/login" method="POST" class="login-form">
      <div class="input-group">
        <i class="fas fa-envelope"></i>
        <input type="email" name="email" placeholder="your@email.com" required>
      </div>

      <div class="input-group">
        <i class="fas fa-lock"></i>
        <input type="password" name="password" placeholder="••••••••" required>
        <i class="fas fa-eye toggle-password"></i>
      </div>

      <button type="submit" class="toy-login-btn">
        <div class="btn-wrapper">
          <span class="btn-text">Login to Toyland</span>
          <div class="toy-elements">
            <div class="building-block"></div>
            <div class="teddy-bear"></div>
            <div class="rubber-duck"></div>
            <div class="balloon"></div>
          </div>
          <div class="sparkles">
            <div class="sparkle"></div>
            <div class="sparkle"></div>
            <div class="sparkle"></div>
            <div class="sparkle"></div>
          </div>
        </div>
        <div class="btn-shadow"></div>
      </button>
    </form>

    <div class="links">
      <a href="${pageContext.request.contextPath}/forgot.jsp">Forgot Password?</a>
      <a href="${pageContext.request.contextPath}/signup.jsp">Create Account</a>
    </div>
  </div>

  <!-- Floating toys -->
  <div class="toy-balloon"></div>
  <div class="toy-car"></div>
  <div class="toy-teddy"></div>
</div>

<script>
  // Toggle password visibility
  document.querySelector('.toggle-password').addEventListener('click', function() {
    const password = document.querySelector('input[name="password"]');
    const type = password.getAttribute('type') === 'password' ? 'text' : 'password';
    password.setAttribute('type', type);
    this.classList.toggle('fa-eye-slash');
  });
</script>
</body>
</html>