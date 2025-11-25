package com.linnea;

public class Member {

    private String firstName;
    private String lastName;
    private String phoneNr;
    private String addressLine1;
    private String addressLine2;

    public Member() {
    }

    public Member(String firstName, String lastName, String phoneNr, String addressLine1, String addressLine2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNr = phoneNr;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNr() {
        return phoneNr;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhoneNr(String phoneNr) {
        this.phoneNr = phoneNr;
    }

    public void setAdressLine1(String adressLine1) {
        this.addressLine1 = adressLine1;
    }

    public void setAdressLine2(String adressLine2) {
        this.addressLine2 = adressLine2;
    }

    @Override
    public String toString()  {
        return firstName + " " + lastName + "\n" + phoneNr + "\n" + addressLine1 + "\n" + addressLine2;
    }
}
