document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('login-form');
    const username = document.getElementById('username');
    const password = document.getElementById('password');
    const errorContainer = document.createElement('div'); // Create a div for displaying errors
    form.prepend(errorContainer); // Add error container at the top of the form

    form.addEventListener('submit', function (event) {
        errorContainer.innerHTML = ''; // Clear any existing errors
        let hasError = false;

        // username validation
        if (username.value.trim() === '') {
            displayError('Username is required!');
            hasError = true;
        }

        //password validation
        if(!validatePassword(password.value)){
            displayError('Invalid password!');
            hasError = true;
        }
        
        if (hasError) {
            event.preventDefault(); // Prevent form submission if there are errors
        }
    });

    function validatePassword(password) {
        const minLength = 8;
        const hasUpperCase = /[A-Z]/.test(password);
        const hasLowerCase = /[a-z]/.test(password);
        const hasDigit = /\d/.test(password);
    
        if (password.length < minLength) {
            displayError(`Password must be at least ${minLength} characters long.`);
            return false; 
        }
        if (!hasUpperCase) {
            displayError("Password must contain at least one uppercase letter.");
            return false; 
        }
        if (!hasLowerCase) {
            displayError("Password must contain at least one lowercase letter.");
            return false;
        }
        if (!hasDigit) {
            displayError("Password must contain at least one digit.");
            return false;
        }
    
        return true; 
    }

    function displayError(message) {
        const errorItem = document.createElement('div');
        errorItem.className = 'alert alert-danger';
        errorItem.textContent = message;
        errorContainer.appendChild(errorItem);
    }
});
