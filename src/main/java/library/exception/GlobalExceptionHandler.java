package library.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j(topic = "fileLogger")
public class GlobalExceptionHandler {
    @ExceptionHandler(BookNotFoundException.class)
    public String handleBookNotFoundException(BookNotFoundException e, Model model, HttpServletResponse response) {
        model.addAttribute("errorMessage", e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("statusCode", response.getStatus());
        model.addAttribute("statusReason", HttpStatus.NOT_FOUND.getReasonPhrase());
        log.error(e.getMessage());
        return "error_page";
    }

    @ExceptionHandler(MyBookNotFoundException.class)
    public String handleMyBookNotFoundException(MyBookNotFoundException e, Model model, HttpServletResponse response) {
        model.addAttribute("errorMessage", e.getMessage());
        response.setStatus(HttpStatus.NOT_FOUND.value());
        model.addAttribute("statusCode", response.getStatus());
        model.addAttribute("statusReason", HttpStatus.NOT_FOUND.getReasonPhrase());
        log.error(e.getMessage());
        return "error_page";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handleMethodArgumentNotValidException(MethodArgumentNotValidException e, Model model, HttpServletResponse response) {
        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        StringBuilder errorMessage = new StringBuilder("A következő mezők érvényesítése nem sikerült:\n");
        fieldErrors.forEach(fieldError -> errorMessage.append("* ").append(fieldError.getField()).append(" (").append(fieldError.getDefaultMessage()).append(")\n"));
        errorMessage.delete(errorMessage.length() - 1, errorMessage.length());

        response.setStatus(HttpStatus.BAD_REQUEST.value());
        model.addAttribute("errorMessage", errorMessage.toString().replace("\n", "<br>"));
        model.addAttribute("statusCode", response.getStatus());
        model.addAttribute("statusReason", HttpStatus.BAD_REQUEST.getReasonPhrase());
        log.error(errorMessage.toString());
        return "error_page";

    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public String handleDataIntegrityViolationException(DataIntegrityViolationException e, Model model, HttpServletResponse response) {
        model.addAttribute("errorMessage", e.getMessage());
        response.setStatus(HttpStatus.CONFLICT.value());
        model.addAttribute("statusCode", response.getStatus());
        model.addAttribute("statusReason", HttpStatus.CONFLICT.getReasonPhrase());
        log.error(e.getMessage());
        return "error_page";
    }
}
