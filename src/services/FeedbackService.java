package services;

import dto.FeedbackRequest;
import entities.Feedback;
import repositories.FeedbackRepository;

import java.sql.SQLException;
import java.util.List;

public class FeedbackService {

    public Feedback getFeedbackById(Integer id) {
        return FeedbackRepository.getById(id);
    }

    public List<Feedback> getAllFeedbacks() {
        return FeedbackRepository.getAll();
    }

    public void insertFeedback(FeedbackRequest request) {
        FeedbackRepository.insertFeedback(request);
    }

    public void updateFeedback(Integer id, FeedbackRequest request) {
        FeedbackRepository.updateFeedback(id, request);
    }

    public void deleteFeedbackById(Integer id) {
        FeedbackRepository.deleteById(id);
    }
}
