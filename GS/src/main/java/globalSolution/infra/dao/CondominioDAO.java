package globalSolution.infra.dao;

import globalSolution.dominio.Apartamento;
import globalSolution.dominio.Condominio;
import globalSolution.dominio.RepositorioCondominio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CondominioDAO implements RepositorioCondominio {
    private Connection conexao;

    public CondominioDAO() { this.conexao = new ConnectionFactory().getConnection(); }

    public void adicionarCondominio(Condominio condominio) {
        String sql = "INSERT INTO tb_gm_condominio (conta_condominio) VALUES (?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id_condominio"})) {

            pstmt.setDouble(1, condominio.getContaCondominio());
            pstmt.executeUpdate();
            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    condominio.setIdCondominio(generatedKeys.getLong(1));
                } else {
                    throw new SQLException("Falha ao obter o ID gerado para o condomínio.");
                }
            }
        } catch (SQLException e) {
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
    public List<Condominio> listarTodosCondominios(){
        String sql = "SELECT * FROM tb_gm_condominio";
        List<Condominio> listaDeCondominio = new ArrayList<>();

        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()){
            while(rs.next()){
                Condominio condominio = new Condominio();
                condominio.setIdCondominio(rs.getLong("id_condominio"));
                condominio.setContaCondominio(rs.getDouble("conta_condominio"));
                listaDeCondominio.add(condominio);
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return listaDeCondominio;
    }
    public Condominio buscarCondominio(long idCondominio) {
        String sql = "SELECT * FROM tb_gm_condominio WHERE id_condominio = ?";
        Condominio condominio = null;

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, idCondominio);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    condominio = new Condominio();
                    condominio.setIdCondominio(rs.getLong("id_condominio"));
                    condominio.setContaCondominio(rs.getDouble("conta_condominio"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return condominio; // Pode retornar null se nenhum registro for encontrado
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
