
document.getElementById('form-open').addEventListener('click', openProfile);
let bankAccountDetails, acc_id1;
try {
bankAccountDetails = JSON.parse(sessionStorage.getItem("bankAccountDetails"))
acc_id1 = bankAccountDetails.acc_id;
} catch (error) {}
console.log(acc_id1);

function openProfile() {
    fetch('/profile/john_doe') // Adjust the path based on your endpoint
    .then(response => response.json())
    .then(data => {
        document.getElementById('fullName').textContent = data.fullName;
        document.getElementById('username').textContent = data.username;
        document.getElementById('acc_id').textContent = data.accountId;
        document.getElementById('acc_balance').textContent = data.accountBalance.toFixed(2);
        document.getElementById('aadharId').textContent = data.aadharId;
        document.getElementById('mobileNo').textContent = data.mobileNo;
        document.getElementById('address').textContent = data.address;
        document.getElementById('gender').textContent = data.gender;
        document.getElementById('profileModal').style.display = "block";
    })
    .catch(error => {
        console.error('Error fetching user data:', error);
    });
}
//^^Not doing this


function closeProfile() {
    document.getElementById('profileModal').style.display = "none";
}
//^^Not doing this


function openDeposit() {
    document.getElementById('depositModal').style.display = "block";
}

function closeDeposit() {
    document.getElementById('depositModal').style.display = "none";
}

function deposit() {
    const amount = parseFloat(document.getElementById('depositAmount').value);
    fetch('/bankaccount/deposit', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({acc_id: acc_id1 ,amount})
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        
        document.getElementById('acc_balance').textContent = data.acc_balance.toFixed(2);
        closeDeposit();
    })
    .catch(async error => console.error(`Error depositing: `, error));
}

function openWithdraw() {
    document.getElementById('withdrawModal').style.display = "block";
}

function closeWithdraw() {
    document.getElementById('withdrawModal').style.display = "none";
}

function withdraw() {
    const amount = parseFloat(document.getElementById('withdrawAmount').value);
    fetch('/bankaccount/withdraw', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({acc_id: acc_id1 ,amount})
    })
    .then(response => response.json())
    .then(data => {
        console.log(data);
        
        document.getElementById('acc_balance').textContent = data.acc_balance.toFixed(2);
        closeDeposit();
    })
    .catch(async error => console.error(`Error depositing: `, error));
}

function checkBalance() {
    fetch(`/bankaccount/balance/${acc_id1}`) // Adjust the path based on your endpoint
    .then(response => response.json())
    .then(data => {
        document.getElementById('currentBalance').textContent = data.acc_balance.toFixed(2);
        document.getElementById('balanceModal').style.display = "block";
    })
    .catch(error => console.error('Error fetching balance:', error));
}
function openBalance() {
    document.getElementById('balanceModal').style.display = "block";
}

function closeBalance() {
    document.getElementById('balanceModal').style.display = "none";
}

function openTransfer() {
    document.getElementById('transferModal').style.display = "block";
}

function closeTransfer() {
    document.getElementById('transferModal').style.display = "none";
}

function transfer() {
    const accId = document.getElementById('transferAccId').value;
    const amount = parseFloat(document.getElementById('transferAmount').value);
    fetch('/transfer', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ accId, amount })
    })
    .then(response => response.json())
    .then(data => {
        document.getElementById('acc_balance').textContent = data.newBalance.toFixed(2);
        closeTransfer();
    })
    .catch(error => console.error('Error transferring:', error));
}

function viewTransactions() {
    fetch('/transactions') // Adjust the path based on your endpoint
    .then(response => response.json())
    .then(data => {
        const historyContainer = document.getElementById('transactionHistory');
        historyContainer.innerHTML = '';
        data.transactions.forEach(transaction => {
            const transactionItem = document.createElement('p');
            transactionItem.textContent = `${transaction.type}: $${transaction.amount}`;
            historyContainer.appendChild(transactionItem);
        });
        document.getElementById('transactionModal').style.display = "block";
    })
    .catch(error => console.error('Error fetching transactions:', error));
}

function closeTransactions() {
    document.getElementById('transactionModal').style.display = "none";
}

window.onclick = function(event) {
    const modals = document.querySelectorAll('.modal');
    modals.forEach(modal => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
};
