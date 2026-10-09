package src.application;

import src.model.dao.BookDao;
import src.model.dao.DaoFactory;
import src.model.entities.Book;


public class Program {
    public static void main(String[] args) {
        
        BookDao bkDao = DaoFactory.createAuthorDao();

        Book bk = bkDao.findById(2);
        System.out.println(bk);
        
    }


}
