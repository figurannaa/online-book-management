package library.aspect.MyBook;

import library.exception.MyBookNotFoundException;
import library.model.MyBook;
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
public class AddMyBookAspect {
    @Before("execution(* library.service.MyBookService.addMyBook(..)) && args(myBook)")
    public void beforeSaveMyBook(JoinPoint joinPoint, MyBook myBook) {
        log.info("Metódus meghívása: {} - Könyv hozzáadása saját könyv listánkhoz: Könyv ID-ja: '{}'", joinPoint.getSignature().getName(), myBook.getId());
    }

    @AfterReturning(pointcut = "execution(* library.service.MyBookService.addMyBook(..))", returning = "myBook")
    public void afterReturningSaveMyBook(MyBook myBook) {
        log.info("Könyv sikeresen hozzáadva a saját listánkhoz. A hozzáadott könyv: ID={}, Könyv ID={}", myBook.getId(), myBook.getBook().getId());
    }

    @AfterThrowing(pointcut = "execution(* library.service.MyBookService.addMyBook(..))", throwing = "exception")
    public void afterThrowingSaveMyBook(MyBookNotFoundException exception) {
        log.error(exception.getMessage());
    }
}
