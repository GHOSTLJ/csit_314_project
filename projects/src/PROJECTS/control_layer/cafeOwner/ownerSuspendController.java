package PROJECTS.control_layer.cafeOwner;

import PROJECTS.entity_layer.userAccount;
import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class ownerSuspendController {
    public boolean suspendSlot(int SlotNo){

        workSlots workSlots= new workSlots();
        return workSlots.suspendSlotToDatabase(SlotNo);
    }


    public DefaultTableModel viewAllSlots(){
        workSlots workSlots = new workSlots();
        DefaultTableModel myModel = workSlots.viewSuspend();
        return myModel;
    }
    public DefaultTableModel refreshAllSlots(){
        workSlots workSlots = new workSlots();
        return workSlots.viewSuspend();
    }
}
