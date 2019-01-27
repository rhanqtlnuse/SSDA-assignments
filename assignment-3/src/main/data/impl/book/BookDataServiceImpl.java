package main.data.impl.book;

import main.common.book.Book;
import main.common.book.Level;
import main.data.service.book.BookDataService;

import java.util.*;

public class BookDataServiceImpl implements BookDataService {

    private static final Set<Book> BOOKS = new HashSet<>();

    static {
        BOOKS.add(new Book("9787536692930", "三体",
                "刘慈欣", Arrays.asList("小说", "科幻"),
                "", Level.ORDINARY, 10));
        BOOKS.add(new Book("9787111075752", "设计模式",
                "Erich Gamma", Arrays.asList("计算机与互联网", "软件工程及软件方法学", "软件工程模型"),
                "", Level.ORDINARY, 20));
        BOOKS.add(new Book("9787115369093", "重构：改善既有代码的设计",
                "Martin Fowler", Arrays.asList("计算机与互联网", "软件工程及软件方法学", "软件方法"),
                "", Level.ORDINARY, 10));
        BOOKS.add(new Book("9787115357618", "编程珠玑",
                "Jon Bentley", Arrays.asList("计算机与互联网", "算法与数据结构"),
                "", Level.ORDINARY, 10));
        BOOKS.add(new Book("9787508660752", "人类简史：从动物到上帝",
                "Yuval Noah Harari", Arrays.asList("人文社科", "历史", "普及读物"),
                "", Level.ORDINARY, 5));
        BOOKS.add(new Book("9787533948108", "浮生六记",
                "沈复", Arrays.asList("文学", "散文随笔", "小品文"),
                "", Level.RARE, 1));
        BOOKS.add(new Book("9787121250583", "自然语言处理综论（第二版）",
                "Daniel Jurafsky", Arrays.asList("计算机与互联网", "自然语言处理"),
                "", Level.ORDINARY, 3));

    }

    private static BookDataServiceImpl singleton = new BookDataServiceImpl();

    public static BookDataServiceImpl getInstance() {
        return singleton;
    }

    private BookDataServiceImpl() { }

    @Override
    public void add(Book b) {
        BOOKS.add(b);
    }

    @Override
    public void remove(Book b) {
        BOOKS.remove(b);
    }

    @Override
    public void update(Book b) {
        BOOKS.remove(b);
        BOOKS.add(b);
    }

    @Override
    public Book findByISBN(String isbn) {
        for (Book b : BOOKS) {
            if (isbn.equals(b.getIsbn())) {
                return b;
            }
        }
        return null;
    }

    @Override
    public List<Book> findByTitle(String title) {
        List<Book> res = new ArrayList<>();
        for (Book b : BOOKS) {
            if (title.equals(b.getTitle())) {
                res.add(b);
            }
        }
        return res;
    }

    @Override
    public List<Book> findByAuthor(String author) {
        List<Book> res = new ArrayList<>();
        for (Book b : BOOKS) {
            if (author.equals(b.getAuthor())) {
                res.add(b);
            }
        }
        return res;
    }

    @Override
    public List<Book> findAll() {
        return new ArrayList<>(BOOKS);
    }

}
