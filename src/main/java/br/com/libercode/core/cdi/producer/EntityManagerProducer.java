package br.com.libercode.core.cdi.producer;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.io.Serializable;

public class EntityManagerProducer implements Serializable {
	
	@Inject 
	private EntityManagerFactory factory;
	
	private EntityManager entityManager;
	
	@Produces
	@RequestScoped
	public EntityManager createEntityManager() {
		if (entityManager == null || !entityManager.isOpen()) {
			entityManager = factory.createEntityManager(); 
		}
		return entityManager;
	}
	
	public void dispose(@Disposes EntityManager entityManager) {
		if (entityManager.isOpen()) {
			entityManager.close();
		}
		entityManager = null;
	}
}
