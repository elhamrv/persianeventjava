package de.rafiei.persianevents.repository;

import de.rafiei.persianevents.model.Events;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import io.micronaut.spring.tx.annotation.Transactional;
import java.util.List;

@Singleton
public class EventsHibernatRepository implements IEventsRepository {


    @PersistenceContext
    private final EntityManager entityManager;

    public EventsHibernatRepository(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Events> getAll() throws Exception {


        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Events> criteriaQuery = criteriaBuilder.createQuery(Events.class);

            Root<Events> root = criteriaQuery.from(Events.class);
            CriteriaQuery<Events> criteriaRootQuery = criteriaQuery.select(root);

            /*if (null != predicateBuilder) {
                criteriaRootQuery.where(predicateBuilder.build(criteriaBuilder, root));
            }

            if (null != orderBuilder) {
                criteriaRootQuery.orderBy(orderBuilder.build(criteriaBuilder, root));
            }*/

            TypedQuery<Events> typedQuery = entityManager.createQuery(criteriaRootQuery);

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
    public boolean saveAll(List<Events> event) throws Exception {


        try {
            for(Events ev : event) {
                saveOne(ev);
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
    public Events getById(int id) throws Exception {


        Events event= null;

        try {
            event = this.entityManager.find(Events.class, id);

            //Hibernate.initialize(event);

            return event;

        }catch (Exception ex){
            String s = ex.getLocalizedMessage();
        }


        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean saveOne(Events event) throws Exception {

        //this.entityManager.getTransaction().begin();

        try{
            this.entityManager.persist(event);

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
    public boolean update(Events event) throws Exception {

        //this.entityManager.getTransaction().begin();

        try{
            this.entityManager.merge(event);

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

        //this.entityManager.getTransaction().begin();
        Events event = getById(id);
        try{
            this.entityManager.remove(event);

            this.entityManager.flush();

            return  true;
        }
        catch (Exception ex){
            this.entityManager.getTransaction().rollback();
        }

        return false;
    }
}
