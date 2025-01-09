package entities;

import validator.Validator;

public class Utente {

    protected Integer id;
    protected String nome;
    protected String cognome;
    protected String email;
    protected String indirizzo;

    public Utente() {}

    public Utente(Integer id, String nome, String cognome, String email, String indirizzo) {
        this.id = id;
        this.nome = Validator.requireNotBlank(nome);
        this.cognome = Validator.requireNotBlank(cognome);
        this.email = Validator.validRegex(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
        this.indirizzo = Validator.requireNotBlank(indirizzo);
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

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = Validator.requireNotBlank(cognome);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = Validator.validRegex(email, "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = Validator.requireNotBlank(indirizzo);
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
