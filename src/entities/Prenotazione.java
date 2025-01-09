package entities;

import validator.Validator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Prenotazione {

    private Integer id;
    private Date dataArrivo;
    private Date dataPartenza;
    private LocalDateTime dataPrenotazione;
    private Double prezzoTotale;
    private Integer idGuest;
    private Integer idAbitazione;

    public Prenotazione(Integer id,
                        Date dataArrivo,
                        Date dataPartenza,
                        LocalDateTime dataPrenotazione,
                        Double prezzoTotale,
                        Integer idGuest,
                        Integer idAbitazione) {
        this.id = id;
        Validator.requireDateAfter(dataArrivo, Date.from(Instant.from(LocalDate.now())));
        this.dataArrivo = Validator.requireDateBefore(dataArrivo,dataPartenza);
        this.dataPartenza = Validator.requireDateAfter(dataPartenza,dataArrivo);
        this.dataPrenotazione = LocalDateTime.now();
        this.prezzoTotale = Validator.requirePositive(prezzoTotale);
        this.idGuest = (Integer) Validator.requireNotNull(idGuest);
        this.idAbitazione = (Integer) Validator.requireNotNull(idAbitazione);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDataArrivo() {
        return dataArrivo;
    }

    public void setDataArrivo(Date dataArrivo) {
        Validator.requireDateAfter(dataArrivo, Date.from(Instant.from(LocalDate.now())));
        this.dataArrivo = Validator.requireDateBefore(dataArrivo,dataPartenza);
    }

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = Validator.requireDateAfter(dataPartenza,dataArrivo);
    }

    public LocalDateTime getDataPrenotazione() {
        return dataPrenotazione;
    }

    public Double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(Double prezzoTotale) {
        this.prezzoTotale = Validator.requirePositive(prezzoTotale);
    }

    public Integer getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(Integer idGuest) {
        this.idGuest = (Integer) Validator.requireNotNull(idGuest);
    }

    public Integer getIdAbitazione() {
        return idAbitazione;
    }

    public void setIdAbitazione(Integer idAbitazione) {
        this.idAbitazione = (Integer) Validator.requireNotNull(idAbitazione);
    }
}
