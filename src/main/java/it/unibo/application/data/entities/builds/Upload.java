package it.unibo.application.data.entities.builds;

import java.util.Date;

public class Upload {
    private int buildId;
    private String username;
    private Date lastEditDate;

    public Upload(int buildId, String username, Date lastEditDate) {
        this.buildId = buildId;
        this.username = username;
        this.lastEditDate = lastEditDate;
    }

    public int getBuildId() {
        return buildId;
    }

    public void setBuildId(int buildId) {
        this.buildId = buildId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }
    
}
