window.addEventListener('load', initApp)

const BASE_URL = "http://localhost:8080"
const MOVIES_URL = `${BASE_URL}/movies`;

const movieId = sessionStorage.getItem("movieId");

async function initApp() {
    await showMovie();
}

async function fetchMovieById() {
    try {
        const response = await fetch(`${MOVIES_URL}/${movieId}`);

        if (!response.ok) {
            throw new Error("Could not fetch movie");
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}

async function showMovie() {
    const movie = await fetchMovieById();

    document.querySelector("#movieposter").src = `posters/${movie.poster}`;

    document.querySelector("#movie-title").textContent=`${movie.title}`;

    document.querySelector("#duration-value").textContent = `${movie.duration_min} Minutes`;
    document.querySelector("#age-value").textContent = `${movie.age_limit}`;
    document.querySelector("#premiere-value").textContent = `${movie.start_date}`;
    document.querySelector("#closing-value").textContent = `${movie.end_date}`;



    const ul = document.querySelector("#ul")

    movie.genres.forEach(genre => {
        const li = document.createElement("li");

        const genreLi = document.querySelector("#genres");
        ul.insertBefore(li, genreLi.nextSibling);

        li.textContent=`${genre.name}`;
    })

    movie.actors.forEach(actor => {
        const li = document.createElement("li");
        ul.appendChild(li);
        li.textContent=`${actor.name}`
        console.log(actor.name);
    })
}