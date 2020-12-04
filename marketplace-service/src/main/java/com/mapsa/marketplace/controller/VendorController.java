package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Vendor;
import com.mapsa.marketplace.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("vendor")
public class VendorController {

    @Autowired
    private VendorRepository vendorRepository;

    @GetMapping
    public List<Vendor> getList(Pageable pageable){
        if (pageable == null)
            return vendorRepository.findAll();
        else
            return vendorRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Vendor vendor){
        vendorRepository.save(vendor);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id){
        vendorRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Vendor> getOne(@PathVariable long id){
        return vendorRepository.findById(id);
    }
}
