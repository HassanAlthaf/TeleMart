/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers;

import com.hassanalthaf.telemart.customers.exceptions.InvalidFormatException;
import com.hassanalthaf.telemart.customers.exceptions.InvalidLengthException;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author hassan
 */
public class CustomerValidator {
    
    private Pattern nicNumberPattern = Pattern.compile("^[0-9]{9}[vVxX]$");
    private Pattern emailPattern = Pattern.compile("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\\\[[0-9]{1,3}\\\\.[0-9]{1,3}\\\\.[0-9]{1,3}\\\\.[0-9]{1,3}\\\\])|(([a-zA-Z\\\\-0-9]+\\\\.)+[a-zA-Z]{2,}))$");
    
    public CustomerValidator(Customer customer) throws Exception {
        this.validateNicNumber(customer.getNicNumber());
        this.validateName(customer.getName());
        this.validateContactNumber(customer.getContactNumber());
        this.validateAddress(customer.getAddress());
        this.validateEmail(customer.getEmailAddress());
    }
    
    public void validateNicNumber(String nicNumber) throws InvalidFormatException {
        
        if (!this.nicNumberPattern.matcher(nicNumber).find()) {
            throw new InvalidFormatException("The NIC Number has an invalid format.");
        }
        
    }
    
    public void validateName(String name) throws InvalidLengthException {
        
        if (name.length() < 3 || name.length() > 50) {
            throw new InvalidLengthException("Custom name must be from 3 to 50 characters only.");
        }
        
    }
    
    public void validateContactNumber(int contactNumber) throws InvalidFormatException {
        
        if (String.valueOf(contactNumber).length() != 10) {
            throw new InvalidFormatException("The contact number can only be 10 characters in length.");
        }
        
    }
    
    public void validateAddress(String address) throws InvalidLengthException {
        
        if (address.length() < 5 || address.length() > 80) {
            throw new InvalidLengthException("The address must be from 5 to 80 characters only.");
        }
        
    }
    
    public void validateEmail(String email) throws InvalidFormatException {
        
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException exception) {
            throw new InvalidFormatException("Invalid email address.");
        }
        
    }
}
