document.addEventListener("DOMContentLoaded", () => {
    const 
    aadhar_id = JSON.parse(sessionStorage.getItem("customerDetails")).aadhar_id,
    accountList = document.getElementById("account-list");
    let load_shit = async () => {
        let accounts = await fetch(`/bankaccount/customer/data/${aadhar_id}`)
        .then(response => response.json());
        accounts.forEach(account => {
            let elem = (str) => document.createElement(str)
            let div = elem("div"); div.className = "account-box";
            
            var accountBox = accountList.appendChild(elem("li"))
                        .appendChild(div)
            accountBox.appendChild(elem("h2"))
            .textContent = `
            ${account.acc_type}`;
            accountBox.appendChild(elem("p"))
            .textContent = `
            Username: ${account.username}`;
            accountBox.appendChild(elem("p"))
            .textContent = `
            Acc. Balance: ${account.acc_balance}`;
            accountBox.appendChild(elem("p"))
            .textContent = `
            Transaction limit: ${account.transaction_limit}`;

            accountBox.onclick = async () => {
                data = await fetch(`/bankaccount/${account.acc_id}`)
                    .then(data => data.json());
                let bankAccount = JSON.stringify(data);
                sessionStorage.setItem("bankAccountDetails",bankAccount);
                destination = sessionStorage.getItem("destination");
                // sessionStorage.removeItem("destination"); caused errors when user did return back
                window.location.href = destination;}
            
            
        }); 
    };
    load_shit();

    
})