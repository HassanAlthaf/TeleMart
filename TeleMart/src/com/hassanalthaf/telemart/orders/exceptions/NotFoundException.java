/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.orders.exceptions;

/**
 *
 * @author hassan
 */
public class NotFoundException extends Exception {
    public NotFoundException() { }
    
    public NotFoundException(String message) {
        super(message);
    }
}
