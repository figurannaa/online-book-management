package library.aspect.MyBook;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class GetNumberOfMyBooksAspect {
    @Around("execution(* library.service.MyBookService.getNumberOfMyBooks(..))")
    public Object logAroundGetNumberOfMyBooks(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Metódus meghívása: {} - A saját lista hosszának lekérdezése elkezdődött.", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        if (result instanceof Long myBookNum) {
            log.info("Saját könyvek hossza sikeresen lekérdezve: {}", myBookNum);
        }
        return result;
    }
}
