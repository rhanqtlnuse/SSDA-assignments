package main.presentation.entity;

import main.common.book.CheckOutRecord;

import java.text.SimpleDateFormat;

public class CheckOutRecordItem {
    private String bookIsbn;
    private String bookTitle;
    private String username;
    private String fromDate;
    private String toDate;

    private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");

    public CheckOutRecordItem() {}

    public CheckOutRecordItem(CheckOutRecord record) {
        this.bookIsbn = record.getBook().getIsbn();
        this.username = record.getUser().getUsername();
        this.bookTitle = record.getBook().getTitle();
        this.fromDate = DATE_FORMATTER.format(record.getFromDate());
        this.toDate = DATE_FORMATTER.format(record.getToDate());
    }

    public String getBookIsbn() {
        return bookIsbn;
    }

    public void setBookIsbn(String bookIsbn) {
        this.bookIsbn = bookIsbn;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
}
