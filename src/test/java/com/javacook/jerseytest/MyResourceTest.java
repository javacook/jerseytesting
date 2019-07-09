package com.javacook.jerseytest;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Assert;
import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;


public class MyResourceTest extends JerseyTest {

    public class MyTestConfig extends ResourceConfig {
        public MyTestConfig() {
            register(MyResource.class);
            register(new AbstractBinder() {
                @Override
                protected void configure() {
                    // bindAsContract(MyServiceImpl.class);
                    bind(MyServiceImpl.class).to(MyService.class);
                }
            });
        }
    }

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