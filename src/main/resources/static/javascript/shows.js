// === shows.js ===
const SHOWS_ENDPOINT = "/shows"; // Skal matche din @RequestMapping
let tbody; // udfyldes når DOM er klar

document.addEventListener("DOMContentLoaded", () => {
    tbody = document.getElementById("showsTbody");
    console.log("shows.js loaded");
    load();
});

// === Henter data og renderer tabellen ===
async function load() {
    tbody.innerHTML = row("Indlæser…");

    try {
        const res = await fetch(SHOWS_ENDPOINT, { headers: { "Accept": "application/json" } });

        if (!res.ok) {
            tbody.innerHTML = row(`Fejl: ${res.status} ${res.statusText}`);
            return;
        }

        const data = await res.json();

        if (!Array.isArray(data) || data.length === 0) {
            tbody.innerHTML = row("No show found");
            return;
        }

        tbody.innerHTML = data.map(renderRow).join("");
        console.log("Shows:", data);
    } catch (e) {
        console.error(e);
        tbody.innerHTML = row("Kunne ikke hente shows (network/JS-fejl). Se konsollen.");
    }
}

// === Bygger én række ===
function renderRow(s) {
    const id      = s.id ?? s.show_id ?? s.showId ?? "";           // robust id-fallback
    const title   = s.movie?.title ?? s.title ?? "";
    const teather = s.teather?.name ?? s.theater?.name ?? "";       // håndterer stavevarianter
    const start   = fmtTime(s.start_time ?? s.startTime);
    const end     = fmtTime(s.end_time ?? s.endTime);

    return `
  <tr data-id="${id}">
    <td>
      <button class="disclosure-btn" aria-expanded="false" onclick="toggleExpand(this)">▼</button>
    </td>
    <td>${esc(title)}</td>
    <td>${esc(teather)}</td>
    <td>${esc(start)}</td>
    <td>${esc(end)}</td>
    <td class="actions">
      <button class="btn btn-edit"   onclick="editShow('${id}')">Edit</button>
      <button class="btn btn-book"   onclick="bookShow('${id}')">Book</button>
      <button class="btn btn-delete" onclick="deleteShow('${id}')">Delete</button>
    </td>
  </tr>`;
}

// === Udvid/folde-række ===
function toggleExpand(btn) {
    const row = btn.closest("tr");
    const expanded = btn.getAttribute("aria-expanded") === "true";
    btn.setAttribute("aria-expanded", !expanded);

    if (expanded) {
        const next = row.nextElementSibling;
        if (next && next.classList.contains("expand-row")) next.remove();
    } else {
        const panel = `
      <tr class="expand-row">
        <td colspan="6">
          <div class="expand-panel">
            <strong>Bookinger:</strong>
            <p>(Her vil bookinger blive vist senere)</p>
            <div class="button-group">
              <button class="edit-btn">Edit</button>
              <button class="delete-btn">Delete</button>
            </div>
          </div>
        </td>
      </tr>`;
        row.insertAdjacentHTML("afterend", panel);

        const next = row.nextElementSibling;
        next.querySelector(".edit-btn").addEventListener("click", () => {
            console.log("Rediger (expand) – id:", row.dataset.id);
            // TODO: editShow(row.dataset.id)
        });
        next.querySelector(".delete-btn").addEventListener("click", () => {
            console.log("Slet (expand) – id:", row.dataset.id);
            // TODO: deleteShow(row.dataset.id)
        });
    }
}

// === Hjælpefunktioner ===
function fmtTime(v) {
    if (!v) return "";
    return typeof v === "string" && /^\d{2}:\d{2}/.test(v) ? v.slice(0, 5) : String(v);
}

function row(text) {
    return `<tr><td colspan="6" style="padding:10px;">${text}</td></tr>`;
}

function esc(s) {
    return String(s ?? "")
        .replaceAll("&", "&amp;")
        .replaceAll("<", "&lt;")
        .replaceAll(">", "&gt;")
        .replaceAll('"', "&quot;");
}

// 🆕 HH:MM -> HH:MM:SS  (placér her sammen med dine helpers)
function toHHMMSS(v) {
    if (!v) return "";
    const [h, m, s] = String(v).split(":");
    if (s) return `${h.padStart(2,"0")}:${m.padStart(2,"0")}:${s.padStart(2,"0")}`;
    return `${(h||"00").padStart(2,"0")}:${(m||"00").padStart(2,"0")}:00`;
}

