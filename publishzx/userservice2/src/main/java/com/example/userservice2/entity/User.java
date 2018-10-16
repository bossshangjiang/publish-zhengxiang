package com.example.userservice2.entity;


public class User {

    private String name;

    private String role;

    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String name) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}