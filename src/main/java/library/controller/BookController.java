package library.controller;

import jakarta.validation.Valid;
import library.model.Book;
import library.service.BookService;
import library.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private MyBookService myBookService;

    @GetMapping("/")
    public String viewHomePage(Model model) {
        model.addAttribute("listBooks", bookService.getAllBooks());
        model.addAttribute("mostExpensive", bookService.getMostExpensiveBook());
        model.addAttribute("aBookCount", bookService.getAvailableBooksCount());
        model.addAttribute("naBookNum", bookService.getNABooksCount());
        model.addAttribute("myBookNum", myBookService.getNumberOfMyBooks());
        model.addAttribute("myBookIds", myBookService.getMyBookIds());
        return "index";
    }

    @GetMapping("/new-book")
    public String showNewBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "book_form";
    }

    @GetMapping("/update-book/{id}")
    public String showFormForUpdate(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book_form";
    }

    @PostMapping("/saveBook")
    public String saveBook(@Valid @ModelAttribute("book") Book book, RedirectAttributes redirectAttributes) {
        if (book.getId() == null) {
            bookService.createBook(book);
            redirectAttributes.addFlashAttribute("successMessage", "Könyv sikeresen hozzáadva a táblához!");
        } else {
            bookService.updateBook(book.getId(), book);
            redirectAttributes.addFlashAttribute("successMessage", "Könyv sikeresen módosítva!");
        }
        return "redirect:/";
    }

    @GetMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("successMessage", "Könyv sikeresen törölve a táblából!");
        return "redirect:/";
    }

    @GetMapping("/books-not-available")
    public String getBooksNotAvailable(Model model) {
        List<Book> books = bookService.getNotAvailableBooks();
        model.addAttribute("listNAbooks", books);
        return "books_not_available";
    }
}
