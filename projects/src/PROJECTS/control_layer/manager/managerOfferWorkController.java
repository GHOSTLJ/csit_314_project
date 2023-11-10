package PROJECTS.control_layer.manager;

import PROJECTS.entity_layer.bids;
import PROJECTS.entity_layer.userAccount;
import PROJECTS.entity_layer.workSlots;

import javax.swing.table.DefaultTableModel;

public class managerOfferWorkController {

    public boolean offerWork(int SlotNO ,String Account,String role){
        workSlots workSlots = new workSlots();
        bids bids = new bids();

        //判断 staff 是否 注册过当前slot
        if (bids.verifyAssignedBid(SlotNO,Account)){
            System.out.println("1");
            return false;
        }


        // 验证 slot need 和 registered； registered 小于 need 证明 当前角色 在 slot有空位 可以继续；
        if (!verifyNeedAndRegistered(SlotNO,role)){
            System.out.println("2");
            return false;
        }

        // 再验证 slot hour 和 staff Max hour ,slot hour 小于 Max hour 证明时间合适，可以继续
        if (!verifyHours(SlotNO,Account)){
            System.out.println("3");
            return false;
        }

        return bids.allocateWork(SlotNO, Account, role);
        //return workSlots.updateRoleRegistered(SlotNO, role);
    }

    public boolean verifyHours(int SlotNO,String Account){
        workSlots workSlots = new workSlots();
        bids bids = new bids();

        double slotHour = workSlots.getSlotHour(SlotNO);
        double staffHour = bids.getPersonalWorkingHour(Account);
        if (slotHour == 0 || staffHour == 0 || staffHour < slotHour) {
            return false;
        }else return true;
    }

    public boolean verifyNeedAndRegistered(int SlotNO,String role){
        workSlots workSlots = new workSlots();
        return workSlots.verifyNeedAndRegisteredRole(SlotNO, role);
    }


    public DefaultTableModel viewAvailableSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewAvailableSlots();

    }
    public DefaultTableModel refreshAvailableSlotList(){
        workSlots workSlots = new workSlots();
        return workSlots.viewAvailableSlots();
    }

    public DefaultTableModel viewAllStaffList(){
        userAccount userAccount = new userAccount();
        return userAccount.viewStaffList();

    }
    public DefaultTableModel refreshAllStaffList(){
        userAccount userAccount = new userAccount();
        return userAccount.viewStaffList();
    }
}
