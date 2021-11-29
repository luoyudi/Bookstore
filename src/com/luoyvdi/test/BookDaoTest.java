package com.luoyvdi.test;

import com.luoyvdi.dao.BookDao;
import com.luoyvdi.dao.impl.BookDaoImpl;
import com.luoyvdi.domain.Book;
import com.luoyvdi.domain.Page;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookDaoTest {

    BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {
        bookDao.addBook(new Book(null, "测试", "测试", new BigDecimal(11.11), 10, 999, null));
    }

    @Test
    public void deleteBookById() {
        bookDao.deleteBookById(21);
    }

    @Test
    public void updateBook() {
        bookDao.updateBook(new Book(21, "测试123", "123", new BigDecimal(99999), 10, 1, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookDao.queryBookById(10);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCount() {
        System.out.println(bookDao.queryForPageTotalCount());
    }

    @Test
    public void queryForPageItems() {
        List<Book> books = bookDao.queryForPageItems(0, Page.PAGE_SIZE);
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForPageTotalCountByPrice() {
        Integer integer = bookDao.queryForPageTotalCountByPrice(0, Integer.MAX_VALUE);
        System.out.println(integer);
    }

    @Test
    public void queryForPageItemsByPrice() {
        List<Book> books = bookDao.queryForPageItemsByPrice(8, Page.PAGE_SIZE, 10, 200);
        for (Book book : books) {
            System.out.println(book);
        }
    }
}