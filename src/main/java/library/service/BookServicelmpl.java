package library.service;

import library.exception.BookNotFoundException;
import library.model.Book;
import library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServicelmpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return findBookById(id, "Lekérdezési hiba");
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book updatedBook) {
        Book book = findBookById(id, "Frissítési hiba");
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setYear(updatedBook.getYear());
        book.setPrice(updatedBook.getPrice());
        book.setAvailable(updatedBook.getAvailable());
        return bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        Book book = findBookById(id, "Törlési hiba");
        bookRepository.delete(book);
    }

    @Override
    public List<Book> getNotAvailableBooks() {
        return bookRepository.findBookByAvailableIsFalse();
    }

    @Override
    public Book getMostExpensiveBook() {
        return bookRepository.findTopByOrderByPriceDesc();
    }

    @Override
    public Integer getAvailableBooksCount() {
        return bookRepository.countBooksByAvailableIsTrue();
    }

    @Override
    public Integer getNABooksCount() {
        return bookRepository.countBooksByAvailableIsFalse();
    }

    private Book findBookById(Long id, String errorMessage) {
        return bookRepository.findById(id).orElseThrow(() -> {
            String message = errorMessage + ". Az ID=" + id + " könyv nem található.";
            return new BookNotFoundException(message);
        });
    }
}
