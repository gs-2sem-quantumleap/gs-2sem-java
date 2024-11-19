package globalSolution.dominio;

public class ConsumoMorador {
    private String nomeMorador;
    private int numeroApartamento;
    private double consumoTotalKwh;

    public ConsumoMorador(String nomeMorador, int numeroApartamento, double consumoTotalKwh) {
        this.nomeMorador = nomeMorador;
        this.numeroApartamento = numeroApartamento;
        this.consumoTotalKwh = consumoTotalKwh;
    }

    public String getNomeMorador() {
        return nomeMorador;
    }

    public void setNomeMorador(String nomeMorador) {
        this.nomeMorador = nomeMorador;
    }

    public int getNumeroApartamento() {
        return numeroApartamento;
    }

    public void setNumeroApartamento(int numeroApartamento) {
        this.numeroApartamento = numeroApartamento;
    }

    public double getConsumoTotalKwh() {
        return consumoTotalKwh;
    }

    public void setConsumoTotalKwh(double consumoTotalKwh) {
        this.consumoTotalKwh = consumoTotalKwh;
    }

    @Override
    public String toString() {
        return "Morador: " + nomeMorador + " | Apartamento: " + numeroApartamento +
                " | Consumo Total: " + String.format("%.2f", consumoTotalKwh) + " kWh";
    }
}
