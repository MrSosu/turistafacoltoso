package services;

import dto.AbitazioneRequest;
import entities.Abitazione;
import repositories.AbitazioneRepository;

import java.sql.SQLException;
import java.util.List;

public class AbitazioneService {

    public Abitazione getAbitazioneById(Integer id) throws SQLException {
        return AbitazioneRepository.getById(id);
    }

    public List<Abitazione> getAllAbitazioni() throws SQLException {
        return AbitazioneRepository.getAll();
    }

    public void insertAbitazione(AbitazioneRequest request) throws SQLException {
        AbitazioneRepository.insertAbitazione(request);
    }

    public void updateAbitazione(Integer id, AbitazioneRequest request) throws SQLException {
        AbitazioneRepository.updateAbitazione(id, request);
    }

    public void deleteAbitazioneById(Integer id) throws SQLException {
        AbitazioneRepository.deleteById(id);
    }

}
