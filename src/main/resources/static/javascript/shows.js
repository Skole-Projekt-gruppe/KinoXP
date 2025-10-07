// === Enkel shows.js der matcher din ShowDto + 5 kolonner i HTML ===
// Denne fil henter shows (forestillinger) fra backend via /shows-endpoint,
// og viser dem dynamisk i HTML-tabellen med knapper og mulighed for udvidelse.

const SHOWS_ENDPOINT = "/shows"; // URL til API’et – skal matche din controller @RequestMapping("/shows")

// Finder <tbody> i tabellen, hvor alle rækker skal indsættes
const tbody = document.getElementById("showsTbody");
console.log("shows.js loaded"); // Debugbesked i konsollen – bekræfter at JS er indlæst korrekt

// Når hele HTML’en er indlæst, start funktionen "load"
document.addEventListener("DOMContentLoaded", load);

// === Hovedfunktion: Henter data fra backend og viser dem i tabellen ===
async function load() {
    // Viser foreløbig besked mens data hentes
    tbody.innerHTML = row("Indlæser…");

    try {
        // Kalder API’et og beder om JSON-data
        const res = await fetch(SHOWS_ENDPOINT, { headers: { "Accept": "application/json" } });

        // Hvis serveren svarer med fejlstatus (fx 404/500), vis fejlbesked i tabellen
        if (!res.ok) {
            tbody.innerHTML = row(`Fejl: ${res.status} ${res.statusText}`);
            return;
        }

        // Læser data som JSON (liste af shows)
        const data = await res.json();

        // Hvis der ikke findes nogen shows, vis besked
        if (!Array.isArray(data) || data.length === 0) {
            tbody.innerHTML = row("No show found");
            return;
        }

        // Hvis der findes shows, opret HTML-rækker for hver via renderRow()
        tbody.innerHTML = data.map(renderRow).join("");

        // Logger hele listen i browserkonsollen (til fejlsøgning)
        console.log("Shows:", data);

    } catch (e) {
        // Hvis noget går galt (fx netværksfejl), vis besked og log fejlen
        console.error(e);
        tbody.innerHTML = row("Kunne ikke hente shows (network/JS-fejl). Se konsollen.");
    }
}

// === Funktion der opretter én tabelrække ud fra et show-objekt ===
function renderRow(s) {
    // Udpakker værdier fra DTO’en (movie, teather, start/end time)
    const title   = s.movie?.title ?? "";
    const teather = s.teather?.name ?? "";
    const start   = fmtTime(s.start_time);
    const end     = fmtTime(s.end_time);

    // Returnerer HTML-streng med alle kolonner for et show
    // Her tilføjes også knapper til Edit, Book og Delete
    return `
  <tr>
    <td>
      <!-- Knappen ▼ bruges til at udvide/folde rækken ud -->
      <button class="disclosure-btn" aria-expanded="false" onclick="toggleExpand(this)">
        ▼
      </button>
    </td>
    <td>${esc(title)}</td>
    <td>${esc(teather)}</td>
    <td>${esc(start)}</td>
    <td>${esc(end)}</td>
    <td class="actions">
      <!-- Knapper (uden funktioner endnu) -->
      <button class="btn btn-edit">Edit</button>
      <button class="btn btn-book">Book</button>
      <button class="btn btn-delete">Delete</button>
    </td>
  </tr>
  `;
}

// === Funktion til at vise eller skjule “udvidelig række” (bookinger) ===
function toggleExpand(btn) {
    // Finder rækken, hvor knappen blev trykket
    const row = btn.closest("tr");

    // Tjekker om rækken allerede er åben (aria-expanded = true)
    const expanded = btn.getAttribute("aria-expanded") === "true";

    // Skifter status (åbn/luk)
    btn.setAttribute("aria-expanded", !expanded);

    if (expanded) {
        // Hvis rækken er åben → luk den (fjern ekstra række under)
        const next = row.nextElementSibling;
        if (next && next.classList.contains("expand-row")) next.remove();
    } else {
        // Hvis rækken er lukket → åbn en ekstra række under
        // Denne del viser blot placeholder-tekst for nu
        const panel = `
      <tr class="expand-row">
        <td colspan="6">
          <div class="expand-panel">
            <strong>Bookinger:</strong>
            <p>(Her vil bookinger blive vist senere)</p>
          </div>
        </td>
      </tr>`;
        // Indsætter ekstra række lige efter den aktuelle
        row.insertAdjacentHTML("afterend", panel);
    }
}

// === Hjælpefunktioner ===

// Formatterer tid fra "19:30:00" → "19:30"
function fmtTime(v) {
    if (!v) return "";
    return typeof v === "string" && /^\d{2}:\d{2}/.test(v) ? v.slice(0,5) : String(v);
}

// Returnerer en <tr> med besked (fx “Indlæser…” eller fejl)
function row(text){
    return `<tr><td colspan="6" style="padding:10px;">${text}</td></tr>`;
}

// Sørger for at specialtegn ikke ødelægger HTML (fx <, >, &)
function esc(s){
    return String(s ?? "")
        .replaceAll("&","&amp;")
        .replaceAll("<","&lt;")
        .replaceAll(">","&gt;")
        .replaceAll('"',"&quot;");
}