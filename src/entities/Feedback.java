package entities;

import validator.Validator;

import java.time.LocalDateTime;

public class Feedback {

    private Integer id;
    private String titolo;
    private String testo;
    private Integer punteggio;
    private LocalDateTime createdAt;
    private Integer idPrenotazione;

    public Feedback(Integer id,
                    String titolo,
                    String testo,
                    Integer punteggio,
                    LocalDateTime createdAt,
                    Integer idPrenotazione) {
        this.id = id;
        this.titolo = titolo;
        this.testo = testo;
        this.punteggio = Validator.requireBetween(punteggio, 1, 5);
        this.createdAt = createdAt;
        this.idPrenotazione = (Integer) Validator.requireNotNull(idPrenotazione);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitolo() {
        return titolo;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }

    public Integer getPunteggio() {
        return punteggio;
    }

    public void setPunteggio(Integer punteggio) {
        this.punteggio = punteggio;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Integer getIdPrenotazione() {
        return idPrenotazione;
    }

    public void setIdPrenotazione(Integer idPrenotazione) {
        this.idPrenotazione = idPrenotazione;
    }
}
