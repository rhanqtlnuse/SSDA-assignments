package main.common.user;

import main.common.Visitor;

public class Administrator extends User {

    public Administrator(String username, String password) {
        super(username, password);
    }

    @Override
    public void accept(Visitor visitor) {

    }

    @Override
    public boolean equals(Object o) {
        // TODO
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        // TODO
        return super.hashCode();
    }

}
