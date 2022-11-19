package com.tfliorko.db_lab_5.service.impl;

import com.tfliorko.db_lab_5.domain.Show;
import com.tfliorko.db_lab_5.repository.ShowRepository;
import com.tfliorko.db_lab_5.service.ShowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ShowServiceImpl implements ShowService {
    @Autowired
    ShowRepository showRepository;

    @Override
    public List<Show> findAll(){
        return showRepository.findAll();
    }

    @Override
    public Show findById(Integer id){

        RuntimeException RuntimeException = new RuntimeException("Couldn't update element with id:"+id);
        return showRepository.findById(id).orElseThrow(() -> RuntimeException);
    }

    @Transactional
    public Show create(Show obj){
        showRepository.save(obj);
        return obj;
    }

    @Transactional
    public void update(Integer id, Show entity){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find element with id:"+id);
        Show obj = showRepository.findById(id).orElseThrow(() -> RuntimeException);
        obj.setDate(entity.getDate());
        obj.setMaxAmountOfVisitors(entity.getMaxAmountOfVisitors());
        obj.setName(entity.getName());
        showRepository.save(obj);
    }

    @Transactional
    public void delete (Integer id){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find delete with id:"+id);
        Show show = showRepository.findById(id).orElseThrow(() -> RuntimeException);
        showRepository.delete(show);
    }

    @Override
    public Optional<Show> findByName(String name){
        return showRepository.findShowsByName(name);
    }
}
