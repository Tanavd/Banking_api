// script.js

document.addEventListener('DOMContentLoaded', function () {
    const addAccountForm = document.getElementById('addAccountForm');
    const depositForm = document.getElementById('depositForm');
    const withdrawForm = document.getElementById('withdrawForm');
    const deleteAccountForm = document.getElementById('deleteAccountForm');
    const fetchAccountsButton = document.getElementById('fetchAccounts');
    const accountsList = document.getElementById('accountsList');
    
    // Handle Add Account Form submission
    addAccountForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const holderName = document.getElementById('holderName').value;
        const initialBalance = parseFloat(document.getElementById('initialBalance').value);

        console.log("Form Values - Holder Name:", holderName, "Initial Balance:", initialBalance);

        try {
            const response = await fetch('/api/accounts/add', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ accountholdername: holderName, balance: initialBalance }),
            });

            if (!response.ok) {
                const errorData = await response.json();
                console.error('Error adding account:', errorData);
                alert('Error adding account: ' + (errorData.message || 'Unknown error'));
                return;
            }

            const data = await response.json();
            console.log("Response Data:", data);
            alert('Account added successfully');
        } catch (error) {
            console.error('Error adding account:', error);
        }
    });


    // Handle Deposit Form submission
    depositForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const depositAmount = parseFloat(document.getElementById('depositAmount').value);
        const accountId = parseInt(prompt('Enter account ID to deposit into:'));

        try {
            const response = await fetch(`/api/accounts/${accountId}/deposit`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ amount: depositAmount }),
            });
            const data = await response.json();
            alert('Deposit successful');
        } catch (error) {
            console.error('Error depositing:', error);
        }
    });

    // Handle Withdraw Form submission
    withdrawForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const withdrawAmount = parseFloat(document.getElementById('withdrawAmount').value);
        const accountId = parseInt(prompt('Enter account ID to withdraw from:'));

        try {
            const response = await fetch(`/api/accounts/${accountId}/withdraw`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({ amount: withdrawAmount }),
            });
            const data = await response.json();
            alert('Withdrawal successful');
        } catch (error) {
            console.error('Error withdrawing:', error);
        }
    });

    // Handle Delete Account Form submission
    deleteAccountForm.addEventListener('submit', async (e) => {
        e.preventDefault();
        const accountId = parseInt(document.getElementById('deleteAccountId').value);

        try {
            const response = await fetch(`/api/accounts/${accountId}/delete`, {
                method: 'DELETE',
            });
            if (response.ok) {
                alert('Account deleted successfully');
            } else {
                alert('Error deleting account');
            }
        } catch (error) {
            console.error('Error deleting account:', error);
        }
    });

    // Fetch Accounts Button functionality
    fetchAccountsButton.addEventListener('click', async () => {
        try {
            const response = await fetch('/api/accounts/get');
            const accounts = await response.json();
            accountsList.innerHTML = '';
            accounts.forEach(account => {
                const li = document.createElement('li');
                li.textContent = `ID: ${account.id}, Holder: ${account.accountholdername}, Balance: $${account.balance}`;
                accountsList.appendChild(li);
            });
        } catch (error) {
            console.error('Error fetching accounts:', error);
        }
    });

    // Increase deposit amount by 100
    const increaseAmountButton = document.getElementById('increaseAmount');
    increaseAmountButton.addEventListener('click', () => {
        const depositAmountInput = document.getElementById('depositAmount');
        let currentAmount = parseFloat(depositAmountInput.value) || 0;
        depositAmountInput.value = currentAmount + 100;
    });
});
