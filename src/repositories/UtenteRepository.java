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
        if (resultSet.next()) {
            return mapResultSetToUtente(resultSet);
        }
        else throw new IllegalArgumentException("Utente con id " + id + " non presente");
    }

    public static List<Utente> getAll() throws SQLException {
        String query = "SELECT * FROM utente";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Utente> utenti = new ArrayList<>();
        while (resultSet.next()) {
            utenti.add(mapResultSetToUtente(resultSet));
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
        String query = "UPDATE utente SET nome = ?, cognome = ?, email = ?, indirizzo = ?, codiceHost = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,request.nome());
        statement.setString(2,request.cognome());
        statement.setString(3,request.email());
        statement.setString(4,request.indirizzo());
        statement.setString(5,request.codiceHost());
        statement.setInt(6,id);
        statement.executeUpdate();
    }

    public static void deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM utente WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,id);
        statement.executeUpdate();
    }

    private static Utente mapResultSetToUtente(ResultSet resultSet) throws SQLException {
        Utente utente = new Utente();
        utente.setId(resultSet.getInt("id"));
        utente.setNome(resultSet.getString("nome"));
        utente.setCognome(resultSet.getString("cognome"));
        utente.setEmail(resultSet.getString("email"));
        utente.setIndirizzo(resultSet.getString("indirizzo"));
        utente.setCodiceHost(resultSet.getString("codiceHost"));
        return utente;
    }


}
