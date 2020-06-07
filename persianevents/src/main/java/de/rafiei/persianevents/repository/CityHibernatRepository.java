package de.rafiei.persianevents.repository;

import de.rafiei.persianevents.model.City;
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
public class CityHibernatRepository  implements ICityRepository{

    @PersistenceContext
    private final EntityManager entityManager;

    public CityHibernatRepository(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    @Override
    public List<City> getAll() throws Exception {
        try{
            CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
            CriteriaQuery<City> criteriaQuery = criteriaBuilder.createQuery(City.class);

            Root<City> root = criteriaQuery.from(City.class);
            CriteriaQuery<City> criteriaRootQuery = criteriaQuery.select(root);
            TypedQuery<City> typedQuery = entityManager.createQuery(criteriaRootQuery);

            return typedQuery.getResultList();


        }catch (Exception ex){
            String s = ex.getLocalizedMessage();
        }
        finally {

        }

        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean saveAll(List<City> city) throws Exception {
        try {
            for(City ct : city) {
                save(ct);
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
    @Transactional(readOnly = false)
    public City getById(int id) throws Exception {


        City city= null;

        try {
            city = this.entityManager.find(City.class, id);

            //Hibernate.initialize(event);

            return city;

        }catch (Exception ex){
            String s = ex.getLocalizedMessage();
        }


        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean save(City city) throws Exception {
        try{
            this.entityManager.persist(city);

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
    public boolean update(City city) throws Exception {
        try{
            this.entityManager.merge(city);

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

        City city = getById(id);
        try{
            this.entityManager.remove(city);

            this.entityManager.flush();

            return  true;
        }
        catch (Exception ex){
            this.entityManager.getTransaction().rollback();
        }

        return false;
    }
}
