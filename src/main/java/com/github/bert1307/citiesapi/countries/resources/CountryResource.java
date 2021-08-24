package com.github.bert1307.citiesapi.countries.resources;

import com.github.bert1307.citiesapi.countries.entities.Country;
import com.github.bert1307.citiesapi.countries.repositories.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/countries") //chamada Spring pelo GET
public class CountryResource {

    //API = DB é um repositório de dados
    private CountryRepository repository;

    //construtor para o Spring "injetar" as dependências do JPA
    public CountryResource(CountryRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    //Pageable = Retorna a página de países do DB pelo findAll()
    public Page<Country> countries(Pageable page) {
        return repository.findAll(page);
    }

    //Retorno somente de um pais
    // findById return é Optional (pode ou não existir o país)
    @GetMapping("/{id}")
    public ResponseEntity<Country> getOne(@PathVariable Long id) {
        Optional<Country> optional = repository.findById(id);

        //retorno status da requisição no Postman
        if (optional.isPresent()) {
            return ResponseEntity.ok().body(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
