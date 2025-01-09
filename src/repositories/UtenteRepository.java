package repositories;

import configuration.DBConnection;
import entities.Utente;
import validator.Validator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UtenteRepository {

    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Utente getById(Integer id) throws SQLException {
        String query = "SELECT * FROM utente WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Utente utente = new Utente();
        if (resultSet.next()) {
            utente.setId(resultSet.getInt("id"));
            utente.setNome(resultSet.getString("nome"));
            utente.setCognome(resultSet.getString("cognome"));
            utente.setEmail(resultSet.getString("email"));
            utente.setIndirizzo(resultSet.getString("indirizzo"));
        }
        else throw new IllegalArgumentException("Utente con id " + id + " non presente");
        return utente;
    }

    public static List<Utente> getAll() throws SQLException {
        String query = "SELECT * FROM utente";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Utente> utenti = new ArrayList<>();
        while (resultSet.next()) {
            Utente utente = new Utente();
            utente.setId(resultSet.getInt("id"));
            utente.setNome(resultSet.getString("nome"));
            utente.setCognome(resultSet.getString("cognome"));
            utente.setEmail(resultSet.getString("email"));
            utente.setIndirizzo(resultSet.getString("indirizzo"));
            utenti.add(utente);
        }
        return utenti;
    }

    public static void insertUtente(String nome,
                                    String cognome,
                                    String email,
                                    String indirizzo) throws SQLException {
        String query = "INSERT INTO utente (nome,cognome,email,indirizzo)" +
                "VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,Validator.requireNotBlank(nome));
        statement.setString(2,Validator.requireNotBlank(cognome));
        statement.setString(3,Validator.validRegex(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"));
        statement.setString(4,Validator.requireNotBlank(indirizzo));
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        getAll().forEach(System.out::println);
        insertUtente("Paulo", "Dybala", "paulino@example.com", "via DAAROMA 1");
    }

}
