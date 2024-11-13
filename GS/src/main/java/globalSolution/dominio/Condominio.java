package globalSolution.dominio;

import java.util.ArrayList;

public class Condominio {
    private long idApartamento;
    private double contaCondominio;
    private ArrayList<Apartamento> listaDeApartamentos;

    public Condominio(long idApartamento, double contaCondominio, ArrayList<Apartamento> listaDeApartamentos) {
        this.idApartamento = idApartamento;
        this.contaCondominio = contaCondominio;
        this.listaDeApartamentos = listaDeApartamentos;
    }

    public Condominio() {}

    public long getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(long idApartamento) {
        this.idApartamento = idApartamento;
    }

    public double getContaCondominio() {
        return contaCondominio;
    }

    public void setContaCondominio(double contaCondominio) {
        this.contaCondominio = contaCondominio;
    }

    public ArrayList<Apartamento> getListaDeApartamentos() {
        return listaDeApartamentos;
    }

    public void setListaDeApartamentos(ArrayList<Apartamento> listaDeApartamentos) {
        this.listaDeApartamentos = listaDeApartamentos;
    }
}
