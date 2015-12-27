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
public enum UserRanks {
    DISABLED(0), CASHIER(1), SALES_EXECUTIVE(2), MANAGER(3), ADMINISTRATOR(4);
    
    private int value;
    
    UserRanks(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return this.value;
    }
}
