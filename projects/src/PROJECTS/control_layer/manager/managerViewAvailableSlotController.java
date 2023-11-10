package PROJECTS.control_layer.manager;

import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class managerViewAvailableSlotController {
    public DefaultTableModel viewAvailableSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewAvailableSlots();

    }
    public DefaultTableModel refreshAvailableSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewAvailableSlots();
    }
}
