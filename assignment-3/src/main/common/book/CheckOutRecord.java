package main.common.book;

import main.common.user.User;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 借阅记录
 *
 * @author HanQi
 * @version 1.0
 * @since 2018/1/26
 */
public class CheckOutRecord implements Comparable<CheckOutRecord> {

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

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

    @Override
    public int compareTo(CheckOutRecord that) {
        int t1 = fromDate.compareTo(that.fromDate);
        if (t1 != 0) {
            return t1;
        } else {
            return toDate.compareTo(that.toDate);
        }
    }

    @Override
    public String toString() {
        return "借阅者：" + user.getUsername()
                + "，书籍：《" + book.getTitle() + '》'
                + "，借阅时间：" + DATE_FORMATTER.format(fromDate)
                + "，应还时间：" + DATE_FORMATTER.format(toDate);
    }

}
