package main.common.user;

import main.common.Element;
import main.common.Visitor;

public class Graduate extends User implements Element {

    private static final int GRADUATE_CHECK_OUT_LIMIT = 10;

    public Graduate(String username, String password) {
        super(username, password);
        setLimit(GRADUATE_CHECK_OUT_LIMIT);
    }

    @Override
    public void accept(Visitor visitor) {

    }

}
