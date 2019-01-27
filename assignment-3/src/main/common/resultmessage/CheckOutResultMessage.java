package main.common.resultmessage;

public final class CheckOutResultMessage extends ResultMessage {

    public static final CheckOutResultMessage SUCCEEDED =
            new CheckOutResultMessage(
                    "SUCCEEDED",
                    0,
                    "借阅成功"
            );
    public static final CheckOutResultMessage COND_NOT_SATISFIED =
            new CheckOutResultMessage(
                    "COND_NOT_SATISFIED",
                    1,
                    "级别不够"
            );
    public static final CheckOutResultMessage CANNOT_BORROW_MORE =
            new CheckOutResultMessage(
                    "CANNOT_BORROW_MORE",
                    2,
                    "借阅数已达上限"
            );
    public static final CheckOutResultMessage NO_BOOK =
            new CheckOutResultMessage(
                    "NO_BOOK",
                    3,
                    "无可借书籍"
            );
    public static final CheckOutResultMessage NOT_ALLOWED =
            new CheckOutResultMessage(
                    "NOT_ALLOWED",
                    4,
                    "不允许"
            );

    private CheckOutResultMessage(String name, int ordinal, String literal) {
        super(name, ordinal, literal);
    }

}
