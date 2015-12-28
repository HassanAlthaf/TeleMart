/*
 * Program developed by Hassan Althaf.
 * Copyright © 2015, Hassan Althaf.
 * Website: http://hassanalthaf.com
 */
package com.hassanalthaf.telemart.customers;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author hassan
 */
public class Customer {
    private int id;
    private StringProperty nicNumber = new SimpleStringProperty();
    private IntegerProperty membershipNumber = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private IntegerProperty contactNumber = new SimpleIntegerProperty();
    private StringProperty address = new SimpleStringProperty();
    private StringProperty emailAddress = new SimpleStringProperty();
    
    public Customer() { }
    
    public Customer (String nicNumber, int membershipNumber, String name, int contactNumber, String address, String emailAddress) {
        this.setNicNumber(nicNumber);
        this.setMembershipNumber(membershipNumber);
        this.setName(name);
        this.setContactNumber(contactNumber);
        this.setAddress(address);
        this.setEmailAddress(emailAddress);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNicNumber() {
        return this.nicNumber.get();
    }

    public void setNicNumber(String nicNumber) {
        this.nicNumber.set(nicNumber);
    }

    public int getMembershipNumber() {
        return this.membershipNumber.get();
    }

    public void setMembershipNumber(int membershipNumber) {
        this.membershipNumber.set(membershipNumber);
    }

    public String getName() {
        return this.name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getContactNumber() {
        return this.contactNumber.get();
    }

    public void setContactNumber(int contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    public String getAddress() {
        return this.address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public String getEmailAddress() {
        return this.emailAddress.get();
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress.set(emailAddress);
    }
    
    public boolean hasMembership() {
        if (this.membershipNumber.get() == 0) {
            return false;
        }
        
        return true;
    }
}
