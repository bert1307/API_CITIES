package com.github.bert1307.citiesapi.cities.services;

import com.github.bert1307.citiesapi.cities.entities.City;
import com.github.bert1307.citiesapi.cities.repositories.CityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
//classe intermediária = regra de negócio para cálculo de distancia entre as cidades
//responsável por "pegar" as requisições do RestController >>> DB >>> RestController
@Service
//métodos

public class DistanceService {

    Logger log = LoggerFactory.getLogger(DistanceService.class);
    private final CityRepository cityRepository;

    public DistanceService(final CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    //options = @param city1 / @param city2 / @return
    public Double distanceByPointsInMiles(final Long city1, final Long city2) {
        log.info("nativePostgresInMiles({}, {})", city1, city2);
        return cityRepository.distanceByPoints(city1, city2);
    }

    public Double distanceByCubeInMeters(Long city1, Long city2) {
        log.info("distanceByCubeInMeters({}, {})", city1, city2);
        final List<City> cities = cityRepository.findAllById((Arrays.asList(city1, city2)));

        Point p1 = cities.get(0).getLocation();
        Point p2 = cities.get(1).getLocation();

        return cityRepository.distanceByCube(p1.getX(), p1.getY(), p2.getX(), p2.getY());
    }

}