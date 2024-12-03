package library.controller;

import library.model.MyBook;
import library.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MyBookController {
    @Autowired
    private MyBookService myBookService;

    @GetMapping("/my-books")
    public String viewMyBookPage(Model model) {
        model.addAttribute("listMyBooks", myBookService.getAllMyBooks());
        return "my_books";
    }

    @GetMapping("/new-my-book")
    public String addNewMyBook(Model model) {
        model.addAttribute("myBook", new MyBook());
        return "index";
    }

    @PostMapping("/saveMyBook/{bookId}")
    public String saveMyBook(@PathVariable("bookId") Long bookId, RedirectAttributes redirectAttributes) {
        myBookService.addMyBook(bookId);
        redirectAttributes.addFlashAttribute("successMessage", "Könyv sikeresen hozzáadva a saját könyvlistához!");
        return "redirect:/";
    }

    @GetMapping("/deleteMyBook/{id}")
    public String deleteBook(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        myBookService.deleteMyBook(id);
        redirectAttributes.addFlashAttribute("successMessage", "Könyv sikeresen szétkapcsolva az adott könyvel!");
        return "redirect:/";
    }
}
