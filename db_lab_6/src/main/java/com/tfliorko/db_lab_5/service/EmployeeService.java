package com.tfliorko.db_lab_5.service;


import com.tfliorko.db_lab_5.domain.Employee;
import java.util.Optional;

public interface EmployeeService extends GeneralService <Employee, Integer>{
    Optional<Employee> findByType_of_job(String type_of_job);
    void insert_into_employee_show(String employee_first_name, String show_name);
}