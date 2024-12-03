package library.aspect.MyBook;

import library.exception.BookNotFoundException;
import library.exception.MyBookNotFoundException;
import library.model.Book;
import library.model.MyBook;
import library.service.BookService;
import library.service.MyBookService;
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
public class DeleteMyBookAspect {
    @Autowired
    private MyBookService bookService;

    @Before("execution(* library.service.MyBookService.deleteMyBook(..)) && args(id)")
    public void beforeDeleteMyBook(JoinPoint joinPoint, Long id) {
        MyBook myBook = bookService.getMyBookById(id);
        log.info("Metódus meghívása: {} - Az ID={} saját könyvem törlése elkezdődött. ID={} könyv törölve lesz a listából.",
                joinPoint.getSignature().getName(), id, myBook.getBook().getId());
    }

    @AfterReturning(pointcut = "execution(* library.service.MyBookService.deleteMyBook(..)) && args(itemId)", argNames = "itemId")
    public void afterReturningDeleteMyBook(Long itemId) {
        log.info("Az ID={} könyv eltávolítva.", itemId);
    }

    @AfterThrowing(pointcut = "execution(* library.service.MyBookService.deleteMyBook(..))", throwing = "exception")
    public void afterThrowingDeleteMyBook(MyBookNotFoundException exception) {
        log.error(exception.getMessage());
    }
}
