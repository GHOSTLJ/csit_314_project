package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userAccount;

import javax.swing.table.DefaultTableModel;


public class adminSuspendUserController {
    public boolean suspendAccount(String account){
        if (account.length() < 4){
            return false;
        }else {
            userAccount userAccount = new userAccount();
            return (userAccount.suspendAccountToDatabase(account));
        }
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


}
