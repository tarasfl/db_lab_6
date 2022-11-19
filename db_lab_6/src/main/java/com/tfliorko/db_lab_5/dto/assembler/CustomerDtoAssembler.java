package com.tfliorko.db_lab_5.dto.assembler;

import com.tfliorko.db_lab_5.controller.CustomerController;
import com.tfliorko.db_lab_5.domain.Customer;
import com.tfliorko.db_lab_5.dto.CustomerDto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class CustomerDtoAssembler  implements RepresentationModelAssembler<Customer, CustomerDto>  {
    @Override
    public CustomerDto toModel(Customer entity){
        CustomerDto customerDto = CustomerDto.builder()
                .id(entity.getId())
                .firstName(entity.getFirstName())
                .birthday(entity.getBirthday())
                .secondName(entity.getSecondName())
                .build();
        Link selfLink = linkTo(methodOn(CustomerController.class).getCustomer(customerDto.getId())).withSelfRel();
        customerDto.add(selfLink);
        return customerDto;
    }

    @Override
    public CollectionModel<CustomerDto> toCollectionModel(Iterable<? extends Customer> entities){
        CollectionModel<CustomerDto> customerDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        Link selfLink = linkTo(methodOn(CustomerController.class).getAllCustomers()).withSelfRel();
        customerDtos.add(selfLink);
        return customerDtos;
    }

    public CollectionModel<CustomerDto> toCollectionModel(Iterable<? extends Customer> entities, Link link) {
        CollectionModel<CustomerDto> customerDtos = RepresentationModelAssembler.super.toCollectionModel(entities);
        customerDtos.add(link);
        return customerDtos;
    }
}
