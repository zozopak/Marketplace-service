package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Customer;
import com.mapsa.marketplace.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getList(Pageable pageable){
        if(pageable==null)
        return customerRepository.findAll();
        else
            return customerRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Customer customer){
        customerRepository.save(customer);
        return "saved";

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id){
        customerRepository.deleteById(id);
        return "deleted!";

    }

    @GetMapping("/{id}")
    public Optional<Customer> getOne(@PathVariable long id){
        return customerRepository.findById(id);
    }
}
