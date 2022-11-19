package com.tfliorko.db_lab_5.repository;

import com.tfliorko.db_lab_5.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findEmployeeByTypeOfJob(String type_of_job);

    @Procedure("insert_into_employee_show")
    void insert_into_employee_show(String employee_first_name, String show_name);
}
