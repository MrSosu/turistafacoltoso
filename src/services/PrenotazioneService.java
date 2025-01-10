package services;

import dto.PrenotazioneRequest;
import entities.Prenotazione;
import repositories.PrenotazioneRepository;

import java.sql.SQLException;
import java.util.List;

public class PrenotazioneService {

    public Prenotazione getPrenotazioneById(Integer id) throws SQLException {
        return PrenotazioneRepository.getById(id);
    }

    public List<Prenotazione> getAllPrenotazioni() throws SQLException {
        return PrenotazioneRepository.getAll();
    }

    public void insertPrenotazione(PrenotazioneRequest request) throws SQLException {
        PrenotazioneRepository.insertPrenotazione(request);
    }

    public void updatePrenotazione(Integer id, PrenotazioneRequest request) throws SQLException {
        PrenotazioneRepository.updatePrenotazione(id, request);
    }

    public void deletePrenotazioneById(Integer id) throws SQLException {
        PrenotazioneRepository.deleteById(id);
    }

    // ottenere l'ultima prenotazione dato un id utente
    public Prenotazione getLastPrenotazioneByUtenteId(Integer utenteId) throws SQLException {
        return PrenotazioneRepository.getLastPrenotazioneByUtenteId(utenteId);
    }

}
