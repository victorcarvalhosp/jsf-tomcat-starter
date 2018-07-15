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
@Exclude(exceptIfProjectStage = ProjectStage.Development.class)
public class DevelopmentApplicationConfigProducer implements ApplicationConfigProducer, Serializable {

    @Override
    @Produces
    @ApplicationConfig
    public Map<String, Object> produceConfig() {
        Map<String, Object> config = new HashMap<>();
        config.put("javax.persistence.jdbc.url", "jdbc:postgresql://localhost/jsf-tomcat-starter");
        config.put("javax.persistence.jdbc.user", "postgres");
        config.put("javax.persistence.jdbc.password", "postgres");
        config.put("hibernate.show_sql", "true");
        config.put("hibernate.format_sql", "false");
        config.put("hibernate.use_sql_comments", "false");
        config.put("hibernate.hbm2ddl.auto", "update");

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
