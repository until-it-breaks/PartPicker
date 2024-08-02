package it.unibo.application.data.entities;

import java.util.Date;

public class Review {
    private int buildId;
    private String username;
    private int reviewRating;
    private String comment;
    private Date lastEditDate;

    public Review(int buildId, String username, int reviewRating, String comment, Date lastEditDate) {
        this.buildId = buildId;
        this.username = username;
        this.reviewRating = reviewRating;
        this.comment = comment;
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

    public int getReviewRating() {
        return reviewRating;
    }

    public void setReviewRating(int reviewRating) {
        this.reviewRating = reviewRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getLastEditDate() {
        return lastEditDate;
    }

    public void setLastEditDate(Date lastEditDate) {
        this.lastEditDate = lastEditDate;
    }

}
