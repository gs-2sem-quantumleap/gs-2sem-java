package globalSolution.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Morador {

    private long idMorador;
    @JsonProperty
    private String nomeMorador;
    @JsonProperty
    private String cpf;
    @JsonProperty
    private String email;
    @JsonProperty
    private String telefone;

    public Morador(String nomeMorador, String cpf, String email, String telefone) {
        this.nomeMorador = nomeMorador;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public Morador() {}

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getIdMorador() { return this.idMorador; };

    public void setIdMorador(Long idMorador) {
        this.idMorador = idMorador;
    }

}
