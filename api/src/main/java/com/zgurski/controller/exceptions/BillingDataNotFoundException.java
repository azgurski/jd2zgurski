package com.zgurski.controller.exceptions;

public class BillingDataNotFoundException extends RuntimeException  {
    public BillingDataNotFoundException(String message) {
        super(message);
    }
}
