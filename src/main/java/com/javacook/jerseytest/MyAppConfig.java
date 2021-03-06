package com.javacook.jerseytest;

import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


@ApplicationPath("/jerseytesting")
public class MyAppConfig extends ResourceConfig {

    public MyAppConfig() {
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
