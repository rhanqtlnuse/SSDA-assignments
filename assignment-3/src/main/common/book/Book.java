package main.common.book;

import main.common.constant.BookConstants;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private List<String> categories;
    private String contentURL;

    public Book(String isbn, String title, String contentURL) {
        this(isbn, title, BookConstants.DEFAULT_AUTHOR, new ArrayList<>(BookConstants.DEFAULT_CATEGORY), contentURL);
    }

    public Book(String isbn, String title, String author, String contentURL) {
        this(isbn, title, author, new ArrayList<>(BookConstants.DEFAULT_CATEGORY), contentURL);
    }

    public Book(String isbn, String title, List<String> categories, String contentURL) {
        this(isbn, title, BookConstants.DEFAULT_AUTHOR, categories, contentURL);
    }

    public Book(String isbn, String title, String author, List<String> categories, String contentURL) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.categories = categories;
        this.contentURL = contentURL;
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

}
