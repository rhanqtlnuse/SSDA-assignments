package main.business.service;

import main.business.impl.reader.format.Formatter;
import main.common.book.Book;

public interface ReaderBusinessService {

    /**
     * 显示内容，使用默认的 PDF 格式
     *
     * @param isbn 出版物的 ISBN 号码
     * @return 格式化的内容，此处为 stub 数据
     */
    String show(String isbn);

    /**
     * 显示内容，使用默认的 PDF 格式
     *
     * @param b 出版物
     * @return 格式化的内容，此处为 stub 数据
     */
    String show(Book b);

    String show(String isbn, Formatter formatter);
    String show(Book b, Formatter formatter);

}
