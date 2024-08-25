document.addEventListener('DOMContentLoaded', function () {
    const doctorSelect = document.getElementById('doctor');
    const appointmentsContainer = document.getElementById('appointments');

    const appointmentsData = {
        doctor1: [
            { date: '2024-08-20', time: '10:00 AM', patient: 'John Doe', slot: 'A1' },
            { date: '2024-08-21', time: '11:00 AM', patient: 'Jane Smith', slot: 'B2' }
        ],
        doctor2: [
            { date: '2024-08-22', time: '12:00 PM', patient: 'Alice Johnson', slot: 'C3' },
            { date: '2024-08-23', time: '02:00 PM', patient: 'Bob Brown', slot: 'D4' }
        ],
        doctor3: [
            { date: '2024-08-24', time: '03:00 PM', patient: 'Charlie Davis', slot: 'E5' },
            { date: '2024-08-25', time: '04:00 PM', patient: 'Diana Evans', slot: 'F6' }
        ]
    };

    doctorSelect.addEventListener('change', function () {
        const selectedDoctor = doctorSelect.value;
        const appointments = appointmentsData[selectedDoctor] || [];

        // Clear previous appointments
        appointmentsContainer.innerHTML = '';

        if (appointments.length > 0) {
            appointments.forEach(appointment => {
                const appointmentCard = document.createElement('div');
                appointmentCard.className = 'card mb-3';

                appointmentCard.innerHTML = `
                    <div class="card-body">
                        <h5 class="card-title">Patient: ${appointment.patient}</h5>
                        <p class="card-text">Date: ${appointment.date}</p>
                        <p class="card-text">Time: ${appointment.time}</p>
                        <p class="card-text">Slot: ${appointment.slot}</p>
                        <button class="btn btn-danger">Cancel Appointment</button>
                    </div>
                `;

                appointmentsContainer.appendChild(appointmentCard);
            });
        } else {
            appointmentsContainer.innerHTML = '<p>No appointments available for this doctor.</p>';
        }
    });
});
