package com.example.client;

import com.example.model.Category;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "product-service")
public interface ProductClient {

    @GetMapping("/product/category")
    List<Category> getList();

    @PostMapping("/product/category")
    String saveCategory();

    @DeleteMapping("product/category/{id}")
    String deleteId();

    @GetMapping("product/category/{id}")
    Optional<Category> getOne();
}
