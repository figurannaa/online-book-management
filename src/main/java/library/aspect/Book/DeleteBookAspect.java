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
public class DeleteBookAspect {
    @Autowired
    private BookService bookService;

    @Before("execution(* library.service.BookService.deleteBook(..)) && args(id)")
    public void beforeDeleteBook(JoinPoint joinPoint, Long id) {
        Book book = bookService.getBookById(id);
        log.info("Metódus meghívása: {} - Az ID={} könyv ('{} {}, {}, {}Ft, {}') törlése elkezdődött.",
                joinPoint.getSignature().getName(), id, book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice(), book.getAvailable());
    }

    @AfterReturning(pointcut = "execution(* library.service.BookService.deleteBook(..)) && args(itemId)", argNames = "itemId")
    public void afterReturningDeleteBook(Long itemId) {
        log.info("Az ID={} könyv eltávolítva.", itemId);
    }

    @AfterThrowing(pointcut = "execution(* library.service.BookService.deleteBook(..))", throwing = "exception")
    public void afterThrowingDeleteBook(BookNotFoundException exception) {
        log.error(exception.getMessage());
    }
}
