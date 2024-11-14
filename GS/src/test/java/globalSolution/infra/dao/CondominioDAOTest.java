package globalSolution.infra.dao;

import globalSolution.dominio.Condominio;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class CondominioDAOTest {

    @Test
    public void testAdicionaCondominio(){
        Condominio condominio = new Condominio(550.50);
        Condominio condominio2 = new Condominio(255.75);
        CondominioDAO condominioDAO = new CondominioDAO();

        condominioDAO.adicionarCondominio(condominio);
        condominioDAO.adicionarCondominio(condominio2);
    }
}
