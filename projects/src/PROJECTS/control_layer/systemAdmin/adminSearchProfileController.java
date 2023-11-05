package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userProfile;

import javax.swing.table.DefaultTableModel;

public class adminSearchProfileController {
    public DefaultTableModel searchProfileList(String profile){
        if (profile.isEmpty()) {
            return new DefaultTableModel();
        }

        userProfile userProfile = new userProfile();
        return userProfile.searchProfile(profile);

    }
}
