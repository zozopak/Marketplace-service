package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Bill;
import com.mapsa.marketplace.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bill")
public class BillController {
    @Autowired
    private BillRepository billRepository;

    @GetMapping
    public List<Bill> getList(Pageable pageable){
        if(pageable==null)
        return   billRepository.findAll();
        else{
            return billRepository.findAll(pageable).toList();
        }
    }

    @PostMapping
    public Bill save(@RequestBody Bill bill){
        return   billRepository.save(bill);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id){
        billRepository.deleteById(id);
    }

    @GetMapping("/get/{id}")
    public Optional<Bill> getOne(@PathVariable long id ){
        return billRepository.findById(id);
    }



}
