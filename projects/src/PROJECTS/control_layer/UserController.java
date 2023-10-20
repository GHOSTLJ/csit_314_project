package PROJECTS.control_layer;

import PROJECTS.boundary_layer.userWorkSpace;
import PROJECTS.entity_layer.user;

public class UserController {

    private String account;
    private String password;
    private String name;
    private String profile;

    public UserController() {
    }

    public UserController(String account, String password, String name, String profile) {
        this.account = account;
        this.password = password;
        this.name = name;
        this.profile = profile;
    }

    public UserController(String description) {
        this.profile = description;
    }
    public UserController(String name, String profile) {
        this.name = name;
        this.profile = profile;
    }

    public boolean UserLogin(String account, String password){

        user user = new user();

        return user.LoginVerify(account, password);
    }


    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
