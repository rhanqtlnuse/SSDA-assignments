package main.data.impl.book;

import main.common.book.Book;
import main.data.service.book.BookDataService;

import java.util.List;

public class BookDataServiceImpl implements BookDataService {

    private static final Book[] BOOKS = new Book[] {

    };

    private static BookDataServiceImpl singleton = new BookDataServiceImpl();

    public static BookDataServiceImpl getInstance() {
        return singleton;
    }

    private BookDataServiceImpl() { }

    @Override
    public void add(Book b) {

    }

    @Override
    public void remove(Book b) {

    }

    @Override
    public void update(Book b) {

    }

    @Override
    public Book findByISBN(String isbn) {
        return null;
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> findAll() {
        return null;
    }

}
