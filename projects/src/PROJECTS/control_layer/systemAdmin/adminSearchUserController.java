package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userAccount;

import javax.swing.table.DefaultTableModel;
import java.util.Vector;



public class adminSearchUserController {

    public DefaultTableModel searchUser(String account){
        if (account.isEmpty()) {
            return new DefaultTableModel();
        }
        userAccount userAccount = new userAccount();
        return userAccount.searchUserFromDatabase(account);

    }
}
