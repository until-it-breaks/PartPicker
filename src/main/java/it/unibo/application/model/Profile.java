package it.unibo.application.model;

public class Profile {
    private String profileName;
    private int rating;

    public Profile(String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return "John Doe";
    }

    public int getRating() {
        return 5;
    }
}
