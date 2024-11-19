package globalSolution.dominio;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.List;

public class ContaDeEnergia {
    private long idContaDeEnergia;
    @JsonProperty
    private double valorConta;
    @JsonProperty
    private LocalDate dataConta;
    @JsonProperty
    private double consumoKwh;
    @JsonProperty
    private long idApartamento;

    public ContaDeEnergia(double valorConta, LocalDate dataConta, double consumoKwh, long idApartamento){
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

    public LocalDate getDataConta() {
        return dataConta;
    }

    public void setDataConta(LocalDate dataConta) {
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

    public void verificaCarro(List<Veiculo> veiculos, ContaDeEnergia contaDeEnergia) {
        for (Veiculo veiculo : veiculos) {
            if (veiculo.isEletrico()) {
                double novoConsumo = contaDeEnergia.getConsumoKwh() + 50;
                contaDeEnergia.setConsumoKwh(novoConsumo);
            }
        }
    }



}
