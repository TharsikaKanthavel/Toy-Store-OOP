<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us - Toy Wonderland</title>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/about.css">
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
                <li><a href="${pageContext.request.contextPath}/about.jsp" class="active">About</a></li>
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
    <div class="about-hero">
        <div class="about-hero-content">
            <h1>Our Story of Sparking Joy</h1>
            <p>For over 15 years, we've been bringing smiles to children's faces with our carefully selected toys and games.</p>
        </div>
    </div>

    <div class="about-section">
        <h2 class="section-title">Who We Are</h2>
        <img src="https://example.com/store-front.jpg" alt="Toy Wonderland Store Front" class="about-image">
        <p class="about-text">Toy Wonderland was founded in 2008 by childhood friends Sarah and Michael, who shared a passion for play and its importance in child development. What started as a small neighborhood toy shop has grown into a beloved destination for families seeking quality toys that inspire creativity and learning.</p>
        <p class="about-text">We carefully curate our collection to include toys that are not only fun but also educational, safe, and built to last. Every item in our store is hand-selected by our team of toy experts who test each product for durability, safety, and most importantly - fun factor!</p>
    </div>

    <div class="about-section">
        <h2 class="section-title">Our Team</h2>
        <p class="about-text">Our team of toy enthusiasts is here to help you find the perfect toys for your children. We're parents, educators, and big kids at heart who truly believe in the power of play.</p>

        <div class="team-grid">
            <div class="team-member">
                <img src="https://example.com/sarah.jpg" alt="Sarah Johnson" class="team-member-image">
                <div class="team-member-info">
                    <h3 class="team-member-name">Sarah Johnson</h3>
                    <p class="team-member-role">Co-Founder & CEO</p>
                    <p class="team-member-bio">With a background in early childhood education, Sarah ensures all our toys meet developmental needs.</p>
                    <div class="social-links">
                        <a href="#"><i class="fab fa-twitter"></i></a>
                        <a href="#"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>

            <div class="team-member">
                <img src="https://example.com/michael.jpg" alt="Michael Chen" class="team-member-image">
                <div class="team-member-info">
                    <h3 class="team-member-name">Michael Chen</h3>
                    <p class="team-member-role">Co-Founder & COO</p>
                    <p class="team-member-bio">Michael's engineering background helps us select only the most durable, well-designed toys.</p>
                    <div class="social-links">
                        <a href="#"><i class="fab fa-twitter"></i></a>
                        <a href="#"><i class="fab fa-linkedin-in"></i></a>
                    </div>
                </div>
            </div>

            <div class="team-member">
                <img src="https://example.com/emma.jpg" alt="Emma Rodriguez" class="team-member-image">
                <div class="team-member-info">
                    <h3 class="team-member-name">Emma Rodriguez</h3>
                    <p class="team-member-role">Toy Buyer</p>
                    <p class="team-member-bio">Emma travels the world to discover unique toys that spark imagination and creativity.</p>
                    <div class="social-links">
                        <a href="#"><i class="fab fa-instagram"></i></a>
                        <a href="#"><i class="fab fa-pinterest-p"></i></a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="about-section">
        <h2 class="section-title">Our Values</h2>
        <p class="about-text">At Toy Wonderland, we're guided by principles that ensure every child gets the best play experience possible.</p>

        <div class="values-grid">
            <div class="value-card">
                <div class="value-icon">
                    <i class="fas fa-child"></i>
                </div>
                <h3 class="value-title">Child-Centered</h3>
                <p class="value-description">We prioritize toys that align with children's developmental stages and interests, ensuring age-appropriate fun and learning.</p>
            </div>

            <div class="value-card">
                <div class="value-icon">
                    <i class="fas fa-shield-alt"></i>
                </div>
                <h3 class="value-title">Safety First</h3>
                <p class="value-description">All our toys meet or exceed international safety standards. We rigorously test every product before it reaches our shelves.</p>
            </div>

            <div class="value-card">
                <div class="value-icon">
                    <i class="fas fa-leaf"></i>
                </div>
                <h3 class="value-title">Eco-Friendly</h3>
                <p class="value-description">We're committed to sustainability, offering toys made from renewable, recycled, and non-toxic materials whenever possible.</p>
            </div>

            <div class="value-card">
                <div class="value-icon">
                    <i class="fas fa-heart"></i>
                </div>
                <h3 class="value-title">Community Focused</h3>
                <p class="value-description">We support local schools and charities, donating toys and hosting free play workshops for children in need.</p>
            </div>
        </div>
    </div>

    <div class="cta-section">
        <h2 class="cta-title">Ready to Explore Our Toy Wonderland?</h2>
        <p class="cta-text">Discover our hand-picked collection of toys that inspire creativity, learning, and hours of fun!</p>
        <a href="/products" class="btn">Shop Now</a>
    </div>
</main>

<footer>
    <div class="footer-container">
        <div class="footer-links">
            <a href="${pageContext.request.contextPath}/about.jsp">About Us</a>
            <a href="${pageContext.request.contextPath}/contactpage.jsp">Contact</a>
            <a href="/faq">FAQ</a>
            <a href="${pageContext.request.contextPath}/ordertracking.jsp">Shipping & Returns</a>
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