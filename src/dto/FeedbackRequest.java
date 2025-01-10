package dto;

import validator.Validator;

import java.time.LocalDateTime;

public record FeedbackRequest(
        String titolo,
        String testo,
        Integer punteggio,
        LocalDateTime createdAt,
        Integer idPrenotazione
) {

    public FeedbackRequest(String titolo,
                           String testo,
                           Integer punteggio,
                           LocalDateTime createdAt,
                           Integer idPrenotazione) {
        this.titolo = titolo;
        this.testo = testo;
        this.punteggio = Validator.requireBetween(punteggio, 1, 5);
        this.createdAt = createdAt;
        this.idPrenotazione = (Integer) Validator.requireNotNull(idPrenotazione);
    }

}
