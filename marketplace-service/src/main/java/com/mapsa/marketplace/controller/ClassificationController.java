package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Classification;
import com.mapsa.marketplace.repository.ClassificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
}
