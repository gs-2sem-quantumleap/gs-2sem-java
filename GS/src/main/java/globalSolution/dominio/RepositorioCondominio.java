package globalSolution.dominio;

import java.util.List;

public interface RepositorioCondominio {

    void adicionarCondominio(Condominio condominio);
    List<Apartamento> listarTodosApartamentos(long idCondominio);
    List<Condominio> listarTodosCondominios();
    Condominio buscarCondominio(long idCondominio);
    void atualizarCondominio(long idCondominio, Condominio condominio);
    void removerCondiminio(Long idCondominio);
    void fecharConexao();
}
