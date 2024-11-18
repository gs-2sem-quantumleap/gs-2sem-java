package globalSolution.infra.dominio;

import globalSolution.dominio.ContaDeEnergia;
import globalSolution.dominio.Veiculo;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ContaDeEnergiaTest {

    @Test
    public void testandoAdicaoDeConsumo(){
        List<Veiculo> veiculos = new ArrayList<>();

        Veiculo veiculo = new Veiculo("1234567", 2002, true, 22L);
        Veiculo veiculo2 = new Veiculo("1234567", 2002, true, 22L);
        Veiculo veiculo3 = new Veiculo("1234567", 2002, true, 22L);
        veiculos.add(veiculo);
        veiculos.add(veiculo2);
        veiculos.add(veiculo3);
        ContaDeEnergia conta = new ContaDeEnergia(100, LocalDate.of(2024,12,10), 150, 22l);
        conta.verificaCarro(veiculos, conta);
        System.out.println("Novo consumo de kWh: " + conta.getConsumoKwh());
    }
}
