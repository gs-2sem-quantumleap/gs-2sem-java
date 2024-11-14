package globalSolution.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

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

    private ArrayList<Apartamento> listaDeApartamentos;

    public Morador(String nomeMorador, String cpf, String email, String telefone) {
        this.nomeMorador = nomeMorador;
        this.cpf = cpf;
        this.email = email;
        this.telefone = telefone;
        this.listaDeApartamentos = listaDeApartamentos;
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

    public ArrayList<Apartamento> getListaDeApartamentos() {
        return listaDeApartamentos;
    }

    public void setListaDeApartamentos(ArrayList<Apartamento> listaDeApartamentos) {
        this.listaDeApartamentos = listaDeApartamentos;
    }
}
