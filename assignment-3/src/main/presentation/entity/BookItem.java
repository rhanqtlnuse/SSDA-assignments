package main.presentation.entity;

import main.common.book.Book;

public class BookItem {
    private String isbn;
    private String title;
    private String author;
    private String categories;
    private String level;
    private int outCount;
    private int remainingCount;
    private String contentURL;

    public BookItem() {}

    public BookItem(Book book) {
        this.isbn = book.getIsbn();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.categories = "";
        this.level = book.getLevel().toString();
        this.outCount = book.getOutCount();
        this.remainingCount = book.getRemainingCount();
        this.contentURL = book.getContentURL();
        for(int i = 0; i < book.getCategories().size(); i++) {
            this.categories += book.getCategories().get(i);
            if(i < book.getCategories().size() - 1) {
                this.categories += "、";
            }
        }
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getOutCount() {
        return outCount;
    }

    public void setOutCount(int outCount) {
        this.outCount = outCount;
    }

    public int getRemainingCount() {
        return remainingCount;
    }

    public void setRemainingCount(int remainingCount) {
        this.remainingCount = remainingCount;
    }

    public String getContentURL() {
        return contentURL;
    }

    public void setContentURL(String contentURL) {
        this.contentURL = contentURL;
    }
}
