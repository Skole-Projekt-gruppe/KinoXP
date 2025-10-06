document.addEventListener('DOMContentLoaded', () => {
    loadStaff();
});

async function loadStaff() {
    const tbody = document.getElementById('staff-tbody');
    const assignSelect = document.getElementById('assign-staff');

    if (!tbody) return;

    tbody.innerHTML = '<tr><td colspan="3">Loading...</td></tr>';

    try {
        const res = await fetch('/api/staff', { headers: { 'Accept': 'application/json' } });
        if (!res.ok) throw new Error(`HTTP ${res.status}`);
        const staff = await res.json();

        if (!Array.isArray(staff) || staff.length === 0) {
            tbody.innerHTML = '<tr><td colspan="3">No staff found.</td></tr>';
        } else {
            tbody.innerHTML = '';
            staff.forEach(s => {
                const tr = document.createElement('tr');

                const name = s.name || [s.firstName, s.lastName].filter(Boolean).join(' ') || 'Unknown';
                const role = s.role || s.title || '—';
                const email = s.email || s.mail || '—';

                tr.appendChild(td(name));
                tr.appendChild(td(role));
                tr.appendChild(td(email));
                tbody.appendChild(tr);
            });
        }

        if (assignSelect) {
            assignSelect.innerHTML = '';
            assignSelect.appendChild(new Option('Select staff...', '', true, false));
            staff.forEach(s => {
                const name = s.name || [s.firstName, s.lastName].filter(Boolean).join(' ') || `#${s.id}`;
                assignSelect.appendChild(new Option(name, s.id));
            });
        }
    } catch (e) {
        tbody.innerHTML = `<tr><td colspan="3">Failed to load staff.</td></tr>`;
        console.error('Failed to load staff:', e);
    }
}

function td(text) {
    const cell = document.createElement('td');
    cell.textContent = text;
    return cell;
}
