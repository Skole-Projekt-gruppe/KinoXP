window.addEventListener('load', initApp);

const BASE_URL = "http://localhost:8080";
const PRODUCTS_URL = `${BASE_URL}/products`; // endpoint for produkter

async function initApp() {
    await showProducts();
}

// Hent produkter fra backend
async function fetchProducts() {
    try {
        const response = await fetch(PRODUCTS_URL);

        if (!response.ok) {
            throw new Error("Could not fetch products");
        }

        const data = await response.json();
        return data;
    } catch (error) {
        console.error(error);
    }
}

async function createProduct(payload) {
    const res = await fetch(`${PRODUCTS_URL}`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    });
    if (!res.ok) {
        const msg = await res.text().catch(() => "");
        throw new Error(`Failed to create product. ${msg}`);
    }
    return await res.json();
}

function setupCreateProductForm() {
    const form = document.getElementById("createProductForm");
    const msg = document.getElementById("createProductMsg");
    if (!form) return;

    form.addEventListener("submit", async (e) => {
        e.preventDefault();
        msg.textContent = "";

        const name = form.name.value.trim();
        const price = Number(form.price.value);

        if (!name || Number.isNaN(price) || price < 0) {
            msg.textContent = "Please enter a valid name and price.";
            return;
        }

        try {
            await createProduct({ name, price });
            msg.textContent = "Product created.";
            form.reset();
            await showProducts(); // re-fetch and render products
        } catch (err) {
            console.error(err);
            msg.textContent = "Error creating product.";
        }
    });
}

// Render produkter i DOM
async function showProducts() {
    const products = await fetchProducts();
    const container = document.getElementById("products");
    const cart = {}; // lokalt cart-objekt

    products.forEach(product => {
        const div = document.createElement("div");
        div.className = "product-item";
        div.textContent = `${product.name} - ${product.price} kr`;
        container.appendChild(div);

        div.addEventListener('click', () => {
            addToCart(product);
        });
    });

    // Tilføj til kurv
    function addToCart(product) {
        if (cart[product.product_id]) {
            cart[product.product_id].quantity++;
        } else {
            cart[product.product_id] = { ...product, quantity: 1 };
        }
        renderCart();
    }

    // Render kurv
    function renderCart() {
        const tbody = document.getElementById("cartItems");
        tbody.innerHTML = "";
        let total = 0;

        for (const id in cart) {
            const item = cart[id];
            const row = document.createElement("tr");
            const totalPrice = item.price * item.quantity;
            total += totalPrice;

            row.innerHTML = `
                <td>${item.name}</td>
                <td>${item.price} kr</td>
                <td>${item.quantity}</td>
                <td>${totalPrice} kr</td>
                <td><button onclick="removeFromCart(${id})">Fjern</button></td>
            `;
            tbody.appendChild(row);
        }
        document.getElementById("total").textContent = total;
    }

    // Fjern vare fra kurv
    window.removeFromCart = function(productId) {
        delete cart[productId];
        renderCart();
    }
}
