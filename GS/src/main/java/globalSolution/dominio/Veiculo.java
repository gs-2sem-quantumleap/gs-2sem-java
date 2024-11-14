package globalSolution.dominio;

public class Veiculo {
    private long idVeiculo;
    private String placaVeiculo;
    private int anoVeiculo;
    private boolean isEletrico;
    private long idApartamento;

    public Veiculo(String placaVeiculo, int anoVeiculo, boolean isEletrico, long idApartamento) {
        this.placaVeiculo = placaVeiculo;
        this.anoVeiculo = anoVeiculo;
        this.isEletrico = isEletrico;
        this.idApartamento = idApartamento;
    }

    public Veiculo(){}

    public long getIdVeiculo() {
        return idVeiculo;
    }

    public void setIdVeiculo(long idVeiculo) {
        this.idVeiculo = idVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public int getAnoVeiculo() {
        return anoVeiculo;
    }

    public void setAnoVeiculo(int anoVeiculo) {
        this.anoVeiculo = anoVeiculo;
    }

    public boolean isEletrico() {
        return isEletrico;
    }

    public void setEletrico(boolean eletrico) {
        isEletrico = eletrico;
    }

    public long getIdApartamento() {
        return idApartamento;
    }

    public void setIdApartamento(long idApartamento) {
        this.idApartamento = idApartamento;
    }
}
