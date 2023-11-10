package PROJECTS.control_layer.manager;

import PROJECTS.entity_layer.bids;

import javax.swing.table.DefaultTableModel;

public class managerApproveBidsController {
    //判断 staff 是否 注册过当前slot ==不用判断

    // 验证 slot need 和 registered； registered 小于 need 证明 当前角色 在 slot有空位 可以继续；
    // 再验证 slot hour 和 staff Max hour ,slot hour 小于 Max hour 证明时间合适，可以继续

    //更新slot
    //更新max hour
    public boolean approveBid(int BidNO){
        bids bids = new bids();
        return bids.ApprovedBid(BidNO);
    }


    public DefaultTableModel viewPendingBids(){
        bids bids = new bids();
        return bids.viewPendingBid();

    }
    public DefaultTableModel refreshPendingBids(){
        bids bids = new bids();
        return bids.viewPendingBid();
    }
}
