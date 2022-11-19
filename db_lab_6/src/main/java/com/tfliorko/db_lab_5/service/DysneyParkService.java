package com.tfliorko.db_lab_5.service;

import com.tfliorko.db_lab_5.domain.DysneyPark;

import java.util.List;
import java.util.Optional;

public interface DysneyParkService extends GeneralService<DysneyPark, Integer>{

    Optional<DysneyPark> findByCity(String city);

    void insert_into_dysney_park(String city, String street, Integer max_amount_of_visitors);
}