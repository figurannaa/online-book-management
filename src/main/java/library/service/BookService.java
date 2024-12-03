package library.service;

import library.model.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();

    Book getBookById(Long id);

    Book createBook(Book book);

    Book updateBook(Long id, Book updatedBook);

    List<Book> getNotAvailableBooks();

    Book getMostExpensiveBook();

    Integer getAvailableBooksCount();

    Integer getNABooksCount();

    void deleteBook(Long id);
}
