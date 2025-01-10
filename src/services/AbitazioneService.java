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

    public Abitazione getAbitazioneById(Integer id) {
        return AbitazioneRepository.getById(id);
    }

    public List<Abitazione> getAllAbitazioni() {
        return AbitazioneRepository.getAll();
    }

    public void insertAbitazione(AbitazioneRequest request) {
        AbitazioneRepository.insertAbitazione(request);
    }

    public void updateAbitazione(Integer id, AbitazioneRequest request) {
        AbitazioneRepository.updateAbitazione(id, request);
    }

    public void deleteAbitazioneById(Integer id) throws SQLException {
        AbitazioneRepository.deleteById(id);
    }

    // ottenere le abitazioni corrispondente ad un certo codice host
    public List<Abitazione> getByCodiceHost(String codiceHost) {
        Utente host = UtenteRepository.getByCodiceHost(codiceHost);
        return AbitazioneRepository.getByHost(host.getId());
    }

    // ottenere l'abitazione più gettonata nell'ultimo mese
    public Abitazione getAbitazionePiuGettonata() {
        return AbitazioneRepository.getAll()
                .stream()
                .max((ab1, ab2) -> {
                        Integer numPrenotazioniAb1 = PrenotazioneRepository.getNumeroPrenotazioniByAbitazioneLastMonth(ab1.getId());
                        Integer numPrenotazioniAb2 = PrenotazioneRepository.getNumeroPrenotazioniByAbitazioneLastMonth(ab2.getId());
                        return Integer.compare(numPrenotazioniAb1, numPrenotazioniAb2);
                })
                .orElseThrow();
    }

    // ottenere il numero medio di posti letto calcolato in base a tutte le abitazioni caricate dagli host
    public Double mediaPostiLetto() {
        return AbitazioneRepository.mediaPostiLetto();
    }

}
