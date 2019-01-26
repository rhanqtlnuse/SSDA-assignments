package main.common.book;

import main.common.user.User;

import java.util.Date;

/**
 * 借阅记录
 *
 * @author HanQi
 * @version 1.0
 * @since 2018/1/26
 */
public class CheckOutRecord {

    private User user;
    private Book book;
    private Date fromDate;
    private Date toDate;

    public CheckOutRecord(User user, Book book, Date fromDate, Date toDate) {
        this.user = user;
        this.book = book;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

}
