package com.tfliorko.db_lab_5.controller;

import com.tfliorko.db_lab_5.domain.Show;
import com.tfliorko.db_lab_5.dto.ShowDto;
import com.tfliorko.db_lab_5.dto.assembler.ShowDtoAssembler;
import com.tfliorko.db_lab_5.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/shows")
public class ShowController {
    @Autowired
    ShowService showService;
    @Autowired
    ShowDtoAssembler showDtoAssembler;

    @GetMapping(value = "{showId}")
    public ResponseEntity<ShowDto> getShow(@PathVariable Integer showId){
        Show show = showService.findById(showId);
        ShowDto showDto = showDtoAssembler.toModel(show);
        return  new ResponseEntity<>(showDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public  ResponseEntity<CollectionModel<ShowDto>> getAllShows(){
        List<Show> shows = showService.findAll();
        CollectionModel<ShowDto> showDtos = showDtoAssembler.toCollectionModel(shows);
        return new ResponseEntity<>(showDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/name/{name}")
    public ResponseEntity<CollectionModel<ShowDto>> getShowByName(@PathVariable String name){
        List<Show> shows = showService.findByName(name).stream().toList();
        CollectionModel<ShowDto> showDtos = showDtoAssembler.toCollectionModel(shows);
        return new ResponseEntity<>(showDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<ShowDto> addShow(@RequestBody Show show) {
        Show newShow = showService.create(show);
        ShowDto showDto = showDtoAssembler.toModel(newShow);
        return new ResponseEntity<>(showDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{showId}")
    public ResponseEntity<?> updateShow(@RequestBody Show show, @PathVariable Integer showId) {
        showService.update(showId, show);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{showId}")
    public ResponseEntity<?> deleteShow(@PathVariable Integer showId) {
        showService.delete(showId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
