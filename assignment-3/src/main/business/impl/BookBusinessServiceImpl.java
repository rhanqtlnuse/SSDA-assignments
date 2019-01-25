package main.business.impl;

import main.business.service.BookBusinessService;
import main.common.Book;
import main.common.message.BookManagementResultMessage;
import main.data.impl.book.BookDataServiceImpl;
import main.data.service.book.BookDataService;

import java.util.List;

/**
 * TODO
 *
 * 书籍管理业务逻辑实现类
 *
 * @author HanQi
 * @version 1.0
 * @since 2019/1/25
 */
public class BookBusinessServiceImpl implements BookBusinessService {

    private static BookBusinessServiceImpl singleton = new BookBusinessServiceImpl();

    public static BookBusinessService getInstance() {
        return singleton;
    }

    private BookBusinessServiceImpl() { }

    private BookDataService bookDataService = BookDataServiceImpl.getInstance();

    @Override
    public BookManagementResultMessage add(Book b) {
        if (b == null) {
            return BookManagementResultMessage.NULL_PARAM;
        }
        if (b.getIsbn() == null || "".equals(b.getIsbn())) {
            return BookManagementResultMessage.INVALID_ISBN;
        }
        if (b.getTitle() == null || "".equals(b.getTitle())) {
            return BookManagementResultMessage.INVALID_TITLE;
        }
        if (b.getAuthor() == null || "".equals(b.getAuthor())) {
            return BookManagementResultMessage.INVALID_AUTHOR;
        }
        if (b.getCategories() == null) {
            return BookManagementResultMessage.INVALID_CATEGORY;
        }
        bookDataService.add(b);
        return BookManagementResultMessage.SUCCEEDED;
    }

    @Override
    public BookManagementResultMessage remove(Book b) {
        return BookManagementResultMessage.SUCCEEDED;
    }

    @Override
    public BookManagementResultMessage editBookInfo(Book b) {
        return BookManagementResultMessage.SUCCEEDED;
    }

    @Override
    public Book findByISBN(String isbn) {
        return bookDataService.findByISBN(isbn);
    }

    @Override
    public List<Book> findByTitle(String title) {
        return bookDataService.findByTitle(title);
    }

    @Override
    public List<Book> findByAuthor(String author) {
        return bookDataService.findByAuthor(author);
    }

}
