package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userAccount;

import java.util.Vector;



public class adminSearchController {

    public Vector<String> searchUser(String account){
        Vector<String> newRowData = new Vector<>();
        if (account.length() < 4){
            return null;
        }else {
            userAccount userAccount = new userAccount();
            return (newRowData=userAccount.searchUserFromDatabase(account));
        }
    }
}
