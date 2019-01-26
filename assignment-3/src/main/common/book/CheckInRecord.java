package main.common.book;

import main.common.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CheckInRecord implements Comparable<CheckInRecord> {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

    @Override
    public int compareTo(CheckInRecord that) {
        return date.compareTo(that.date);
    }

    @Override
    public String toString() {
        return "借阅者：" + user.getUsername()
                + "，书籍：《" + book.getTitle() + '》'
                + "，归还时间：" + DATE_FORMATTER.format(date);
    }

}
