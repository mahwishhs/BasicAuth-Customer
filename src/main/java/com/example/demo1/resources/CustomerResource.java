package com.example.demo1.resources;


import com.example.demo1.domain.Customer;
import com.example.demo1.services.CustomerCRUD;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/customers")
public class CustomerResource {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCustomers() {
        List<Customer> customers = CustomerCRUD.getAllCustomers();
        return Response.ok(customers).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomerById(@PathParam("id") int id) {
        Customer customer = CustomerCRUD.getCustomerById(id);
        if (customer != null) {
            return Response.ok(customer).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Customer not found with ID: " + id).build();
        }
    }

    @GET
    @Path("/name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomersByName(@QueryParam("name") String name) {
        List<Customer> customersByName = CustomerCRUD.getCustomersByName(name);
        if (!customersByName.isEmpty()) {
            return Response.ok(customersByName).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("No customers found with name: " + name).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveCustomer(Customer customer) {
        try {
            CustomerCRUD.saveCustomer(customer);
            return Response.status(Response.Status.CREATED).entity("Customer saved successfully.").build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to save customer.").build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        CustomerCRUD.updateCustomer(id, customer);
        return Response.status(Response.Status.NO_CONTENT).entity("Customer updated successfully.").build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCustomer(@PathParam("id") int id) {
        CustomerCRUD.deleteCustomer(id);
        return Response.status(Response.Status.NO_CONTENT).entity("Customer deleted successfully.").build();
    }
}
