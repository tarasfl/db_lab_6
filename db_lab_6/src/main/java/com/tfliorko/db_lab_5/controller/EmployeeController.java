package com.tfliorko.db_lab_5.controller;

import com.tfliorko.db_lab_5.domain.Employee;
import com.tfliorko.db_lab_5.dto.EmployeeDto;
import com.tfliorko.db_lab_5.dto.assembler.EmployeeDtoAssembler;
import com.tfliorko.db_lab_5.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/employees")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @Autowired
    EmployeeDtoAssembler employeeDtoAssembler;

    @GetMapping(value = "{employeeId}")
    public ResponseEntity<EmployeeDto> getEmployee(@PathVariable Integer employeeId){
        Employee employee = employeeService.findById(employeeId);
        EmployeeDto employeeDto = employeeDtoAssembler.toModel(employee);
        return  new ResponseEntity<>(employeeDto, HttpStatus.OK);
    }

    @GetMapping(value = "")
    public  ResponseEntity<CollectionModel<EmployeeDto>> getAllEmployees(){
        List<Employee> employees = employeeService.findAll();
        CollectionModel<EmployeeDto> employeeDtos = employeeDtoAssembler.toCollectionModel(employees);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }


    @GetMapping(value = "/type_of_job/{typeOfJob}")
    public  ResponseEntity<CollectionModel<EmployeeDto>> getAllEmployeesByTypeOfJob(String typeOfJob){
        List<Employee> employees = employeeService.findByType_of_job(typeOfJob).stream().toList();
        CollectionModel<EmployeeDto> employeeDtos = employeeDtoAssembler.toCollectionModel(employees);
        return new ResponseEntity<>(employeeDtos, HttpStatus.OK);
    }

    @PostMapping(value = "")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody Employee employee) {
        Employee newEmployee = employeeService.create(employee);
        EmployeeDto employeeDto = employeeDtoAssembler.toModel(newEmployee);
        return new ResponseEntity<>(employeeDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{employeeId}")
    public ResponseEntity<?> updateEmployee(@RequestBody Employee employee, @PathVariable Integer employeeId) {
        employeeService.update(employeeId, employee);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.delete(employeeId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/employee_show_relation/{employee_first_name}/{show_name}")
    public ResponseEntity<?> insert_into_employee_show(String employee_first_name, String show_name){
        employeeService.insert_into_employee_show( employee_first_name, show_name);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
