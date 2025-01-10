package repositories;

import configuration.DBConnection;
import dto.AbitazioneRequest;
import entities.Abitazione;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AbitazioneRepository {

    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Abitazione getById(Integer id) throws SQLException {
        String query = "SELECT * FROM abitazione WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        Abitazione abitazione = new Abitazione();
        if (resultSet.next()) {
            abitazione.setId(resultSet.getInt("id"));
            abitazione.setNome(resultSet.getString("nome"));
            abitazione.setIndirizzo(resultSet.getString("indirizzo"));
            abitazione.setPiano(resultSet.getInt("piano"));
            abitazione.setnPostiLetto(resultSet.getInt("n_posti_letto"));
            abitazione.setnLocali(resultSet.getInt("n_locali"));
            abitazione.setPrezzo(resultSet.getDouble("prezzo"));
            abitazione.setDataFine(resultSet.getDate("data_fine"));
            abitazione.setDataInizio(resultSet.getDate("data_inizio"));
            abitazione.setHost(resultSet.getInt("id_host"));
        }
        else throw new IllegalArgumentException("Abitazione con id " + id + " non presente");
        return abitazione;
    }

    public static List<Abitazione> getAll() throws SQLException {
        String query = "SELECT * FROM abitazione";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Abitazione> abitazioni = new ArrayList<>();
        while (resultSet.next()) {
            Abitazione abitazione = new Abitazione();
            abitazione.setId(resultSet.getInt("id"));
            abitazione.setNome(resultSet.getString("nome"));
            abitazione.setIndirizzo(resultSet.getString("indirizzo"));
            abitazione.setPiano(resultSet.getInt("piano"));
            abitazione.setnPostiLetto(resultSet.getInt("n_posti_letto"));
            abitazione.setnLocali(resultSet.getInt("n_locali"));
            abitazione.setPrezzo(resultSet.getDouble("prezzo"));
            abitazione.setDataFine(resultSet.getDate("data_fine"));
            abitazione.setDataInizio(resultSet.getDate("data_inizio"));
            abitazione.setHost(resultSet.getInt("id_host"));
            abitazioni.add(abitazione);
        }
        return abitazioni;
    }

    public static void insertAbitazione(AbitazioneRequest request) throws SQLException {
        String query = "INSERT INTO abitazione (nome, indirizzo, nLocali, nPostiLetto, piano, prezzo, dataInizio, dataFine, host) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, request.nome());
        statement.setString(2, request.indirizzo());
        statement.setInt(3, request.nLocali());
        statement.setInt(4, request.nPostiLetto());
        statement.setInt(5, request.piano());
        statement.setDouble(6, request.prezzo());
        statement.setDate(7, java.sql.Date.valueOf(String.valueOf(request.dataInizio())));
        statement.setDate(8, java.sql.Date.valueOf(String.valueOf(request.dataFine())));
        statement.setInt(9, request.host());
        statement.executeUpdate();
    }

    public static void updateAbitazione(Integer id, AbitazioneRequest request) throws SQLException {
        String query = "UPDATE abitazione SET nome = ?, indirizzo = ?, nLocali = ?, nPostiLetto = ?, piano = ?, prezzo = ?, dataInizio = ?, dataFine = ? WHERE id = ? AND host = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, request.nome());
        statement.setString(2, request.indirizzo());
        statement.setInt(3, request.nLocali());
        statement.setInt(4, request.nPostiLetto());
        statement.setInt(5, request.piano());
        statement.setDouble(6, request.prezzo());
        statement.setDate(7, java.sql.Date.valueOf(String.valueOf(request.dataInizio())));
        statement.setDate(8, java.sql.Date.valueOf(String.valueOf(request.dataFine())));
        statement.setInt(9, id); // L'id da aggiornare
        statement.setInt(10, request.host()); // L'host deve rimanere immutato
        statement.executeUpdate();
    }

    public static void deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM abitazione WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1,id);
        statement.executeUpdate();
    }


}
