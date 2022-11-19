package com.tfliorko.db_lab_5.dto.assembler;

import com.tfliorko.db_lab_5.controller.ShowController;
import com.tfliorko.db_lab_5.domain.Show;
import com.tfliorko.db_lab_5.dto.ShowDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ShowDtoAssembler  implements RepresentationModelAssembler<Show, ShowDto> {
    @Override
    public ShowDto toModel(Show entity){
        ShowDto showDto = ShowDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .maxAmountOfVisitors(entity.getMaxAmountOfVisitors())
                .date(entity.getDate())
                .dysneyPark(entity.getDysneyPark())
                .build();
        Link selfLink = linkTo(methodOn(ShowController.class).getShow(showDto.getId())).withSelfRel();
        showDto.add(selfLink);
        return showDto;
    }

    @Override
    public CollectionModel<ShowDto> toCollectionModel(Iterable<? extends Show> entities){
        CollectionModel<ShowDto> showDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(ShowController.class).getAllShows()).withSelfRel();
        showDtos.add(selfLink);
        return showDtos;
    }

    public CollectionModel<ShowDto> toCollectionModel(Iterable<? extends Show> entities, Link link) {
        CollectionModel<ShowDto> showDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        showDtos.add(link);
        return showDtos;
    }
}
