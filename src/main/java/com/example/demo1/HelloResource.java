package com.example.demo1;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Produces("text/plain")
    public String hello() {
        try {
            return "Hello, World!";
        }
        catch (Exception exception)
        {
            System.out.println("hello");
            exception.printStackTrace();
            return Response.status(500).build().toString();
        }
    }
}