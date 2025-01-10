package services;

import dto.UtenteRequest;
import entities.Utente;
import repositories.AbitazioneRepository;
import repositories.PrenotazioneRepository;
import repositories.UtenteRepository;

import java.sql.SQLException;
import java.util.List;

public class UtenteService {

    public Utente getUtenteById(Integer id) throws SQLException {
        return UtenteRepository.getById(id);
    }

    public List<Utente> getAllUtenti() throws SQLException {
        return UtenteRepository.getAll();
    }

    public void insertUtente(UtenteRequest request) throws SQLException {
        UtenteRepository.insertUtente(request);
    }

    public void updateUtente(Integer id, UtenteRequest request) throws SQLException {
        UtenteRepository.updateUtente(id, request);
    }

    public void deleteUtenteById(Integer id) throws SQLException {
        UtenteRepository.deleteById(id);
    }

    // ottenere gli host con più prenotazioni nell'ultimo mese
    public Utente hostPiuGettonatoLastMonth() throws SQLException {
        List<Utente> hosts = UtenteRepository.getAllHost();
        return hosts.stream().max((h1, h2) -> {
            try {
                Integer prenotazioniH1 = AbitazioneRepository
                        .getByHost(h1.getId())
                        .stream()
                        .mapToInt(ab -> {
                            try {
                                return PrenotazioneRepository.getNumeroPrenotazioniLastMonth(ab.getId());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
                Integer prenotazioniH2 = AbitazioneRepository
                        .getByHost(h2.getId())
                        .stream()
                        .mapToInt(ab -> {
                            try {
                                return PrenotazioneRepository.getNumeroPrenotazioniLastMonth(ab.getId());
                            } catch (SQLException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .sum();
                return Integer.compare(prenotazioniH1,prenotazioniH2);
            }
            catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }).orElseThrow();
    }
}
