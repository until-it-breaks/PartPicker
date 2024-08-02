package it.unibo.application.data.entities;

import java.util.Date;

public class User {
    private String username;    
    private String password;
    private Date signUpDate;
    private String email;
    private Boolean isModerator;

    public User(String username, String password, Date signUpDate, String email, Boolean isModerator) {
        this.username = username;
        this.password = password;
        this.signUpDate = signUpDate;
        this.email = email;
        this.isModerator = isModerator;
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

    public Date getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(Date signUpDate) {
        this.signUpDate = signUpDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getIsModerator() {
        return isModerator;
    }

    public void setIsModerator(Boolean isModerator) {
        this.isModerator = isModerator;
    }
}
