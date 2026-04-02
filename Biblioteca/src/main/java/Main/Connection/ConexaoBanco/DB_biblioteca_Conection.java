package Main.Connection.ConexaoBanco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// Classe para conexão com o banco de dados
public class DB_biblioteca_Conection {

    private static DB_biblioteca_Conection instance;


    private DB_biblioteca_Conection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca_DB", "root", "Amor@080398");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    // private final Connection connection;

    public static DB_biblioteca_Conection getInstance() {
        if (instance == null) {
            instance = new DB_biblioteca_Conection();
        }
        return instance;
    }

    public Connection connection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca_DB", "root", "Amor@080398");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }
}