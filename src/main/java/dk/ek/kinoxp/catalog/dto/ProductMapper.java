package dk.ek.kinoxp.catalog.dto;

import dk.ek.kinoxp.catalog.model.Product;

public class ProductMapper
{

    public static ProductDto toDto(Product product) {
        return new ProductDto(
                product.getProduct_id(),
                product.getName(),
                product.getPrice()

        );
    }

    public static Product toEntity(ProductDto productDto) {
        Product product = new Product();

        product.setProduct_id(productDto.product_id());
        product.setName(productDto.name());
        product.setPrice(productDto.price());
        return product;
    }

}
