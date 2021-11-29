package com.luoyvdi.test;

import com.luoyvdi.domain.Book;
import com.luoyvdi.domain.Page;
import com.luoyvdi.service.BookService;
import com.luoyvdi.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class BookServiceTest {

    BookService bookService = new BookServiceImpl();

    @Test
    public void addBook() {
        bookService.addBook(new Book(null, "桃花源记", "陶渊明", new BigDecimal(10), 10, 11, null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(22, "taohuanyuanji", "taoyuanming", new BigDecimal(11), 10, 11, null));
    }

    @Test
    public void queryBookById() {
        Book book = bookService.queryBookById(22);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookService.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void page() {
        Page<Book> page = bookService.page(1, Page.PAGE_SIZE);
        System.out.println(page);
    }

    @Test
    public void pageByPrice() {
        Page<Book> bookPage = bookService.pageByPrice(2, Page.PAGE_SIZE, 10, 200);
        System.out.println(bookPage);
    }
}