package it.unibo.application.model.building;

import java.util.Date;

public class Comment {
    private String description;
    private Date dateOfPublication;
    private Double rating;

    public Comment(String description, Date dateOfPublication, Double rating) {
        this.description = description;
        this.dateOfPublication = dateOfPublication;
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }

    public Double getRating() {
        return rating;
    }
    
}
