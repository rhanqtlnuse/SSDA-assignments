package main.common.user;

import main.common.resultmessage.ResultMessage;
import main.common.user.visitor.Visitor;

public class Graduate extends User {

    private static final int GRADUATE_CHECK_OUT_COUNT_LIMIT = 10;
    private static final int GRADUATE_CHECK_OUT_PERIOD_LIMIT = 30;

    public Graduate(String username, String password) {
        super(username, password);
        setCountLimitation(GRADUATE_CHECK_OUT_COUNT_LIMIT);
        setPeriodLimitation(GRADUATE_CHECK_OUT_PERIOD_LIMIT);
    }

    @Override
    public ResultMessage accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
