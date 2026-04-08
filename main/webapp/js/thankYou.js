document.addEventListener('DOMContentLoaded', function() {
    // Create confetti
    createConfetti();

    // Animate progress bar
    setTimeout(() => {
        document.querySelector('.progress-fill').style.width = '100%';
    }, 100);

    // Add floating toys
    createFloatingToys();
});

function createConfetti() {
    const colors = ['#FF6B6B', '#4ECDC4', '#FFD166', '#FF8E8E', '#A5FFD6'];
    const container = document.querySelector('.confetti-container');

    for (let i = 0; i < 100; i++) {
        const confetti = document.createElement('div');
        confetti.className = 'confetti';

        // Random properties
        const size = Math.random() * 10 + 5;
        const color = colors[Math.floor(Math.random() * colors.length)];
        const rotation = Math.random() * 360;
        const animationDuration = Math.random() * 3 + 2;
        const animationDelay = Math.random() * 5;

        // Position randomly on screen
        const posX = Math.random() * 100;
        const posY = Math.random() * 100 - 10;

        // Apply styles
        confetti.style.width = `${size}px`;
        confetti.style.height = `${size}px`;
        confetti.style.backgroundColor = color;
        confetti.style.left = `${posX}%`;
        confetti.style.top = `${posY}%`;
        confetti.style.transform = `rotate(${rotation}deg)`;
        confetti.style.animation = `fall ${animationDuration}s linear ${animationDelay}s infinite`;

        // Random shape
        if (Math.random() > 0.5) {
            confetti.style.borderRadius = '50%';
        } else {
            confetti.style.borderRadius = '0';
        }

        container.appendChild(confetti);
    }

    // Add CSS for confetti animation
    const style = document.createElement('style');
    style.textContent = `
        @keyframes fall {
            0% { transform: translateY(-10vh) rotate(0deg); opacity: 1; }
            100% { transform: translateY(110vh) rotate(360deg); opacity: 0; }
        }
        .confetti {
            position: absolute;
            z-index: 1;
        }
    `;
    document.head.appendChild(style);
}

function createFloatingToys() {
    // Additional floating toys can be added here if needed
}