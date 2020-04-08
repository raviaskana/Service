package org.service.config;

import org.springframework.beans.factory.config.YamlMapFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class Config {
    private Map<String, List<String>> dataMap;

    public List<String> get(String key) {
        return  dataMap.get(key);
    }



    @SuppressWarnings("unchecked")
    public Config() {
        YamlMapFactoryBean yamlMapFactoryBean = new YamlMapFactoryBean();
        yamlMapFactoryBean.setResources(new ClassPathResource("config.yml"));
        this.dataMap = (Map<String, List<String>>) yamlMapFactoryBean.getObject().get("config");
    }
}
