package main.common.message;

public enum SignInResultMessage {

    SUCCEEDED("登录成功"),
    USERNAME_NOT_EXISTS("用户名不存在"),
    WRONG_PASSWORD("密码错误"),
    INVALID_ACCOUNT("账户已注销"),
    DUPLICATE_SIGN_IN("重复登陆");

    private String literal;

    SignInResultMessage(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
