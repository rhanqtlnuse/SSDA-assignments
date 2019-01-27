package main.common.user;

import main.common.resultmessage.ResultMessage;
import main.common.user.visitor.Visitor;

public class Teacher extends User {

    private static final int TEACHER_CHECK_OUT_COUNT_LIMIT = 20;
    private static final int TEACHER_CHECK_OUT_PERIOD_LIMIT = 60;

    public Teacher(String username, String password) {
        super(username, password);
        setCountLimitation(TEACHER_CHECK_OUT_COUNT_LIMIT);
        setPeriodLimitation(TEACHER_CHECK_OUT_PERIOD_LIMIT);
    }

    @Override
    public ResultMessage accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
