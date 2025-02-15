package entities;

import validator.Validator;

import java.util.UUID;

public class Utente {

    private Integer id;
    private String nome;
    private String cognome;
    private String email;
    private String indirizzo;
    private String codiceHost;

    public Utente() {}

    public Utente(Integer id, String nome, String cognome, String email, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.indirizzo = indirizzo;
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
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }

    public String getCodiceHost() {
        return codiceHost;
    }

    public void setCodiceHost(String codiceHost) {
        this.codiceHost = codiceHost;
    }

    public void setCodiceHost() {
        this.codiceHost = UUID.randomUUID().toString();
    }

    @Override
    public String toString() {
        return "Utente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", cognome='" + cognome + '\'' +
                ", email='" + email + '\'' +
                ", indirizzo='" + indirizzo + '\'' +
                '}';
    }
}
