package main.business.service;

import main.common.book.Book;
import main.common.resultmessage.BookManagementResultMessage;
import main.common.resultmessage.CheckOutResultMessage;
import main.common.user.Graduate;
import main.common.user.Teacher;
import main.common.user.Undergraduate;
import main.common.user.User;

import java.util.List;

public interface BookBusinessService {

    BookManagementResultMessage add(Book b);

    BookManagementResultMessage remove(Book b);

    /**
     * Book 类未提供 isbn，title，author 等属性的接口，
     * 需要创建新的 Book 对象
     *
     * @see Book
     */
    BookManagementResultMessage editBookInfo(Book b);

    Book findByISBN(String isbn);
    List<Book> findByTitle(String title);
    List<Book> findByAuthor(String author);

    CheckOutResultMessage checkOut(Teacher u, Book b);
    CheckOutResultMessage checkOut(Graduate u, Book b);
    CheckOutResultMessage checkOut(Undergraduate u, Book b);
    void checkIn(User u, Book b);

}
