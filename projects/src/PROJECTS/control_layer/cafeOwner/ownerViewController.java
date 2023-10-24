package PROJECTS.control_layer.cafeOwner;

import PROJECTS.entity_layer.userAccount;
import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class ownerViewController {
    public DefaultTableModel viewAllSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewSlotFromDatabase();

    }
    public DefaultTableModel refreshAllSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewSlotFromDatabase();
    }
}
