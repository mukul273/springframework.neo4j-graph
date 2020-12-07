package com.springframework.neo4jgraph.controller;

import com.springframework.neo4jgraph.exception.ResourceNotFoundException;
import com.springframework.neo4jgraph.node.Book;
import com.springframework.neo4jgraph.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        bookRepository.save(book);
        return "Book Added";
    }

    @GetMapping("/getallbooks")
    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping("/getbookById/{id}")
    public ResponseEntity<Book> getBook(@PathVariable String id) {
        Book book = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book " + id + " not Found"));
        return ResponseEntity.ok(book);
    }

    @GetMapping("/getbook/{name}")
    public ResponseEntity<List<Book>> getBookByName(@PathVariable String name) {
        List<Book> byName = bookRepository.findByName(name);
        return ResponseEntity.ok(byName);
    }

    @DeleteMapping("/deletebook/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable String id) {

        Book deleteBook = bookRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book " + id + " not Found")
        );
        System.out.println("deleteBook:"+deleteBook);
        bookRepository.delete(deleteBook);

        Map<String, Boolean> deletedBook = new HashMap<>();
        deletedBook.put("Deleted", true);

        return ResponseEntity.ok(deletedBook)   ;
    }
}
