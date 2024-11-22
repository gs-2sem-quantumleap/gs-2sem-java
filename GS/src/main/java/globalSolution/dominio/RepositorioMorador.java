package globalSolution.dominio;

import java.util.ArrayList;

public interface RepositorioMorador {

    void adicionarMorador(Morador morador);
    Morador buscarMoradorPorId(Long id);
    ArrayList<Morador> listarMorador();
    void atualizarMorador(long idMorador, Morador morador);
    void removerMorador(Long idMorador);
    void fecharConexao();
    Morador buscarMoradorPorCpf(String cpf);
}
