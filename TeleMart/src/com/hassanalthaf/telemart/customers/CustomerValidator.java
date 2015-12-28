/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers;

/**
 *
 * @author hassan
 */
public class CustomerValidator {
    
    public CustomerValidator(Customer customer) throws Exception {
        this.validateNicNumber(customer.getNicNumber());
        this.validateMembershipNumber(customer.getMembershipNumber());
        this.validateName(customer.getName());
        this.validateContactNumber(customer.getContactNumber());
        this.validateAddress(customer.getAddress());
        this.validateEmail(customer.getEmailAddress());
    }
    
    public void validateNicNumber(String nicNumber) {
        // REGEX: /^[0-9]{9}[vVxX]$/
    }
    
    public void validateMembershipNumber(int membershipNumber) {
        
    }
    
    public void validateName(String name) {
        
    }
    
    public void validateContactNumber(int contactNumber) {
        
    }
    
    public void validateAddress(String address) {
        
    }
    
    public void validateEmail(String email) {
        
    }
}
