package edu.javacourse.spring.dao;

import edu.javacourse.spring.model.Region;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

public class HibernateRegionDao implements RegionDao {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Integer createRegion(Region region) {
        Integer r = (Integer) sessionFactory.getCurrentSession().save(region);
        return r;
    }

    @Override
    public void deleteRegion(Region region) {
        sessionFactory.getCurrentSession().delete(region);
    }

    @Override
    public Region getRegionByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Region r where r.name = ?");
        query.setParameter(0, name);
        return (Region) query.uniqueResult();
    }
}
