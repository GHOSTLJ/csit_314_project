import PROJECTS.boundary_layer.*;
import PROJECTS.control_layer.*;
import PROJECTS.control_layer.manager.managerOfferWorkController;
import PROJECTS.entity_layer.bids;
import PROJECTS.entity_layer.workSlots;

import java.util.Map;


//import project.control_layer.*;
//import project.entity_layer.*;
public class Main {
    public static void main(String[] args) {
        //new adminPage("adm");
        //new staffPage("tom7","staff14");
        //new managerPage();

        /*User user = null;
        //s
        //sstest

        user.UserLogin();*/
        new loginPage();//main entry
        //new ownerPage("22");

        //managerOfferWorkController managerOfferWorkController = new managerOfferWorkController();
        //managerOfferWorkController.offerWork(1,"staff1","waiter");
        /*workSlots workSlots = new workSlots();
        workSlots.updateRoleRegistered(2,"chef");*/

        //managerOfferWorkController.offerWork(1,"staff5","chef");
        /*bids bids = new bids();
        bids.getBidInformation(3);*/

    }
}