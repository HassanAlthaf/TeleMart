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
public class NoPermissionException extends Exception {
    public NoPermissionException() { }
    public NoPermissionException(String message) {
        super(message);
    }
}
