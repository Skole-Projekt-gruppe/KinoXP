window.addEventListener('load', initApp)

const BASE_URL = "http://localhost:8080"
const MOVIES_URL = `${BASE_URL}/movies`;

async function initApp() {
    await showMovieBox();
}

async function fetchMovies() {

    try {
        const response = await fetch(`${MOVIES_URL}`);

        if (!response.ok) {
            throw new Error("Could not fetch movies");
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}

async function showMovieBox() {
    const movies = await fetchMovies();
    const container = document.getElementById("moviebox-container");

    movies.forEach(movie => {
        const div = document.createElement("div");
        container.appendChild(div);
        div.className = "movie-box";

        const poster = document.createElement("img");
        div.appendChild(poster);
        poster.alt = "Movie Poster"
        poster.src = `posters/${movie.poster}`;
        poster.className = "movie-poster";

        const title = document.createElement("a");
        div.appendChild(title);
        title.textContent = movie.title;
        title.className = "movie-title";
        title.href = "viewMovie.html"
        title.dataset.id = movie.movie_id;
        title.addEventListener("click", async (event) => {
            event.preventDefault();
            const movieId = event.target.dataset.id;
            sessionStorage.setItem("movieId", movieId);
            window.location.href = "viewMovie.html";
        })

        const startdate = document.createElement("p");
        div.appendChild(startdate);
        startdate.innerHTML = "<strong>Premiere Date:</strong> " + movie.start_date;
        startdate.className = "movie-startdate";

        const enddate = document.createElement("p");
        div.appendChild(enddate);
        enddate.innerHTML = "<strong>Closing night:</strong> " + movie.end_date;
        enddate.className = "movie-enddate";

    });
}