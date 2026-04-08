
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Toy Wonderland - Sign Up</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signup.css">

</head>
<body>
<div class="signup-container">
  <!-- Replace with your toy store logo -->
  <img src="https://dynamic.brandcrowd.com/asset/logo/47d78bac-60aa-4df4-afea-5fce63678f89/logo-search-grid-2x?logoTemplateVersion=2&v=638363499510770000&text=toy&colorpalette=grayscale" alt="Toy Wonderland Logo" class="logo">
  <h1>Join the Toy Wonderland!</h1>

  <img src="https://example.com/teddy-bear.png" alt="Teddy Bear" class="toy-decoration toy1">
  <img src="https://example.com/rocking-horse.png" alt="Rocking Horse" class="toy-decoration toy2">

  <form action="/signup" method="POST">
    <div class="form-row">
      <div class="input-group">
        <label for="first-name">First Name</label>
        <input type="text" id="first-name" name="first_name" placeholder="Child's first name" required>
      </div>

      <div class="input-group">
        <label for="last-name">Last Name</label>
        <input type="text" id="last-name" name="last_name" placeholder="Child's last name" required>
      </div>
    </div>

    <div class="input-group">
      <label for="email">Parent's Email</label>
      <input type="email" id="email" name="email" placeholder="your@email.com" required>
    </div>

    <div class="input-group">
      <label for="password">Create Password</label>
      <input type="password" id="password" name="password" placeholder="••••••••" required>
    </div>

    <div class="input-group">
      <label for="confirm-password">Confirm Password</label>
      <input type="password" id="confirm-password" name="confirm_password" placeholder="••••••••" required>
    </div>

    <div class="input-group">
      <label>Child's Birthday</label>
      <div class="birthday-input">
        <select name="birth_month" required>
          <option value="" disabled selected>Month</option>
          <option value="1">January</option>
          <option value="2">February</option>
          <option value="3">March</option>
          <option value="4">April</option>
          <option value="5">May</option>
          <option value="6">June</option>
          <option value="7">July</option>
          <option value="8">August</option>
          <option value="9">September</option>
          <option value="10">October</option>
          <option value="11">November</option>
          <option value="12">December</option>

          <!-- Add all months -->
        </select>
        <select name="birth_day" required>
          <option value="" disabled selected>Day</option>
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
          <option value="6">6</option>
          <option value="7">7</option>
          <option value="8">8</option>
          <option value="9">9</option>
          <option value="10">10</option>
          <option value="11">11</option>
          <option value="12">12</option>
          <option value="13">13</option>
          <option value="14">14</option>
          <option value="15">15</option>
          <option value="16">16</option>
          <option value="17">17</option>
          <option value="18">18</option>
          <option value="19">19</option>
          <option value="20">20</option>
          <option value="21">21</option>
          <option value="22">22</option>
          <option value="23">23</option>
          <option value="24">24</option>
          <option value="25">25</option>
          <option value="26">26</option>
          <option value="27">27</option>
          <option value="28">28</option>
          <option value="29">29</option>
          <option value="30">30</option>
          <option value="31">31</option>
          </select>
          <!-- Add days 1-31 -->
        </select>
        <select name="birth_year" required>
          <option value="" disabled selected>Year</option>
          <option value="1990">1990</option>
          <option value="1991">1991</option>
          <option value="1992">1992</option>
          <option value="1993">1993</option>
          <option value="1994">1994</option>
          <option value="1995">1995</option>
          <option value="1996">1996</option>
          <option value="1997">1997</option>
          <option value="1998">1998</option>
          <option value="1999">1999</option>
          <option value="2000">2000</option>
          <option value="2001">2001</option>
          <option value="2002">2002</option>
          <option value="2003">2003</option>
          <option value="2004">2004</option>
          <option value="2005">2005</option>
          <option value="2006">2006</option>
          <option value="2007">2007</option>
          <option value="2008">2008</option>
          <option value="2009">2009</option>
          <option value="2010">2010</option>
          <option value="2011">2011</option>
          <option value="2012">2012</option>
          <option value="2013">2013</option>
          <option value="2014">2014</option>
          <option value="2015">2015</option>
          <option value="2016">2016</option>
          <option value="2017">2017</option>
          <option value="2018">2018</option>
          <option value="2019">2019</option>
          <option value="2020">2020</option>
          <option value="2021">2021</option>
          <option value="2022">2022</option>
          <option value="2023">2023</option>
          <option value="2024">2024</option>
          <option value="2025">2025</option>
          </select>
        <!-- Add recent years -->
        </select>
      </div>
    </div>

    <div class="terms">
      <input type="checkbox" id="terms" name="terms" required>
      <label for="terms">I agree to the <a href="/terms">Terms of Service</a> and <a href="/privacy">Privacy Policy</a></label>
    </div>

    <div class="terms">
      <input type="checkbox" id="newsletter" name="newsletter">
      <label for="newsletter">Sign me up for special offers and toy news!</label>
    </div>

    <a href="${pageContext.request.contextPath}/thankYou.jsp" button type="submit" class="btn">Create Account</abutton>
  </form>

  <div class="links">
    <a href="${pageContext.request.contextPath}/login.jsp">Already have an account? Log In</a>
    <a href="${pageContext.request.contextPath}/homepage.jsp">Back to Home</a>
  </div>
</div>
</body>
</html>