package main.business.service;

import main.common.Book;
import main.common.message.BookManagementResultMessage;

import java.util.List;

public interface BookBusinessService {

    BookManagementResultMessage add(Book b);

    BookManagementResultMessage remove(Book b);

    BookManagementResultMessage editBookInfo(Book b);

    Book findByISBN(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

}
