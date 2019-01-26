package main.common.resultmessage;

public enum SignUpResultMessage {

    SUCCEEDED("注册成功"),
    DUPLICATE_USERNAME("用户名重复"),
    INVALID_CHARACTER_IN_USERNAME("用户名中含有非法字符"),
    PASSWORD_TOO_SHORT("密码太短");

    private String literal;

    SignUpResultMessage(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
