package com.tfliorko.db_lab_5.controller;

import com.tfliorko.db_lab_5.domain.Attraction;
import com.tfliorko.db_lab_5.dto.AttractionDto;
import com.tfliorko.db_lab_5.dto.assembler.AttractionDtoAssembler;
import com.tfliorko.db_lab_5.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/api/attractions")
public class AttractionController {
    @Autowired
    AttractionService attractionService;
    @Autowired
    AttractionDtoAssembler attractionDtoAssembler;

    @GetMapping(value = "{attractionId}")
    public ResponseEntity<AttractionDto> getAttraction(@PathVariable Integer attractionId){
        Attraction attraction = attractionService.findById(attractionId);
        AttractionDto attractionDto = attractionDtoAssembler.toModel(attraction);
        return  new ResponseEntity<>(attractionDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public  ResponseEntity<CollectionModel<AttractionDto>> getAllAttractions(){
        List<Attraction> attractions = attractionService.findAll();
        CollectionModel<AttractionDto> attractionDtos = attractionDtoAssembler.toCollectionModel(attractions);
        return new ResponseEntity<>(attractionDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/name/{name}")
    public ResponseEntity<AttractionDto> getAttractionByName(@PathVariable String name){
        Attraction attraction = attractionService.findByName(name);
        AttractionDto attractionDto = attractionDtoAssembler.toModel(attraction);
        return  new ResponseEntity<>(attractionDto, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<AttractionDto> addAttraction(@RequestBody Attraction attraction) {
        Attraction newAttraction = attractionService.create(attraction);
        AttractionDto attractionDto = attractionDtoAssembler.toModel(newAttraction);
        return new ResponseEntity<>(attractionDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{attractionId}")
    public ResponseEntity<?> updateAttraction(@RequestBody Attraction attraction, @PathVariable Integer attractionId) {
        attractionService.update(attractionId, attraction);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{attractionId}")
    public ResponseEntity<?> deleteAttraction(@PathVariable Integer attractionId) {
        attractionService.delete(attractionId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
