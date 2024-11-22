package globalSolution.infra.dao;

import globalSolution.dominio.Morador;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


public class MoradorDAOTest {

    @Test
    public void testeAdicionandoMorador(){
        Morador morador1 = new Morador("MoradorTest", "123.123.123-12", "morador@gmail.com", "11911111");
        Morador morador2 = new Morador("MoradorTest2", "123.123.123-12", "morador@gmail.com", "11911111");
        MoradorDAO moradorDAO = new MoradorDAO();
        moradorDAO.adicionarMorador(morador1);
        moradorDAO.adicionarMorador(morador2);
        moradorDAO.fecharConexao();
    }

    @Test
    public void buscandoClientePorId() {
        MoradorDAO moradorDAO = new MoradorDAO();
        Morador morador = moradorDAO.buscarMoradorPorId(2l);

        if (morador == null) {
            System.out.println(".");
        } else {
            System.out.println("ID:" + morador.getIdMorador());
            System.out.println("Nome:" + morador.getNomeMorador());
            System.out.println("CPF:" + morador.getCpf());
            System.out.println("Email:" + morador.getEmail());
            System.out.println("Telefone" + morador.getTelefone());
        }
        moradorDAO.fecharConexao();
    }

    @Test
    public void testListarClientes() {
        MoradorDAO moradorDAO = new MoradorDAO();
        ArrayList<Morador> moradors = moradorDAO.listarMorador();

        for (Morador morador : moradors) {
            System.out.println("ID:" + morador.getIdMorador());
            System.out.println("Nome:" + morador.getNomeMorador());
            System.out.println("CPF:" + morador.getCpf());
            System.out.println("Email:" + morador.getEmail());
            System.out.println("Telefone" + morador.getTelefone());
            System.out.println("---------------------------");
        }
        moradorDAO.fecharConexao();

    }

    @Test
    public void atualizarCliente() {
        MoradorDAO moradorDAO = new MoradorDAO();
        Morador morador = new Morador("MoradorAtualizado", "123.123.123-12", "morador@gmail.com", "11911111");

        long idMorador = 2L;

        if(moradorDAO.buscarMoradorPorId(idMorador) == null){
            System.out.println("ID Cliente não encontrado.");
        }else {
            System.out.println("Cliente atualizado com sucesso! .");
            moradorDAO.atualizarMorador(idMorador, morador);
        }
    }

    @Test
    public void deletarMorador() {
        MoradorDAO moradorDAO = new MoradorDAO();
        long idMorador = 4L;
        moradorDAO.removerMorador(idMorador);
    }

    @Test
    public void buscarMoradorPorCpf(){
        MoradorDAO moradorDAO = new MoradorDAO();

        Morador morador = moradorDAO.buscarMoradorPorCpf("12345678900");
        System.out.println("ID:" + morador.getIdMorador());
        System.out.println("Nome:" + morador.getNomeMorador());
        System.out.println("CPF:" + morador.getCpf());
        System.out.println("Email:" + morador.getEmail());
        System.out.println("Telefone" + morador.getTelefone());
        System.out.println("---------------------------");
    }





}
