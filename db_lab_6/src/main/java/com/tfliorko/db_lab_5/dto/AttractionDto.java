package com.tfliorko.db_lab_5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tfliorko.db_lab_5.domain.DysneyPark;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "attraction", collectionRelation = "attractions")
public class AttractionDto extends RepresentationModel<AttractionDto> {
    private final int id;
    private final String name;
    private final Byte priorityPass;
    private final int maxAmountOfVisitors;
    private final DysneyPark dysneyPark;
}
