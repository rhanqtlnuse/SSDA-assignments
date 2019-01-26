package main.common.user;

import main.common.Element;

import java.util.Objects;

public abstract class User implements Element {

    private String username;
    private String password;

    private boolean online;
    private boolean active;

    /**
     * precondition: username and password must not be null
     *
     * @param username
     * @param password
     */
    protected User(String username, String password) {
        this.username = username;
        this.password = password;
        this.online = false;
    }

    public String getUsername() {
        return username;
    }

    protected void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
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
        return Objects.hash(username);
    }

}
