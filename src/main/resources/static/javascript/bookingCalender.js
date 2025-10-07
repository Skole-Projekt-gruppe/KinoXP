const daysTag = document.querySelector(".days"),
    currentDate = document.querySelector(".current-date"),
    prevNextIcon = document.querySelectorAll(".icons span");

// state
let date = new Date();
let currYear = date.getFullYear();
let currMonth = date.getMonth();

const months = [
    "January","February","March","April","May","June",
    "July","August","September","October","November","December"
];

/**
 * 1) HENT BOOKING COUNTS
 * Forbind til din backend her. For Spring Boot kunne du lave et endpoint som:
 * GET /api/bookings/counts?year=2025&month=10
 * Returnér fx: { "2025-10-03": 12, "2025-10-05": 127, ... }
 */
async function getBookingCounts(year, monthIndexZeroBased) {
    // Month som 1–12 til API:
    const month = monthIndexZeroBased + 1;

    // === LIVE: API fetch (fjern kommentar når API findes) ===
    // try {
    //   const res = await fetch(`/api/bookings/counts?year=${year}&month=${month}`);
    //   if (!res.ok) throw new Error("Network response was not ok");
    //   return await res.json(); // forventer { "YYYY-MM-DD": number, ... }
    // } catch (e) {
    //   console.warn("Faldt tilbage til dummy-data pga. fejl:", e);
    //   return buildDummyCounts(year, month);
    // }

    // === MIDLER­TIDIG: Dummy data så UI virker nu ===
    return buildDummyCounts(year, month);
}

// Dummy: generér nogle pseudo-tal til demo
function buildDummyCounts(year, month1to12) {
    const daysInMonth = new Date(year, month1to12, 0).getDate();
    const data = {};
    for (let d = 1; d <= daysInMonth; d++) {
        // lav et mønster: weekend højere, midtuge lavere
        const dow = new Date(year, month1to12 - 1, d).getDay();
        let base = [10, 14, 18, 22, 30, 65, 90][dow]; // Sun..Sat
        // små udsving
        base += Math.floor(Math.random() * 15);
        // et par spikes
        if (Math.random() > 0.92) base += 40 + Math.floor(Math.random() * 80);
        const key = `${year}-${String(month1to12).padStart(2, "0")}-${String(d).padStart(2, "0")}`;
        data[key] = base;
    }
    // og en håndfuld nul-dage
    for (let i = 0; i < 3; i++) {
        const dd = 1 + Math.floor(Math.random() * daysInMonth);
        const key = `${year}-${String(month1to12).padStart(2, "0")}-${String(dd).padStart(2, "0")}`;
        data[key] = 0;
    }
    return data;
}

/** Map count -> heat klasse 0..5 */
function heatClassFromCount(n) {
    if (n <= 0) return "heat-0";
    if (n < 10) return "heat-1";
    if (n < 25) return "heat-2";
    if (n < 50) return "heat-3";
    if (n < 100) return "heat-4";
    return "heat-5";
}

/** Indsæt badge og farver for indeværende måneds dage */
function applyHeatAndBadges(year, monthIndex, countsMap) {
    const month1to12 = monthIndex + 1;
    const dayItems = document.querySelectorAll(".calendar .days li");

    dayItems.forEach(li => {
        const isInactive = li.classList.contains("inactive");
        if (isInactive) return; // kun farver på “rigtige” dage i måneden

        const dayNumber = parseInt(li.textContent, 10);
        if (isNaN(dayNumber)) return;

        const key = `${year}-${String(month1to12).padStart(2, "0")}-${String(dayNumber).padStart(2, "0")}`;
        const count = countsMap[key] ?? 0;

        // datatribut til tooltip
        li.setAttribute("data-count", count);

        // heat klasse
        li.classList.add("has-bookings", heatClassFromCount(count));

        // badge
        const old = li.querySelector(".count-badge");
        if (old) old.remove();
        const badge = document.createElement("span");
        badge.className = "count-badge";
        badge.textContent = count;
        li.appendChild(badge);

        // klik (åbn dag-visning / filter / modal – udskift med din logik)
        li.onclick = () => {
            // Her kan du fx navigere til en dag-side:
            // window.location.href = `/shows?date=${key}`;
            alert(`${key}: ${count} bookings`);
        };
    });
}

async function renderCalendar() {
    const firstDayofMonth = new Date(currYear, currMonth, 1).getDay();
    const lastDateofMonth = new Date(currYear, currMonth + 1, 0).getDate();
    const lastDayofMonth = new Date(currYear, currMonth, lastDateofMonth).getDay();
    const lastDateofLastMonth = new Date(currYear, currMonth, 0).getDate();

    let liTag = "";

    // forrige måneds hale
    for (let i = firstDayofMonth; i > 0; i--) {
        liTag += `<li class="inactive">${lastDateofLastMonth - i + 1}</li>`;
    }

    // indeværende måned
    for (let i = 1; i <= lastDateofMonth; i++) {
        const isToday =
            i === new Date().getDate() &&
            currMonth === new Date().getMonth() &&
            currYear === new Date().getFullYear()
                ? "active"
                : "";
        liTag += `<li class="${isToday}">${i}</li>`;
    }

    // næste måneds begyndelse for at udfylde ugen
    for (let i = lastDayofMonth; i < 6; i++) {
        liTag += `<li class="inactive">${i - lastDayofMonth + 1}</li>`;
    }

    currentDate.innerText = `${months[currMonth]} ${currYear}`;
    daysTag.innerHTML = liTag;

    // HENT counts og anvend heat/badges
    const counts = await getBookingCounts(currYear, currMonth);
    applyHeatAndBadges(currYear, currMonth, counts);
}

// init
renderCalendar();

// prev/next
prevNextIcon.forEach(icon => {
    icon.addEventListener("click", () => {
        currMonth = icon.id === "prev" ? currMonth - 1 : currMonth + 1;
        if (currMonth < 0 || currMonth > 11) {
            date = new Date(currYear, currMonth, new Date().getDate());
            currYear = date.getFullYear();
            currMonth = date.getMonth();
        }
        renderCalendar();
    });
});