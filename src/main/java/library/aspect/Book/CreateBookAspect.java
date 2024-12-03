package library.aspect.Book;

import library.exception.BookNotFoundException;
import library.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class CreateBookAspect {
    @Before("execution(* library.service.BookService.createBook(..)) && args(book)")
    public void beforeSaveBook(JoinPoint joinPoint, Book book) {
        log.info("Metódus meghívása: {} - Létrehozandó könyv adatai: Cím: '{}', Szerző: '{}', Kiadási év: '{}', Ár (Ft): '{}', Elérhető?: '{}'",
                joinPoint.getSignature().getName(), book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice(), book.getAvailable());
    }

    @AfterReturning(pointcut = "execution(* library.service.BookService.createBook(..))", returning = "book")
    public void afterReturningSaveBook(Book book) {
        log.info("Könyv sikeresen mentve. A létrehozott könyv: ID={}, '{}, {} ({}) {}Ft, {}'", book.getId(), book.getTitle(), book.getAuthor(), book.getYear(), book.getPrice(), book.getAvailable());
    }

    @AfterThrowing(pointcut = "execution(* library.service.BookService.createBook(..))", throwing = "exception")
    public void afterThrowingSaveBook(BookNotFoundException exception) {
        log.error(exception.getMessage());
    }
}
