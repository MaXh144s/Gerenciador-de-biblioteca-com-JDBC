package src.db; 

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {

    private static Connection conn = getConnection();

    public static Connection getConnection() {
        try {
            if (conn == null) {
                Properties props = loadProperties();
                String url = props.getProperty("dburl");

                conn = DriverManager.getConnection(url, props);
            }
            return conn;
        } catch (SQLException e) {
            throw new DbException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }

    }

    public static void closeConnection() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new DbException("Erro ao desconectar do banco de dados: " + e.getMessage());
            }
        }
    }

    private static Properties loadProperties() {
        Properties props = new Properties();
        try (FileInputStream fs = new FileInputStream("db.properties")) {
            props.load(fs);
        } catch (IOException e) {
            throw new DbException("Erro ao carregar o arquivo db.properties: " + e.getMessage());
        }
        return props;
    }

    public static void closeStatement(Statement st){
        if (st != null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar o Statement: " + e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if (rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new DbException("Erro ao fechar o ResultSet: " + e.getMessage());
            }
        }
    }

}
