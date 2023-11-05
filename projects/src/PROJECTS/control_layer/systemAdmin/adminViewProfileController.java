package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userProfile;
import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class adminViewProfileController {
    public DefaultTableModel viewAllProfileList(){
        userProfile userProfile = new userProfile();
        return userProfile.viewProfileFromDatabase();

    }
    public DefaultTableModel refreshAllProfileList(){
        userProfile userProfile = new userProfile();
        return userProfile.viewProfileFromDatabase();
    }
}
