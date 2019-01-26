package main.business.impl;

import main.business.impl.reader.format.Formatter;
import main.business.service.ReaderBusinessService;
import main.common.book.Book;

public class ReaderBusinessServiceImpl implements ReaderBusinessService {

    private static final ReaderBusinessService singleton = new ReaderBusinessServiceImpl();

    public static ReaderBusinessService getInstance() {
        return singleton;
    }

    private ReaderBusinessServiceImpl() { }

    @Override
    public String show(String isbn) {
        return null;
    }

    @Override
    public String show(Book b) {
        return null;
    }

    @Override
    public String show(String isbn, Formatter formatter) {
        return null;
    }

    @Override
    public String show(Book b, Formatter formatter) {
        return null;
    }

}
