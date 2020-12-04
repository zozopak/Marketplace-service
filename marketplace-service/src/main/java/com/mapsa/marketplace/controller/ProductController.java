package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Product;
import com.mapsa.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getList(Pageable pageable){
        if (pageable == null)
            return productRepository.findAll();
        else
            return productRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Product product){
        productRepository.save(product);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public  String delete(@PathVariable long id){
        productRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Product> getOne(@PathVariable long id){
        return productRepository.findById(id);
    }
}
