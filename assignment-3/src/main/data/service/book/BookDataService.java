package main.data.service.book;

import main.common.book.Book;

import java.util.List;

public interface BookDataService {

    void add(Book b);

    void remove(Book b);

    void update(Book b);

    Book findByISBN(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

}
