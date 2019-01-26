package main.common.resultmessage;

public enum BookManagementResultMessage {

    SUCCEEDED("成功"),
    NULL_PARAM("参数为 null"),
    INVALID_ISBN("ISBN 不合法"),
    INVALID_TITLE("标题不合法"),
    INVALID_AUTHOR("作者不合法"),
    INVALID_CATEGORY("类别不合法");

    private String literal;

    BookManagementResultMessage(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
