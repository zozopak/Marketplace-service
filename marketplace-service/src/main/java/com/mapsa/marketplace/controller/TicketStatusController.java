package com.mapsa.marketplace.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.mapsa.marketplace.model.Orders;
import com.mapsa.marketplace.model.TicketStatus;
import com.mapsa.marketplace.repository.TicketStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PatchMapping(value = "/{id}",consumes = "application/json-patch+json")
    public ResponseEntity<TicketStatus> updateTicketStatus(@PathVariable long id, JsonPatch patch){
        try{
           TicketStatus ticketStatus=ticketStatusRepository.findById(id).orElseThrow(NullPointerException::new);
           TicketStatus ticketStatusPatched=applyPatchToTicketStatus(patch,ticketStatus);
           ticketStatusRepository.save(ticketStatusPatched);
            return ResponseEntity.ok(ticketStatusPatched);
        }catch (JsonPatchException | JsonProcessingException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }catch (NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    private TicketStatus applyPatchToTicketStatus(JsonPatch patch, TicketStatus ticketStatus) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        JsonNode patched=patch.apply(objectMapper.convertValue(ticketStatus,JsonNode.class));
        return objectMapper.treeToValue(patched,TicketStatus.class);
    }
}
