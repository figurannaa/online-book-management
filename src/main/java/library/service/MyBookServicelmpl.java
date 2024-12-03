package library.service;

import library.exception.MyBookNotFoundException;
import library.model.Book;
import library.model.MyBook;
import library.repository.MyBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyBookServicelmpl implements MyBookService {
    @Autowired
    private MyBookRepository myBookRepository;
    @Autowired
    private BookService bookService;

    @Override
    public List<MyBook> getAllMyBooks() {
        return myBookRepository.findAll();
    }

    @Override
    public MyBook getMyBookById(Long id) {
        return findMyBookById(id, "Lekérdezési hiba.");
    }

    @Override
    public void addMyBook(Long bookId) {
        if(myBookRepository.existsByBookId(bookId)) {
            throw new DataIntegrityViolationException("Ez a könyv már szerepel a listában.");
        }

        Book book = bookService.getBookById(bookId);

        MyBook myBook = new MyBook();
        myBook.setBook(book);

        myBookRepository.save(myBook);
    }

    @Override
    public Long getNumberOfMyBooks() {
        return myBookRepository.count();
    }

    @Override
    public List<Long> getMyBookIds() {
        return myBookRepository.findAllBookIds();
    }

    @Override
    public void deleteMyBook(Long id) {
        MyBook myBook = findMyBookById(id, "Törlési hiba");
        myBookRepository.delete(myBook);
    }

    private MyBook findMyBookById(Long id, String errorMessage) {
        return myBookRepository.findById(id).orElseThrow(() -> {
            String message = errorMessage + ". Az ID=" + id + " könyvem nem található.";
            return new MyBookNotFoundException(message);
        });
    }
}
