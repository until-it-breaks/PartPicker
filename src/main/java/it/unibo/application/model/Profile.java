package it.unibo.application.model;

public class Profile {
    private final String profileName;
    private int rating;

    public Profile(final String profileName) {
        this.profileName = profileName;
    }

    public String getProfileName() {
        return "John Doe";
    }

    public int getRating() {
        return 5;
    }
}
