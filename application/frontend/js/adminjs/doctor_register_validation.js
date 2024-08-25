document.addEventListener('DOMContentLoaded', function () {
    const form = document.querySelector('form');
    const doctorname = document.getElementById('doctor-name');
    const speciality = document.getElementById('speciality');
    const contact = document.getElementById('contact');
    const experience = document.getElementById('years-of-experience');

    const errorContainer = document.createElement('div'); // Create a div for displaying errors
    form.prepend(errorContainer); // Add error container at the top of the form

    form.addEventListener('submit', function (event) {
        errorContainer.innerHTML = ''; // Clear any existing errors
        let hasError = false;

        // name validation
        if (doctorname.value.trim() === '') {
            displayError('Username is required!');
            hasError = true;
        }

        // speciality validation
        if(speciality.value === ''){
            displayError('Speciality is required!');
            hasError = true;
        }
        
        if(!validateContact(contact.value)){
            displayError('Please enter a valid 10-digit phone number.');
            hasError = true;
        }
        // validate years of experience
        if(experience.value === '' || experience.value <= 0 || isNaN(experience.value) ){
            displayError('Please enter valid experience');
            hasError = true;
        }

        if (hasError) {
            event.preventDefault(); // Prevent form submission if there are errors
        }
    });

    function validateContact(contact) {
        const reg = /^\d{10}$/;
        return reg.test(String(contact));
    }

    function displayError(message) {
        const errorItem = document.createElement('div');
        errorItem.className = 'alert alert-danger';
        errorItem.textContent = message;
        errorContainer.appendChild(errorItem);
    }
});
