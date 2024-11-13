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
    @JsonProperty
    private ArrayList<Veiculo> listaDeVeiculos;
    @JsonProperty
    private ArrayList<Desconto> listaDeDescontos;
    @JsonProperty
    private ArrayList<ContaDeEnergia> listaDeContas;

    public Apartamento(
                long idApartamento,
           int numeroApartamento,
           long idMorador,
           long idCondominio,
           ArrayList<Veiculo> listaDeVeiculos,
           ArrayList<Desconto> listaDeDescontos,
           ArrayList<ContaDeEnergia> listaDeContas
    ) {
        this.idApartamento = idApartamento;
        this.numeroApartamento = numeroApartamento;
        this.idMorador = idMorador;
        this.idCondominio = idCondominio;
        this.listaDeVeiculos = listaDeVeiculos;
        this.listaDeDescontos = listaDeDescontos;
        this.listaDeContas = listaDeContas;
    }
}
