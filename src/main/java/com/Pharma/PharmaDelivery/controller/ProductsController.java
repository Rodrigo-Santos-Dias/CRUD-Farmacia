package com.Pharma.PharmaDelivery.controller;

import com.Pharma.PharmaDelivery.model.Products;
import com.Pharma.PharmaDelivery.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import com.Pharma.PharmaDelivery. repository.ProductsRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
@CrossOrigin(origins = "*" ,allowedHeaders = "*")
public class ProductsController {
    @Autowired
    private ProductsRepository productsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public ResponseEntity<List<Products>> getAll() {
        return ResponseEntity.ok(productsRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getById(@PathVariable Long id) {
        return productsRepository.findById(id)
                .map(response -> ResponseEntity.ok(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Products>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(productsRepository.findAllByNameContainingIgnoreCase(name));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Products>createProduct(@Valid @RequestBody Products product){
        if (categoryRepository.existsById(product.getCategory().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productsRepository.save(product));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This product needs a category",null);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping
    public ResponseEntity<Products>updateProduct(@Valid @RequestBody Products product){
        if (productsRepository.existsById(product.getId())){

            if (categoryRepository.existsById(product.getCategory().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productsRepository.save(product));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This product needs a category", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        Optional<Products> product = productsRepository.findById(id);

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        productsRepository.findById(id);
    }
}