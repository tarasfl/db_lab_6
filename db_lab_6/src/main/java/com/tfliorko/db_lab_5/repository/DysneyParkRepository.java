package com.tfliorko.db_lab_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import com.tfliorko.db_lab_5.domain.DysneyPark;

import java.util.List;
import java.util.Optional;

@Repository
public interface DysneyParkRepository extends JpaRepository<DysneyPark, Integer> {
    Optional<DysneyPark> findDysneyParkByCity(String city);

    @Procedure("insert_into_dysney_park")
    void insert_into_dysney_park(String city, String street, Integer max_amount_of_visitors);
}
