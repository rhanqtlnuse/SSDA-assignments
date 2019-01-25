package main.common.user;

import main.common.Visitor;

public class Graduate extends User {

    public Graduate(String username, String password) {
        super(username, password);
    }

    @Override
    public void accept(Visitor visitor) {

    }

}
