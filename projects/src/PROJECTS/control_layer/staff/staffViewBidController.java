package PROJECTS.control_layer.staff;

import PROJECTS.entity_layer.bids;

import javax.swing.table.DefaultTableModel;

public class staffViewBidController {

    public DefaultTableModel viewPersonalBid(String account){
        bids bids = new bids();
        return  bids.viewPersonalBid(account);
    }
    public DefaultTableModel refreshPersonalBid(String account){
        bids bids = new bids();
        return  bids.viewPersonalBid(account);
    }
}
