package com.example.nectar.model;

import com.google.gson.annotations.SerializedName;

public class SignUpRequest_Response {
    private String email;
    private String username;
    private String password;
    Name NameObject;
    Address AddressObject;
    private String phone = "1-570-236-7033";

    public SignUpRequest_Response(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Name getNameObject() {
        return NameObject;
    }

    public void setNameObject(Name nameObject) {
        NameObject = nameObject;
    }

    public Address getAddressObject() {
        return AddressObject;
    }

    public void setAddressObject(Address addressObject) {
        AddressObject = addressObject;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}

class Name {
    private String firstname = "John";
    private String lastname = "Doe";

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

}

class Address {
    private String city = "kilcoole";
    private String street = "7835 new road";
    private float number = 3;
    private String zipcode = "12926-3874";
    Geolocation GeolocationObject;


    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public float getNumber() {
        return number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public Geolocation getGeolocationObject() {
        return GeolocationObject;
    }

    public void setGeolocationObject(Geolocation geolocationObject) {
        GeolocationObject = geolocationObject;
    }
}

class Geolocation {
    private String lat = "-37.3159";
    @SerializedName("long")
    private String lon = "81.1496";

    public Geolocation(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }
}