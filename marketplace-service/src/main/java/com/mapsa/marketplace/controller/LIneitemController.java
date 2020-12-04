package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.Lineitem;
import com.mapsa.marketplace.repository.LineitemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
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
}
