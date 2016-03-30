package com.studytrails.tutorials.springhibernatejpa;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository("personDao")
@Transactional(propagation = Propagation.REQUIRED)
public class PersonDao {

	private static final String SELECT_QUERY = "select p from Person p";

	@PersistenceContext(name = "SSJPA")
	private EntityManager entityManager;

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void insert(Person person) {
		entityManager.persist(person);
	}

	public List<Person> selectAll() {
		Query query = entityManager.createQuery(SELECT_QUERY);
		List<Person> persons = (List<Person>) query.getResultList();
		return persons;
	}

}