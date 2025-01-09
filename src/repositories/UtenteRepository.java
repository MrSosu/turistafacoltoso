package repositories;

import configuration.DBConnection;
import dto.UtenteRequest;
import entities.Utente;

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

    public static void insertUtente(UtenteRequest request) throws SQLException {
        String query = "INSERT INTO utente (nome,cognome,email,indirizzo)" +
                "VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,request.nome());
        statement.setString(2,request.cognome());
        statement.setString(3,request.email());
        statement.setString(4,request.indirizzo());
        statement.executeUpdate();
    }

    public static void updateUtente(Integer id, UtenteRequest request) throws SQLException {
        String query = "UPDATE utente SET nome = ?, cognome = ?, email = ?, indirizzo = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,request.nome());
        statement.setString(2,request.cognome());
        statement.setString(3,request.email());
        statement.setString(4,request.indirizzo());
        statement.setInt(5,id);
        statement.executeUpdate();
    }

    public static void deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM utente WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,id);
        statement.executeUpdate();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        //getAll().forEach(System.out::println);
        // insertUtente(new UtenteRequest("Paulo", "Dybala", "paulino@example.com", "via DAAROMA 1"));
        updateUtente(13, new UtenteRequest("Paulinho", "Dybala", "paulinho@example.com", "via DAAROMA 1"));
    }

}
