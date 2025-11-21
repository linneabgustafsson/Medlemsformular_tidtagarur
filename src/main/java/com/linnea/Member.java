package com.linnea;

public class Member {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String addressLine1;
    private String addressLine2;

    public Member() {
    }

    public Member(String firstName, String lastName, String phoneNumber, String addressLine1, String addressLine2) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
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

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setAdressLine1(String adressLine1) {
        this.addressLine1 = adressLine1;
    }

    public void setAdressLine2(String adressLine2) {
        this.addressLine2 = adressLine2;
    }

    @Override
    public String toString()  {
        return firstName + " " + lastName + "\n" + phoneNumber + "\n" + addressLine1 + "\n" + addressLine2;
    }

}
