acc_id1 = JSON.parse(sessionStorage.getItem("bankAccountDetails")).acc_id;
customerId = JSON.parse(sessionStorage.getItem("customerDetails")).aadhar_id;
sessionStorage.set
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
    fetch(`/bankaccount/balance/${acc_id1}`) // Adjust the path based on your endpoint
    .then(response => response.json())
    .then(data => {
        console.log(data)
        document.getElementById('currentBalance').textContent = data.acc_balance.toFixed(2);
        document.getElementById('balanceModal').style.display = "block";
    })
    .catch(error => console.error('Error fetching balance:', error));
}
function closeBalance() {
    document.getElementById('balanceModal').style.display = "none";
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
    console.log({ amount, duration, loanType });
    // Send data to server
    fetch('/loan/credit_forecast', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ amount, duration, loanType })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById("forecastNotification").textContent = `Final amount: Rs. ${Math.trunc(data.finalAmount,2)}  ROI: ${Math.trunc(data.ROI*100, 2)}%`;
    })
    .catch(error => console.error('Error:', error));
};

document.getElementById("obtainLoanForm").onsubmit = function(event) {
    event.preventDefault();
    const amount = parseFloat(document.getElementById("obtainAmount").value);
    const duration = parseInt(document.getElementById("obtainDuration").value);
    const loanType = document.querySelector('input[name="obtainLoanType"]:checked').value;
    
    
    // Send data to server
    fetch('/loan/obtain_loan', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ amount, duration, loanType, "customerId": customerId })
    })
    .then(response => response.json())
    .then(async data => {
        console.log(data)
        
        fetch('/bankaccount/deposit', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({acc_id: acc_id1 ,amount})
        })
        .then(response => response.json())
        .then(data => {
            console.log(data);
            
            document.getElementById('balance').textContent = data.acc_balance.toFixed(2);
            document.getElementById("obtainNotification").textContent = `Loan granted! Updated balance: $${data.acc_balance}`;
        })
        .catch(async error => console.error(`Error depositing: `, error));
        
        
    })
    .catch(error => console.error('Error:', error));
};
