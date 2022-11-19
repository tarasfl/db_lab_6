package com.tfliorko.db_lab_5.service.impl;

import com.tfliorko.db_lab_5.domain.Tickets;
import com.tfliorko.db_lab_5.repository.TicketsRepository;
import com.tfliorko.db_lab_5.service.TicketsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TicketsServiceImpl implements TicketsService {
    @Autowired
    TicketsRepository ticketsRepository;

    @Override
    public List<Tickets> findAll(){
        return ticketsRepository.findAll();
    }

    @Override
    public Tickets findById(Integer id){

        RuntimeException RuntimeException = new RuntimeException("Couldn't update element with id:"+id);
        return ticketsRepository.findById(id).orElseThrow(() -> RuntimeException);
    }

    @Transactional
    public Tickets create(Tickets obj){
        ticketsRepository.save(obj);
        return obj;
    }

    @Transactional
    public void update(Integer id, Tickets entity){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find element with id:"+id);
        Tickets obj = ticketsRepository.findById(id).orElseThrow(() -> RuntimeException);
        obj.setCustomer(entity.getCustomer());
        obj.setPrice(entity.getPrice());
        obj.setUseDate(entity.getUseDate());
        obj.setPriorityPass(obj.getPriorityPass());
        ticketsRepository.save(obj);
    }

    @Transactional
    public void delete (Integer id){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find delete with id:"+id);
        Tickets t = ticketsRepository.findById(id).orElseThrow(() -> RuntimeException);
        ticketsRepository.delete(t);
    }

    @Override
    public Optional<Tickets> findByPriority_pass(byte priority_pass){
        return ticketsRepository.findTicketsByPriorityPass(priority_pass);
    }

    @Override
    public Float avg_ticket_price(){
        return ticketsRepository.avg_ticket_price();
    };
}
