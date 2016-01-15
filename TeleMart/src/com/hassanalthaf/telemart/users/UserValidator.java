/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.users;

import com.hassanalthaf.telemart.inventory.exceptions.InvalidArgumentException;
import com.hassanalthaf.telemart.users.exceptions.UniqueAttributeDuplicationException;
import com.hassanalthaf.telemart.users.exceptions.InvalidFormatException;
import com.hassanalthaf.telemart.users.exceptions.InvalidLengthException;
import com.hassanalthaf.telemart.users.exceptions.NoPermissionException;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author hassan
 */
public class UserValidator {
    
    private Pattern nicNumberPattern = Pattern.compile("^[0-9]{9}[vVxX]$");
    private UserRepository userRepository;
    
    public UserValidator(User user, boolean allowDuplicateNIC, String originalNIC, boolean allowDuplicateUsername, String originalUsername, int creatorRank) throws Exception {
        this.userRepository = new UserRepository();
        this.validatePermission(creatorRank, user.getRank());
        this.validateNicNumber(user.getNicNumber(), allowDuplicateNIC, originalNIC);
        this.validateUsername(user.getUsername(), allowDuplicateUsername, originalUsername);
        this.validatePassword(user.getPassword());
        this.validateFullName(user.getFullName());
        this.validateContactNumber(user.getContactNumber());
        this.validateEmail(user.getEmail());
        this.validateAddress(user.getAddress());
        this.validateSalary(user.getSalary());
        this.validateRank(user.getRank());
    }

    public void validatePermission(int rank, int userRank) throws NoPermissionException {
        int[] ranks = new int[]{UserRanks.DISABLED.getValue(), UserRanks.CASHIER.getValue(), UserRanks.SALES_EXECUTIVE.getValue(), UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()};
        
        if (rank < UserRanks.MANAGER.getValue()) {
            throw new NoPermissionException("You cannot create users!");
        }
        
        if (userRank >= UserRanks.MANAGER.getValue() && rank != UserRanks.ADMINISTRATOR.getValue()) {
            throw new NoPermissionException("You cannot create Managers and Administrators!");
        }
    }
    
    public void validateNicNumber(String nicNumber, boolean allowDuplicateNIC, String originalNIC) throws InvalidFormatException, UniqueAttributeDuplicationException {
        
        if (!this.nicNumberPattern.matcher(nicNumber).find()) {
            throw new InvalidFormatException("The NIC Number has an invalid format.");
        }
        
        if(!allowDuplicateNIC && (!nicNumber.equals(originalNIC))) {
            if (this.userRepository.isNicNumberTaken(nicNumber)) {
                throw new UniqueAttributeDuplicationException("The NIC Number is already taken.");
            }
        }
    }
    
    public void validateUsername(String username, boolean allowDuplicate, String original) throws InvalidLengthException, UniqueAttributeDuplicationException {
        
        if (username.length() < 3 || username.length() > 50) {
            throw new InvalidLengthException("Username must be from 3 to 50 characters only.");
        }
        
        if (!allowDuplicate && (!username.equals(original))) {
            if (this.userRepository.isUsernameTaken(username)) {
                throw new UniqueAttributeDuplicationException("The username is already taken.");
            }
        }
        
    }
    
    public void validatePassword(String password) throws InvalidLengthException {
        
        if (password.length() < 6 || password.length() > 72) {
            throw new InvalidLengthException("Password can be from 6 to 72 characters only.");
        }
        
    }
    
    public void validateFullName(String name) throws InvalidLengthException {
        
        if (name.length() < 3 || name.length() > 50) {
            throw new InvalidLengthException("Full name must be from 3 to 50 characters only.");
        }
        
    }
    
    public void validateContactNumber(int contactNumber) throws InvalidFormatException {
        
        if (String.valueOf(contactNumber).length() != 9) {
            System.out.println(contactNumber);
            throw new InvalidFormatException("The contact number can only be 9 characters in length (no zero).");
        }
        
    }
    
    public void validateAddress(String address) throws InvalidLengthException {
        
        if (address.length() < 5 || address.length() > 80) {
            throw new InvalidLengthException("The address must be from 5 to 80 characters only.");
        }
        
    }
    
    public void validateEmail(String email) throws InvalidFormatException, AddressException {
        
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException exception) {
            throw new InvalidFormatException("Invalid email address format.");
        }
        
    }
    
    public void validateSalary(double salary) throws InvalidArgumentException {
        
        if (salary < 0) {
            throw new InvalidArgumentException("Invalid salary entered.");
        }
        
    }
    
    public void validateRank(int rank) throws InvalidArgumentException {
        
        boolean valid = false;
        int[] ranks = new int[]{UserRanks.DISABLED.getValue(), UserRanks.CASHIER.getValue(), UserRanks.SALES_EXECUTIVE.getValue(), UserRanks.MANAGER.getValue(), UserRanks.ADMINISTRATOR.getValue()};
        
        for (int rankNumber : ranks) {
            
            if (rank == rankNumber) {
                valid = true;
                break;
            }
            
        }
        
        if (!valid) {
            throw new InvalidArgumentException("Invalid rank specified.");
        }
        
    }
}
