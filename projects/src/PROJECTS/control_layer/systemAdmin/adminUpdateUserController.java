package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userAccount;

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
}
