/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers.exceptions;

/**
 *
 * @author hassan
 */
public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException() { }
    
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
