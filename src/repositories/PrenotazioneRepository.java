package repositories;

import configuration.DBConnection;
import dto.PrenotazioneRequest;
import entities.Prenotazione;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PrenotazioneRepository {

    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Prenotazione getById(Integer id) throws SQLException {
        String query = "SELECT * FROM prenotazione WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return mapResultSetToPrenotazione(resultSet);
        } else {
            throw new IllegalArgumentException("Prenotazione con id " + id + " non presente");
        }
    }

    public static Prenotazione getLastPrenotazioneByUtenteId(Integer utenteId) throws SQLException {
        String query = "SELECT * FROM prenotazione WHERE id_guest = ? ORDER BY data_prenotazione DESC LIMIT 1";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, utenteId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return mapResultSetToPrenotazione(resultSet);
        } else {
            throw new IllegalArgumentException("Prenotazione con utente id " + utenteId + " non presente");
        }
    }

    public static Integer getNumeroPrenotazioniLastMonth(Integer abitazioneId) throws SQLException {
        String query = "SELECT COUNT(*) AS numero_prenotazioni " +
                "FROM prenotazione " +
                "WHERE id_abitazione = ? AND data_prenotazione >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, abitazioneId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt("numero_prenotazioni");
        }
        return 0;
    }

    public static List<Prenotazione> getAll() throws SQLException {
        String query = "SELECT * FROM prenotazione";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Prenotazione> prenotazioni = new ArrayList<>();
        while (resultSet.next()) {
            prenotazioni.add(mapResultSetToPrenotazione(resultSet));
        }
        return prenotazioni;
    }

    public static void insertPrenotazione(PrenotazioneRequest request) throws SQLException {
        String query = "INSERT INTO prenotazione (dataArrivo, dataPartenza, prezzoTotale, dataPrenotazione, idGuest, idAbitazione) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDate(1, new java.sql.Date(request.dataArrivo().getTime()));
        statement.setDate(2, new java.sql.Date(request.dataPartenza().getTime()));
        statement.setDouble(3, request.prezzoTotale());
        statement.setTimestamp(4, Timestamp.valueOf(request.dataPrenotazione()));
        statement.setInt(5, request.idGuest());
        statement.setInt(6, request.idAbitazione());
        statement.executeUpdate();
    }

    public static void updatePrenotazione(Integer id, PrenotazioneRequest request) throws SQLException {
        String query = "UPDATE prenotazione SET dataArrivo = ?, dataPartenza = ?, prezzoTotale = ?, idGuest = ?, idAbitazione = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setDate(1, new java.sql.Date(request.dataArrivo().getTime()));
        statement.setDate(2, new java.sql.Date(request.dataPartenza().getTime()));
        statement.setDouble(3, request.prezzoTotale());
        statement.setInt(4, request.idGuest());
        statement.setInt(5, request.idAbitazione());
        statement.setInt(6, id);
        statement.executeUpdate();
    }

    public static void deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM prenotazione WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    private static Prenotazione mapResultSetToPrenotazione(ResultSet resultSet) throws SQLException {
        return new Prenotazione(
                resultSet.getInt("id"),
                resultSet.getDate("dataArrivo"),
                resultSet.getDate("dataPartenza"),
                resultSet.getTimestamp("dataPrenotazione").toLocalDateTime(),
                resultSet.getDouble("prezzoTotale"),
                resultSet.getInt("idGuest"),
                resultSet.getInt("idAbitazione")
        );
    }

}

