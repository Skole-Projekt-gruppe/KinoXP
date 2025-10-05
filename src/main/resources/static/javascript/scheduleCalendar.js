document.addEventListener("DOMContentLoaded", () => {
    const calendar = document.getElementById("calendar");

    fetch("/api/schedule")
        .then(res => res.json())
        .then(data => renderWeekCalendar(data))
        .catch(err => {
            console.error("Error loading schedule:", err);
            calendar.innerHTML = "<p>Could not load staff schedule.</p>";
        });

    function renderWeekCalendar(schedules) {
        // Find alle unikke medarbejdere
        const staffNames = [...new Set(schedules.map(s => s.staff.name))];

        // Find alle datoer (for en uge)
        const dates = [...new Set(schedules.map(s => s.work_date))].sort();
        const weekDays = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];

        // Lav tabel
        const table = document.createElement("table");
        table.className = "week-calendar";

        // Header (ugedage)
        const headerRow = document.createElement("tr");
        const nameHeader = document.createElement("th");
        nameHeader.textContent = "Employee";
        headerRow.appendChild(nameHeader);

        weekDays.forEach(day => {
            const th = document.createElement("th");
            th.textContent = day;
            headerRow.appendChild(th);
        });
        table.appendChild(headerRow);

        // Rækker pr. medarbejder
        staffNames.forEach(name => {
            const staffRow = document.createElement("tr");
            const nameCell = document.createElement("td");
            nameCell.textContent = name;
            staffRow.appendChild(nameCell);

            // En celle pr. dag
            weekDays.forEach((day, i) => {
                const td = document.createElement("td");
                const shift = schedules.find(s => {
                    const workDate = new Date(s.work_date);
                    return (
                        s.staff.name === name &&
                        workDate.getDay() === ((i + 1) % 7) // Mandag = 1, Søndag = 0
                    );
                });

                if (shift) {
                    td.innerHTML = `
            <div class="shift">
              <span>${shift.staff.role}</span><br>
              <span>${shift.shift_start} - ${shift.shift_end}</span>
            </div>
          `;
                } else {
                    td.textContent = "-";
                }

                staffRow.appendChild(td);
            });

            table.appendChild(staffRow);
        });

        calendar.innerHTML = "";
        calendar.appendChild(table);
    }
});
