package globalSolution.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class Desconto {
    private long idDesconto;
    @JsonProperty
    private double valorDesconto;
    @JsonProperty
    private String descricaoDesconto;
    @JsonProperty
    private Date dataDesconto;
    @JsonProperty
    private long idApartamento;

    public Desconto(long idDesconto, double valorDesconto, String descricaoDesconto, Date dataDesconto, long idApartamento) {
        this.idDesconto = idDesconto;
        this.valorDesconto = valorDesconto;
        this.descricaoDesconto = descricaoDesconto;
        this.dataDesconto = dataDesconto;
        this.idApartamento = idApartamento;
    }

    public Desconto(){}

    public long getIdDesconto() {
        return idDesconto;
    }

    public void setIdDesconto(long idDesconto) {
        this.idDesconto = idDesconto;
    }

    public double getValorDesconto() {
        return valorDesconto;
    }

    public void setValorDesconto(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    public String getDescricaoDesconto() {
        return descricaoDesconto;
    }

    public void setDescricaoDesconto(String descricaoDesconto) {
        this.descricaoDesconto = descricaoDesconto;
    }

    public Date getDataDesconto() {
        return dataDesconto;
    }

    public void setDataDesconto(Date dataDesconto) {
        this.dataDesconto = dataDesconto;
    }

    public long getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(long idApartamento) {
        this.idApartamento = idApartamento;
    }
}
