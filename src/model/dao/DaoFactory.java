package src.model.dao;

import src.db.DB;
import src.model.dao.impl.BookDaoJDBC;

public class DaoFactory {

    public static BookDao createBookDao(){
        return new BookDaoJDBC(DB.getConnection());
    }

    public static BookDao createAuthorDao(){
        return new BookDaoJDBC(DB.getConnection());
    }

}
