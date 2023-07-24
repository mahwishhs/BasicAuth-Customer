package com.example.demo1.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerResource {
    private static final Logger logger = LoggerFactory.getLogger(LoggerResource.class);

    public static void logGetAllCustomers() {
        logger.info("API call: Get all customers");
    }

    public static void logGetCustomersByName(String name) {
        logger.info("API call: Get customer by Name - Name: {}", name);
    }

    public static void logGetCustomerById(int id) {
        logger.info("API call: Get customer by ID - ID: {}", id);
    }

    public static void logAddCustomer() {
        logger.info("API call: Save customer");
    }

    public static void logUpdateCustomer(int id) {
        logger.info("API call: Update customer - ID: {}", id);
    }

    public static void logDeleteCustomer(int id) {
        logger.info("API call: Delete customer - ID: {}", id);
    }

    public static void logElapsedTime(String activity, long startTime, long endTime) {
        long elapsedTime = endTime - startTime;
        logger.info("{} - Elapsed Time: {} ms", activity, elapsedTime);
    }

    public static void logError(String message, Throwable throwable) {
        logger.error(message, throwable);
    }
}