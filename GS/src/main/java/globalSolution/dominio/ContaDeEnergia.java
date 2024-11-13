package globalSolution.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class ContaDeEnergia {
    private long idContaDeEnergia;
    @JsonProperty
    private double valorConta;
    @JsonProperty
    private Date dataConta;
    @JsonProperty
    private double consumoKwh;
    @JsonProperty
    private long idApartamento;

    public ContaDeEnergia(long idContaDeEnergia, double valorConta, Date dataConta, double consumoKwh, long idApartamento){
        this.idContaDeEnergia = idContaDeEnergia;
        this.valorConta = valorConta;
        this.dataConta = dataConta;
        this.consumoKwh = consumoKwh;
        this.idApartamento = idApartamento;
    }

    public ContaDeEnergia(){}

    public long getIdContaDeEnergia() {
        return idContaDeEnergia;
    }

    public void setIdContaDeEnergia(long idContaDeEnergia) {
        this.idContaDeEnergia = idContaDeEnergia;
    }

    public double getValorConta() {
        return valorConta;
    }

    public void setValorConta(double valorConta) {
        this.valorConta = valorConta;
    }

    public Date getDataConta() {
        return dataConta;
    }

    public void setDataConta(Date dataConta) {
        this.dataConta = dataConta;
    }

    public double getConsumoKwh() {
        return consumoKwh;
    }

    public void setConsumoKwh(double consumoKwh) {
        this.consumoKwh = consumoKwh;
    }

    public long getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(long idApartamento) {
        this.idApartamento = idApartamento;
    }
}
