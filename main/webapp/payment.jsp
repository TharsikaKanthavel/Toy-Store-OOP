<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Payment - Toy Wonderland</title>
  <style>
    /* Add your existing styles here */
    .payment-container {
      max-width: 600px;
      margin: 30px auto;
      padding: 30px;
      background: white;
      border-radius: 15px;
      box-shadow: 0 5px 15px rgba(0,0,0,0.1);
      border: 2px solid #f0f0f0;
    }

    .payment-methods {
      margin: 20px 0;
    }

    .payment-method {
      display: flex;
      align-items: center;
      padding: 15px;
      border: 2px solid #f0f0f0;
      border-radius: 10px;
      margin-bottom: 15px;
      cursor: pointer;
      transition: all 0.3s;
    }

    .payment-method:hover {
      border-color: #4cc9f0;
    }

    .payment-method.selected {
      border-color: #4cc9f0;
      background-color: #f0f7ff;
    }

    .payment-method input {
      margin-right: 15px;
    }

    .payment-icon {
      font-size: 30px;
      margin-right: 15px;
      color: #666;
    }

    .payment-details {
      display: none;
      padding: 20px;
      background-color: #f9f9f9;
      border-radius: 10px;
      margin-top: 20px;
    }

    .payment-details.active {
      display: block;
    }

    .form-group {
      margin-bottom: 15px;
    }

    .form-group label {
      display: block;
      margin-bottom: 8px;
      color: #666;
      font-weight: bold;
    }

    .form-control {
      width: 100%;
      padding: 12px 15px;
      border: 2px solid #ddd;
      border-radius: 10px;
      font-size: 16px;
    }

    .form-row {
      display: flex;
      gap: 15px;
    }

    .form-row .form-group {
      flex: 1;
    }

    .pay-now-btn {
      background-color: #4cc9f0;
      color: white;
      border: none;
      padding: 15px;
      border-radius: 10px;
      font-size: 18px;
      font-weight: bold;
      width: 100%;
      margin-top: 20px;
      cursor: pointer;
      transition: background-color 0.3s;
    }

    .pay-now-btn:hover {
      background-color: #3aa8cc;
    }
  </style>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
</head>
<body>
<!-- Your existing header -->

<div class="payment-container">
  <h1>Complete Your Purchase</h1>
  <p>Order Total: <span id="order-total">$0.00</span></p>

  <div class="payment-methods">
    <div class="payment-method selected" data-method="credit">
      <input type="radio" name="payment-method" checked>
      <div class="payment-icon">
        <i class="far fa-credit-card"></i>
      </div>
      <div>
        <h3>Credit/Debit Card</h3>
        <p>Pay with Visa, Mastercard, or other cards</p>
      </div>
    </div>

    <div class="payment-method" data-method="paypal">
      <input type="radio" name="payment-method">
      <div class="payment-icon">
        <i class="fab fa-paypal"></i>
      </div>
      <div>
        <h3>PayPal</h3>
        <p>Pay with your PayPal account</p>
      </div>
    </div>
  </div>

  <div class="payment-details active" id="credit-details">
    <form id="payment-form">
      <div class="form-group">
        <label for="card-number">Card Number</label>
        <input type="text" id="card-number" class="form-control" placeholder="1234 5678 9012 3456">
      </div>

      <div class="form-group">
        <label for="card-name">Name on Card</label>
        <input type="text" id="card-name" class="form-control" placeholder="John Doe">
      </div>

      <div class="form-row">
        <div class="form-group">
          <label for="card-expiry">Expiry Date</label>
          <input type="text" id="card-expiry" class="form-control" placeholder="MM/YY">
        </div>

        <div class="form-group">
          <label for="card-cvc">CVC</label>
          <input type="text" id="card-cvc" class="form-control" placeholder="123">
        </div>
      </div>

      <button type="submit" class="pay-now-btn">Pay Now</button>
    </form>
  </div>

  <div class="payment-details" id="paypal-details">
    <p>You will be redirected to PayPal to complete your payment.</p>
    <button class="pay-now-btn" id="paypal-btn">
      <i class="fab fa-paypal"></i> Continue with PayPal
    </button>
  </div>
</div>

<!-- Your existing footer -->

<script>
  // Get order total from URL parameter
  const urlParams = new URLSearchParams(window.location.search);
  const orderTotal = urlParams.get('total') || '$0.00';
  document.getElementById('order-total').textContent = orderTotal;

  // Payment method selection
  const paymentMethods = document.querySelectorAll('.payment-method');
  const paymentDetails = document.querySelectorAll('.payment-details');

  paymentMethods.forEach(method => {
    method.addEventListener('click', function() {
      // Remove selected class from all methods
      paymentMethods.forEach(m => m.classList.remove('selected'));
      // Add to clicked method
      this.classList.add('selected');

      // Update radio button
      this.querySelector('input').checked = true;

      // Show corresponding payment details
      const methodType = this.getAttribute('data-method');
      paymentDetails.forEach(detail => {
        detail.classList.remove('active');
      });
      document.getElementById(methodType + '-details').classList.add('active');
    });
  });

  // Form submission
  document.getElementById('payment-form').addEventListener('submit', function(e) {
    e.preventDefault();

    // In a real app, you would:
    // 1. Validate the form
    // 2. Process payment with Stripe or other processor
    // 3. Redirect to order confirmation

    // For demo purposes:
    alert('Payment processing would happen here!');
    // window.location.href = '/order-confirmation.html';
  });

  // PayPal button
  document.getElementById('paypal-btn').addEventListener('click', function() {
    // In a real app, you would:
    // 1. Initiate PayPal payment
    // 2. Redirect to PayPal
    // 3. Then to order confirmation

    // For demo purposes:
    alert('Redirecting to PayPal...');
    // window.location.href = 'https://www.paypal.com/checkoutnow';
  });
</script>
</body>
</html>