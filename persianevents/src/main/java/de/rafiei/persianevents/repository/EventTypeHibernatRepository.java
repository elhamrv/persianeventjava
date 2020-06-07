package de.rafiei.persianevents.repository;

import de.rafiei.persianevents.model.EventType;
import io.micronaut.spring.tx.annotation.Transactional;
import javax.inject.Singleton;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Singleton
public class EventTypeHibernatRepository implements IEventTypeRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    public EventTypeHibernatRepository(EntityManager entityManager){

        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<EventType> getAll() throws Exception {


        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<EventType> criteriaQuery = criteriaBuilder.createQuery(EventType.class);

            Root<EventType> root = criteriaQuery.from(EventType.class);
            CriteriaQuery<EventType> criteriaRootQuery = criteriaQuery.select(root);


            TypedQuery<EventType> typedQuery = entityManager.createQuery(criteriaRootQuery);

            return typedQuery.getResultList();


        }catch (Exception ex){
            String s = ex.getLocalizedMessage();
        }
        finally {

        }

        return null;
    }

    @Override
    @Transactional
    public boolean saveAll(List<EventType> eventTypeList) throws Exception {
        try {
            for(EventType et : eventTypeList) {
                save(et);
            }

            return true;

        } catch (Exception ex){
            String s = ex.getLocalizedMessage();
        }
        finally {

        }

        return false;
    }

    @Override
    @Transactional(readOnly = true)
    public EventType getById(int id) throws Exception {
        EventType eventType= null;

        try {
            eventType = this.entityManager.find(EventType.class, id);

            //Hibernate.initialize(event);

            return eventType;

        }catch (Exception ex){
            String s = ex.getLocalizedMessage();
        }


        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean save(EventType eventType) throws Exception {
        try{
            this.entityManager.persist(eventType);

            this.entityManager.flush();

            return  true;
        }
        catch (Exception ex){
            this.entityManager.getTransaction().rollback();
        }

        return false;
    }

    @Override
    @Transactional
    public boolean update(EventType eventType) throws Exception {
        try{
            this.entityManager.merge(eventType);

            this.entityManager.flush();

            return  true;
        }
        catch (Exception ex){
            this.entityManager.getTransaction().rollback();
        }

        return false;
    }

    @Override
    @Transactional
    public boolean deleteById(int id) throws Exception {
        EventType eventType = getById(id);
        try{
            this.entityManager.remove(eventType);

            this.entityManager.flush();

            return  true;
        }
        catch (Exception ex){
            this.entityManager.getTransaction().rollback();
        }

        return false;
    }
}
