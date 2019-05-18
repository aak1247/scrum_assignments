package com.aak1247;

import com.aak1247.common.AbstractDBService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@SpringBootConfiguration
public class DBServiceTest {
    @Autowired
    AbstractDBService dbService;

    @Test
    public void testAddAndFind() {
        String k = "1";
        Integer v = 100;
        dbService.insert(k, v);
        var found = dbService.find(k);
        Assert.assertEquals(found, v);
    }

    @Test
    public void testDelete() {
        String k = String.valueOf(UUID.randomUUID());
        Integer v = 100;
        dbService.insert(k, v);
        dbService.remove(k);
        var res = dbService.find(k);
        Assert.assertNull(res);
    }
}
