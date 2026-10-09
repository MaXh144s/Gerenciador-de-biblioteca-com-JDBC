package src.model.dao;

import src.db.DB;
import src.model.dao.impl.BookDaoJDBC;

public class DaoFactory {

    public BookDao createBookDao(){
        return new BookDaoJDBC(DB.getConnection());
    }

    public BookDao createAuthorDao(){
        return new BookDaoJDBC(DB.getConnection());
    }

}
