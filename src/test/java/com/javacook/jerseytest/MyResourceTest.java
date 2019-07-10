package com.javacook.jerseytest;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.Test;
//import org.junit.Test;

import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;


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
    public void testGetRequestSayHello() {
        final Response response = target("/say/hello").request().get();
        assertThat(response.getStatus()).isEqualTo(200);
        String content = response.readEntity(String.class);
        assertThat(content).isEqualTo("Hello Schnucki");
    }
}