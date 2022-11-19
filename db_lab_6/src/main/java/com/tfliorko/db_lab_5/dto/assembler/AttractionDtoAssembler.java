package com.tfliorko.db_lab_5.dto.assembler;

import com.tfliorko.db_lab_5.controller.AttractionController;
import com.tfliorko.db_lab_5.domain.Attraction;
import com.tfliorko.db_lab_5.dto.AttractionDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class AttractionDtoAssembler implements RepresentationModelAssembler<Attraction, AttractionDto> {

    @Override
    public AttractionDto toModel(Attraction entity){
        AttractionDto attractionDto = AttractionDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .maxAmountOfVisitors(entity.getMaxAmountOfVisitors())
                .priorityPass(entity.getPriorityPass())
                .dysneyPark(entity.getDysneyPark())
                .build();
        Link selfLink = linkTo(methodOn(AttractionController.class).getAttraction(attractionDto.getId())).withSelfRel();
        attractionDto.add(selfLink);
        return attractionDto;
    }

    @Override
    public CollectionModel<AttractionDto> toCollectionModel(Iterable<? extends Attraction> entities){
        CollectionModel<AttractionDto> attractionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(AttractionController.class).getAllAttractions()).withSelfRel();
        attractionDtos.add(selfLink);
        return attractionDtos;
    }

    public CollectionModel<AttractionDto> toCollectionModel(Iterable<? extends Attraction> entities, Link link) {
        CollectionModel<AttractionDto> attractionDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        attractionDtos.add(link);
        return attractionDtos;
    }
}
