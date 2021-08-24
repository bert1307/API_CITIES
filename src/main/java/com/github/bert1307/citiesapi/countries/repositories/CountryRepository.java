package com.github.bert1307.citiesapi.countries.repositories;

import com.github.bert1307.citiesapi.countries.entities.Country;
import org.springframework.data.jpa.repository.JpaRepository;

//Spring Data habilita o método findAll()/findById() no CountryResource
public interface CountryRepository extends JpaRepository<Country, Long> {

}
