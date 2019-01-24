package main.common;

public class Administrator extends User {

    public Administrator(String username, String password) {
        super(username, password);
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
