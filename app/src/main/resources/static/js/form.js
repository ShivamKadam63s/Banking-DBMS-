document.getElementById('registrationForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const formData = new FormData(this);
    const jsonData = {};
    formData.forEach((value, key) => {
        if (key === 'aadhar_id') {
            jsonData[key] = parseInt(value);
        } else if (key === 'dob') {
            jsonData['DOB'] = value;
        } else {
            jsonData[key] = value;
        }
    });

    console.log('Sending data:', JSON.stringify(jsonData));
    fetch('/customer/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(jsonData)
    })
    .then(response => {
        console.log('Response status:', response.status); 
        return response.text().then(text => {
            console.log('Response body:', text);
            if (response.ok) {
                return JSON.parse(text);
            } else {
                throw new Error(`Registration failed: ${text}`);
            }
        });
    })
    .then(data => {
        console.log('Registration successful:', data);
        alert("Successfully registered.");
        sessionStorage.setItem("customerDetails", JSON.stringify(data));
        window.location.href = "homepage.html";
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error registering user: ' + error.message);
    });
});