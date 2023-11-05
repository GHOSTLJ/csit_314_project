package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userProfile;

import javax.swing.table.DefaultTableModel;

public class adminActivateProfileController {
    public boolean activateProfile(int profileNO){
        userProfile userProfile = new userProfile();
        if (profileNO==1){
            return false;
        }
        if (userProfile.profileNoVerification(profileNO)){
            return userProfile.activateProfile(profileNO);
        }else {
            return false;
        }
    }


    public DefaultTableModel viewProfileList(){
        userProfile userProfile = new userProfile();
        return userProfile.viewProfileFromDatabase();
    }
    public DefaultTableModel refreshProfileList(){
        userProfile userProfile = new userProfile();
        return userProfile.viewProfileFromDatabase();
    }
}
