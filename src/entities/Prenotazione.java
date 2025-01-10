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
        this.dataArrivo = dataArrivo;
        this.dataPartenza = dataPartenza;
        this.dataPrenotazione = dataPrenotazione;
        this.prezzoTotale = prezzoTotale;
        this.idGuest = idGuest;
        this.idAbitazione = idAbitazione;
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
        this.dataArrivo = dataArrivo;
    }

    public Date getDataPartenza() {
        return dataPartenza;
    }

    public void setDataPartenza(Date dataPartenza) {
        this.dataPartenza = dataPartenza;
    }

    public LocalDateTime getDataPrenotazione() {
        return dataPrenotazione;
    }

    public Double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(Double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public Integer getIdGuest() {
        return idGuest;
    }

    public void setIdGuest(Integer idGuest) {
        this.idGuest = idGuest;
    }

    public Integer getIdAbitazione() {
        return idAbitazione;
    }

    public void setIdAbitazione(Integer idAbitazione) {
        this.idAbitazione = idAbitazione;
    }
}
