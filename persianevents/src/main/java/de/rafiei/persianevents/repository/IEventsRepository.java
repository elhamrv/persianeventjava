package de.rafiei.persianevents.repository;

import de.rafiei.persianevents.model.Events;
import java.util.List;

public interface IEventsRepository {
    List<Events> readEvents()throws Exception;
    boolean  writeEvents(List<Events> events)throws Exception;
    Events getById(int id )throws Exception;
    boolean  insertOneEmployee(Events event)throws Exception;
    boolean updateEvents(Events event)throws Exception;
    boolean deleteById(int id)throws Exception;
}
