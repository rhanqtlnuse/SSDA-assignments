package main.common.user;

import main.common.Element;
import main.common.user.message.Message;
import main.common.user.message.MessageBox;

import java.util.List;
import java.util.Objects;

public abstract class User {

    private String username;
    private String password;

    private int limit;
    private int borrowed;

    private boolean online;
    private boolean active;

    private MessageBox messageBox;

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
        this.online = false;
        this.active = true;
        this.messageBox = new MessageBox();
        // limit 的设定交给子类
        this.borrowed = 0;
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

    public int getLimit() {
        return limit;
    }

    protected void setLimit(int limit) {
        this.limit = limit;
    }

    public int getBorrowed() {
        return borrowed;
    }

    public void setBorrowed(int borrowed) {
        this.borrowed = borrowed;
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

    public void receiveMessage(Message msg) {
        messageBox.add(msg);
    }

    public void removeMessage(Message msg) {
        messageBox.remove(msg);
    }

    public void checkMessage(Message msg) {
        messageBox.check(msg);
    }

    public List<Message> getMessageList() {
        return messageBox.getMessages();
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

    @Override
    public String toString() {
        return getClass().getSimpleName() + '['
                + "username='" + username + '\'' + ", "
                + "password='" + password + '\'' + ", "
                + "online=" + online + ", "
                + "active=" + active
                + ']';
    }

}
