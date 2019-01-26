package main.data.service.book;

import main.common.CheckOutVisitor;
import main.common.book.Book;
import main.common.resultmessage.CheckOutResultMessage;
import main.common.user.User;

import java.util.List;

public interface BookDataService {

    void add(Book b);

    void remove(Book b);

    void update(Book b);

    Book findByISBN(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

    CheckOutResultMessage checkOut(User u, Book b);
}
