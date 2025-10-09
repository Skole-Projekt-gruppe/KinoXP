package dk.ek.kinoxp.catalog.common;

import dk.ek.kinoxp.catalog.model.Product;
import dk.ek.kinoxp.catalog.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(4)
public class InitProductData implements CommandLineRunner {

    private final ProductRepository productRepository;

    public InitProductData(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Product pizza = new Product(null, "Pizza", 50);
        productRepository.save(pizza);

        Product cola = new Product(null, "Cola", 20);
        productRepository.save(cola);

        Product burger = new Product(null, "Burger", 40);
        productRepository.save(burger);

        Product fries = new Product(null, "Pommes Frites", 25);
        productRepository.save(fries);

        Product iceCream = new Product(null, "Ice Cream", 30);
        productRepository.save(iceCream);

        Product hotdog = new Product(null, "Hotdog", 35);
        productRepository.save(hotdog);

        Product nachos = new Product(null, "Nachos", 45);
        productRepository.save(nachos);

        Product popcorn = new Product(null, "Popcorn", 15);
        productRepository.save(popcorn);

        Product candy = new Product(null, "Candy", 10);
        productRepository.save(candy);

        Product sandwich = new Product(null, "Sandwich", 40);
        productRepository.save(sandwich);
    }
}
