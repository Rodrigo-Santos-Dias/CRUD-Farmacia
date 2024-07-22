package com.Pharma.PharmaDelivery.controller;

import com.Pharma.PharmaDelivery.model.Products;
import com.Pharma.PharmaDelivery.repository.CategoryRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ResponseEntity<Products>post(@Valid @RequestBody Products product){
        if (categoryRepository.existsById(product.getCategory().getId()))
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(productsRepository.save(product));
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"This product needs a category",null);
    }

    @PutMapping
    public ResponseEntity<Products>put(@Valid @RequestBody Products product){
        if (productsRepository.existsById(product.getId())){

            if (productsRepository.existsById(product.getCategory().getId()))
                return ResponseEntity.status(HttpStatus.OK)
                        .body(productsRepository.save(product));
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This product needs a category", null);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete (@PathVariable Long id){
        Optional<Products> product = productsRepository.findById(id);

        if (product.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        productsRepository.findById(id);
    }
}