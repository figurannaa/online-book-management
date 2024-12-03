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
public class GetNABooksAspect {
    @Around("execution(* library.service.BookService.getNotAvailableBooks(..))")
    public Object logAroundGetNotAvailableBooks(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Metódus meghívása: {} - A nem elérhető könyvek lekérdezése elkezdődött.", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        if (result instanceof List && !((List<?>) result).isEmpty())
            log.info("Nem elérhető könyvek sikeresen lekérdezve. Az könyvek száma: {}.", ((List<?>) result).size());
        else log.info("Az nem elérhető könyvek listája üres.");
        return result;
    }
}
