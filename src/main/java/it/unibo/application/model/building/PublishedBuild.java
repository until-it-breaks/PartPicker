package it.unibo.application.model.building;

import java.util.List;
import java.util.Date;

public class PublishedBuild {
    private Build build;
    private List<Comment> comments;
    private Date dateOfPublication;

    public PublishedBuild(Build build, List<Comment> comments, Date dateOfPublication) {
        this.build = build;
        this.comments = comments;
        this.dateOfPublication = dateOfPublication;
    }

    public Build getBuild() {
        return build;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public Date getDateOfPublication() {
        return dateOfPublication;
    }
}
