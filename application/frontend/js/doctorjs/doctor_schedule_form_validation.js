document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('scheduleForm');
    const startTimeInput = document.getElementById('startTime');
    const endTimeInput = document.getElementById('endTime');
    const errorContainer = document.createElement('div'); // Create a div for displaying errors
    form.prepend(errorContainer); // Add error container at the top of the form

    form.addEventListener('submit', function (event) {
        errorContainer.innerHTML = ''; // Clear any existing errors
        let hasError = false;

        // Validate start time
        if (startTimeInput.value.trim() === '') {
            displayError('Start time is required.');
            hasError = true;
        }

        // Validate end time
        if (endTimeInput.value.trim() === '') {
            displayError('End time is required.');
            hasError = true;
        }

        // Check if end time is after start time
        if (startTimeInput.value && endTimeInput.value && startTimeInput.value >= endTimeInput.value) {
            displayError('End time must be after start time.');
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
