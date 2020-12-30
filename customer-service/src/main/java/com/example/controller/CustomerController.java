package com.example.controller;

import com.example.client.ProductClient;
import com.example.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    public ProductClient productClient;

    @GetMapping
    public List<Category> getList(){
        return   productClient.getList();

    }

    @PostMapping
    public String save(@RequestBody Category category){
        return   productClient.saveCategory();
    }

    @GetMapping("/{id}")
    public Optional<Category> getOne(@PathVariable long id){
        return productClient.getOne();
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable long id){
        return productClient.deleteId();
    }
}
