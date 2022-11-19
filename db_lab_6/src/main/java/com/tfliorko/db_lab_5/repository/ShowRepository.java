package com.tfliorko.db_lab_5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tfliorko.db_lab_5.domain.Show;

import java.util.Optional;


@Repository
public interface ShowRepository extends JpaRepository<Show, Integer> {
    Optional<Show> findShowsByName(String name);
}
