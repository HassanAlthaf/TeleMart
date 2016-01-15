/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers;

import com.hassanalthaf.telemart.customers.exceptions.InvalidFormatException;
import com.hassanalthaf.telemart.customers.exceptions.InvalidLengthException;
import com.hassanalthaf.telemart.customers.exceptions.UniqueAttributeDuplicationException;
import java.util.regex.Pattern;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 *
 * @author hassan
 */
public class CustomerValidator {
    
    private Pattern nicNumberPattern = Pattern.compile("^[0-9]{9}[vVxX]$");
    private CustomerRepository customerRepository;
    
    public CustomerValidator(Customer customer, boolean allowDuplicateNIC, String originalNIC) throws Exception {
        this.customerRepository = new CustomerRepository();
        this.validateNicNumber(customer.getNicNumber(), allowDuplicateNIC, originalNIC);
        this.validateName(customer.getName());
        this.validateContactNumber(customer.getContactNumber());
        this.validateAddress(customer.getAddress());
        this.validateEmail(customer.getEmailAddress());
    }
    
    public void validateNicNumber(String nicNumber, boolean allowDuplicateNIC, String originalNIC) throws InvalidFormatException, UniqueAttributeDuplicationException {
        
        if (!this.nicNumberPattern.matcher(nicNumber).find()) {
            throw new InvalidFormatException("The NIC Number has an invalid format.");
        }
        
        if(!allowDuplicateNIC && (!nicNumber.equals(originalNIC))) {
            if (this.customerRepository.isNicNumberTaken(nicNumber)) {
                throw new UniqueAttributeDuplicationException("The NIC Number is already taken.");
            }
        }
    }
    
    public void validateName(String name) throws InvalidLengthException {
        
        if (name.length() < 3 || name.length() > 50) {
            throw new InvalidLengthException("Customer name must be from 3 to 50 characters only.");
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
    
    public void validateEmail(String email) throws InvalidFormatException {
        
        try {
            InternetAddress emailAddress = new InternetAddress(email);
            emailAddress.validate();
        } catch (AddressException exception) {
            throw new InvalidFormatException("Invalid email address format.");
        }
        
    }
}
