package PROJECTS.control_layer.systemAdmin;

import PROJECTS.entity_layer.userAccount;


public class adminSuspendController {
    public boolean suspendAccount(String account){
        if (account.length() < 4){
            return false;
        }else {
            userAccount userAccount = new userAccount();
            return (userAccount.suspendAccountToDatabase(account));
        }
    }


}
