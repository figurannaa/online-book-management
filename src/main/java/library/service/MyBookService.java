package library.service;

import library.model.MyBook;

import java.util.List;

public interface MyBookService {
    List<MyBook> getAllMyBooks();

    MyBook getMyBookById(Long id);

    void addMyBook(Long bookId);

    Long getNumberOfMyBooks();

    List<Long> getMyBookIds();

    void deleteMyBook(Long id);
}
