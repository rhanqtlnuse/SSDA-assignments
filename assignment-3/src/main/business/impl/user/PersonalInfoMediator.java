package main.business.impl.user;

import main.common.user.*;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * 中介类，用户修改信息之后通知管理员
 * 考虑使用**队列**
 */
public class PersonalInfoMediator {

    private List<Administrator> admins;

    private static final PersonalInfoMediator singleton = new PersonalInfoMediator();

    public static PersonalInfoMediator getInstance() {
        return singleton;
    }

    private PersonalInfoMediator() {
        this.admins = new ArrayList<>();
    }

    public void addAdmin(Administrator admin)  {
        admins.add(admin);
    }

    public void removeAdmin(Administrator admin) {
        admins.remove(admin);
    }

    public void editUserInfo(User u) {
        informAll(u);
    }

    protected final List<Administrator> getAdmins() {
        return admins;
    }

    private void informAll(User u) {
        for (Administrator a : admins) {
            inform(a, u);
        }
    }

    private void inform(Administrator a, User u) {

    }

}
