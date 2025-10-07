window.addEventListener('load', initApp);

const BASE_URL = "http://localhost:8080";
const STAFF_URL = `${BASE_URL}/staff`;

async function initApp() {
    await showStaff();
}

async function fetchStaff() {
    try {
        const response = await fetch(STAFF_URL);
        if (!response.ok) {
            throw new Error("Could not fetch staff");
        }

        return await response.json();
    } catch (error) {
        console.error(error);
    }
}

async function showStaff() {
    const staff = await fetchStaff();
    const tbody = document.getElementById("staff-tbody");

    // Clear any existing rows
    tbody.innerHTML = "";

    for (const member of staff) {
        const row = document.createElement("tr");

        // === Name ===
        const nameCell = document.createElement("td");
        nameCell.textContent = member.name;

        // === Role ===
        const roleCell = document.createElement("td");
        roleCell.textContent = member.role;

        // === Schedule ===
        const scheduleCell = document.createElement("td");
        try {
            const response = await fetch(`${STAFF_URL}/${member.id}/schedule`);
            if (!response.ok) throw new Error("Failed to fetch schedule");
            const schedule = await response.json();

            if (schedule.length > 0) {
                scheduleCell.innerHTML = schedule
                    .map(s => `${new Date(s.date).toLocaleDateString()} (${s.startTime} - ${s.endTime})`)
                    .join("<br>");
            } else {
                scheduleCell.textContent = "No shifts assigned";
            }
        } catch (err) {
            scheduleCell.textContent = "Error loading schedule";
            console.error(err);
        }

        // === Add Shift Button ===
        const actionsCell = document.createElement("td");
        const button = document.createElement("button");
        button.textContent = "Add";
        button.classList.add("add-shift-button");

        button.addEventListener('click', () => {
            alert(`Add shift for ${member.name}`);
        });

        actionsCell.appendChild(button);

        // === Add all cells to row ===
        row.appendChild(nameCell);
        row.appendChild(roleCell);
        row.appendChild(scheduleCell);
        row.appendChild(actionsCell);

        tbody.appendChild(row);
    }
}

function addShift(member) {
    alert(`Add shift for ${member.name}`);
}
