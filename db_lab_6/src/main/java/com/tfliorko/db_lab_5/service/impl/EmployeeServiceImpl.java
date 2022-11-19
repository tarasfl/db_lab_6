package com.tfliorko.db_lab_5.service.impl;

import com.tfliorko.db_lab_5.domain.Employee;
import com.tfliorko.db_lab_5.repository.EmployeeRepository;
import com.tfliorko.db_lab_5.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll(){
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(Integer id){

        RuntimeException RuntimeException = new RuntimeException("Couldn't update element with id:"+id);
        return employeeRepository.findById(id).orElseThrow(() -> RuntimeException);
    }

    @Transactional
    public Employee create(Employee obj){
        employeeRepository.save(obj);
        return obj;
    }

    @Transactional
    public void update(Integer id, Employee entity){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find element with id:"+id);
        Employee obj = employeeRepository.findById(id).orElseThrow(() -> RuntimeException);
        obj.setTypeOfJob(entity.getTypeOfJob());
        obj.setFirstName(entity.getFirstName());
        obj.setSecondName(entity.getSecondName());
        employeeRepository.save(obj);
    }

    @Transactional
    public void delete (Integer id){
        RuntimeException RuntimeException = new RuntimeException("Couldn't find delete with id:"+id);
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> RuntimeException);
        employeeRepository.delete(employee);
    }

    @Override
    public Optional<Employee> findByType_of_job(String type_of_job){
        return employeeRepository.findEmployeeByTypeOfJob(type_of_job);
    }

    @Override
    public void insert_into_employee_show(String employee_first_name, String show_name){
        employeeRepository.insert_into_employee_show(employee_first_name, show_name);
    }

}
