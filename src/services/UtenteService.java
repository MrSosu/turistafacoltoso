package services;

import dto.UtenteRequest;
import entities.Prenotazione;
import entities.Utente;
import repositories.AbitazioneRepository;
import repositories.PrenotazioneRepository;
import repositories.UtenteRepository;

import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
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
                Integer prenotazioniH1 = AbitazioneRepository
                        .getByHost(h1.getId())
                        .stream()
                        .mapToInt(ab -> PrenotazioneRepository.getNumeroPrenotazioniByAbitazioneLastMonth(ab.getId()))
                        .sum();
                Integer prenotazioniH2 = AbitazioneRepository
                        .getByHost(h2.getId())
                        .stream()
                        .mapToInt(ab -> PrenotazioneRepository.getNumeroPrenotazioniByAbitazioneLastMonth(ab.getId()))
                        .sum();
                return Integer.compare(prenotazioniH1,prenotazioniH2);
        }).orElseThrow();
    }

    // ottenere tutti i super-host (un super-host è un host con almeno 100 prenotazioni)
    public List<Utente> getSuperHost() {
        return UtenteRepository
                .getAllHost()
                .stream()
                .filter(host -> {
                    Integer nPrenotazioni = AbitazioneRepository
                                .getByHost(host.getId())
                                .stream()
                                .mapToInt(ab -> PrenotazioneRepository.getNumeroPrenotazioniByAbitazione(ab.getId()))
                                .sum();
                    return nPrenotazioni >= 100;
                })
                .toList();
    }

    // ottenere i 5 utenti con più giorni prenotati nell'ultimo mese
    public List<Utente> maxGuestGiorniPrenotatiLastMonth() throws SQLException {
        return getAllUtenti().stream()
                .sorted((u1, u2) -> {
                    List<Prenotazione> prenotazioniU1 =
                            PrenotazioneRepository.getPrenotazioniByGuestIdLastMonth(u1.getId());
                    List<Prenotazione> prenotazioniU2 =
                            PrenotazioneRepository.getPrenotazioniByGuestIdLastMonth(u2.getId());
                    Long numeroGiorniU1 = prenotazioniU1.stream().mapToLong(
                            p -> ChronoUnit.DAYS.between(p.getDataArrivo().toInstant(), p.getDataPartenza().toInstant())
                    ).sum();
                    Long numeroGiorniU2 = prenotazioniU2.stream().mapToLong(
                            p -> ChronoUnit.DAYS.between(p.getDataArrivo().toInstant(), p.getDataPartenza().toInstant())
                    ).sum();
                    return Long.compare(numeroGiorniU1, numeroGiorniU2);
                })
                .limit(5)
                .toList();
    }

}
