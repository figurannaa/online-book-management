package library.aspect.Book;

import library.exception.BookNotFoundException;
import library.model.Book;
import library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class UpdateBookAspect {
    @Autowired
    private BookService bookService;

    @Before(value = "execution(* library.service.BookService.updateBook(..)) && args(id, updatedBook)", argNames = "joinPoint,id,updatedBook")
    public void beforeUpdateBook(JoinPoint joinPoint, Long id, Book updatedBook) {
        Book book = bookService.getBookById(id);
        String message = "Metódus meghívása: " + joinPoint.getSignature().getName() + "\n" +
                " * Az ID=" + id + " könyv módosítása megkezdődött.\n" +
                " * Módosítás előtti adatok -> Cím: " + book.getTitle() + ", Szerző: " + book.getAuthor() + ", Kiadás éve: " + book.getYear() + ", Ár (Ft): " + book.getPrice() + ", Elérhető?: " + book.getAvailable() + "\n" +
                " * Új adatok betöltése -> Cím: " + updatedBook.getTitle() + ", Szerző: " + updatedBook.getAuthor() + ", Kiadás éve: " + updatedBook.getYear() + ", Ár (Ft): " + updatedBook.getPrice() + ", Elérhető?: " + updatedBook.getAvailable();
        log.info(message);
    }

    @AfterReturning(pointcut = "execution(* library.service.BookService.updateBook(..))", returning = "book")
    public void afterReturningUpdateBook(Book book) {
        log.info("Az ID={} könyv sikeresen módosítva. A módosított könyv: '{}, {} ({}) {}Ft, {}'", book.getId(), book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice(), book.getAvailable());
    }

    @AfterThrowing(pointcut = "execution(* library.service.BookService.updateBook(..))", throwing = "exception")
    public void afterThrowingUpdateBook(BookNotFoundException exception) {
        log.error(exception.getMessage());
    }
}
