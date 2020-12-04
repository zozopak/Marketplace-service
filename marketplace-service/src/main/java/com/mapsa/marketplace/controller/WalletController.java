package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Wallet;
import com.mapsa.marketplace.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("wallet")
public class WalletController {

    @Autowired
    private WalletRepository walletRepository;

    @GetMapping
    public List<Wallet> getList(Pageable pageable){
        if (pageable == null)
            return walletRepository.findAll();
        else
            return walletRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Wallet wallet){
        walletRepository.save(wallet);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id){
        walletRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Wallet> getOne(@PathVariable long id){
        return walletRepository.findById(id);
    }
}
