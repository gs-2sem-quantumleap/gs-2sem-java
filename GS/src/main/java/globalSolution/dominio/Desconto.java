package globalSolution.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public class Desconto {
    private long idDesconto;
    @JsonProperty
    private double valorDesconto;
    @JsonProperty
    private String descricaoDesconto;
    @JsonProperty
    private LocalDate dataDesconto;
    @JsonProperty
    private long idApartamento;

    public Desconto(double valorDesconto, String descricaoDesconto, LocalDate dataDesconto, long idApartamento) {
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

    public LocalDate getDataDesconto() {
        return dataDesconto;
    }

    public void setDataDesconto(LocalDate dataDesconto) {
        this.dataDesconto = dataDesconto;
    }

    public long getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(long idApartamento) {
        this.idApartamento = idApartamento;
    }
}
