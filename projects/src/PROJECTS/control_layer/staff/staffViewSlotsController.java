package PROJECTS.control_layer.staff;

import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class staffViewSlotsController {
    public DefaultTableModel viewAllSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewSlotFromDatabase();

    }
    public DefaultTableModel refreshAllSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewSlotFromDatabase();
    }
}
