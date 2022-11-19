package com.tfliorko.db_lab_5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.sql.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "customer", collectionRelation = "customers")
public class CustomerDto  extends RepresentationModel<CustomerDto>{
    private final int id;
    private final String firstName;
    private final String secondName;
    private final Date birthday;
}
