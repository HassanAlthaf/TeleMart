/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.users;

import com.hassanalthaf.telemart.users.exceptions.UserNotFoundException;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author hassan
 */
public class UserController {
    
    private final UserRepository userRepository;
    
    public UserController() {
        this.userRepository = new UserRepository();
    }
    
    public UserLoginResponseModes login(String username, String password) {
        try {
            User user = this.userRepository.fetchByUsername(username);
            
            if (!BCrypt.checkpw(password, user.getPassword())) {
                return UserLoginResponseModes.INVALID;
            }
            
            if (user.getRank() == UserRanks.DISABLED.getValue()) {
                return UserLoginResponseModes.DISABLED;
            }
            
            return UserLoginResponseModes.SUCCESS;
        } catch (UserNotFoundException exception) {
            return UserLoginResponseModes.INVALID;
        }
    }
    
}
