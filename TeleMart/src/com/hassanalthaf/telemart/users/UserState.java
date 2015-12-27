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
public class UserState {
    private User loggedInUser;
    private boolean loggedIn = false;
    
    public UserState() {}
    
    public void login(User loggedInUser) {
        this.loggedInUser = loggedInUser;
        this.loggedIn = true;
    }
    
    public void logout() {
        this.loggedInUser = null;
        this.loggedIn = false;
    }
    
    public User getUser() {
        return this.loggedInUser;
    }
}
