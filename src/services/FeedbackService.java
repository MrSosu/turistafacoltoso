package services;

import dto.FeedbackRequest;
import entities.Feedback;
import repositories.FeedbackRepository;

import java.sql.SQLException;
import java.util.List;

public class FeedbackService {

    public Feedback getFeedbackById(Integer id) throws SQLException {
        return FeedbackRepository.getById(id);
    }

    public List<Feedback> getAllFeedbacks() throws SQLException {
        return FeedbackRepository.getAll();
    }

    public void insertFeedback(FeedbackRequest request) throws SQLException {
        FeedbackRepository.insertFeedback(request);
    }

    public void updateFeedback(Integer id, FeedbackRequest request) throws SQLException {
        FeedbackRepository.updateFeedback(id, request);
    }

    public void deleteFeedbackById(Integer id) throws SQLException {
        FeedbackRepository.deleteById(id);
    }
}
