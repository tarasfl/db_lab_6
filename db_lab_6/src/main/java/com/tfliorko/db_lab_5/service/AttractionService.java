package com.tfliorko.db_lab_5.service;

import com.tfliorko.db_lab_5.domain.Attraction;
import java.util.Optional;

public interface AttractionService extends GeneralService<Attraction, Integer>{
    Attraction findByName(String name);
}
