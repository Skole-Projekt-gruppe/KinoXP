document.addEventListener("DOMContentLoaded", () => {
    const calendar = document.getElementById("calendar");

    if (!calendar) {
        console.error("❌ Calendar element not found!");
        return;
    }

    // Hent vagtplan fra backend
    fetch("/api/schedule")
        .then(res => res.json())
        .then(data => {
            renderCalendar(data);
        })
        .catch(err => {
            console.error("Failed to load schedule:", err);
            calendar.innerHTML = "<p>Could not load staff schedule.</p>";
        });

    function renderCalendar(shifts) {
        calendar.innerHTML = "";

        const table = document.createElement("table");
        table.className = "calendar-table";

        const headerRow = document.createElement("tr");
        ["Name", "Role", "Date", "Start", "End"].forEach(h => {
            const th = document.createElement("th");
            th.textContent = h;
            headerRow.appendChild(th);
        });
        table.appendChild(headerRow);

        shifts.forEach(shift => {
            const row = document.createElement("tr");
            row.innerHTML = `
        <td>${shift.staff.name}</td>
        <td>${shift.staff.role}</td>
        <td>${new Date(shift.work_date).toLocaleDateString()}</td>
        <td>${shift.shift_start}</td>
        <td>${shift.shift_end}</td>
      `;
            table.appendChild(row);
        });

        calendar.appendChild(table);
    }
});
