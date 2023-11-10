package PROJECTS.control_layer.manager;

import PROJECTS.entity_layer.userAccount;
import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class managerViewStaffListController {
    public DefaultTableModel viewAllStaffList(){
        userAccount userAccount = new userAccount();
        return userAccount.viewStaffList();

    }
    public DefaultTableModel refreshAllStaffList(){
        userAccount userAccount = new userAccount();
        return userAccount.viewStaffList();
    }
}
