package de.rafiei.persianevents.repository;

import de.rafiei.persianevents.model.EventType;

import java.util.List;

public interface IEventTypeRepository {
    List<EventType> getAll()throws Exception;
    boolean saveAll(List<EventType> eventTypeList)throws Exception;
    EventType getById(int id )throws Exception;
    boolean save(EventType eventType)throws Exception;
    boolean update(EventType eventType)throws Exception;
    boolean deleteById(int id)throws Exception;
}
