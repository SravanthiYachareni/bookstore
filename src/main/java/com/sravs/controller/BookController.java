package com.sravs.controller;

import com.sravs.entity.Book;
import com.sravs.entity.MyBookList;
import com.sravs.service.BookService;
import com.sravs.service.MyBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    MyBookService myBookService;
    @Autowired
    BookService bookService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    @GetMapping("/available_books")
    public ModelAndView getAllBook() {
        List<Book> list = bookService.getAllBook();
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("bookList");
//        modelAndView.addObject("book",list);
//        return modelAndView;
        return new ModelAndView("bookList", "book", list);
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/available_books";
    }

    @GetMapping("/my_books")
    public String getMyBooks(Model model) {
        List<MyBookList> list= myBookService.getAllBooks();
        model.addAttribute("book",list);
        return "myBooks";
    }
@RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable ("id") int id) {
        Book book = bookService.getBookById(id);
    MyBookList myBookList = new MyBookList(book.getId(),book.getName(),book.getAuthor(),book.getPrice());
        myBookService.saveMyBooks(myBookList);
        return "redirect:/my_books";
    }
    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable ("id") int id ,Model model) {
       Book b=  bookService.getBookById(id);
         model.addAttribute("book",b);
        return "bookEdit";
    }
@RequestMapping("/deleteBook/{id}")
    public String deleteBooks(@PathVariable("id") int id){
        bookService.deleteById(id);
        return "redirect:/available_books";
}
}
