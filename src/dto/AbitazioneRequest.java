package dto;

import validator.Validator;

import java.util.Date;

public record AbitazioneRequest(
        String nome,
        String indirizzo,
        Integer nLocali,
        Integer nPostiLetto,
        Double prezzo,
        Integer piano,
        Date dataInizio,
        Date dataFine,
        Integer host
) {
    public AbitazioneRequest(String nome,
                             String indirizzo,
                             Integer nLocali,
                             Integer nPostiLetto,
                             Double prezzo,
                             Integer piano,
                             Date dataInizio,
                             Date dataFine,
                             Integer host) {
        this.nome = Validator.requireNotBlank(nome);
        this.indirizzo = Validator.requireNotBlank(indirizzo);
        this.nLocali = Validator.requireGreaterThenZero(nLocali);
        this.nPostiLetto = Validator.requireGreaterThenZero(nPostiLetto);
        this.prezzo = Validator.requirePositive(prezzo);
        this.piano = piano;
        this.dataInizio = Validator.requireDateBefore(dataInizio,dataFine);
        this.dataFine = dataFine;
        this.host = (Integer) Validator.requireNotNull(host);
    }


}
