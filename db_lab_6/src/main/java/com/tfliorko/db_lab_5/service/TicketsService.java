package com.tfliorko.db_lab_5.service;

import com.tfliorko.db_lab_5.domain.Tickets;
import java.util.Optional;

public interface TicketsService extends GeneralService<Tickets, Integer>{
    Optional<Tickets> findByPriority_pass(byte priority_pass);

    Float avg_ticket_price();
}
