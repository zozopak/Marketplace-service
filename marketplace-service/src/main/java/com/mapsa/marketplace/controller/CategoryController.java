package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonNullFormatVisitor;
import com.mapsa.marketplace.model.Category;
import com.mapsa.marketplace.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("category")

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


    @PatchMapping("/{id}")
    public void updateCategory(@PathVariable long id, @RequestBody Category category) {
        Optional<Category> myCategory = categoryRepository.findById(id);
        Category c = myCategory.get();
        if (category.getId() != 0)
            c.setId(category.getId());
        if (category.getStatus() != null)
            c.setStatus(category.getStatus());
        if (category.getRemarks() != null)
            c.setRemarks(category.getRemarks());
        categoryRepository.save(c);
    }


}



