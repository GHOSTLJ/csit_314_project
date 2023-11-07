package PROJECTS.control_layer;

import PROJECTS.boundary_layer.userWorkSpace;
import PROJECTS.entity_layer.user;

public class UserController {
    public boolean UserLogin(String account, String password){
        user user = new user();
        return user.LoginVerify(account, password);
    }
}
