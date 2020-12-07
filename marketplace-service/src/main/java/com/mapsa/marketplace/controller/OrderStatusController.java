package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.OrderStatus;
import com.mapsa.marketplace.model.Orders;
import com.mapsa.marketplace.repository.OrderStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("orderstatus")
public class OrderStatusController {

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @GetMapping
    public List<OrderStatus> getList(Pageable pageable) {

        if (pageable == null)
            return orderStatusRepository.findAll();
        else
            return orderStatusRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody OrderStatus orderStatus) {
        orderStatusRepository.save(orderStatus);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id) {
        orderStatusRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<OrderStatus> getOne(@PathVariable long id) {
        return orderStatusRepository.findById(id);

    }

    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<OrderStatus> updateOrderStatus(@PathVariable long id, JsonPatch patch){
        try{
            OrderStatus orderStatus=orderStatusRepository.findById(id).orElseThrow(NullPointerException::new);
            OrderStatus orderStatusPatched=applyPatchToOrders(patch,orderStatus);
            orderStatusRepository.save(orderStatusPatched);
            return ResponseEntity.ok(orderStatusPatched);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private OrderStatus applyPatchToOrders(JsonPatch patch, OrderStatus orderStatus) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(orderStatus,JsonNode.class));
        return objectMapper.treeToValue(patched,OrderStatus.class);
    }
}
