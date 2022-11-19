package com.tfliorko.db_lab_5.controller;

import com.tfliorko.db_lab_5.domain.Tickets;
import com.tfliorko.db_lab_5.dto.TicketsDto;
import com.tfliorko.db_lab_5.dto.assembler.TicketsDtoAssembler;
import com.tfliorko.db_lab_5.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/tickets")
public class TicketsController {
    @Autowired
    TicketsService ticketsService;
    @Autowired
    TicketsDtoAssembler ticketsDtoAssembler;

    @GetMapping(value = "{ticketsId}")
    public ResponseEntity<TicketsDto> getTicket(@PathVariable Integer ticketsId){
        Tickets ticket = ticketsService.findById(ticketsId);
        TicketsDto ticketsDto = ticketsDtoAssembler.toModel(ticket);
        return  new ResponseEntity<>(ticketsDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public  ResponseEntity<CollectionModel<TicketsDto>> getAllTickets(){
        List<Tickets> tickets = ticketsService.findAll();
        CollectionModel<TicketsDto> ticketsDtos = ticketsDtoAssembler.toCollectionModel(tickets);
        return new ResponseEntity<>(ticketsDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/priority_pass/{priorityPass}")
    public ResponseEntity<CollectionModel<TicketsDto>> getTicketsByPriorityPass(@PathVariable byte priorityPass){
        List<Tickets> tickets = ticketsService.findByPriority_pass(priorityPass).stream().toList();
        CollectionModel<TicketsDto> ticketsDtos = ticketsDtoAssembler.toCollectionModel(tickets);
        return new ResponseEntity<>(ticketsDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<TicketsDto> addTicket(@RequestBody Tickets tickets) {
        Tickets newTicket = ticketsService.create(tickets);
        TicketsDto ticketsDto = ticketsDtoAssembler.toModel(newTicket);
        return new ResponseEntity<>(ticketsDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{ticketId}")
    public ResponseEntity<?> updateTicket(@RequestBody Tickets tickets, @PathVariable Integer ticketId) {
        ticketsService.update(ticketId, tickets);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{ticketId}")
    public ResponseEntity<?> deleteTicket(@PathVariable Integer ticketId) {
        ticketsService.delete(ticketId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/avg_ticket_price")
    public ResponseEntity<?> avg_ticket_price(){
        Float avgPrice = ticketsService.avg_ticket_price();
        return new ResponseEntity<>(avgPrice, HttpStatus.OK);
    }
}
