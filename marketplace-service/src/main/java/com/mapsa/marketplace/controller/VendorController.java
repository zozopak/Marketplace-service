package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Orders;
import com.mapsa.marketplace.model.Vendor;
import com.mapsa.marketplace.repository.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<Vendor> updateVendor(@PathVariable long id, JsonPatch patch){
        try{
           Vendor vendor=vendorRepository.findById(id).orElseThrow(NullPointerException::new);
            Vendor vendorPatched=applyPatchToVendor(patch,vendor);
            vendorRepository.save(vendorPatched);
            return ResponseEntity.ok(vendorPatched);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Vendor applyPatchToVendor(JsonPatch patch, Vendor vendor) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(vendor,JsonNode.class));
        return objectMapper.treeToValue(patched,Vendor.class);
    }
}
