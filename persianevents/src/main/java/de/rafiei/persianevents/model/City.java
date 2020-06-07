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
    private int countryId;

    public City() {
    }

    public City(int id, String name, int code, int countryId) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.countryId = countryId;
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

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }
}
