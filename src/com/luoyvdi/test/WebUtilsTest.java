package com.luoyvdi.test;

import com.luoyvdi.domain.Book;
import com.luoyvdi.utils.WebUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class WebUtilsTest {

    @Test
    public void copyParamToBean() {
        Map<String, String> map = new HashMap<String, String>();
        Book book = new Book();
        map.put("name", "时间简史");
        Book book1 = WebUtils.copyParamToBean(book,map);
        System.out.println(book1);
    }
}