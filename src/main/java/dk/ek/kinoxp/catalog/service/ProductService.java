package dk.ek.kinoxp.catalog.service;

import dk.ek.kinoxp.catalog.dto.ProductDto;
import dk.ek.kinoxp.catalog.dto.ProductMapper;
import dk.ek.kinoxp.catalog.exception.NotFoundException;
import dk.ek.kinoxp.catalog.model.Product;
import dk.ek.kinoxp.catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            throw new NotFoundException("No products found");
        }
        return products.stream()
                .map(ProductMapper::toDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProductById(Integer id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Product with id " + id + " not found"));
        return ProductMapper.toDto(product);
    }

    public ProductDto createProduct(ProductDto dto) {
        Product entity = ProductMapper.toEntity(dto);
        Product saved = productRepository.save(entity);
        return ProductMapper.toDto(saved);
    }

    public void deleteProduct(Integer id) {
        if (!productRepository.existsById(id)) {
            throw new NotFoundException("Product with id " + id + " not found");
        }
        productRepository.deleteById(id);
    }
}
