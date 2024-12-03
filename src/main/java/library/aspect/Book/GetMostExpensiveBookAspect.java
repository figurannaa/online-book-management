package library.aspect.Book;

import library.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class GetMostExpensiveBookAspect {
    @Around("execution(* library.service.BookService.getMostExpensiveBook(..))")
    public Object logAroundGetMostExpensiveBook(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Metódus meghívása: {} - Az összes könyv lekérdezése elkezdődött.", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        if (result instanceof Book book) {
            log.info("Legdrágább könyv sikeresen lekérdezve: {}", book.getTitle());
        }
        else log.info("Nem létezik legdrágább könyv.");
        return result;
    }
}
