package br.com.libercode.core.cdi.producer;

import br.com.libercode.core.cdi.qualifier.ApplicationConfig;
import org.apache.deltaspike.core.api.exclude.Exclude;
import org.apache.deltaspike.core.api.projectstage.ProjectStage;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@Exclude(exceptIfProjectStage = ProjectStage.Production.class)
public class ProductionApplicationConfigProducer implements ApplicationConfigProducer, Serializable {

    @Override
    @Produces
    @ApplicationConfig
    public Map<String, Object> produceConfig() {
        Map<String, Object> config = new HashMap<>();

        config.put("javax.persistence.jdbc.url", "jdbc:postgresql://productionip/postgres");
        config.put("javax.persistence.jdbc.user", "wproduction-user");
        config.put("javax.persistence.jdbc.password", "production-password");

        config.put("hibernate.connection.provider", "org.hibernate.connection.C3P0ConnectionProvider");

        config.put("hibernate.c3p0.initialPoolSize", "20");
        config.put("hibernate.c3p0.min_size", "20");
        config.put("hibernate.c3p0.max_size", "90");
        config.put("hibernate.c3p0.acquire_increment", "5");
        config.put("hibernate.c3p0.timeout", "179");
        config.put("hibernate.c3p0.idle_test_period", "90");

        return config;
    }

}
