
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Toy Wonderland - Rewards</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/rewards.css">

</head>
<body>
<div class="rewards-container">
  <img src="https://img.drz.lazcdn.com/static/lk/p/8b54cead8a08ff997ae988de03fbb8f2.jpg_400x400q75.avif" alt="Teddy Bear" class="toy-decoration toy1">
  <img src="https://m.media-amazon.com/images/I/61OowkNVFqL._AC_UL480_FMwebp_QL65_.jpg" alt="Rubber Duck" class="toy-decoration toy2">

  <div class="rewards-header">
    <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscale" alt="Toy Wonderland Logo" class="logo" style="width: 150px";>
    <h1>Toy Wonderland Rewards</h1>
    <p>Play, earn, and get amazing toys!</p>
  </div>

  <div class="rewards-points">
    <h2>Your Reward Points</h2>
    <div class="points-display">1,250</div>
    <p>Keep shopping to earn more points!</p>
  </div>

  <h2 style="text-align: center; color: #ff6b6b;">Redeem Your Points</h2>
  <div class="rewards-grid">
    <div class="reward-card">
      <img src="https://m.media-amazon.com/images/I/61OowkNVFqL._AC_UL480_FMwebp_QL65_.jpg" alt="Mini Figurine">
      <h3>Mini Figurine</h3>
      <p>Adorable collectible toy</p>
      <div class="reward-points">500 points</div>
      <button class="redeem-btn">Redeem Now</button>
    </div>

    <div class="reward-card">
      <img src="https://m.media-amazon.com/images/I/71hUzFG7mIL._AC_UL480_FMwebp_QL65_.jpg" alt="Stuffed Animal">
      <h3>Stuffed Animal</h3>
      <p>Cuddly friend for life</p>
      <div class="reward-points">1,000 points</div>
      <button class="redeem-btn">Redeem Now</button>
    </div>

    <div class="reward-card">
      <img src="https://m.media-amazon.com/images/I/71WkOJYH3RL._AC_UL480_FMwebp_QL65_.jpg" alt="Building Blocks Set">
      <h3>Building Blocks</h3>
      <p>Creative construction fun</p>
      <div class="reward-points">1,500 points</div>
      <button class="redeem-btn">Redeem Now</button>
    </div>

    <div class="reward-card">
      <img src="https://m.media-amazon.com/images/I/71JQd+Zh3RL._AC_UL480_FMwebp_QL65_.jpg" alt="Remote Control Car">
      <h3>RC Car</h3>
      <p>Zoom around with this cool car</p>
      <div class="reward-points">2,500 points</div>
      <button class="redeem-btn">Redeem Now</button>
    </div>
  </div>

  <div class="how-it-works">
    <h3>How Our Rewards Work</h3>
    <ul>
      <li>🛍️ Earn 10 points for every $1 spent</li>
      <li>🎁 Get 100 bonus points on your birthday</li>
      <li>⭐ Double points during special events</li>
      <li>🔔 Points never expire!</li>
    </ul>
    <p>Redeem your points for exclusive toys or discounts on future purchases.</p>
  </div>
</div>
</body>
</html>