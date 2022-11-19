package com.tfliorko.db_lab_5.service;

import com.tfliorko.db_lab_5.domain.Customer;
import java.util.Optional;

public interface CustomerService extends GeneralService <Customer, Integer>{
    Optional<Customer> findByFirst_name(String first_name);

    void create_10_customers();
    void create_random_customer_table();
}