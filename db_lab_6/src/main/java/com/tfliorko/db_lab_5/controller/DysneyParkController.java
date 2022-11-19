package com.tfliorko.db_lab_5.controller;

import com.tfliorko.db_lab_5.domain.DysneyPark;
import com.tfliorko.db_lab_5.dto.DysneyParkDto;
import com.tfliorko.db_lab_5.dto.assembler.DysneyParkDtoAssembler;
import com.tfliorko.db_lab_5.service.DysneyParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/dysneyParks")
public class DysneyParkController {
    @Autowired
    DysneyParkService dysneyParkService;
    @Autowired
    DysneyParkDtoAssembler dysneyParkDtoAssembler;

    @GetMapping(value = "{dysneyParkId}")
    public ResponseEntity<DysneyParkDto> getDysneyPark(@PathVariable Integer dysneyParkId){
        DysneyPark dysneyPark = dysneyParkService.findById(dysneyParkId);
        DysneyParkDto dysneyParkDto = dysneyParkDtoAssembler.toModel(dysneyPark);
        return  new ResponseEntity<>(dysneyParkDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public  ResponseEntity<CollectionModel<DysneyParkDto>> getAllDysneyParks(){
        List<DysneyPark> dysneyParks = dysneyParkService.findAll();
        CollectionModel<DysneyParkDto> dysneyParkDtos = dysneyParkDtoAssembler.toCollectionModel(dysneyParks);
        return new ResponseEntity<>(dysneyParkDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/city/{city}")
    public ResponseEntity<CollectionModel<DysneyParkDto>> getDysneyParkByName(@PathVariable String city){
        List<DysneyPark> dysneyParks = dysneyParkService.findByCity(city).stream().toList();
        CollectionModel<DysneyParkDto> dysneyParkDtos = dysneyParkDtoAssembler.toCollectionModel(dysneyParks);
        return new ResponseEntity<>(dysneyParkDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<DysneyParkDto> addDysneyPark (@RequestBody DysneyPark dysneyPark) {
        DysneyPark newDysneyPark = dysneyParkService.create(dysneyPark);
        DysneyParkDto dysneyParkDto = dysneyParkDtoAssembler.toModel(newDysneyPark);
        return new ResponseEntity<>(dysneyParkDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{dysneyParkId}")
    public ResponseEntity<?> updateDysneyPark (@RequestBody DysneyPark dysneyPark, @PathVariable Integer dysneyParkId) {
        dysneyParkService.update(dysneyParkId, dysneyPark);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{dysneyParkId}")
    public ResponseEntity<?> deleteDysneyPark(@PathVariable Integer dysneyParkId) {
        dysneyParkService.delete(dysneyParkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/insert_procedure/{city}/{street}/{max_amount_of_visitors}")
    public ResponseEntity<?> insert_into_dysney_park(String city, String street, Integer max_amount_of_visitors){
        dysneyParkService.insert_into_dysney_park(city, street, max_amount_of_visitors);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}