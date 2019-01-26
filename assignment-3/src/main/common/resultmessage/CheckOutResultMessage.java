package main.common.resultmessage;

public enum CheckOutResultMessage {

    SUCCEEDED("借阅成功"),
    NO_BOOK("无可借书籍");

    private String literal;

    CheckOutResultMessage(String literal) {
        this.literal = literal;
    }

    @Override
    public String toString() {
        return literal;
    }

}
