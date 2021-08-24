package com.github.bert1307.citiesapi.states.repositories;

import com.github.bert1307.citiesapi.states.entities.State;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StateRepository extends JpaRepository<State, Long> {

}