package de.rafiei.persianevents.model;

import javax.persistence.*;

@Entity
@Table(
        name = "tblcity"
)
public class City {
    @Id
    @Column(
            name = "id"
    )
    private int id ;


    @Column(
            name = "name"
    )
    private String name;


    @Column(
            name = "code"
    )
    private int code ;


    @Column(
            name = "country_id"
    )
    private int country_id ;

    public City() {
    }

    public City(int id, String name, int code, int country_id) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.country_id = country_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
