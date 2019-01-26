package main.business.service;

import main.business.impl.reader.format.Formatter;
import main.common.book.Book;

public interface ReaderBusinessService {

    String show(String isbn);
    String show(Book b);
    String showIn(String isbn, Formatter formatter);
    String showIn(Book b, Formatter formatter);

}
