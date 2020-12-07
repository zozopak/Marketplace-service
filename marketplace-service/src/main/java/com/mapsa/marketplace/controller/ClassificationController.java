package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Category;
import com.mapsa.marketplace.model.Classification;
import com.mapsa.marketplace.repository.ClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("classification")
public class ClassificationController {
    @Autowired
    private ClassificationRepository classificationRepository;

    @GetMapping
    public List<Classification> getList(Pageable pageable) {
        if (pageable == null)
            return classificationRepository.findAll();
        else
            return classificationRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody Classification classification) {
        classificationRepository.save(classification);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id) {
        classificationRepository.deleteById(id);
        return "deleted!";
    }

    @GetMapping("/{id}")
    public Optional<Classification> getOne(@PathVariable long id) {
        return classificationRepository.findById(id);
    }


    @PatchMapping(path="/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<Classification> updateClassification(@PathVariable long id, @RequestBody JsonPatch patch){
        try {
          Classification classification = classificationRepository.findById(id).orElseThrow( NullPointerException::new);

           Classification classificationPatched = applyPatchToClassification(patch,classification);

            classificationRepository.save(classificationPatched);
            return ResponseEntity.ok(classificationPatched);
        }catch(JsonPatchException | JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private Classification applyPatchToClassification(JsonPatch patch, Classification classification) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(classification,JsonNode.class));

        return objectMapper.treeToValue(patched,Classification.class);
    }
}
