package com.tfliorko.db_lab_5.controller;

import com.tfliorko.db_lab_5.domain.Customer;
import com.tfliorko.db_lab_5.dto.CustomerDto;
import com.tfliorko.db_lab_5.dto.assembler.CustomerDtoAssembler;
import com.tfliorko.db_lab_5.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;
    @Autowired
    CustomerDtoAssembler customerDtoAssembler;

    @GetMapping(value = "{customerId}")
    public ResponseEntity<CustomerDto> getCustomer(@PathVariable Integer customerId){
        Customer customer = customerService.findById(customerId);
        CustomerDto customerDto = customerDtoAssembler.toModel(customer);
        return  new ResponseEntity<>(customerDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public  ResponseEntity<CollectionModel<CustomerDto>> getAllCustomers(){
        List<Customer> customers = customerService.findAll();
        CollectionModel<CustomerDto> customerDtos = customerDtoAssembler.toCollectionModel(customers);
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/first_name/{first_name}")
    public  ResponseEntity<CollectionModel<CustomerDto>> getAllCustomersByFirstName(@PathVariable String first_name){
        List<Customer> customers = customerService.findByFirst_name(first_name).stream().toList();
        CollectionModel<CustomerDto> customerDtos = customerDtoAssembler.toCollectionModel(customers);
        return new ResponseEntity<>(customerDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody Customer customer) {
        Customer newCustomer = customerService.create(customer);
        CustomerDto customerDto = customerDtoAssembler.toModel(newCustomer);
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @PathVariable Integer customerId) {
        customerService.update(customerId, customer);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<?> deleteCustomer(@PathVariable Integer customerId) {
        customerService.delete(customerId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/create_10_customers")
    public ResponseEntity<?> create_10_customers(){
        customerService.create_10_customers();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/create_random_customer_table")
    public ResponseEntity<?> create_random_customer_table(){
        customerService.create_random_customer_table();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
