package main.common.user;

import main.common.book.CheckInRecord;
import main.common.book.CheckOutRecord;
import main.common.user.message.Message;
import main.common.user.message.MessageBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class User {

    private String username;
    private String password;

    private int countLimitation;
    private int periodLimitation;
    private int borrowedCount;

    private boolean online;
    private boolean active;

    private MessageBox messageBox;

    private List<CheckOutRecord> checkOutRecords;
    private List<CheckInRecord> checkInRecords;

    protected User(String username, String password) {
        this.username = username;
        this.password = password;
        this.online = false;
        this.active = true;
        this.messageBox = new MessageBox();
        // countLimitation 和 periodLimitation 的设定交给子类
        this.borrowedCount = 0;
        this.checkOutRecords = new ArrayList<>();
        this.checkInRecords = new ArrayList<>();
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

    public int getCountLimitation() {
        return countLimitation;
    }

    protected void setCountLimitation(int countLimitation) {
        this.countLimitation = countLimitation;
    }

    public int getPeriodLimitation() {
        return periodLimitation;
    }

    protected void setPeriodLimitation(int periodLimitation) {
        this.periodLimitation = periodLimitation;
    }
    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
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

    public void uncheckMessage(Message msg) {
        messageBox.uncheck(msg);
    }

    public List<Message> getMessageList() {
        return messageBox.getMessages();
    }

    public List<CheckOutRecord> getCheckOutRecords() {
        return checkOutRecords;
    }

    public List<CheckInRecord> getCheckInRecords() {
        return checkInRecords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User that = (User) o;
        return Objects.equals(username, that.username);
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
                + "countLimitation=" + countLimitation + ", "
                + "periodLimitation=" + periodLimitation + ", "
                + "borrowedCount=" + borrowedCount + ", "
                + "online=" + online + ", "
                + "active=" + active
                + ']';
    }

}
