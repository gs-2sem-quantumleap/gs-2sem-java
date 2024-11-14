package globalSolution.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Morador {

    private long idMorador;
    @JsonProperty
    private String nome_morador;
    @JsonProperty
    private String cpf;
    @JsonProperty
    private String email;
    @JsonProperty
    private String telefone;

    public Morador(String nome_morador, String cpf, String email, String telefone) {
        this.nome_morador = nome_morador;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
    }

    public Morador() {}

    public String getNomeMorador() {
        return nome_morador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nome_morador = nomeMorador;
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
