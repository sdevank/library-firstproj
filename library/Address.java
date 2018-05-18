package com.sample.library;

public class Address {
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String zipCode;

    public Address(String setAddressLine1,String setAddressLine2, String setCity, String setState, String setZipCode){

        addressLine1 = setAddressLine1;
        addressLine2 = setAddressLine2;
        city = setCity;
        state = setState;
        zipCode = setZipCode;
    }

    public String toString() {
        return addressLine1 + "," + addressLine2 + ", " + city + ", " + state + "" + zipCode;
    }
}
