package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userAccount;
import PROJECTS.entity_layer.userProfile;

import java.util.HashMap;
import java.util.Map;

public class adminAddProfileController {
    public boolean addVerification(String profile) {
        userProfile userProfile = new userProfile();
        // Check profile length requirements
        if (profile.isEmpty()) {
            return false; // Return false if profile is empty
        }

        // Check profile value ( if input not in database can continue)
        if (userProfile.profileNameVerification(profile)) {
            return false;
        }

        // If all conditions are met, return add to database true
        return userProfile.addNewProfile(profile);//connect to entity layer;
    }
}
