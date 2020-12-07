package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Lineitem;
import com.mapsa.marketplace.repository.LineitemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("lineitem")
public class LIneitemController {
    @Autowired
    private LineitemRepository lineitemRepository;

    @GetMapping
    public List<Lineitem> getList(Pageable pageable) {
        if (pageable == null)
            return lineitemRepository.findAll();
        else
            return lineitemRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Lineitem lineitem) {
        lineitemRepository.save(lineitem);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id) {
        lineitemRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Lineitem> getOne(@PathVariable long id) {
        return lineitemRepository.findById(id);
    }


    @PatchMapping(value = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Lineitem> updateLineitem(@PathVariable long id, JsonPatch patch){
        try {
            Lineitem lineitem=lineitemRepository.findById(id).orElseThrow(NullPointerException::new);
            Lineitem lineitemPatched=applyPatchToLineitem(patch,lineitem);
            lineitemRepository.save(lineitemPatched);
            return ResponseEntity.ok(lineitemPatched);
        }catch (JsonPatchException | JsonProcessingException e){
          return   ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
           return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Lineitem applyPatchToLineitem(JsonPatch patch, Lineitem lineitem) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(lineitem,JsonNode.class));
        return objectMapper.treeToValue(patched,Lineitem.class);
    }
}