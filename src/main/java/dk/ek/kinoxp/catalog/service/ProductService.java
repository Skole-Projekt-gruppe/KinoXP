package dk.ek.kinoxp.catalog.service;

import dk.ek.kinoxp.catalog.dto.ProductMapper;
import dk.ek.kinoxp.catalog.dto.ProductDto;
import dk.ek.kinoxp.catalog.exception.NotFoundException;
import dk.ek.kinoxp.catalog.model.Product;
import dk.ek.kinoxp.catalog.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService
{

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository)
    {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts()
    {
        List<Product> products = productRepository.findAll();

        if (products.isEmpty())
        {
            throw new NotFoundException("No products found");
        }

        List<ProductDto> productDtos = new ArrayList<>();
        for(var product : products)
        {
            productDtos.add(ProductMapper.toDto(product));
        }
        return productDtos;
    }
}