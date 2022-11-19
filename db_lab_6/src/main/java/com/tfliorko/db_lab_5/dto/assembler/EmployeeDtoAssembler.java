package com.tfliorko.db_lab_5.dto.assembler;

import com.tfliorko.db_lab_5.controller.EmployeeController;
import com.tfliorko.db_lab_5.domain.Employee;
import com.tfliorko.db_lab_5.dto.EmployeeDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EmployeeDtoAssembler implements RepresentationModelAssembler<Employee, EmployeeDto> {
    @Override
    public EmployeeDto toModel(Employee entity){
        EmployeeDto employeeDto = EmployeeDto.builder()
                .id(entity.getId())
                .attraction(entity.getAttraction())
                .secondName(entity.getSecondName())
                .firstName(entity.getFirstName())
                .typeOfJob(entity.getTypeOfJob())
                .build();
        Link selfLink = linkTo(methodOn(EmployeeController.class).getEmployee(employeeDto.getId())).withSelfRel();
        employeeDto.add(selfLink);
        return employeeDto;
    }

    @Override
    public CollectionModel<EmployeeDto> toCollectionModel(Iterable<? extends Employee> entities){
        CollectionModel<EmployeeDto> employeeDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel();
        employeeDtos.add(selfLink);
        return employeeDtos;
    }

    public CollectionModel<EmployeeDto> toCollectionModel(Iterable<? extends Employee> entities, Link link) {
        CollectionModel<EmployeeDto> employeeDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        employeeDtos.add(link);
        return employeeDtos;
    }
}
