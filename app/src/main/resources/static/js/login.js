document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById('loginModal');
    const loginBtn = document.getElementById('loginBtn');
    const closeBtn = document.querySelector('.close-btn');
    const loginForm = document.getElementById('loginForm');
    const signupForm = document.getElementById('signupForm');
    const showSignupLink = document.getElementById('showSignup');
    const showLoginLink = document.getElementById('showLogin');
    const passwordToggles = document.querySelectorAll('.toggle-password');
    const loginInvalid = document.getElementById('loginForm-invalid');

    // Modal controls
    loginBtn.onclick = () => modal.style.display = 'block';
    closeBtn.onclick = () => modal.style.display = 'none';
    window.onclick = (e) => {
        if (e.target === modal) {
            modal.style.display = 'none';
            loginInvalid.style.display = 'none';
        }
    }

    // Form switching
    showSignupLink.onclick = (e) => {
        e.preventDefault();
        window.location.href = "form.html"
        
    }

    showLoginLink.onclick = (e) => {
        e.preventDefault();
        signupForm.classList.add('hidden');
        loginForm.classList.remove('hidden');
    }

    // Password visibility toggle
    passwordToggles.forEach(toggle => {
        toggle.onclick = () => {
            const input = toggle.previousElementSibling;
            input.type = input.type === 'password' ? 'text' : 'password';
            toggle.classList.toggle('uil-eye');
            toggle.classList.toggle('uil-eye-slash');
        }
    });

    // Form submissions
    loginForm.onsubmit = async (e) => {
        e.preventDefault();
        const email = loginForm.querySelector('input[type="email"]').value
        const password = loginForm.querySelector('input[type="password"]').value
        const response = await fetch("/customer/login", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify({email, password}),
        })    
        if (response.ok) {
            console.log("logged in");
            const data = await response.json();
            sessionStorage.setItem("customerDetails", JSON.stringify(data));
            window.location.href = '/homepage.html';
        }
        else {
            console.log("Wrong Email/Password")
            loginInvalid.style.display = 'block';
            //make a hidden css text down there and have this else unhide it.
        }
    }

    signupForm.onsubmit = async (e) => {
        e.preventDefault();
        // Add your signup logic here
    }
});