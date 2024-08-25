document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const nameInput = document.getElementById('name');
    const ageInput = document.getElementById('age');
    const genderSelect = document.getElementById('gender');
    const contactInput = document.getElementById('contact');
    const errorContainer = document.createElement('div'); // Create a div for displaying errors
    form.prepend(errorContainer); // Add error container at the top of the form

    form.addEventListener('submit', function (event) {
        errorContainer.innerHTML = ''; // Clear any existing errors
        let hasError = false;

        // Validate name
        if (nameInput.value.trim() === '') {
            displayError('Name is required.');
            hasError = true;
        }

        // Validate age
        if (ageInput.value.trim() === '' || isNaN(ageInput.value) || parseInt(ageInput.value) <= 0) {
            displayError('Please enter a valid age.');
            hasError = true;
        }

        // Validate gender
        if (genderSelect.value === '') {
            displayError('Please select a gender.');
            hasError = true;
        }

        // Validate contact (assuming a 10-digit phone number)
        if (!validatePhone(contactInput.value)) {
            displayError('Please enter a valid 10-digit phone number.');
            hasError = true;
        }

        if (hasError) {
            event.preventDefault(); // Prevent form submission if there are errors
        }
    });

    function validatePhone(phone) {
        const re = /^\d{10}$/;
        return re.test(String(phone));
    }

    function displayError(message) {
        const errorItem = document.createElement('div');
        errorItem.className = 'alert alert-danger';
        errorItem.textContent = message;
        errorContainer.appendChild(errorItem);
    }
});
