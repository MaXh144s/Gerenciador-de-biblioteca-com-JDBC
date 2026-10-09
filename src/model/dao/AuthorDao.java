package src.model.dao;

import java.util.List;

import src.model.entities.Author;

public interface AuthorDao {

    void insert(Author author);
    void update(Author author);
    void deleteById(int id);
    Author findById(int id);
    List<Author> findAll();
    
} 
