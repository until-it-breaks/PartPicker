package it.unibo.application.model.selectors;

import it.unibo.application.model.Profile;

public class ProfileSelector {
    private Profile currentProfile;

    public ProfileSelector() {
    }

    public Profile getCurrentProfile() {
        return new Profile("John Doe");
    }

    public void setCurrentProfile(Profile currentProfile) {
        this.currentProfile = currentProfile;
    }
}
