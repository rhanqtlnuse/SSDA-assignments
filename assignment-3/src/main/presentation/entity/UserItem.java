package main.presentation.entity;

import main.common.user.Graduate;
import main.common.user.Teacher;
import main.common.user.Undergraduate;
import main.common.user.User;

public class UserItem {
    private String username;
    private String name;
    private String identity;
    private String gender;
    private int countLimitation;
    private int periodLimitation;
    private String forfeit;
    private int borrowedCount;

    public UserItem() {}

    public UserItem(User user) {
        this.username = user.getUsername();
        this.name = "";
        this.gender = "";
        this.countLimitation = user.getCountLimitation();
        this.periodLimitation = user.getPeriodLimitation();
        this.forfeit = "0";
        this.borrowedCount = user.getBorrowedCount();
        switch (user.getClass().getSimpleName()) {
            case "Undergraduate": this.identity = "本科生"; break;
            case "Graduate": this.identity = "研究生"; break;
            case "Teacher": this.identity = "教师"; break;
            case "Administrator": this.identity = "管理员"; break;
            default: this.identity = "";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public int getCountLimitation() {
        return countLimitation;
    }

    public void setCountLimitation(int countLimitation) {
        this.countLimitation = countLimitation;
    }

    public int getPeriodLimitation() {
        return periodLimitation;
    }

    public void setPeriodLimitation(int periodLimitation) {
        this.periodLimitation = periodLimitation;
    }

    public int getBorrowedCount() {
        return borrowedCount;
    }

    public void setBorrowedCount(int borrowedCount) {
        this.borrowedCount = borrowedCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getForfeit() {
        return forfeit;
    }

    public void setForfeit(String forfeit) {
        this.forfeit = forfeit;
    }
}
