/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.users;

import com.hassanalthaf.telemart.users.exceptions.UserNotFoundException;
import java.util.List;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author hassan
 */
public class UserController {
    
    private final UserRepository userRepository;
    private UserState userState;
    
    public UserController(UserState userState) {
        this.userRepository = new UserRepository();
        this.userState = userState;
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
            
            this.userState.login(user);
            
            return UserLoginResponseModes.SUCCESS;
        } catch (UserNotFoundException exception) {
            return UserLoginResponseModes.INVALID;
        }
    }
    
    public List<User> fetchAll() {
        return this.userRepository.fetchAll();
    }
    
    public void createUser(User user) throws Exception {
        new UserValidator(user, false, "", false, "", 0, this.userState.getUser().getRank(), true);
        
        user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        
        this.userRepository.insert(user);
    }
    
    public void update(User user, int loggedInRank, boolean passwordChanged) throws Exception {
        User oldUser = this.userRepository.fetch(user.getId());
        
        new UserValidator(user, true, oldUser.getNicNumber(), true, oldUser.getUsername(), oldUser.getRank(), loggedInRank, passwordChanged);
        
        if (passwordChanged) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        }
        
        this.userRepository.update(user);
    }
    
}
