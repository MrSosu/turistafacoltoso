package dto;

import validator.Validator;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public record PrenotazioneRequest(
        Date dataArrivo,
        Date dataPartenza,
        Double prezzoTotale,
        LocalDateTime dataPrenotazione,
        Integer idGuest,
        Integer idAbitazione
) {

    public PrenotazioneRequest(
            Date dataArrivo,
            Date dataPartenza,
            Double prezzoTotale,
            LocalDateTime dataPrenotazione,
            Integer idGuest,
            Integer idAbitazione
    ) {
        Validator.requireDateAfter(dataArrivo, Date.from(Instant.from(LocalDate.now())));
        this.dataArrivo = Validator.requireDateBefore(dataArrivo,dataPartenza);
        this.dataPartenza = Validator.requireDateAfter(dataPartenza,dataArrivo);
        this.dataPrenotazione = LocalDateTime.now();
        this.prezzoTotale = Validator.requirePositive(prezzoTotale);
        this.idGuest = (Integer) Validator.requireNotNull(idGuest);
        this.idAbitazione = (Integer) Validator.requireNotNull(idAbitazione);
    }

}
