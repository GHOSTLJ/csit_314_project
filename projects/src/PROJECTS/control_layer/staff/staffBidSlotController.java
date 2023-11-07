package PROJECTS.control_layer.staff;

import PROJECTS.entity_layer.bids;
import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class staffBidSlotController {
    public boolean bidSlots(String account,int slotNo,String role){
        bids bids= new bids();
        return bids.bidSlot(account,slotNo,role);
    }


    public DefaultTableModel viewAllSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewSlotFromDatabase();

    }
    public DefaultTableModel refreshAllSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewSlotFromDatabase();
    }
}
