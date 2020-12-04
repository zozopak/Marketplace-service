package com.mapsa.marketplace.controller;

import com.mapsa.marketplace.model.TicketStatus;
import com.mapsa.marketplace.repository.TicketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ticketstatus")
public class TicketStatusController {
    @Autowired
    private TicketStatusRepository ticketStatusRepository;

    @GetMapping
    public List<TicketStatus> getList(Pageable pageable){
        if (pageable == null)
            return ticketStatusRepository.findAll();
        else
            return ticketStatusRepository.findAll(pageable).toList();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String save(@RequestBody TicketStatus ticketStatus){
        ticketStatusRepository.save(ticketStatus);
        return "saved!";
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String delete(@PathVariable long id){
        ticketStatusRepository.deleteById(id);
        return "deleted!";
    }

   @GetMapping("/{id}")
    public Optional<TicketStatus> getOne(@PathVariable long id){
        return ticketStatusRepository.findById(id);

   }
}
