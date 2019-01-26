package main.common.user.visitor;

import main.common.book.Book;
import main.common.book.CheckOutRecord;
import main.common.book.Level;
import main.common.resultmessage.CheckOutResultMessage;
import main.common.resultmessage.ResultMessage;
import main.common.user.Graduate;
import main.common.user.Teacher;
import main.common.user.Undergraduate;
import main.common.user.User;
import main.data.impl.book.BookDataServiceImpl;
import main.data.impl.user.UserDataServiceImpl;
import main.data.service.book.BookDataService;
import main.data.service.user.UserDataService;
import main.util.DateTranslator;

import java.util.Date;

public class CheckOutVisitor extends Visitor {

    private final UserDataService userDataService;
    private final BookDataService bookDataService;

    private Book book;

    public CheckOutVisitor(Book book) {
        this.userDataService = UserDataServiceImpl.getInstance();
        this.bookDataService = BookDataServiceImpl.getInstance();
        this.book = book;
    }

    @Override
    public ResultMessage visit(Teacher u) {
        if (!hasEnoughBooks(book)) {
            return CheckOutResultMessage.NO_BOOK;
        } else {
            if (!canBorrowMore(u)) {
                return CheckOutResultMessage.CANNOT_BORROW_MORE;
            } else {
                return checkOut(u, book);
            }
        }
    }

    @Override
    public ResultMessage visit(Graduate u) {
        if (!hasEnoughBooks(book)) {
            return CheckOutResultMessage.NO_BOOK;
        } else {
            if (!canBorrowMore(u)) {
                return CheckOutResultMessage.CANNOT_BORROW_MORE;
            } else {
                return checkOut(u, book);
            }
        }
    }

    @Override
    public ResultMessage visit(Undergraduate u) {
        if (!hasEnoughBooks(book)) {
            return CheckOutResultMessage.NO_BOOK;
        } else {
            if (!canBorrowMore(u)) {
                return CheckOutResultMessage.CANNOT_BORROW_MORE;
            } else {
                if (book.getLevel() == Level.RARE) {
                    return CheckOutResultMessage.COND_NOT_SATISFIED;
                } else {
                    return checkOut(u, book);
                }
            }
        }
    }

    private ResultMessage checkOut(User u, Book b) {
        Date now = new Date();
        CheckOutRecord record = new CheckOutRecord(u, b, now, DateTranslator.after(now, u.getPeriodLimitation()));
        u.getCheckOutRecords().add(record);
        u.setBorrowedCount(u.getBorrowedCount() + 1);
        b.setOutCount(b.getOutCount() + 1);
        b.setRemainingCount(b.getRemainingCount() - 1);
        userDataService.update(u);
        bookDataService.update(b);
        return CheckOutResultMessage.SUCCEEDED;
    }

    private boolean hasEnoughBooks(Book b) {
        return b.getRemainingCount() > 0;
    }

    private boolean canBorrowMore(User u) {
        return u.getBorrowedCount() < u.getCountLimitation();
    }

}
