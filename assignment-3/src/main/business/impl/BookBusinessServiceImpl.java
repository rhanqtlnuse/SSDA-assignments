package main.business.impl;

import main.business.service.BookBusinessService;
import main.common.book.Book;
import main.common.book.CheckInRecord;
import main.common.resultmessage.BookManagementResultMessage;
import main.common.resultmessage.CheckOutResultMessage;
import main.common.user.User;
import main.common.user.visitor.CheckOutVisitor;
import main.data.impl.book.BookDataServiceImpl;
import main.data.impl.user.UserDataServiceImpl;
import main.data.service.book.BookDataService;
import main.data.service.user.UserDataService;

import java.util.Date;
import java.util.List;

/**
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
    private UserDataService userDataService = UserDataServiceImpl.getInstance();

    private static final double OVERDUE_PENALTY = 0.5;

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
        bookDataService.remove(b);
        return BookManagementResultMessage.SUCCEEDED;
    }

    @Override
    public BookManagementResultMessage editBookInfo(Book b) {
        bookDataService.update(b);
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

    @Override
    public List<Book> findAll() {
        return bookDataService.findAll();
    }

    @Override
    public CheckOutResultMessage checkOut(User u, Book b) {
        return (CheckOutResultMessage) u.accept(new CheckOutVisitor(b));
    }

    /**
     * TODO: 超期惩罚
     */
    @Override
    public void checkIn(User u, Book b) {
        CheckInRecord record = new CheckInRecord(u, b, new Date());
        u.getCheckInRecords().add(record);
        u.setBorrowedCount(u.getBorrowedCount() - 1);
        b.setOutCount(b.getOutCount() - 1);
        b.setRemainingCount(b.getRemainingCount() + 1);
        userDataService.update(u);
        bookDataService.update(b);
    }

}
