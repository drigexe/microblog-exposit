package com.vysocki.yuri.microblog_exposit;

public class User {

    private String age;
    private String email;
    private String firstname;
    private String gender;
    private String lastname;

    public User() {
    }

    public User(String age, String email, String firstname, String gender, String lastname) {
        this.age = age;
        this.email = email;
        this.firstname = firstname;
        this.gender = gender;
        this.lastname = lastname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

}
