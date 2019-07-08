package com.javacook.jerseytest;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;


@Path("/say")
public class MyResource {

    @Inject
    private MyService service;

    @Inject
    UriInfo uriInfo;

    @GET
    @Path("/hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello " + service.getPalName();
    }

}
