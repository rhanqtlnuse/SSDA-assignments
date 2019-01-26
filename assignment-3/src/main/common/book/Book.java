package main.common.book;

import java.util.List;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private List<String> categories;
    private String contentURL;

    private int outCount;
    private int remainingCount;

    public Book(String isbn, String title, String author, List<String> categories, String contentURL, int count) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.contentURL = contentURL;
        this.outCount = 0;
        this.remainingCount = count;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getCategories() {
        return categories;
    }

    public String getContentURL() {
        return contentURL;
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

}
