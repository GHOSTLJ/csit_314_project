package PROJECTS.control_layer.manager;

import PROJECTS.entity_layer.bids;
import PROJECTS.entity_layer.userAccount;

import javax.swing.table.DefaultTableModel;

public class managerViewAllBidsController {
    public DefaultTableModel viewAllBids(){
        bids bids = new bids();
        return bids.viewAllBid();

    }
    public DefaultTableModel refreshAllBids(){
        bids bids = new bids();
        return bids.viewAllBid();
    }
}
