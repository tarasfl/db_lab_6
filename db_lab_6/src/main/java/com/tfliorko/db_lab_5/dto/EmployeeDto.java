package com.tfliorko.db_lab_5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tfliorko.db_lab_5.domain.Attraction;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "employee", collectionRelation = "employees")
public class EmployeeDto extends RepresentationModel<EmployeeDto>{
    private final int id;
    private final String firstName;
    private final String secondName;
    private final String typeOfJob;
    private final Attraction attraction;
}
