package globalSolution.infra.dao;

import globalSolution.dominio.Apartamento;
import globalSolution.dominio.Condominio;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.util.List;

public class CondominioDAOTest {

    @Test
    public void testAdicionaCondominio(){
        Condominio condominio = new Condominio(550.50);
        Condominio condominio2 = new Condominio(255.75);
        CondominioDAO condominioDAO = new CondominioDAO();

        condominioDAO.adicionarCondominio(condominio);
        condominioDAO.adicionarCondominio(condominio2);
    }

    @Test
    public void listandoApartamento(){
        CondominioDAO condominioDAO = new CondominioDAO();
        long idCondominio = 1L;
        List<Apartamento> condominios = condominioDAO.listarTodosApartamentos(idCondominio);

        for(Apartamento condominio: condominios){
            System.out.println("ID: " + condominio.getIdCondominio());
            System.out.println("ID Morador: " + condominio.getIdMorador());
            System.out.println("ID Apartamento: " + condominio.getIdApartamento());
            System.out.println("Numero Apartamento: " + condominio.getNumeroApartamento());
            System.out.println("--------------------------------");
        }
    }

    @Test
    public void atualizarCondominio(){
        CondominioDAO condominioDAO = new CondominioDAO();
        Condominio condominio = new Condominio(111);
        condominioDAO.atualizarCondominio(1l, condominio);
    }

    @Test
    public void removerCondominio(){
        CondominioDAO condominioDAO = new CondominioDAO();
        long idCondo = 4l;
        condominioDAO.removerCondiminio(idCondo);
    }
}
