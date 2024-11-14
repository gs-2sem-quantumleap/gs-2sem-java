package globalSolution.infra.dao;

import globalSolution.dominio.Apartamento;
import org.junit.jupiter.api.Test;

public class ApartamentoDAOTest {

    @Test
    public void testAdicionaApartamento(){
        Apartamento apartamento = new Apartamento(1, 2, 1);
        Apartamento outroApartamento = new Apartamento(2, 3, 1);

        ApartamentoDAO apartamentoDAO = new ApartamentoDAO();
        apartamentoDAO.adicionarApartamento(apartamento);
        apartamentoDAO.adicionarApartamento(outroApartamento);
        apartamentoDAO.fecharConexao();
    }
}
