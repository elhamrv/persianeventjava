package de.rafiei.persianevents.repository;

import de.rafiei.persianevents.model.Events;

import java.util.List;

public interface  IGenericRepository <M> {
    List<M> getAll()throws Exception;
    boolean saveAll(List<M> events)throws Exception;
    M getById(int id)throws Exception;
    boolean save(M event)throws Exception;
    boolean update(M event)throws Exception;
    boolean deleteById(int id)throws Exception;
}
