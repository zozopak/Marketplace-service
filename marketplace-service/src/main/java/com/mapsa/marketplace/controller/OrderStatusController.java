package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.OrderStatus;
import com.mapsa.marketplace.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orderstatus")
public class OrderStatusController {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @GetMapping
    public List<OrderStatus> getList(Pageable pageable) {

        if (pageable == null)
            return orderStatusRepository.findAll();
        else
            return orderStatusRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody OrderStatus orderStatus) {
        orderStatusRepository.save(orderStatus);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id) {
        orderStatusRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<OrderStatus> getOne(@PathVariable long id) {
        return orderStatusRepository.findById(id);

    }
}