// Edit knap
async function editShow(id){
    const row = document.querySelector(`tr[data-id="${id}"]`);
    if(!row) return;

    const curTitle   = row.querySelector("td:nth-child(2)")?.textContent.trim() || "";
    const curTeather = row.querySelector("td:nth-child(3)")?.textContent.trim() || "";
    const curStart   = row.querySelector("td:nth-child(4)")?.textContent.trim() || "";
    const curEnd     = row.querySelector("td:nth-child(5)")?.textContent.trim() || "";

    const titleIn   = prompt("Ny titel:", curTitle);        if (titleIn   === null) return;
    const teatherIn = prompt("Nyt teater/sal-navn:", curTeather); if (teatherIn === null) return;
    const startIn   = prompt("Ny starttid (HH:MM):", curStart || "19:00"); if (startIn   === null) return;
    const endIn     = prompt("Ny sluttid (HH:MM):",  curEnd   || "21:00"); if (endIn     === null) return;

    if (!/^\d{2}:\d{2}$/.test(startIn) || !/^\d{2}:\d{2}$/.test(endIn)) {
        alert("Ugyldigt format. Brug HH:MM, fx 19:30");
        return;
    }

    // Lokal konvertering: "HH:MM" -> "HH:MM:00"
    const toSec = v => (String(v).length === 5 ? `${v}:00` : String(v));

    const payload = {
        start_time: toSec(startIn),
        end_time:   toSec(endIn),
        movie:   { title: titleIn },
        teather: { name:  teatherIn }
    };

    const actions = row.querySelector(".actions");
    const prev = actions.innerHTML;
    actions.innerHTML = `<span class="badge">Gemmer…</span>`;

    try {
        const res = await fetch(`${SHOWS_ENDPOINT}/${id}`, {
            method: "PUT",
            headers: { "Content-Type": "application/json", "Accept": "application/json" },
            body: JSON.stringify(payload)
        });
        if (!res.ok) {
            const txt = await res.text().catch(()=>"(no body)");
            console.error("PUT error body:", txt);
            throw new Error(`${res.status} ${res.statusText}`);
        }
        await load(); // Vis de nye værdier
    } catch (err) {
        console.error(err);
        alert("Kunne ikke gemme ændringer.");
    } finally {
        actions.innerHTML = prev;
    }
}

//  Create Show
async function createShow({ planned_at, start_time, end_time, movie_id, teather_id, movie_title, teather_name }) {
    // Tillad "HH:MM" ved at tilføje ":00"
    const toSec = v => (String(v).length === 5 ? `${v}:00` : String(v));

    const payload = {
        planned_at,
        start_time: toSec(start_time),
        end_time:   toSec(end_time),
        // Send både id + navn for at tilfredsstille evt. @NotNull/@NotBlank på DTO'erne
        movie:   { movie_id: Number(movie_id), title: movie_title ?? "(auto)" },
        teather: { teather_id: Number(teather_id), name: teather_name ?? "(auto)" }
    };

    console.log("POST /shows payload:", payload);

    const res = await fetch(SHOWS_ENDPOINT, {
        method: "POST",
        headers: { "Content-Type": "application/json", "Accept": "application/json" },
        body: JSON.stringify(payload)
    });

    const txt = await res.text(); // læs altid responsen én gang
    if (!res.ok) {
        console.error("Create failed. Status:", res.status, "Body:", txt);
        throw new Error(`Fejl ${res.status}: ${txt || "(no body)"}`);
    }

    const data = txt ? JSON.parse(txt) : null;
    if (typeof load === "function") load(); // Opfrisk tabellen
    return data;
}

// Create Show
async function openCreateForm() {
    const defaultDate = new Date().toISOString().slice(0, 10);

    const planned_at = prompt("Dato (YYYY-MM-DD):", defaultDate);
    if (!planned_at) return;
    if (!/^\d{4}-\d{2}-\d{2}$/.test(planned_at)) {
        alert("Ugyldigt format. Brug YYYY-MM-DD, fx 2025-10-08");
        return;
    }

    const start_time = prompt("Start tid (HH:MM):", "19:00");
    if (!start_time) return;

    const end_time = prompt("Slut tid (HH:MM):", "21:00");
    if (!end_time) return;

    const movie_id = prompt("Movie ID:", "1");
    if (!movie_id) return;

    const teather_id = prompt("Teather ID:", "1");
    if (!teather_id) return;

    // Ekstra felter for at tilfredsstille mulige @NotBlank på nested DTO'er
    const movie_title  = prompt("Movie title:", "(auto)");
    if (movie_title === null) return;

    const teather_name = prompt("Teather name:", "(auto)");
    if (teather_name === null) return;

    try {
        await createShow({ planned_at, start_time, end_time, movie_id, teather_id, movie_title, teather_name });
        alert("Forestilling oprettet!");
    } catch (err) {
        console.error(err);
        alert("Kunne ikke oprette forestilling.\n" + err.message);
    }

}

// Stubs til knapper (kan kobles til din backend)
function bookShow(id){ console.log("BOOK", id); }
function deleteShow(id){ console.log("DELETE", id); }

// Gør funktionerne globale, så inline onclick kan finde dem
window.editShow = editShow;
window.bookShow = bookShow;
window.deleteShow = deleteShow;
window.createShow = createShow;
window.openCreateForm = openCreateForm;