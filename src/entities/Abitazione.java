package entities;

import validator.Validator;

import java.util.Date;

public class Abitazione {

    private Integer id;
    private String nome;
    private String indirizzo;
    private Integer nLocali;
    private Integer nPostiLetto;
    private Integer piano;
    private Double prezzo;
    private Date dataInizio;
    private Date dataFine;
    private Integer host;

    public Abitazione(Integer id,
                      String nome,
                      String indirizzo,
                      Integer nLocali,
                      Integer nPostiLetto,
                      Double prezzo,
                      Integer piano,
                      Date dataInizio,
                      Date dataFine,
                      Integer host) {
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = Validator.requireNotBlank(nome);
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = Validator.requireNotBlank(indirizzo);
    }

    public Integer getnLocali() {
        return nLocali;
    }

    public void setnLocali(Integer nLocali) {
        this.nLocali = Validator.requireGreaterThenZero(nLocali);
    }

    public Integer getnPostiLetto() {
        return nPostiLetto;
    }

    public void setnPostiLetto(Integer nPostiLetto) {
        this.nPostiLetto = Validator.requireGreaterThenZero(nPostiLetto);
    }

    public Integer getPiano() {
        return piano;
    }

    public void setPiano(Integer piano) {
        this.piano = piano;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = Validator.requirePositive(prezzo);
    }

    public Date getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(Date dataInizio) {
        this.dataInizio = Validator.requireDateBefore(dataInizio,dataFine);
    }

    public Date getDataFine() {
        return dataFine;
    }

    public void setDataFine(Date dataFine) {
        this.dataFine = Validator.requireDateBefore(dataFine,dataInizio);
    }

    public Integer getHost() {
        return host;
    }

    public void setHost(Integer host) {
        this.host = (Integer) Validator.requireNotNull(host);
    }
}
