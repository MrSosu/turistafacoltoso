package repositories;

import configuration.DBConnection;
import dto.PrenotazioneRequest;
import entities.Prenotazione;

import java.sql.*;
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

    public static Prenotazione getById(Integer id) {
        String query = "SELECT * FROM prenotazione WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPrenotazione(resultSet);
            } else {
                throw new IllegalArgumentException("Prenotazione con id " + id + " non presente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Prenotazione getLastPrenotazioneByUtenteId(Integer utenteId) {
        String query = "SELECT * FROM prenotazione WHERE id_guest = ? ORDER BY data_prenotazione DESC LIMIT 1";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, utenteId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToPrenotazione(resultSet);
            } else {
                throw new IllegalArgumentException("Prenotazione con utente id " + utenteId + " non presente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Integer getNumeroPrenotazioniByAbitazione(Integer abitazioneId) {
        String query = "SELECT COUNT(*) AS numero_prenotazioni " +
                "FROM prenotazione " +
                "WHERE id_abitazione = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, abitazioneId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("numero_prenotazioni");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Integer getNumeroPrenotazioniByAbitazioneLastMonth(Integer abitazioneId) {
        String query = "SELECT COUNT(*) AS numero_prenotazioni " +
                "FROM prenotazione " +
                "WHERE id_abitazione = ? AND data_prenotazione >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, abitazioneId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("numero_prenotazioni");
            }
            return 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Prenotazione> getPrenotazioniByGuestIdLastMonth(Integer guestId) {
        String query = "SELECT * " +
                "FROM prenotazione " +
                "WHERE id_guest = ? AND data_prenotazione >= DATE_SUB(CURDATE(), INTERVAL 1 MONTH)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, guestId);
            ResultSet resultSet = statement.executeQuery();
            List<Prenotazione> prenotazioni = new ArrayList<>();
            while (resultSet.next()) {
                prenotazioni.add(mapResultSetToPrenotazione(resultSet));
            }
            return prenotazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Prenotazione> getAll() {
        String query = "SELECT * FROM prenotazione";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Prenotazione> prenotazioni = new ArrayList<>();
            while (resultSet.next()) {
                prenotazioni.add(mapResultSetToPrenotazione(resultSet));
            }
            return prenotazioni;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void insertPrenotazione(PrenotazioneRequest request) {
        String query = "INSERT INTO prenotazione (dataArrivo, dataPartenza, prezzoTotale, dataPrenotazione, idGuest, idAbitazione) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(request.dataArrivo().getTime()));
            statement.setDate(2, new java.sql.Date(request.dataPartenza().getTime()));
            statement.setDouble(3, request.prezzoTotale());
            statement.setTimestamp(4, Timestamp.valueOf(request.dataPrenotazione()));
            statement.setInt(5, request.idGuest());
            statement.setInt(6, request.idAbitazione());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updatePrenotazione(Integer id, PrenotazioneRequest request) {
        String query = "UPDATE prenotazione SET dataArrivo = ?, dataPartenza = ?, prezzoTotale = ?, idGuest = ?, idAbitazione = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setDate(1, new java.sql.Date(request.dataArrivo().getTime()));
            statement.setDate(2, new java.sql.Date(request.dataPartenza().getTime()));
            statement.setDouble(3, request.prezzoTotale());
            statement.setInt(4, request.idGuest());
            statement.setInt(5, request.idAbitazione());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteById(Integer id) {
        String query = "DELETE FROM prenotazione WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Prenotazione mapResultSetToPrenotazione(ResultSet resultSet) {
        try {
            return new Prenotazione(
                    resultSet.getInt("id"),
                    resultSet.getDate("dataArrivo"),
                    resultSet.getDate("dataPartenza"),
                    resultSet.getTimestamp("dataPrenotazione").toLocalDateTime(),
                    resultSet.getDouble("prezzoTotale"),
                    resultSet.getInt("idGuest"),
                    resultSet.getInt("idAbitazione")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}

