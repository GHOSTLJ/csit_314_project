package PROJECTS.control_layer.staff;

import PROJECTS.entity_layer.bids;
import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class staffIndicateHourController {
    public boolean indicateMaxHour(String account, int hour){
        if (hour>100||hour <1){
            return false;
        }
        bids bids = new bids();

        return bids.indicatePersonalWorkingHour(account,hour);
    }

    public DefaultTableModel viewHourList(String account){
        bids bids = new bids();
        return bids.viewPersonalWorkingHour(account);

    }
    public DefaultTableModel refreshHourList(String account){
        bids bids = new bids();
        return bids.viewPersonalWorkingHour(account);
    }
}
