package PROJECTS.control_layer.cafeOwner;

import PROJECTS.entity_layer.workSlots;

import java.util.Map;

public class ownerAddController {
    public boolean addVerification(Map<String, Object> slotAddInfo){
        if ((Double) slotAddInfo.get("hours")<1||(Double) slotAddInfo.get("hours")>8){
            return false;
        }else {
            workSlots workSlots= new workSlots();
            return workSlots.addNewSlot(slotAddInfo);
        }
    }
}
