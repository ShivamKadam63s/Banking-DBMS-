document.addEventListener('DOMContentLoaded', () => {
    
    const formOpenBtn = document.querySelector("#form-open"),
        home = document.querySelector(".home"),
        formContainer = document.querySelector(".form_container"),
        formCloseBtn = document.querySelector(".form_close"),
        signupBtn = document.querySelector("#signup"),
        loginBtn = document.querySelector("#login"),
        pwShowHide = document.querySelectorAll(".pw_hide"),
        customerDetailsModal = document.getElementById("customerDetails-modal"),
        customerDetailsModalOpenBtn = document.getElementById("customerDetailsModal-open");
    
    customerDetailsModalOpenBtn.style.display = 'none';
    
    if (sessionStorage.getItem("customerDetails") != null) {
        formOpenBtn.style.display = 'none';
        const customer = JSON.parse(sessionStorage.getItem("customerDetails"));
        customerDetailsModalOpenBtn.style.display = 'block';
        document.getElementById('modal-inner-content').innerHTML = `
            <p>Name: ${customer.fname + ' ' + customer.mname + ' ' + customer.lname}</p>
            <p>Aadhar ID: ${customer.aadhar_id}</p>
        `;
    }

    formOpenBtn.addEventListener("click", () => home.classList.add("show"));
    formCloseBtn.addEventListener("click", () => home.classList.remove("show"));

    pwShowHide.forEach((icon) => {
        icon.addEventListener("click", () => {
            let getPwInput = icon.parentElement.querySelector("input");
            if (getPwInput.type === "password") {
                getPwInput.type = "text";
                icon.classList.replace("uil-eye-slash", "uil-eye");
            } else {
                getPwInput.type = "password";
                icon.classList.replace("uil-eye", "uil-eye-slash");
            }
        });
    });

    signupBtn.addEventListener("click", (e) => {
        e.preventDefault();
        formContainer.classList.add("active");
    });

    loginBtn.addEventListener("click", (e) => {
        e.preventDefault();
        formContainer.classList.remove("active");
    });

    // Handle login form submission
    const loginForm = document.querySelector(".login_form form");
    loginForm.addEventListener("submit", async (e) => {
        e.preventDefault();

        const email = loginForm.querySelector('input[type="email"]').value;
        const password = loginForm.querySelector('input[type="password"]').value;

        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({ email, password })
            });

            const result = await response.text();
            if (response.ok) {
                alert(result); // Login successful
                // Redirect or perform other actions
            } else {
                alert(result); // Invalid credentials
            }
        } catch (error) {
            console.error('Error:', error);
        }
    });

    customerDetailsModalOpenBtn.onclick = () => {

        customerDetailsModal.style.display = 'block';
    
        customerDetailsModal.style.visibility = 'visible'; // Add this line
    
    }
    customerDetailsModal.querySelector('.modal-content').innerHTML += `

    <button id="closeModal">Close</button>

    `;


    document.getElementById('closeModal').onclick = () => {

        customerDetailsModal.style.display = 'none';

    }
});