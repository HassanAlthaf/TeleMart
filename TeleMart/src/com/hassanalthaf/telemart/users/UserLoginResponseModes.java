/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.users;

/**
 *
 * @author hassan
 */
public enum UserLoginResponseModes {
    DISABLED(0), INVALID(1), SUCCESS(2);
    
    private final int value;
    
    UserLoginResponseModes(int value) {
       this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
