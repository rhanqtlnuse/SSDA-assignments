package main.business.impl.user;

import main.common.user.Administrator;
import main.common.user.User;
import main.common.user.message.MessageGenerator;
import main.data.impl.user.UserDataServiceImpl;
import main.data.service.user.UserDataService;

import java.util.ArrayList;
import java.util.List;

/**
 * 中介类，用户修改信息之后通知管理员
 */
public class PersonalInfoMediator {

    private List<Administrator> admins;

    private static final PersonalInfoMediator singleton = new PersonalInfoMediator();

    public static PersonalInfoMediator getInstance() {
        return singleton;
    }

    private final UserDataService userDataService = UserDataServiceImpl.getInstance();

    private PersonalInfoMediator() {
        this.admins = new ArrayList<>();
    }

    public void addAdministrator(Administrator admin)  {
        admins.add(admin);
    }

    public void removeAdministrator(Administrator admin) {
        admins.remove(admin);
    }

    public final List<Administrator> getAdministrators() {
        return admins;
    }

    public void updateUserInfo(User u) {
        userDataService.update(u);
        notifyAllAdministrators(u);
    }

    private void notifyAllAdministrators(User u) {
        for (Administrator a : admins) {
            notifyAdministrator(a, u);
        }
    }

    private void notifyAdministrator(Administrator a, User u) {
        a.receiveMessage(MessageGenerator.updatePersonalInfoMessage(u.getUsername()));
    }

}
