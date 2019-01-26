package main.common.book;

import java.util.List;
import java.util.Objects;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private List<String> categories;
    private String contentURL;

    private Level level;

    private int outCount;
    private int remainingCount;

    private boolean active;

    public Book(String isbn, String title, String author,
                List<String> categories, String contentURL,
                Level level, int count) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.contentURL = contentURL;
        this.level = level;
        this.outCount = 0;
        this.remainingCount = count;
        this.active = true;
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

    public Level getLevel() {
        return level;
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

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Book[" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", categories=" + categories +
                ", level=" + level +
                ", outCount=" + outCount +
                ", remainingCount=" + remainingCount +
                ", active=" + active +
                ']';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Book that = (Book) o;
        return Objects.equals(isbn, that.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }

}
