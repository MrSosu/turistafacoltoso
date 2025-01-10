package repositories;

import configuration.DBConnection;
import dto.FeedbackRequest;
import entities.Feedback;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FeedbackRepository {

    private static final Connection connection;

    static {
        try {
            connection = DBConnection.getConnection();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Feedback getById(Integer id) throws SQLException {
        String query = "SELECT * FROM feedback WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return mapResultSetToFeedback(resultSet);
        } else {
            throw new IllegalArgumentException("Feedback con id " + id + " non presente");
        }
    }

    public static List<Feedback> getAll() throws SQLException {
        String query = "SELECT * FROM feedback";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Feedback> feedbacks = new ArrayList<>();
        while (resultSet.next()) {
            feedbacks.add(mapResultSetToFeedback(resultSet));
        }
        return feedbacks;
    }

    public static void insertFeedback(FeedbackRequest request) throws SQLException {
        String query = "INSERT INTO feedback (titolo, testo, punteggio, createdAt, idPrenotazione) " +
                "VALUES (?, ?, ?, ?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, request.titolo());
        statement.setString(2, request.testo());
        statement.setInt(3, request.punteggio());
        statement.setTimestamp(4, Timestamp.valueOf(request.createdAt()));
        statement.setInt(5, request.idPrenotazione());
        statement.executeUpdate();
    }

    public static void updateFeedback(Integer id, FeedbackRequest request) throws SQLException {
        String query = "UPDATE feedback SET titolo = ?, testo = ?, punteggio = ?, createdAt = ?, idPrenotazione = ? " +
                "WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, request.titolo());
        statement.setString(2, request.testo());
        statement.setInt(3, request.punteggio());
        statement.setTimestamp(4, Timestamp.valueOf(request.createdAt()));
        statement.setInt(5, request.idPrenotazione());
        statement.setInt(6, id);
        statement.executeUpdate();
    }

    public static void deleteById(Integer id) throws SQLException {
        String query = "DELETE FROM feedback WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, id);
        statement.executeUpdate();
    }

    private static Feedback mapResultSetToFeedback(ResultSet resultSet) throws SQLException {
        return new Feedback(
                resultSet.getInt("id"),
                resultSet.getString("titolo"),
                resultSet.getString("testo"),
                resultSet.getInt("punteggio"),
                resultSet.getTimestamp("createdAt").toLocalDateTime(),
                resultSet.getInt("idPrenotazione")
        );
    }
}
