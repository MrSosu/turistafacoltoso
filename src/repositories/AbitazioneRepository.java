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

    public static Abitazione getById(Integer id) {
        String query = "SELECT * FROM abitazione WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToAbitazione(resultSet);
            }
            else throw new IllegalArgumentException("Abitazione con id " + id + " non presente");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Abitazione> getAll() {
        String query = "SELECT * FROM abitazione";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Abitazione> abitazioni = new ArrayList<>();
            while (resultSet.next()) {
                abitazioni.add(mapResultSetToAbitazione(resultSet));
            }
            return abitazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Abitazione> getByHost(Integer idHost) {
        String query = "SELECT * FROM abitazione WHERE id_host = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, idHost);
            ResultSet resultSet = statement.executeQuery();
            List<Abitazione> abitazioni = new ArrayList<>();
            while (resultSet.next()) {
                abitazioni.add(mapResultSetToAbitazione(resultSet));
            }
            return abitazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void insertAbitazione(AbitazioneRequest request) {
        String query = "INSERT INTO abitazione (nome, indirizzo, nLocali, nPostiLetto, piano, prezzo, dataInizio, dataFine, host) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updateAbitazione(Integer id, AbitazioneRequest request) {
        String query = "UPDATE abitazione SET nome = ?, indirizzo = ?, nLocali = ?, nPostiLetto = ?, piano = ?, prezzo = ?, dataInizio = ?, dataFine = ? WHERE id = ? AND host = ?";
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteById(Integer id) {
        String query = "DELETE FROM abitazione WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Double mediaPostiLetto() {
        String query = "SELECT AVG(n_posti_letto) as media_posti_letto FROM abitazione";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            return resultSet.getDouble("media_posti_letto");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static Abitazione mapResultSetToAbitazione(ResultSet resultSet) throws SQLException {
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
        return abitazione;
    }


}
