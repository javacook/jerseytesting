package com.javacook.jerseytest;

import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

public class MyResourceTest extends JerseyTest {

    @Override
    public Application configure() {
        return new MyTestConfig();
    }

    @Test
    public void hello() {
        final Response response = target("/say/hello").request().get();
        Assert.assertEquals(200, response.getStatus());
    }
}