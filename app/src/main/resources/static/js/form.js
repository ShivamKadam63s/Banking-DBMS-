document.getElementById('registrationForm').addEventListener('submit', function(event) {
    event.preventDefault(); // Prevent default form submission

    const formData = new FormData(this);

    fetch('/api/register', {
        method: 'POST',
        body: formData
    })
    .then(response => response.json())
    .then(data => {
        alert('User registered successfully!');
        // Optionally, reset the form or redirect
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error registering user!');
    });
});
