package com.tfliorko.db_lab_5.service.impl;


import com.tfliorko.db_lab_5.repository.AttractionRepository;
import com.tfliorko.db_lab_5.service.AttractionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tfliorko.db_lab_5.domain.Attraction;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AttractionServiceImpl implements AttractionService {
    @Autowired
    AttractionRepository attractionRepository;

    @Override
    public List<Attraction> findAll(){
        return attractionRepository.findAll();
    }

    @Override
    public Attraction findById(Integer id){
        RuntimeException RuntimeException = new RuntimeException("Couldn't update element with id:"+id);
        return attractionRepository.findById(id).orElseThrow(() -> RuntimeException);
    }

    @Transactional
    public Attraction create(Attraction attraction){
        attractionRepository.save(attraction);
        return attraction;
    }

    @Transactional
    public void update(Integer id, Attraction entity){
        RuntimeException RuntimeException = new RuntimeException("Couldn't update element with id:"+id);
        Attraction attraction = attractionRepository.findById(id).orElseThrow(() -> RuntimeException);
        attraction.setName(entity.getName());
        attraction.setPriorityPass(entity.getPriorityPass());
        attraction.setMaxAmountOfVisitors(entity.getMaxAmountOfVisitors());
        attractionRepository.save(attraction);
    }

    @Transactional
    public void delete (Integer id){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find delete with id:"+id);
        Attraction attraction = attractionRepository.findById(id).orElseThrow(() -> RuntimeException);
        attractionRepository.delete(attraction);
    }

    @Override
    public Attraction findByName(String name){
        return attractionRepository.findAttractionByName(name);
    }
}
