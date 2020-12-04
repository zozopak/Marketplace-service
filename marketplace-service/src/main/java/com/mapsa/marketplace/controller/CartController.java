package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Cart;
import com.mapsa.marketplace.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;


    @GetMapping
    public List<Cart> getList(Pageable pageable) {
        if (pageable == null)
            return cartRepository.findAll();
        else
            return cartRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Cart cart) {
        cartRepository.save(cart);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id) {

        cartRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Cart> getOne(@PathVariable long id) {
        return cartRepository.findById(id);
    }
}

