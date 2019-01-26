package main.common.user.message;

import java.util.Date;

public class MessageGenerator {

    private static final String UPDATE_PERSONAL_INFO_TEMPLATE = "用户\"%s\"更新了个人信息。";

    public static Message updatePersonalInfoMessage(String username) {
        return new Message(String.format(UPDATE_PERSONAL_INFO_TEMPLATE, username), new Date());
    }

}
