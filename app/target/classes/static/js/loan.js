document.getElementById("form-open").onclick = function() {
    openProfile(); // Fetch and display the profile data
};

document.getElementById("checkBalance").onclick = function() {
    fetchBalance(); // Fetch the balance from the server
};

document.getElementById("closeForecastModal").onclick = function() {
    document.getElementById("creditForecastModal").style.display = "none";
};

document.getElementById("closeObtainLoanModal").onclick = function() {
    document.getElementById("obtainLoanModal").style.display = "none";
};

window.onclick = function(event) {
    if (event.target == document.getElementById("creditForecastModal") || event.target == document.getElementById("obtainLoanModal")) {
        document.getElementById("creditForecastModal").style.display = "none";
        document.getElementById("obtainLoanModal").style.display = "none";
    }
};

function openProfile() {
    fetch('/profile/john_doe') // Adjust the path based on your endpoint
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        // Populate user data in the profile modal
        document.getElementById('fullName').textContent = data.fullName;
        document.getElementById('username').textContent = data.username;
        document.getElementById('acc_id').textContent = data.accountId;
        document.getElementById('acc_balance').textContent = data.accountBalance.toFixed(2);
        document.getElementById('aadharId').textContent = data.aadharId;
        document.getElementById('mobileNo').textContent = data.mobileNo;
        document.getElementById('address').textContent = data.address;
        document.getElementById('gender').textContent = data.gender;
        document.getElementById("profileModal").style.display = "block";
    })
    .catch(error => {
        console.error('Error fetching user data:', error);
    });
}

function fetchBalance() {
    fetch('/api/balance') // Adjust the path based on your endpoint
    .then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok ' + response.statusText);
        }
        return response.json();
    })
    .then(data => {
        document.getElementById("balance").textContent = data.balance.toFixed(2);
    })
    .catch(error => {
        console.error('Error fetching balance:', error);
    });
}

function openLoanForecastModal() {
    document.getElementById("creditForecastModal").style.display = "block";
}

function openObtainLoanModal() {
    document.getElementById("obtainLoanModal").style.display = "block";
}

document.getElementById("forecastForm").onsubmit = function(event) {
    event.preventDefault();
    const amount = parseFloat(document.getElementById("forecastAmount").value);
    const duration = parseInt(document.getElementById("forecastDuration").value);
    const loanType = document.querySelector('input[name="loanType"]:checked').value;

    // Send data to server
    fetch('/api/credit_forecast', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ amount, duration, loanType })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("forecastNotification").textContent = `Final amount with ROI: $${data.finalAmount}`;
    })
    .catch(error => console.error('Error:', error));
};

document.getElementById("obtainLoanForm").onsubmit = function(event) {
    event.preventDefault();
    const amount = parseFloat(document.getElementById("obtainAmount").value);
    const duration = parseInt(document.getElementById("obtainDuration").value);
    const loanType = document.querySelector('input[name="obtainLoanType"]:checked').value;

    // Send data to server
    fetch('/api/obtain_loan', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ amount, duration, loanType })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("obtainNotification").textContent = `Loan granted! Updated balance: $${data.updatedBalance}`;
        document.getElementById("balance").textContent = data.updatedBalance.toFixed(2);
    })
    .catch(error => console.error('Error:', error));
};
