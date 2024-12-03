package library.aspect.MyBook;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class GetAllMyBooksAspect {
    @Around("execution(* library.service.MyBookService.getAllMyBooks(..))")
    public Object logAroundGetAllMyBooks(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Metódus meghívása: {} - Az összes saját könyv lekérdezése elkezdődött.", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        if (result instanceof List && !((List<?>) result).isEmpty())
            log.info("Saját könyvek sikeresen lekérdezve. A saját könyvek száma: {}.", ((List<?>) result).size());
        else log.info("Saját könyvlista üres.");
        return result;
    }
}
