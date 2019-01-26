package main.common.user;

public enum UserType {

    ADMINISTRATOR("管理员"),
    TEACHER("教师"),
    GRADUATE("研究生"),
    UNDERGRADUATE("本科生");

    private String literal;

    UserType(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
