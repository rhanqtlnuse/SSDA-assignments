package main.common.resultmessage;

public enum CancelResultMessage {

    SUCCEEDED("注销成功"),
    USERNAME_NOT_EXISTS("用户名不存在"),
    WRONG_PASSWORD("密码错误");

    private String literal;

    CancelResultMessage(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
