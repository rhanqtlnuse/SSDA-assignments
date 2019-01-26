package main.business.impl;

import main.business.impl.user.PersonalInfoMediator;
import main.business.impl.user.factory.AdministratorFactory;
import main.business.impl.user.factory.GraduateFactory;
import main.business.impl.user.factory.TeacherFactory;
import main.business.impl.user.factory.UndergraduateFactory;
import main.business.service.UserBusinessService;
import main.common.user.UserType;
import main.common.message.CancelResultMessage;
import main.common.message.SignInResultMessage;
import main.common.message.SignUpResultMessage;
import main.common.user.*;
import main.data.impl.user.UserDataServiceImpl;
import main.data.service.user.UserDataService;

/**
 * 用户管理业务逻辑实现类
 *
 * @author HanQi
 * @version 1.0
 * @since 2019/1/25
 */
public class UserBusinessServiceImpl implements UserBusinessService {

    private static UserBusinessServiceImpl singleton = new UserBusinessServiceImpl();

    public static UserBusinessService getInstance() {
        return singleton;
    }

    private final UserDataService userDataService = UserDataServiceImpl.getInstance();
    private final PersonalInfoMediator personalInfoMediator = PersonalInfoMediator.getInstance();

    private UserBusinessServiceImpl() { }
    @Override
    public SignUpResultMessage signUp(String username, String password, UserType type) {
        switch (type) {
            case TEACHER:
                userDataService.add(TeacherFactory.getInstance().create(username, password));
                break;
            case GRADUATE:
                userDataService.add(GraduateFactory.getInstance().create(username, password));
                break;
            case UNDERGRADUATE:
                userDataService.add(UndergraduateFactory.getInstance().create(username, password));
                break;
            case ADMINISTRATOR:
                userDataService.add(AdministratorFactory.getInstance().create(username, password));
                break;
        }
        return null;
    }

    @Override
    public CancelResultMessage cancel(String username, String password) {
        User u = userDataService.findByUsername(username);
        userDataService.remove(u);
        return CancelResultMessage.SUCCEEDED;
    }

    @Override
    public boolean editPersonalInfo(Teacher u) {
        personalInfoMediator.editUserInfo(u);
        return false;
    }

    @Override
    public boolean editPersonalInfo(Graduate u) {
        personalInfoMediator.editUserInfo(u);
        return false;
    }

    @Override
    public boolean editPersonalInfo(Undergraduate u) {
        personalInfoMediator.editUserInfo(u);
        return false;
    }

    @Override
    public boolean editPersonalInfo(Administrator u) {
        personalInfoMediator.editUserInfo(u);
        return false;
    }

    @Override
    public User findByUsername(String username) {
        if (username == null || "".equals(username)) {
            return null;
        }
        return userDataService.findByUsername(username);
    }

    /**
     * 目前选择直接对主数据库进行修改登录状态，有以下两种修改方案：
     * 1. 类似缓存的机制，将登录信息载入辅数据库（slave）
     * 2. 强制登陆，踢出已登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录信息
     * @see SignInResultMessage
     */
    @Override
    public SignInResultMessage signIn(String username, String password) {
        User u = findByUsername(username);
        if (u == null) {
            return SignInResultMessage.USERNAME_NOT_EXISTS;
        }
        if (!password.equals(u.getPassword())) {
            return SignInResultMessage.WRONG_PASSWORD;
        }
        if (!u.isActive()) {
            return SignInResultMessage.INVALID_ACCOUNT;
        }
        if (u.isOnline()) {
            return SignInResultMessage.DUPLICATE_SIGN_IN;
        }
        u.setOnline(true);
        userDataService.update(u);
        return SignInResultMessage.SUCCEEDED;
    }

    @Override
    public void signOut(String username) {
        User u = findByUsername(username);
        u.setOnline(false);
        userDataService.update(u);
    }

}
