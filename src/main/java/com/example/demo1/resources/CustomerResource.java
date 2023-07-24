package com.example.demo1.resources;
import com.example.demo1.domain.Customer;
import com.example.demo1.services.CustomerCRUD;

import javax.lang.model.element.Name;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LogActivity {
    String value();
}

@NameBinding
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
@interface Security {}

@Path("/customers")
public class CustomerResource {
    private static final Logger logger = LoggerFactory.getLogger(CustomerResource.class);
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Security
    public Response getAllCustomers() {
        try {
            LoggerResource.logGetAllCustomers();
            long startTime = System.currentTimeMillis();
            List<Customer> customers = CustomerCRUD.getAllCustomers();
            long endTime = System.currentTimeMillis();
            LoggerResource.logElapsedTime("Getting All Customers", startTime, endTime);
            return Response.ok(customers).build();
        }
        catch (Exception exception)
        {
            logger.error("Error while getting all customers", exception);
            return Response.status(500).build();
        }
    }

    @GET
    @Path("/name")
    @LogActivity(value = "Get customer by Name")
    @Security
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCustomersByName(@QueryParam("name") String name) {
        LoggerResource.logGetCustomersByName(name);
        long startTime = System.currentTimeMillis();
        try {
            List<Customer> customersByName = CustomerCRUD.getCustomersByName(name);
            long endTime = System.currentTimeMillis();
            if (!customersByName.isEmpty()) {
                LoggerResource.logElapsedTime("Get customer by Name", startTime, endTime);
                return Response.ok(customersByName).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("No customers found with name: " + name).build();
            }
        } catch (Exception exception) {
            LoggerResource.logError("Error while getting customers by name", exception);
            return Response.status(500).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
//    @LogActivity(value = "Get customer by ID")
    @Security
    public Response getCustomerById(@PathParam("id") int id) {
        LoggerResource.logGetCustomerById(id);
        long startTime = System.currentTimeMillis();
        try {
            Customer customer = CustomerCRUD.getCustomerById(id);
            long endTime = System.currentTimeMillis();
            if (customer != null) {
                LoggerResource.logElapsedTime("Get customer by ID", startTime, endTime);
                return Response.ok(customer).build();
            } else {
                return Response.status(Response.Status.NOT_FOUND).entity("Customer not found with ID: " + id).build();
            }
        } catch (Exception exception) {
            LoggerResource.logError("Error while getting customer by ID", exception);
            return Response.status(500).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
//    @LogActivity(value = "Save customer")
    @Security
    public Response saveCustomer(Customer customer) {
        LoggerResource.logAddCustomer();
        long startTime = System.currentTimeMillis();
        try {
            CustomerCRUD.saveCustomer(customer);
        }
        catch (Exception exception) {
            LoggerResource.logError("Error while saving customer", exception);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to save customer.").build();
        }
        long endTime = System.currentTimeMillis();
        LoggerResource.logElapsedTime("Save customer", startTime, endTime);
        return Response.status(Response.Status.CREATED).entity("Customer saved successfully.").build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
//    @LogActivity(value = "Update customer")
    @Security
    public Response updateCustomer(@PathParam("id") int id, Customer customer) {
        LoggerResource.logUpdateCustomer(id);
        long startTime = System.currentTimeMillis();
        try {
            CustomerCRUD.updateCustomer(id, customer);
        }
        catch (Exception exception) {
            LoggerResource.logError("Error while updating customer", exception);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to update customer.").build();
        }
        long endTime = System.currentTimeMillis();
        LoggerResource.logElapsedTime("Update customer", startTime, endTime);
        return Response.status(Response.Status.NO_CONTENT).entity("Customer updated successfully.").build();
    }

    @DELETE
    @Path("/{id}")
//    @LogActivity(value = "Delete customer")
    @Security
    public Response deleteCustomer(@PathParam("id") int id) {
        LoggerResource.logDeleteCustomer(id);
        long startTime = System.currentTimeMillis();
        try {
            CustomerCRUD.deleteCustomer(id);
        } catch (Exception exception) {
            LoggerResource.logError("Error while deleting customer", exception);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Failed to delete customer.").build();
        }
        long endTime = System.currentTimeMillis();
        LoggerResource.logElapsedTime("Delete customer", startTime, endTime);
        return Response.status(Response.Status.NO_CONTENT).entity("Customer deleted successfully.").build();
    }

//    @GET
//    @Path("/test")
//    @Produces(MediaType.TEXT_PLAIN)
//    public Response test() {
//        String msg = "Hello, this is a test API!";
//        return Response.ok(msg).build();
//    }


//    private void logElapsedTime(String activity, long startTime, long endTime) {
//        long elapsedTime = endTime - startTime;
//        System.out.println(activity + " - Elapsed Time: " + elapsedTime + " ms");
//    }
}
