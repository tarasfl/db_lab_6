package com.tfliorko.db_lab_5.dto.assembler;

import com.tfliorko.db_lab_5.controller.DysneyParkController;
import com.tfliorko.db_lab_5.domain.Tickets;
import com.tfliorko.db_lab_5.dto.TicketsDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TicketsDtoAssembler implements RepresentationModelAssembler<Tickets, TicketsDto> {
    @Override
    public TicketsDto toModel(Tickets entity){
        TicketsDto ticketsDto = TicketsDto.builder()
                .id(entity.getId())
                .useDate(entity.getUseDate())
                .customer(entity.getCustomer())
                .priorityPass(entity.getPriorityPass())
                .dysneyPark(entity.getDysneyPark())
                .price(entity.getPrice())
                .build();
        Link selfLink = linkTo(methodOn(DysneyParkController.class).getDysneyPark(ticketsDto.getId())).withSelfRel();
        ticketsDto.add(selfLink);
        return ticketsDto;
    }

    @Override
    public CollectionModel<TicketsDto> toCollectionModel(Iterable<? extends Tickets> entities){
        CollectionModel<TicketsDto> ticketsDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DysneyParkController.class).getAllDysneyParks()).withSelfRel();
        ticketsDtos.add(selfLink);
        return ticketsDtos;
    }

    public CollectionModel<TicketsDto> toCollectionModel(Iterable<? extends Tickets> entities, Link link) {
        CollectionModel<TicketsDto> ticketsDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        ticketsDtos.add(link);
        return ticketsDtos;
    }
}
