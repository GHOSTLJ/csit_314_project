package PROJECTS.control_layer.cafeOwner;

import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class ownerSearchController {
    public DefaultTableModel searchSlot(String formattedDate){
        workSlots workSlots = new workSlots();
        DefaultTableModel updatedModel = workSlots.searchSlotFromDatabase(formattedDate);
        return updatedModel;
    }
}
