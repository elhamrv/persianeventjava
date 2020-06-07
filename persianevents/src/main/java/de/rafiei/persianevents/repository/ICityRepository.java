package de.rafiei.persianevents.repository;

import de.rafiei.persianevents.model.City;

import java.util.List;

public interface ICityRepository {
    List<City> getAll()throws Exception;
    boolean saveAll(List<City> city)throws Exception;
    City getById(int id )throws Exception;
    boolean save(City city)throws Exception;
    boolean update(City city)throws Exception;
    boolean deleteById(int id)throws Exception;
}
