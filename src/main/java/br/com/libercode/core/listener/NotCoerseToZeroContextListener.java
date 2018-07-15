package br.com.libercode.core.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class NotCoerseToZeroContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        // NOOP
    }

}