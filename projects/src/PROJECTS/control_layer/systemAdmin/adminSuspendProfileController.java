package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userProfile;

import javax.swing.table.DefaultTableModel;

public class adminSuspendProfileController {

    public boolean suspendProfile(int profileNO){
        userProfile userprofile = new userProfile();
        if (profileNO==1){
            return false;
        }
        if (userprofile.profileNoVerification(profileNO)){
            return userprofile.suspendProfile(profileNO);
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
