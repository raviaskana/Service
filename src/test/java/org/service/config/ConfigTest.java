package org.service.config;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.stream.Collectors;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class ConfigTest {
    @Autowired
    Config config;
    @Test
    public void testConfig(){
        Assert.assertTrue(config.get(")")!=null);
        Assert.assertTrue(config.get("2").stream().collect(Collectors.joining()).equals("abc"));
    }
}
