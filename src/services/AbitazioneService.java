package services;

import dto.AbitazioneRequest;
import entities.Abitazione;
import entities.Utente;
import repositories.AbitazioneRepository;
import repositories.PrenotazioneRepository;
import repositories.UtenteRepository;

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

    // ottenere le abitazioni corrispondente ad un certo codice host
    public List<Abitazione> getByCodiceHost(String codiceHost) throws SQLException {
        Utente host = UtenteRepository.getByCodiceHost(codiceHost);
        return AbitazioneRepository.getByHost(host.getId());
    }

    // ottenere l'abitazione più gettonata nell'ultimo mese
    public Abitazione getAbitazionePiuGettonata() throws SQLException {
        return AbitazioneRepository.getAll()
                .stream()
                .max((ab1, ab2) -> {
                    try {
                        Integer numPrenotazioniAb1 = PrenotazioneRepository.getNumeroPrenotazioniLastMonth(ab1.getId());
                        Integer numPrenotazioniAb2 = PrenotazioneRepository.getNumeroPrenotazioniLastMonth(ab2.getId());
                        return Integer.compare(numPrenotazioniAb1, numPrenotazioniAb2);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                })
                .orElseThrow();
    }

}
