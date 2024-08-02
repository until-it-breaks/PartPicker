package it.unibo.application.data.entities;

import java.util.Date;

public class Ban {
    private String bannedUser;
    private Date startingDate;
    private Date endingDate;
    private String description;
    private String assigner;

    public Ban(String bannedUser, Date startingDate, Date endingDate, String description, String assigner) {
        this.bannedUser = bannedUser;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
        this.description = description;
        this.assigner = assigner;
    }

    public String getBannedUser() {
        return bannedUser;
    }

    public void setBannedUser(String bannedUser) {
        this.bannedUser = bannedUser;
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndingDate() {
        return endingDate;
    }

    public void setEndingDate(Date endingDate) {
        this.endingDate = endingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAssigner() {
        return assigner;
    }

    public void setAssigner(String assigner) {
        this.assigner = assigner;
    }
    
}
