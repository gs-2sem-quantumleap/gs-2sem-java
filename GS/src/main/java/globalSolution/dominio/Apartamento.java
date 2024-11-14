package globalSolution.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Apartamento {
    private long idApartamento;
    @JsonProperty
    private int numeroApartamento;
    @JsonProperty
    private long idMorador;
    @JsonProperty
    private long idCondominio;


    public Apartamento(int numeroApartamento, long idMorador, long idCondominio) {
        this.numeroApartamento = numeroApartamento;
        this.idMorador = idMorador;
        this.idCondominio = idCondominio;
    }

    public Apartamento(){}

    public long getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(long idApartamento) {
        this.idApartamento = idApartamento;
    }

    public int getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(int numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public long getIdMorador() {
        return idMorador;
    }

    public void setIdMorador(long idMorador) {
        this.idMorador = idMorador;
    }

    public long getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(long idCondominio) {
        this.idCondominio = idCondominio;
    }
}
