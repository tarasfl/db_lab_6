package com.tfliorko.db_lab_5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tfliorko.db_lab_5.domain.DysneyPark;
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
@Relation(itemRelation = "show", collectionRelation = "shows")
public class ShowDto extends RepresentationModel<ShowDto> {
    private final String name;
    private final int id;
    private final Date date;
    private final int maxAmountOfVisitors;
    private final DysneyPark dysneyPark;
}
