package com.tfliorko.db_lab_5.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.tfliorko.db_lab_5.domain.Customer;
import com.tfliorko.db_lab_5.domain.DysneyPark;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.sql.Date;

@Builder
@Getter
@EqualsAndHashCode(callSuper = false)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Relation(itemRelation = "ticket", collectionRelation = "tickets")
public class TicketsDto extends RepresentationModel<TicketsDto> {
    private final int id;
    private final BigDecimal price;
    private final byte priorityPass;
    private final Date useDate;
    private final DysneyPark dysneyPark;
    private final Customer customer;
}
