package com.github.bert1307.citiesapi.countries.entities;

//JPA = persistência de dados (hibernate)
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

//mapeamento de todas as tabelas/colunas do DB de acordo com o tipo dado;
@Entity(name = "Country")
@Table(name = "pais")
public class Country {

    @Id
    private Long id;

    @Column(name = "nome")
    private String name;

    @Column(name = "nome_pt")
    private String portugueseName;

    @Column(name = "sigla")
    private String code;
    private Integer bacen;

    //construtor = getters da classe
    public Country() {
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getPortugueseName() {
        return portugueseName;
    }
    public String getCode() {
        return code;
    }
    public Integer getBacen() {
        return bacen;
    }
}