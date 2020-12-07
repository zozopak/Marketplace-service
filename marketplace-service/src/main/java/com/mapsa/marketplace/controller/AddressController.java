package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Address;
import com.mapsa.marketplace.model.Bill;
import com.mapsa.marketplace.repository.AddressRepository;
import com.mapsa.marketplace.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @PatchMapping(path="/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<Address> updateAddress(@PathVariable long id, @RequestBody JsonPatch patch){
        try {
            Address address = addressRepository.findById(id).orElseThrow(NullPointerException::new);

            Address addressPatched = applyPatchToAddress(patch, address);

            addressRepository.save(addressPatched);
            return ResponseEntity.ok(addressPatched);
        }catch(JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Address applyPatchToAddress(JsonPatch patch,Address address) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(address,JsonNode.class));

        return objectMapper.treeToValue(patched,Address.class);
    }
}
