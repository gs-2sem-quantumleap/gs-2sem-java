package globalSolution.infra.dao;

import globalSolution.dominio.Apartamento;
import globalSolution.dominio.Condominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CondominioDAO {
    private Connection conexao;

    public CondominioDAO() { this.conexao = new ConnectionFactory().getConnection(); }

    public void adicionarCondominio(Condominio condominio){
        String sql = "INSERT INTO tb_gm_condominio (conta_condominio) VALUES (?)";

        try{
            PreparedStatement sqlInsert = conexao.prepareStatement(sql);
            sqlInsert.setDouble(1, condominio.getContaCondominio());
            sqlInsert.execute();
            sqlInsert.close();

        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public List<Apartamento> listarTodosApartamentos(long idCondominio){
        String sql = "SELECT * FROM tb_gm_apartamento WHERE id_condominio = ?";
        List<Apartamento> listaDeApartamentos = new ArrayList<>();
        try {
            PreparedStatement sqlSelect = conexao.prepareStatement(sql);
            sqlSelect.setLong(1, idCondominio);

            ResultSet rs = sqlSelect.executeQuery();

            while(rs.next()){
                Apartamento apartamento = new Apartamento();
                apartamento.setIdApartamento(rs.getLong(1));
                apartamento.setNumeroApartamento(rs.getInt(2));
                apartamento.setIdMorador(rs.getLong(3));
                apartamento.setIdCondominio(rs.getLong(4));
                listaDeApartamentos.add(apartamento);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return listaDeApartamentos;
    }

    public void atualizarCondominio(long idCondominio, Condominio condominio){
        String sql = "UPDATE tb_gm_condominio SET conta_condominio = ? WHERE id_condominio = ?";

        try {
            PreparedStatement sqlUpdate = conexao.prepareStatement(sql);
            sqlUpdate.setDouble(1, condominio.getContaCondominio());
            sqlUpdate.setLong(2, idCondominio);
            sqlUpdate.executeUpdate();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void removerCondiminio(Long idCondominio) {
        String sqlDeleteCliente = "DELETE FROM tb_gm_condominio WHERE id_condominio = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sqlDeleteCliente)) {
            pstmt.setLong(1, idCondominio);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void fecharConexao(){
        try{
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
