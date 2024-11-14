package globalSolution.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class Condominio {
    private long idCondominio;
    @JsonProperty
    private double contaCondominio;


    public Condominio(double contaCondominio) {
        this.contaCondominio = contaCondominio;
    }

    public Condominio() {}

    public long getIdCondominio() {
        return idCondominio;
    }

    public void setIdCondominio(long idCondominio) {
        this.idCondominio = idCondominio;
    }

    public double getContaCondominio() {
        return contaCondominio;
    }

    public void setContaCondominio(double contaCondominio) {
        this.contaCondominio = contaCondominio;
    }

}
