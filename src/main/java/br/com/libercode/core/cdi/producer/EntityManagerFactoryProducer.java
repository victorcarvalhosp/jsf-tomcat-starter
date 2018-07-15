package br.com.libercode.core.cdi.producer;

import br.com.libercode.core.cdi.qualifier.ApplicationConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.Serializable;
import java.util.Map;

@ApplicationScoped
public class EntityManagerFactoryProducer implements Serializable {

	private static final String PERSISTENCE_UNIT_NAME = "msPU";

	private EntityManagerFactory managerFactory;

	@Produces
	@RequestScoped
	public EntityManagerFactory createFactory(@ApplicationConfig Map<String, Object> config) {
		if (managerFactory == null || !managerFactory.isOpen()) {
			managerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME, config);
		}
		return managerFactory;
	}

}
