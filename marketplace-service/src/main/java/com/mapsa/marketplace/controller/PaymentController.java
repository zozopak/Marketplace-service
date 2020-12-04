package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Payment;
import com.mapsa.marketplace.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    public List<Payment> getList(Pageable pageable){
        if (pageable == null)
            return paymentRepository.findAll();
        else
            return paymentRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Payment payment){
        paymentRepository.save(payment);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id){
        paymentRepository.deleteById(id);
        return "deleted!";

    }

    @GetMapping("/{id}")
    public Optional<Payment> getOne(@PathVariable long id){
        return paymentRepository.findById(id);
    }
}
