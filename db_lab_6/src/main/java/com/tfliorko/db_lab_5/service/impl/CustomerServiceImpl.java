package com.tfliorko.db_lab_5.service.impl;

import com.tfliorko.db_lab_5.domain.Customer;
import com.tfliorko.db_lab_5.repository.CustomerRepository;
import com.tfliorko.db_lab_5.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll(){
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Integer id){

        RuntimeException RuntimeException = new RuntimeException("Couldn't update element with id:"+id);
        return customerRepository.findById(id).orElseThrow(() -> RuntimeException);
    }

    @Transactional
    public Customer create(Customer obj){
        customerRepository.save(obj);
        return obj;
    }

    @Transactional
    public void update(Integer id, Customer entity){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find element with id:"+id);
        Customer obj = customerRepository.findById(id).orElseThrow(() -> RuntimeException);
        obj.setBirthday(entity.getBirthday());
        obj.setFirstName(entity.getFirstName());
        obj.setSecondName(entity.getSecondName());
        customerRepository.save(obj);
    }

    @Transactional
    public void delete (Integer id){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find delete with id:"+id);
        Customer customer = customerRepository.findById(id).orElseThrow(() -> RuntimeException);
        customerRepository.delete(customer);
    }

    @Override
    public Optional<Customer> findByFirst_name(String first_name){
        return customerRepository.findCustomerByFirstName(first_name);
    }

    @Override
    public void create_10_customers(){
        customerRepository.create_10_customers();
    };

    @Override
    public void create_random_customer_table(){
        customerRepository.create_random_customer_table();
    };
}
