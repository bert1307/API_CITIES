package com.github.bert1307.citiesapi.states.entities;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;

import javax.persistence.*;
import java.util.List;

@Entity(name = "State")
@Table(name = "estado")
//Anotações Hibernate = mapeamento json da tabela DDD por meio da dependência implementada na build.gradle
@TypeDefs({
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class State {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;
    private String uf;
    private Integer ibge;

    //1st
    @Column(name = "pais")
    private Integer countryId;

    //2nd
    /*@ManyToOne (N estados para 1 país = relacionamento tabelas - Retorna a entity(o nome do pais) em vez só do Id
    @JoinColumn(name = "pais", referencedColumnName = "id") - PK = id pais; na tabela estado é FK;
    private Country country;*/

    @Type(type = "jsonb") //tipo de dado ddd
    @Basic(fetch = FetchType.LAZY)
    @Column(name = "ddd", columnDefinition = "jsonb")
    private List<Integer> ddd; //select com array

    public State() {
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getUf() {
        return uf;
    }
    public Integer getIbge() {
        return ibge;
    }
    //tabela de ddd = Array >>> tipo de dado (Json)
    public List<Integer> getDdd() {
        return ddd;
    }
    //1st
    public Integer getCountryId() {
        return countryId;
    }
    /*
    //2nd
    public Country getCountry() {
        return country;
    }*/
}
