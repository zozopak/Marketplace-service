package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Orders;
import com.mapsa.marketplace.model.Product;
import com.mapsa.marketplace.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getList(Pageable pageable){
        if (pageable == null)
            return productRepository.findAll();
        else
            return productRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Product product){
        productRepository.save(product);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public  String delete(@PathVariable long id){
        productRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Product> getOne(@PathVariable long id){
        return productRepository.findById(id);
    }

    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<Product> updateProduct(@PathVariable long id, JsonPatch patch){
        try{
           Product product=productRepository.findById(id).orElseThrow(NullPointerException::new);
           Product productPatched=applyPatchToProduct(patch,product);
            productRepository.save(productPatched);
            return ResponseEntity.ok(productPatched);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Product applyPatchToProduct(JsonPatch patch, Product product) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(product,JsonNode.class));
        return objectMapper.treeToValue(patched,Product.class);
    }
}
