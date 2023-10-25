package PROJECTS.control_layer.cafeOwner;

import PROJECTS.entity_layer.workSlots;

public class ownerSuspendController {
    public boolean suspendSlot(int SlotNo){

        workSlots workSlots= new workSlots();
        return workSlots.suspendSlotToDatabase(SlotNo);
    }
}
