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

        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}

async function showStaff() {
    const staff = await fetchStaff();
    const tbody = document.getElementById("staff-tbody");

    // Clear any existing rows
    tbody.innerHTML = "";

    staff.forEach(member => {
        const row = document.createElement("tr");

        const nameCell = document.createElement("td");
        nameCell.textContent = member.name;

        const roleCell = document.createElement("td");
        roleCell.textContent = member.role;

        const actionsCell = document.createElement("td");
        const button = document.createElement("addshift-button");
        actionsCell.appendChild(button);
        button.textContent = "Add";
        button.classList.add("add-shift-button");


        button.addEventListener('click', () => {

            alert(`Add shift for ${member.name}`);
        });

        row.appendChild(nameCell);
        row.appendChild(roleCell);
        row.appendChild(actionsCell);

        tbody.appendChild(row);


    });
}

    function addShift(member)
    {
        alert(`Add shift for ${member.name}`);

    }