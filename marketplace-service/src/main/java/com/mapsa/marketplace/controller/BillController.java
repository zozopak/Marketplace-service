package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Bill;
import com.mapsa.marketplace.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{id}")
    public Optional<Bill> getOne(@PathVariable long id ){
        return billRepository.findById(id);
    }

    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<Bill> updateBill(@PathVariable long id, JsonPatch patch){
        try {
            Bill bill = billRepository.findById(id).orElseThrow(NullPointerException::new);
            Bill billPatched = applyPatchToBill(patch, bill);
            billRepository.save(billPatched);
            return ResponseEntity.ok(billPatched);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Bill applyPatchToBill(JsonPatch patch, Bill bill) throws JsonPatchException,JsonProcessingException{
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(bill,JsonNode.class));
        return objectMapper.treeToValue(patched,Bill.class);
    }


}
