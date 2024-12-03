package library.aspect.Book;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j(topic = "fileLogger")
public class GetAvailableBooksCountAspect {
    @Around("execution(* library.service.BookService.getAvailableBooksCount(..))")
    public Object logAroundGetAvailableBooksCount(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Metódus meghívása: {} - Az összes könyv lekérdezése elkezdődött.", joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();
        if (result instanceof Integer aNum) {
            log.info("Elérhető könyvek száma sikeresen lekérdezve: {}", aNum);
        }
        return result;
    }
}
