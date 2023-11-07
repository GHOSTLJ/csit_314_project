package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userProfile;

import javax.swing.table.DefaultTableModel;

public class adminUpdateProfileController {

    public boolean updateProfile(int profileNo,String profileName){
        userProfile userProfile = new userProfile();
        if (profileName.length()<4){
            return false;
        }
        if (userProfile.profileNoVerification(profileNo)){
            if (!userProfile.profileNameVerification(profileName)){

            //call entity
             return userProfile.updateProfileToDatabase(profileNo,profileName);

            }else {
                return false;
            }
        }else{
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
