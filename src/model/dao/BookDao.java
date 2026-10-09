package src.model.dao;

import java.util.List;

import src.model.entities.Author;
import src.model.entities.Book;

public interface BookDao {

    void insert(Book book);
    void update(Book book);
    void deleteById(int id);
    Book findById(int id);
    List<Book> findAll();
    List<Book> findByAuthor(Author author);
    
}
