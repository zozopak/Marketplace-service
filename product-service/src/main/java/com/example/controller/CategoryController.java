package com.example.controller;

import com.example.model.Category;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product/category")
public class CategoryController {

    @Autowired
    public CategoryRepository categoryRepository;
    @GetMapping
    public List<Category> getList(Pageable pagable) {
        if (pagable == null) {
            return categoryRepository.findAll();
        } else {
            return categoryRepository.findAll(pagable).toList();
        }

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Category category) {
        categoryRepository.save(category);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id) {
        categoryRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Category> getOne(@PathVariable long id) {
        return categoryRepository.findById(id);
    }

}
