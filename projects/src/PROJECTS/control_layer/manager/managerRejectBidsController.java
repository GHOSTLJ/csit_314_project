package PROJECTS.control_layer.manager;

import PROJECTS.entity_layer.bids;

public class managerRejectBidsController {
    public boolean rejectBid(int bidNO){
        bids bids = new bids();
        return bids.RejectBid(bidNO);
    }
}
