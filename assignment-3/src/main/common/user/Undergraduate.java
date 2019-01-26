package main.common.user;

import main.common.Element;
import main.common.Visitor;

public class Undergraduate extends User implements Element {

    private static final int UNDERGRADUATE_CHECK_OUT_LIMIT = 5;

    public Undergraduate(String username, String password) {
        super(username, password);
        setLimit(UNDERGRADUATE_CHECK_OUT_LIMIT);
    }

    @Override
    public void accept(Visitor visitor) {

    }

}
