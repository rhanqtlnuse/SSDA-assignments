package main.common.user;

import main.common.user.visitor.Element;
import main.common.user.visitor.Visitor;
import main.common.resultmessage.ResultMessage;

public class Graduate extends User implements Element {

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
