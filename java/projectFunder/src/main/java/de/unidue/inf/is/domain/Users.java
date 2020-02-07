package de.unidue.inf.is.domain;

public final class Users {

    private String firstname;
    private String lastname;


    public Users() {
    }


    public Users(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;

    }


    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }

}