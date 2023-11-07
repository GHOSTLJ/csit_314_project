package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userAccount;
import PROJECTS.entity_layer.userProfile;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;



public class adminUpdateUserController {
    public boolean updateVerification(String account, String password, String name, String profile){
        // Check account, password, and name length requirements
        if (account.length() < 4 || password.length() < 6 || name.isEmpty()) {
            return false; // Return false if any of the conditions are not met
        }

        // Check profile value (1, 2, 3, or 4)
        int profileValue;
        try {
            profileValue = Integer.parseInt(profile);
            if (profileValue < 1 || profileValue > 4) {
                return false; // Return false if profile value is not within the allowed range
            }
        } catch (NumberFormatException e) {
            return false; // Return false if profile is not a valid integer
        }

        // If all conditions are met, return true
        Map<String, String> userUpdateInfo = new HashMap<>();
        userUpdateInfo.put("account",account);
        userUpdateInfo.put("password",password);
        userUpdateInfo.put("name",name);
        userUpdateInfo.put("profile",profile);
        userAccount userAccount = new userAccount();
        return userAccount.updateUserToDatabase(userUpdateInfo);//connect to entity layer;
    }

    public DefaultTableModel viewAllUserList(){
        userAccount userAccount = new userAccount();
        DefaultTableModel myModel = userAccount.viewFromDatabase();
        return myModel;
    }
    public DefaultTableModel refreshAllUserList(){
        userAccount userAccount = new userAccount();
        return userAccount.refreshFromDataBase();
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
