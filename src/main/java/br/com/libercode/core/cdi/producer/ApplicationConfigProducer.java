package br.com.libercode.core.cdi.producer;

import java.util.Map;

public interface ApplicationConfigProducer {

    Map<String, Object> produceConfig();

}
