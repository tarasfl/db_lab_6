package com.tfliorko.db_lab_5.service;

import com.tfliorko.db_lab_5.domain.Show;
import java.util.Optional;

public interface ShowService extends GeneralService<Show, Integer> {
    Optional<Show> findByName(String name);
}