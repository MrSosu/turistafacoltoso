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

    public static Feedback getById(Integer id) {
        String query = "SELECT * FROM feedback WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return mapResultSetToFeedback(resultSet);
            } else {
                throw new IllegalArgumentException("Feedback con id " + id + " non presente");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Feedback> getAll() {
        String query = "SELECT * FROM feedback";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Feedback> feedbacks = new ArrayList<>();
            while (resultSet.next()) {
                feedbacks.add(mapResultSetToFeedback(resultSet));
            }
            return feedbacks;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void insertFeedback(FeedbackRequest request) {
        String query = "INSERT INTO feedback (titolo, testo, punteggio, createdAt, idPrenotazione) " +
                "VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, request.titolo());
            statement.setString(2, request.testo());
            statement.setInt(3, request.punteggio());
            statement.setTimestamp(4, Timestamp.valueOf(request.createdAt()));
            statement.setInt(5, request.idPrenotazione());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void updateFeedback(Integer id, FeedbackRequest request) {
        String query = "UPDATE feedback SET titolo = ?, testo = ?, punteggio = ?, createdAt = ?, idPrenotazione = ? " +
                "WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, request.titolo());
            statement.setString(2, request.testo());
            statement.setInt(3, request.punteggio());
            statement.setTimestamp(4, Timestamp.valueOf(request.createdAt()));
            statement.setInt(5, request.idPrenotazione());
            statement.setInt(6, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void deleteById(Integer id) {
        String query = "DELETE FROM feedback WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Feedback mapResultSetToFeedback(ResultSet resultSet) {
        try {
            return new Feedback(
                    resultSet.getInt("id"),
                    resultSet.getString("titolo"),
                    resultSet.getString("testo"),
                    resultSet.getInt("punteggio"),
                    resultSet.getTimestamp("createdAt").toLocalDateTime(),
                    resultSet.getInt("idPrenotazione")
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
