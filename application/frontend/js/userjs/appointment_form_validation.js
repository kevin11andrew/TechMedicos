document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('appointmentForm');
    const doctorSelect = document.getElementById('doctor');
    const dateInput = document.getElementById('date');
    const timeInput = document.getElementById('time');
    const errorContainer = document.createElement('div'); // Create a div for displaying errors
    form.prepend(errorContainer); // Add error container at the top of the form

    form.addEventListener('submit', function (event) {
        errorContainer.innerHTML = ''; // Clear any existing errors
        let hasError = false;

        // Validate doctor selection
        if (doctorSelect.value === '') {
            displayError('Please select a doctor.');
            hasError = true;
        }

        // Validate date selection
        if (dateInput.value.trim() === '') {
            displayError('Please select a date.');
            hasError = true;
        }

        // Validate time selection
        if (timeInput.value.trim() === '') {
            displayError('Please select a time.');
            hasError = true;
        }

        if (hasError) {
            event.preventDefault(); // Prevent form submission if there are errors
        }
    });

    function displayError(message) {
        const errorItem = document.createElement('div');
        errorItem.className = 'alert alert-danger';
        errorItem.textContent = message;
        errorContainer.appendChild(errorItem);
    }
});
