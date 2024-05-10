package com.fimeco.fimeco.controller;

import com.fimeco.fimeco.domain.product.*;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductRepository productRepository;

    private final ProductService productService;

    public ProductController(ProductRepository productRepository, ProductService productService) {
        this.productRepository = productRepository;
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<DataResponseProduct> createProduct(@RequestBody DataRegisterProduct dataRegisterProduct){
        return productService.createProduct(dataRegisterProduct);
    }

    @GetMapping
    public ResponseEntity<Page<DataResponseProduct>> listProduct(@PageableDefault(size = 5) Pageable pagination){
        return ResponseEntity.ok(productRepository.findAll(pagination).map(DataResponseProduct::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DataResponseProduct> updateProduct(@RequestBody DataUpdateProduct dataUpdateProduct){
        Product product = productRepository.getReferenceById(dataUpdateProduct.id());
        product.dataUpdate(dataUpdateProduct);
        return ResponseEntity.ok(new DataResponseProduct(product));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<DataResponseProduct> deleteProduct(@PathVariable Long id){
        productRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
