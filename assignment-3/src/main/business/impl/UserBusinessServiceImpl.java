package main.business.impl;

import main.business.impl.user.PersonalInfoMediator;
import main.business.impl.user.factory.*;
import main.business.service.UserBusinessService;
import main.common.book.CheckOutRecord;
import main.common.resultmessage.CancelResultMessage;
import main.common.resultmessage.SignInResultMessage;
import main.common.resultmessage.SignUpResultMessage;
import main.common.user.Administrator;
import main.common.user.User;
import main.common.user.UserType;
import main.data.impl.user.UserDataServiceImpl;
import main.data.service.user.UserDataService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 用户管理业务逻辑实现类
 *
 * @author HanQi
 * @version 1.0
 * @since 2019/1/25
 */
public class UserBusinessServiceImpl implements UserBusinessService {

    /**
     * ADMINISTRATOR,
     * TEACHER,
     * GRADUATE,
     * UNDERGRADUATE
     */
    private static final UserFactory[] FACTORIES = new UserFactory[] {
            AdministratorFactory.getInstance(),
            TeacherFactory.getInstance(),
            GraduateFactory.getInstance(),
            UndergraduateFactory.getInstance()
    };

    private static UserBusinessServiceImpl singleton = new UserBusinessServiceImpl();

    public static UserBusinessService getInstance() {
        return singleton;
    }

    private final UserDataService userDataService = UserDataServiceImpl.getInstance();

    private UserBusinessServiceImpl() { }

    @Override
    public SignUpResultMessage signUp(String username, String password, UserType type) {
        userDataService.add(FACTORIES[type.ordinal()].create(username, password));
        return SignUpResultMessage.SUCCEEDED;
    }

    @Override
    public CancelResultMessage cancel(String username, String password) {
        User u = userDataService.findByUsername(username);
        userDataService.remove(u);
        if (u.getClass() == Administrator.class) {
            PersonalInfoMediator.getInstance().removeAdministrator((Administrator) u);
        }
        return CancelResultMessage.SUCCEEDED;
    }

    @Override
    public void updatePersonalInfo(User u) {
        PersonalInfoMediator.getInstance().updateUserInfo(u);
    }

    @Override
    public User findByUsername(String username) {
        if (username == null || "".equals(username)) {
            return null;
        }
        return userDataService.findByUsername(username);
    }

    @Override
    public List<User> findAll() {
        return userDataService.findAll();
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

    @Override
    public List<CheckOutRecord> getCheckOutRecordsOf(User u) {
        return u.getCheckOutRecords();
    }

    @Override
    public List<CheckOutRecord> getAllCheckOutRecords() {
        List<CheckOutRecord> res = new ArrayList<>();
        List<User> users = findAll();
        for (User user : users) {
            res.addAll(user.getCheckOutRecords());
        }
        Collections.sort(res);
        return res;
    }

}
