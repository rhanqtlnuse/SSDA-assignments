package main.common;

import java.util.Objects;

public abstract class User implements Element {

    private String username;
    private String password;

    /**
     * precondition: username and password must not be null
     *
     * @param username
     * @param password
     */
    protected User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    protected String getPassword() {
        return password;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof User) {
            return username.equals(((User) o).username);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

}
