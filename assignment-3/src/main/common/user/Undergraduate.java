package main.common.user;

import main.common.resultmessage.ResultMessage;
import main.common.user.visitor.Visitor;

public class Undergraduate extends User {

    private static final int UNDERGRADUATE_CHECK_OUT_COUNT_LIMIT = 5;
    private static final int UNDERGRADUATE_CHECK_OUT_PERIOD_LIMIT = 30;

    public Undergraduate(String username, String password) {
        super(username, password);
        setCountLimitation(UNDERGRADUATE_CHECK_OUT_COUNT_LIMIT);
        setPeriodLimitation(UNDERGRADUATE_CHECK_OUT_PERIOD_LIMIT);
    }

    @Override
    public ResultMessage accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
