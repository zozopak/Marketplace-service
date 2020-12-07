package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Customer;
import com.mapsa.marketplace.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("customer")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping
    public List<Customer> getList(Pageable pageable) {
        if (pageable == null)
            return customerRepository.findAll();
        else
            return customerRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Customer customer) {
        customerRepository.save(customer);
        return "saved";

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id) {
        customerRepository.deleteById(id);
        return "deleted!";

    }

    @GetMapping("/{id}")
    public Optional<Customer> getOne(@PathVariable long id) {
        return customerRepository.findById(id);
    }

    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")

    public ResponseEntity<Customer> updateCustomer(@PathVariable long id, JsonPatch patch) {
        try {
            Customer customer = customerRepository.findById(id).orElseThrow(NullPointerException::new);
            Customer customerPatched = applyPatchToCustomer(patch, customer);
            customerRepository.save(customerPatched);
            return ResponseEntity.ok(customerPatched);
        } catch (JsonPatchException | JsonProcessingException exception) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (NullPointerException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Customer applyPatchToCustomer(JsonPatch patch, Customer customer) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(customer, JsonNode.class));
       return objectMapper.treeToValue(patched, Customer.class);
    }
}
