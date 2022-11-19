package com.tfliorko.db_lab_5.dto.assembler;

import com.tfliorko.db_lab_5.controller.DysneyParkController;
import com.tfliorko.db_lab_5.domain.DysneyPark;
import com.tfliorko.db_lab_5.dto.DysneyParkDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DysneyParkDtoAssembler implements RepresentationModelAssembler<DysneyPark, DysneyParkDto> {
    @Override
    public DysneyParkDto toModel(DysneyPark entity){
        DysneyParkDto dysneyParkDto = DysneyParkDto.builder()
                .id(entity.getId())
                .city(entity.getCity())
                .maxAmountOfVisitors(entity.getMaxAmountOfVisitors())
                .street(entity.getStreet())
                .build();
        Link selfLink = linkTo(methodOn(DysneyParkController.class).getDysneyPark(dysneyParkDto.getId())).withSelfRel();
        dysneyParkDto.add(selfLink);
        return dysneyParkDto;
    }

    @Override
    public CollectionModel<DysneyParkDto> toCollectionModel(Iterable<? extends DysneyPark> entities){
        CollectionModel<DysneyParkDto> dysneyParkDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(DysneyParkController.class).getAllDysneyParks()).withSelfRel();
        return dysneyParkDtos;
    }

    public CollectionModel<DysneyParkDto> toCollectionModel(Iterable<? extends DysneyPark> entities, Link link) {
        CollectionModel<DysneyParkDto> dysneyParkDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        dysneyParkDtos.add(link);
        return dysneyParkDtos;
    }
}
