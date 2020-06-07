package de.rafiei.persianevents.repository;


import io.micronaut.spring.tx.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class GenericRepository <M> implements IGenericRepository<M> {

    protected final EntityManager entityManager;
    private final Class<M> entityClass;

    public GenericRepository(EntityManager entityManager, Class<M> entityClass) {

        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    @Transactional(readOnly = true)
    public List<M> getAll() throws Exception {


        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<M> criteriaQuery = criteriaBuilder.createQuery(entityClass);

            Root<M> root = criteriaQuery.from(entityClass);
            CriteriaQuery<M> criteriaRootQuery = criteriaQuery.select(root);

            /*if (null != predicateBuilder) {
                criteriaRootQuery.where(predicateBuilder.build(criteriaBuilder, root));
            }

            if (null != orderBuilder) {
                criteriaRootQuery.orderBy(orderBuilder.build(criteriaBuilder, root));
            }*/

            TypedQuery<M> typedQuery = entityManager.createQuery(criteriaRootQuery);

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
    public boolean saveAll(List<M> event) throws Exception {


        try {
            for(M ev : event) {
                save(ev);
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
    public M getById(int id) throws Exception {


        M event= null;

        try {
            event = this.entityManager.find(entityClass, id);

            //Hibernate.initialize(event);

            return event;

        }catch (Exception ex){
            String s = ex.getLocalizedMessage();
        }


        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean save(M event) throws Exception {

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
    public boolean update(M event) throws Exception {

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
        M event = getById(id);
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
