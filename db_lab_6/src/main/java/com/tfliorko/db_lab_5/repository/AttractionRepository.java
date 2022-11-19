package com.tfliorko.db_lab_5.repository;

import com.tfliorko.db_lab_5.domain.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Integer> {

    Attraction findAttractionByName (String name);
}
