package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Orders;
import com.mapsa.marketplace.model.Payment;
import com.mapsa.marketplace.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("payment")
public class PaymentController {

    @Autowired
    private PaymentRepository paymentRepository;

    @GetMapping
    public List<Payment> getList(Pageable pageable){
        if (pageable == null)
            return paymentRepository.findAll();
        else
            return paymentRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Payment payment){
        paymentRepository.save(payment);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id){
        paymentRepository.deleteById(id);
        return "deleted!";

    }

    @GetMapping("/{id}")
    public Optional<Payment> getOne(@PathVariable long id){
        return paymentRepository.findById(id);
    }

    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<Payment> updatePayment(@PathVariable long id, JsonPatch patch){
        try{
           Payment payment=paymentRepository.findById(id).orElseThrow(NullPointerException::new);
            Payment paymentPatched=applyPatchToPayment(patch,payment);
            paymentRepository.save(paymentPatched);
            return ResponseEntity.ok(paymentPatched);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Payment applyPatchToPayment(JsonPatch patch, Payment payment) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(payment,JsonNode.class));
        return objectMapper.treeToValue(patched,Payment.class);
    }
}
