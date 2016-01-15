/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.users.exceptions;

/**
 *
 * @author hassan
 */
public class InvalidArgumentException extends Exception {
    public InvalidArgumentException() { }
    
    public InvalidArgumentException(String message) {
        super(message);
    }
}
