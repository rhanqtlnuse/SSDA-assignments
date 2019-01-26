package main.common.user;

import main.common.Element;
import main.common.Visitor;

public class Teacher extends User implements Element {

    private static final int TEACHER_CHECK_OUT_LIMIT = 20;

    public Teacher(String username, String password) {
        super(username, password);
        setLimit(TEACHER_CHECK_OUT_LIMIT);
    }

    @Override
    public void accept(Visitor visitor) {

    }

}
