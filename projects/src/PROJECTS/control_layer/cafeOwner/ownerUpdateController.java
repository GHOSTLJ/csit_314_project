package PROJECTS.control_layer.cafeOwner;

import PROJECTS.entity_layer.workSlots;

import java.util.Map;

public class ownerUpdateController {
    public boolean updateSlot(Map<String, Object> slotUpdateInfo){
        if ((Double) slotUpdateInfo.get("hours") < 1 || (Double) slotUpdateInfo.get("hours") > 8) {
            return false;
        } else {
            workSlots workSlots = new workSlots();
            return workSlots.updateSlotToDatabase(slotUpdateInfo);
        }
    }
}
