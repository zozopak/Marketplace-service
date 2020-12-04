package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Orders;
import com.mapsa.marketplace.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orders")
public class OrdersController {

    @Autowired
    private OrdersRepository ordersRepository;

    @GetMapping
    public List<Orders> getList(Pageable pageable) {
        if (pageable == null)
            return ordersRepository.findAll();
        else
            return ordersRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Orders orders) {
        ordersRepository.save(orders);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id) {
        ordersRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Orders> getOne(@PathVariable long id) {
        return ordersRepository.findById(id);
    }
}
