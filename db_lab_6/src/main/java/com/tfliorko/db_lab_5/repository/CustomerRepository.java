package com.tfliorko.db_lab_5.repository;

import com.tfliorko.db_lab_5.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findCustomerByFirstName(String first_name);

    @Procedure("create_10_customers")
    void create_10_customers();

    @Procedure("create_random_customer_table")
    void create_random_customer_table();
}
