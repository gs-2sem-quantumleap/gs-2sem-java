package globalSolution.infra.dao;

import globalSolution.dominio.Desconto;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

public class DescontoDAOTest {

    @Test
    public void adicionarDesconto(){
        Desconto desconto = new Desconto(200, "Conseguiu", LocalDate.of(2024, 11, 17), 2l);
        Desconto desconto2 = new Desconto(200, "Conseguiu", LocalDate.of(2024, 11, 17), 2l);
        DescontoDAO descontoDAO = new DescontoDAO();
        descontoDAO.adicionarDesconto(desconto);
        descontoDAO.adicionarDesconto(desconto2);
        descontoDAO.fecharConexao();
    }

    @Test
    public void buscandoDescontos(){
        DescontoDAO descontoDAO = new DescontoDAO();
        ArrayList<Desconto> descontos =  descontoDAO.buscandoTodosDescontos();

        for (Desconto desconto: descontos){
            System.out.println("ID Desconto: "+ desconto.getIdDesconto());
            System.out.println("Valor Desconto" + desconto.getValorDesconto());
            System.out.println("Descrição Desconto: "+desconto.getDescricaoDesconto());
            System.out.println("Data Desconto: "+desconto.getDataDesconto());
            System.out.println("ID Apartamento: "+desconto.getIdApartamento());
            System.out.println("------------------");
        }
        descontoDAO.fecharConexao();
    }

    @Test
    public void atualizarDesconto(){
        Desconto desconto = new Desconto(100, "ConseguiuAtualizado", LocalDate.of(2024, 11, 17), 2l);
        DescontoDAO descontoDAO = new DescontoDAO();
        descontoDAO.atualizarDesconto(1l, desconto);
        descontoDAO.fecharConexao();
    }

    @Test
    public void deletarDesconto(){
        DescontoDAO descontoDAO = new DescontoDAO();
        descontoDAO.removerDesconto(3l);
        descontoDAO.fecharConexao();
    }
}
