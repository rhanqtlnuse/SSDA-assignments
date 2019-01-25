package main.common.user;

import main.common.Visitor;

public class Teacher extends User {

    public Teacher(String username, String password) {
        super(username, password);
    }

    @Override
    public void accept(Visitor visitor) {

    }

}
