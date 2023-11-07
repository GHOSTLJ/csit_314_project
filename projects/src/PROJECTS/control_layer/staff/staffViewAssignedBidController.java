package PROJECTS.control_layer.staff;

import PROJECTS.entity_layer.bids;

import javax.swing.table.DefaultTableModel;

public class staffViewAssignedBidController {
    public DefaultTableModel viewAssignedBidsList(String account){
        bids bids = new bids();
        return bids.vieAssignedBids(account);

    }
    public DefaultTableModel refreshAssignedBidsList(String account){
        bids bids = new bids();
        return bids.vieAssignedBids(account);
    }
}
