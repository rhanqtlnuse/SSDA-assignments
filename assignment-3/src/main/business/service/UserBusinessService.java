package main.business.service;

import main.common.user.UserType;
import main.common.resultmessage.CancelResultMessage;
import main.common.resultmessage.SignInResultMessage;
import main.common.resultmessage.SignUpResultMessage;
import main.common.user.*;

public interface UserBusinessService {

    /**
     * 注册
     *
     * @param username 用户名
     * @param password 密码
     * @param type 用户类型，见 {@link UserType}
     * @return 注册信息，见 {@link SignUpResultMessage}
     */
    SignUpResultMessage signUp(String username, String password, UserType type);

    /**
     * 注销账户
     *
     * @param username 用户名
     * @param password 密码
     * @return 注销账户信息，见 {@link CancelResultMessage}
     */
    CancelResultMessage cancel(String username, String password);

    boolean editPersonalInfo(User u);

    User findByUsername(String username);

    SignInResultMessage signIn(String username, String password);
    void signOut(String username);

}
