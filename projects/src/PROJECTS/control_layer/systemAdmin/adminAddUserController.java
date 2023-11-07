package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userAccount;
import PROJECTS.entity_layer.userProfile;

import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.Map;



public class adminAddUserController {
    public boolean addVerification(String account, String password, String name, String profile) {
        userProfile userProfile = new userProfile();
        // Check account, password, and name length requirements
        if (account.length() < 4 || password.length() < 6 || name.isEmpty()) {
            return false; // Return false if any of the conditions are not met
        }

        // Check profile value ( if input in  database can continue)
        int profileValue;
        try {
            profileValue = Integer.parseInt(profile);
            if (!userProfile.profileNoVerification(profileValue)) {
                return false; // Return false if profile value is not within database
            }
        } catch (NumberFormatException e) {
            return false; // Return false if profile is not a valid integer
        }

        // If all conditions are met, return true
        Map<String, String> userAddInfo = new HashMap<>();
        userAddInfo.put("account",account);
        userAddInfo.put("password",password);
        userAddInfo.put("name",name);
        userAddInfo.put("profile",profile);

        userAccount userAccount = new userAccount();
        return userAccount.addUserToDatabase(userAddInfo);//connect to entity layer;
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
