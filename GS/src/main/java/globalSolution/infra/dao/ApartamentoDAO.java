package globalSolution.infra.dao;

import globalSolution.dominio.Apartamento;
import globalSolution.dominio.ContaDeEnergia;
import globalSolution.dominio.RepositorioApartamento;
import globalSolution.dominio.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO implements RepositorioApartamento {
    private Connection conexao;

    public ApartamentoDAO() { this.conexao = new ConnectionFactory().getConnection(); }

    public void adicionarApartamento(Apartamento apartamento){
        String sql = "INSERT INTO tb_gm_apartamento (numero_apartamento, id_morador, id_condominio) VALUES (?, ?, ?)";

        try {
            PreparedStatement sqlInsert = conexao.prepareStatement(sql);
            sqlInsert.setInt(1, apartamento.getNumeroApartamento());
            sqlInsert.setLong(2, apartamento.getIdMorador());
            sqlInsert.setLong(3, apartamento.getIdCondominio());

            sqlInsert.execute();
            sqlInsert.close();
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void atualizarApartamento(long idApartamento, Apartamento apartamento){
        String sql = "UPDATE tb_gm_apartamento SET numero_apartamento = ?, id_morador = ?, id_condominio = ? where id_apartamento = ?";

        try{
            PreparedStatement sqlUpdate = conexao.prepareStatement(sql);
            sqlUpdate.setInt(1, apartamento.getNumeroApartamento());
            sqlUpdate.setLong(2, apartamento.getIdMorador());
            sqlUpdate.setLong(3, apartamento.getIdCondominio());
            sqlUpdate.setLong(4, idApartamento);

            sqlUpdate.executeUpdate();
            sqlUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarApartamento(long idApartamento) {
        String countDependentesSql = "SELECT COUNT(*) FROM tb_gm_veiculo WHERE id_apartamento = ?";
        String deleteSql = "DELETE FROM tb_gm_apartamento WHERE id_apartamento = ?";

        try {
            PreparedStatement countStmt = conexao.prepareStatement(countDependentesSql);
            countStmt.setLong(1, idApartamento);
            ResultSet rs = countStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                System.out.println("Não é possível excluir o apartamento. Existem veículos associados.");
                return;
            }
            rs.close();
            countStmt.close();
            PreparedStatement deleteStmt = conexao.prepareStatement(deleteSql);
            deleteStmt.setLong(1, idApartamento);
            deleteStmt.execute();
            deleteStmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Apartamento> buscarTodosApartamentos(){
        String sql = "SELECT * FROM tb_gm_apartamento";
        List<Apartamento> lista = new ArrayList<Apartamento>();
        try {
            PreparedStatement sqlSelect = conexao.prepareStatement(sql);
            ResultSet rs = sqlSelect.executeQuery();

            while (rs.next()) {
                Apartamento apartamento = new Apartamento();
                apartamento.setIdApartamento(rs.getLong("id_apartamento"));
                apartamento.setNumeroApartamento(rs.getInt("numero_apartamento"));
                apartamento.setIdMorador(rs.getLong("id_morador"));
                apartamento.setIdCondominio(rs.getLong("id_condominio"));
                lista.add(apartamento);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return lista;
    }

    public Apartamento buscarApartamentoPorID(long idApartamento){
        String sql = "SELECT * FROM tb_gm_apartamento WHERE id_apartamento = ?";
        Apartamento apartamento = null;
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, idApartamento);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    apartamento = new Apartamento();
                    apartamento.setIdApartamento(rs.getLong("id_apartamento"));
                    apartamento.setNumeroApartamento(rs.getInt("numero_apartamento"));
                    apartamento.setIdMorador(rs.getLong("id_morador"));
                    apartamento.setIdCondominio(rs.getLong("id_condominio"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return apartamento;

    }

    public void fecharConexao(){
        try{
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
