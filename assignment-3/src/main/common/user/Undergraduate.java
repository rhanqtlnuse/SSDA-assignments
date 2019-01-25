package main.common.user;

import main.common.Visitor;

public class Undergraduate extends User {

    public Undergraduate(String username, String password) {
        super(username, password);
    }

    @Override
    public void accept(Visitor visitor) {

    }

}
