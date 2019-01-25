package main.business.service;

import main.common.UserType;
import main.common.message.CancelResultMessage;
import main.common.message.SignInResultMessage;
import main.common.message.SignUpResultMessage;
import main.common.user.*;

public interface UserBusinessService {

    SignUpResultMessage signUp(String username, String password, UserType type);

    CancelResultMessage cancel(String username, String password);

    boolean editPersonalInfo(Teacher u);
    boolean editPersonalInfo(Graduate u);
    boolean editPersonalInfo(Undergraduate u);
    boolean editPersonalInfo(Administrator u);

    User findByUsername(String username);

    SignInResultMessage signIn(String username, String password);

}
