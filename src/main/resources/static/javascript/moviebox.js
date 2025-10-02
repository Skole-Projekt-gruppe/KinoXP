window.addEventListener('load',initApp)

const BASE_URL = ""
const MOVIES_URL = `${BASE_URL}/movies`;

async function initApp() {

}

async function fetchMovies() {
    const response = await fetch(`${BASE_URL}`)
}

const html = <div className="movie-box">
    <img src="asd" alt="Poster"/>
    <p>Title</p>
</div>