package main.common.book;

import main.common.user.User;

import java.util.Date;

public class CheckInRecord {

    private User user;
    private Book book;
    private Date date;

    public CheckInRecord(User user, Book book, Date date) {
        this.user = user;
        this.book = book;
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getDate() {
        return date;
    }

}
