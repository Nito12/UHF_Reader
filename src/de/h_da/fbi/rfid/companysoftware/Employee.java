package de.h_da.fbi.rfid.companysoftware;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Employee.java
//  @ Date : 01.06.2014
//  @ Author : 
//
//




public class Employee extends Person {
	private final String division;
	private final Address address;
	private final int securityClass;

    public Employee(String surname, String name, String cardId, String division, int securityClass, String streetname, String housenumber, String zip, String city) {
        this.surname = surname;
        this.name = name;
        this.cardId = cardId;
        this.division = division;
        this.securityClass = securityClass;
        
        Address newAddress = new Address(streetname, housenumber, zip, city);
        this.address = newAddress;
    }

    /**
     * @return the division
     */
    public String getDivision() {
        return division;
    }

    /**
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * @return the securityClass
     */
    public int getSecurityClass() {
        return securityClass;
    }
    
    
        
        
}
