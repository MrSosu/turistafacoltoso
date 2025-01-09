package entities;

import validator.Validator;

public class Host extends Utente {

    private Integer codiceHost;

    public Host(Integer id,
                String nome,
                String cognome,
                String email,
                String indirizzo,
                Integer codiceHost) {
        super(id, nome, cognome, email, indirizzo);
        this.codiceHost = (Integer) Validator.requireNotNull(codiceHost);
    }

    public Integer getCodiceHost() {
        return codiceHost;
    }

    public void setCodiceHost(Integer codiceHost) {
        this.codiceHost = (Integer) Validator.requireNotNull(codiceHost);
    }
}
