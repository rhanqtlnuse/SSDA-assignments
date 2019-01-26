package main.business.service;

import main.common.book.CheckOutRecord;
import main.common.resultmessage.CancelResultMessage;
import main.common.resultmessage.SignInResultMessage;
import main.common.resultmessage.SignUpResultMessage;
import main.common.user.User;
import main.common.user.UserType;

import java.util.List;

public interface UserBusinessService {

    /**
     * 注册
     *
     * @param type 用户类型，见 {@link UserType}
     * @return 注册信息，见 {@link SignUpResultMessage}
     */
    SignUpResultMessage signUp(String username, String password, UserType type);

    /**
     * 注销（停用）账户
     *
     * @return 注销账户信息，见 {@link CancelResultMessage}
     */
    CancelResultMessage cancel(String username, String password);

    void updatePersonalInfo(User u);

    User findByUsername(String username);
    List<User> findAll();

    SignInResultMessage signIn(String username, String password);

    /**
     * 登出
     *
     * @param username 用户名
     */
    void signOut(String username);

    List<CheckOutRecord> getCheckOutRecordsOf(User u);
    List<CheckOutRecord> getAllCheckOutRecords();

}
