package src.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import src.db.DB;
import src.db.DbException;
import src.model.dao.BookDao;
import src.model.entities.Author;
import src.model.entities.Book;

public class BookDaoJDBC implements BookDao {

    private Connection conn;

    public BookDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void deleteById(int id) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "DELETE FROM book " +
                            "WHERE Id = ?");
            st.setInt(1, id);
            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public List<Book> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT book.*,author.Name as AuthorName " +
                            "FROM book INNER JOIN author " +
                            "ON book.AuthorId = author.Id " +
                            "ORDER BY book.Id");

            rs = st.executeQuery();
            List<Book> list = new ArrayList<>();
            Map<Integer, Author> map = new HashMap<>();

            while (rs.next()) {
                Author au = map.get(rs.getInt("AuthorID"));

                if (au == null) {
                    au = instanciateAuthor(rs);
                    map.put(rs.getInt("AuthorID"), au);
                }

                Book book = instanciateBook(rs, au);
                list.add(book);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT book.*,author.Name as AuthorName " +
                            "FROM book INNER JOIN author " +
                            "ON book.AuthorId = author.Id " +
                            "WHERE book.AuthorId = ? " +
                            "ORDER BY book.Id");

            st.setInt(1, author.getId());
            rs = st.executeQuery();

            List<Book> list = new ArrayList<>();
            Map<Integer, Author> map = new HashMap<>();
            while (rs.next()) {
                Author au = map.get(rs.getInt("AuthorId"));

                if (au == null) {
                    au = instanciateAuthor(rs);
                    map.put(rs.getInt("AuthorId"), au);
                }

                Book book = instanciateBook(rs, au);
                list.add(book);
            }
            return list;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public Book findById(int id) {
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(
                    "SELECT book.*, author.Name AS AuthorName " +
                            "FROM book INNER JOIN author " +
                            "ON book.AuthorId = author.Id " +
                            "WHERE book.id = ? " +
                            "ORDER BY Id"
                         );

            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                Author au = instanciateAuthor(rs);
                return instanciateBook(rs, au);
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void insert(Book book) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "INSERT INTO BOOK " +
                            "(Id, Title, PublishedYear, Price, AuthorId) " +
                            "VALUES " +
                            "(?, ?, ?, ?, ?)");

            if (book.getId() != null) {
                st.setInt(1, book.getId());
            } else {
                st.setInt(1, java.sql.Types.INTEGER);
            }

            st.setString(2, book.getTitle());

            if (book.getPublishedYear() != null) {
                st.setInt(3, book.getPublishedYear());
            } else {
                st.setInt(3, java.sql.Types.INTEGER);
            }

            st.setDouble(4, book.getPrice());

            st.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    @Override
    public void update(Book book) {
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(
                    "UPDATE book" +
                            "SET Title = ?, PublishedYear = ?, Price = ?, AuthorId = ?" +
                            "WHERE Id = ?");

            st.setString(1, book.getTitle());
            if (book.getPublishedYear() != null) {
                st.setInt(2, book.getPublishedYear());
            } else {
                st.setInt(2, java.sql.Types.INTEGER);
            }
            st.setDouble(3, book.getPrice());
            if (book.getAuthor().getId() != null) {
                st.setInt(4, book.getAuthor().getId());
            } else {
                st.setInt(4, java.sql.Types.INTEGER);
            }

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    private Book instanciateBook(ResultSet rs, Author au) throws SQLException {
        Book obj = new Book();
        obj.setId(rs.getInt("Id"));
        obj.setTitle(rs.getString("Title"));
        obj.setPublishedYear(rs.getInt("PublishedYear"));
        obj.setPrice(rs.getDouble("Price"));
        obj.setAuthor(au);
        return obj;
    }

    private Author instanciateAuthor(ResultSet rs) throws SQLException {
        Author au = new Author();
        au.setId(rs.getInt("AuthorId"));
        au.setName(rs.getString("AuthorName"));
        return au;
    }

}
