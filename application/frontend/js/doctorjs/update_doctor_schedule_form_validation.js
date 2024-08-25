document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('updateScheduleForm');
    const currentScheduleTextarea = document.getElementById('current-schedule');

    // Dummy data for current schedule
    const today = new Date().toISOString().split('T')[0];
    const dummySchedule = `Today's Date: ${today}\nUpdated At: 09:00 AM\nStart Time: 10:00 AM\nEnd Time: 01:00 PM`;
    
    // Display dummy data in the textarea
    currentScheduleTextarea.value = dummySchedule;

    // Form validation
    form.addEventListener('submit', function (event) {
        const newDate = document.getElementById('new-date').value;
        const newTime = document.getElementById('new-time').value;

        let hasError = false;

        // Validate new date
        if (!newDate) {
            alert('Please select a new date.');
            hasError = true;
        }

        // Validate new time
        if (!newTime) {
            alert('Please select a new time.');
            hasError = true;
        }

        if (hasError) {
            event.preventDefault(); // Prevent form submission if there are errors
        }
    });
});
