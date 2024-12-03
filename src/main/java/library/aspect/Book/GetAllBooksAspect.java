package library.aspect.Book;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class GetAllBooksAspect {
    @Around("execution(* library.service.BookService.getAllBooks(..))")
    public Object logAroundGetAllBooks(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Metódus meghívása: {} - Az összes könyv lekérdezése elkezdődött.", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        if (result instanceof List && !((List<?>) result).isEmpty())
            log.info("Könyvek sikeresen lekérdezve. Az könyvek száma: {}.", ((List<?>) result).size());
        else log.info("Az könyvlista üres.");
        return result;
    }
}
