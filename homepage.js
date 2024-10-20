// Wait for DOM to load before running JavaScript
document.addEventListener('DOMContentLoaded', () => {
    // Get form element
    const loginForm = document.getElementById('loginForm');

    // Attach submit event listener to the form
    loginForm.addEventListener('submit', function(event) {
        // Prevent the default form submission
        event.preventDefault();

        // Get username and password input elements
        const usernameInput = document.getElementById('username');
        const passwordInput = document.getElementById('password');

        // Get error message elements
        const usernameError = document.getElementById('usernameError');
        const passwordError = document.getElementById('passwordError');

        // Clear previous errors
        usernameError.style.display = 'none';
        passwordError.style.display = 'none';

        let isValid = true;

        // Check if username is empty
        if (usernameInput.value.trim() === '') {
            usernameError.textContent = 'Username is required';
            usernameError.style.display = 'block';
            isValid = false;
        }

        // Check if password is empty
        if (passwordInput.value.trim() === '') {
            passwordError.textContent = 'Password is required';
            passwordError.style.display = 'block';
            isValid = false;
        }

        // If all fields are valid, simulate a successful login
        if (isValid) {
            alert('Login successful!');  // Replace this with actual login logic (e.g., API call)
        }
    });
});
