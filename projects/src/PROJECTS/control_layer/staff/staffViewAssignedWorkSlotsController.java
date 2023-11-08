package PROJECTS.control_layer.staff;

import PROJECTS.entity_layer.bids;

import javax.swing.table.DefaultTableModel;

public class staffViewAssignedWorkSlotsController {
    public DefaultTableModel viewAssignedSlotList(String account){
        bids bids = new bids();
        return bids.viewAssignedSlotList(account);

    }
    public DefaultTableModel refreshAssignedSlotList(String account){
        bids bids = new bids();
        return bids.viewAssignedSlotList(account);
    }
}
