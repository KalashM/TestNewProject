package com.example.beansvalidationdemo;

import com.example.annotations.*;

public class Employee {

    @NotNullOrEmpty(message = "Id cannot be empty")
    private int id;

    @MinLength(len = 2, message = "First Name must contain at least 2 characters")
    @MaxLength(len = 20, message = "First Name length must not exceed 20 characters")
    private String firstName;

    @NotNullOrEmpty(message = "Last Name cannot be empty")
    @MinLength(len = 2, message = "Last Name must contain at least 2 characters")
    @MaxLength(len = 20, message = "Last Name length must not exceed 20 characters")
    private String lastName;

    @Email(message = "The email address is not valid")
    private String email;

    @AssertTrue(message = "Employee must speak Ukrainian")
    private boolean speaksUkrainian;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSpeaksUkrainian() {
        return speaksUkrainian;
    }

    public void setSpeaksUkrainian(boolean speaksUkrainian) {
        this.speaksUkrainian = speaksUkrainian;
    }

}
