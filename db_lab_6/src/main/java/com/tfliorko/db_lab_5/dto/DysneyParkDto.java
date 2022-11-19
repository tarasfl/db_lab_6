package com.tfliorko.db_lab_5.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "dysneyPark", collectionRelation = "dysneyParks")
public class DysneyParkDto extends RepresentationModel<DysneyParkDto>{
    private final int id;
    private final String city;
    private final String street;
    private final Integer maxAmountOfVisitors;
}
