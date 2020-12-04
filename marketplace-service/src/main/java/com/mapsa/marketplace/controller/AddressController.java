package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Address;
import com.mapsa.marketplace.model.Bill;
import com.mapsa.marketplace.repository.AddressRepository;
import com.mapsa.marketplace.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("address")
public class AddressController {
    @Autowired
    private AddressRepository addressRepository;

    @GetMapping
    public List<Address> getList(Pageable pageable){
        if(pageable==null){
            return   addressRepository.findAll();
        }else{
            return  addressRepository.findAll(pageable).toList();
        }

    }

    @PostMapping
    public Address save(@RequestBody Address address){
        return   addressRepository.save(address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        addressRepository.deleteById(id);
    }

    @GetMapping("/get/{id}")
    public Optional<Address> getOne(@PathVariable long id ){
        return addressRepository.findById(id);
    }

}
