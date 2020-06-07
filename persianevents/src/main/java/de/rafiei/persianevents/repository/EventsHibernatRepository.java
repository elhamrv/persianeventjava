package de.rafiei.persianevents.repository;

import de.rafiei.persianevents.model.Events;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Singleton
public class EventsHibernatRepository extends GenericRepository<Events>  {


    public EventsHibernatRepository(EntityManager entityManager) {
        super(entityManager, Events.class);

    }

}
