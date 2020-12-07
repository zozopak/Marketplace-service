package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Vendor;
import com.mapsa.marketplace.model.Wallet;
import com.mapsa.marketplace.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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


    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<Wallet> updateVendor(@PathVariable long id, JsonPatch patch){
        try{
           Wallet wallet=walletRepository.findById(id).orElseThrow(NullPointerException::new);
           Wallet walletPatched=applyPatchToWallet(patch,wallet);
            walletRepository.save(walletPatched);
            return ResponseEntity.ok(walletPatched);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Wallet applyPatchToWallet(JsonPatch patch, Wallet wallet) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(wallet,JsonNode.class));
        return objectMapper.treeToValue(patched,Wallet.class);
    }

}
