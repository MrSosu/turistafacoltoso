package services;

import dto.UtenteRequest;
import entities.Utente;
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

}
