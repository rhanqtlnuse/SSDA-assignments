package main.common;

import main.common.constant.BookConstants;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private String isbn;
    private String title;
    private String author;
    private List<String> categories;

    public Book(String isbn, String title) {
        this(isbn, title, BookConstants.DEFAULT_AUTHOR, new ArrayList<>(BookConstants.DEFAULT_CATEGORY));
    }

    public Book(String isbn, String title, String author) {
        this(isbn, title, author, new ArrayList<>(BookConstants.DEFAULT_CATEGORY));
    }

    public Book(String isbn, String title, List<String> categories) {
        this(isbn, title, BookConstants.DEFAULT_AUTHOR, categories);
    }

    public Book(String isbn, String title, String author, List<String> categories) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.categories = categories;
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

}
