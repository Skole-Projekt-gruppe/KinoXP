let cart = [];

function addToCart(name, price) {
    const existingItem = cart.find(item => item.name === name);
    if (existingItem) {
        existingItem.amount++;
    } else {
        cart.push({ name, price, amount: 1 });
    }
    renderCart();
}

function removeFromCart(name) {
    const index = cart.findIndex(item => item.name === name);
    if (index !== -1) {
        if (cart[index].amount > 1) {
            cart[index].amount--;
        } else {
            cart.splice(index, 1);
        }
    }
    renderCart();
}

function renderCart() {
    const tbody = document.getElementById("cartItems");
    tbody.innerHTML = "";

    let total = 0;

    cart.forEach(item => {
        const row = document.createElement("tr");
        const totalPrice = item.price * item.amount;
        total += totalPrice;

        row.innerHTML = `
      <td>${item.name}</td>
      <td>${item.price} kr</td>
      <td>${item.amount}</td>
      <td>${totalPrice} kr</td>
      <td><button onclick="removeFromCart('${item.name}')">Remove</button></td>
    `;

        tbody.appendChild(row);
    });

    document.getElementById("total").textContent = total.toFixed(2);
}

async function fetchProducts() {
    try {
        const response = await fetch("/kiosk/products");
        const products = await response.json();
        renderProducts(products);
    } catch (error) {
        console.error("Error fetching products:", error);
    }
}

function renderProducts(products) {
    const container = document.getElementById("products");
    container.innerHTML = "";

    products.forEach(product => {
        const div = document.createElement("div");
        div.classList.add("product-item");

        div.innerHTML = `
      <h3>${product.name}</h3>
      <p>${product.price} kr</p>
      <button onclick="addToCart('${product.name}', ${product.price})">Add to Cart</button>
    `;

        container.appendChild(div);
    });
}

async function fetchShows() {
    try {
        const response = await fetch("/kiosk/shows");
        const shows = await response.json();
        renderShows(shows);
    } catch (error) {
        console.error("Error fetching shows:", error);
    }
}

// Displays shows in kiosk
function renderShows(shows) {
    const container = document.getElementById("shows");
    container.innerHTML = "";

    shows.forEach(show => {
        const div = document.createElement("div");
        div.classList.add("show-item");

        const movieTitle = show.movie?.title || "Unknown Movie";
        const date = show.planned_at;
        const startTime = show.start_time?.substring(0, 5); // Format HH:mm
        const endTime = show.end_time?.substring(0, 5);
        const teatherName = show.teather?.name || "Unknown Theatre";

        div.innerHTML = `
      <h3>${movieTitle}</h3>
      <p><strong>Date:</strong> ${date}</p>
      <p><strong>Time:</strong> ${startTime} - ${endTime}</p>
      <p><strong>Theatre:</strong> ${teatherName}</p>
      <button onclick="addToCart('Ticket - ${movieTitle}', 90)">Buy Ticket (90 kr)</button>
    `;

        container.appendChild(div);
    });
}

fetchProducts();
fetchShows();
