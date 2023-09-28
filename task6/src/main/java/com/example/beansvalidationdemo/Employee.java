package com.example.beansvalidationdemo;

import com.example.annotations.*;

public class Employee {

    @NotNullOrEmpty(message = "error.employee.id.empty")
    private int id;

    @MinLength(len = 2, message = "error.employee.firstName.minLength")
    @MaxLength(len = 20, message = "error.employee.firstName.maxLength")
    private String firstName;

    @NotNullOrEmpty(message = "error.employee.lastName.empty")
    @MinLength(len = 2, message = "error.employee.lastName.minLength")
    @MaxLength(len = 20, message = "error.employee.lastName.maxLength")
    private String lastName;

    @Email(message = "error.employee.email.noValid")
    private String email;

    @AssertTrue(message = "error.employee.speaksUkrainian.false")
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
