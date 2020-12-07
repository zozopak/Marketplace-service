package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Cart;
import com.mapsa.marketplace.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("cart")
public class CartController {

    @Autowired
    private CartRepository cartRepository;


    @GetMapping
    public List<Cart> getList(Pageable pageable) {
        if (pageable == null)
            return cartRepository.findAll();
        else
            return cartRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Cart cart) {
        cartRepository.save(cart);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id) {

        cartRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Cart> getOne(@PathVariable long id) {
        return cartRepository.findById(id);
    }


    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<Cart> updateCart(@PathVariable long id, JsonPatch patch){
        try {
            Cart cart = cartRepository.findById(id).orElseThrow(NullPointerException::new);
            Cart cartPatched = applyPatchToCart(patch, cart);
            cartRepository.save(cartPatched);
            return ResponseEntity.ok(cartPatched);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Cart applyPatchToCart(JsonPatch patch, Cart cart) throws JsonPatchException,JsonProcessingException{
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(cart,JsonNode.class));
        return objectMapper.treeToValue(patched,Cart.class);
    }

}

