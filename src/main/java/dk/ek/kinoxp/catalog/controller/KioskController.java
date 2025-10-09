package dk.ek.kinoxp.catalog.controller;

import dk.ek.kinoxp.catalog.dto.ProductDto;
import dk.ek.kinoxp.catalog.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import dk.ek.kinoxp.catalog.dto.ShowDto;
import dk.ek.kinoxp.catalog.service.ShowService;

import java.util.List;

@RestController
@RequestMapping("/kiosk")
public class KioskController
{
    private final ProductService productService;
    private final ShowService showService;

    public KioskController(ProductService productService, ShowService showService)
    {
        this.productService = productService;
        this.showService = showService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        List<ProductDto> productDtos = productService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/shows")
    public ResponseEntity<List<ShowDto>> getAllShows() {
        List<ShowDto> showDtos = showService.getAllShow();
        return ResponseEntity.ok(showDtos);
    }

}
