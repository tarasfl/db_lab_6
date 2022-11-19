package com.tfliorko.db_lab_5.service.impl;

import com.tfliorko.db_lab_5.domain.DysneyPark;
import com.tfliorko.db_lab_5.repository.DysneyParkRepository;
import com.tfliorko.db_lab_5.service.DysneyParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class DysneyParkServiceImpl implements DysneyParkService {
    @Autowired
    DysneyParkRepository dysneyParkRepository;

    @Override
    public List<DysneyPark> findAll(){
        return dysneyParkRepository.findAll();
    }

    @Override
    public DysneyPark findById(Integer id){

        RuntimeException RuntimeException = new RuntimeException("Couldn't update element with id:"+id);
        return dysneyParkRepository.findById(id).orElseThrow(() -> RuntimeException);
    }

    @Transactional
    public DysneyPark create(DysneyPark obj){
        dysneyParkRepository.save(obj);
        return obj;
    }

    @Transactional
    public void update(Integer id, DysneyPark entity){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find element with id:"+id);
        DysneyPark obj = dysneyParkRepository.findById(id).orElseThrow(() -> RuntimeException);
        obj.setCity(entity.getCity());
        obj.setStreet(entity.getStreet());
        obj.setMaxAmountOfVisitors(entity.getMaxAmountOfVisitors());
        dysneyParkRepository.save(obj);
    }

    @Transactional
    public void delete (Integer id){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find delete with id:"+id);
        DysneyPark obj = dysneyParkRepository.findById(id).orElseThrow(() -> RuntimeException);
        dysneyParkRepository.delete(obj);
    }

    @Override
    public Optional<DysneyPark> findByCity(String city){
        return dysneyParkRepository.findDysneyParkByCity(city);
    }

    @Override
    public void insert_into_dysney_park(String city, String street, Integer max_amount_of_visitors){
        dysneyParkRepository.insert_into_dysney_park(city, street, max_amount_of_visitors);
    }
}
