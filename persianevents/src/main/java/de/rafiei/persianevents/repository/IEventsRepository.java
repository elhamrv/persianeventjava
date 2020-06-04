package de.rafiei.persianevents.repository;

import de.rafiei.persianevents.model.Events;
import java.util.List;

public interface IEventsRepository {
    List<Events> getAll()throws Exception;
    boolean saveAll(List<Events> events)throws Exception;
    Events getById(int id )throws Exception;
    boolean saveOne(Events event)throws Exception;
    boolean update(Events event)throws Exception;
    boolean deleteById(int id)throws Exception;
}
