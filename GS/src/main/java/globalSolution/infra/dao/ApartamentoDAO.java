package globalSolution.infra.dao;

import globalSolution.dominio.Apartamento;
import globalSolution.dominio.ContaDeEnergia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ApartamentoDAO {
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
        String sql = "UPDATE tb_gm_apartamento SET numero_apartamento = ?, id_morador = ?, id_condominio = ?";

        try{
            PreparedStatement sqlUpdate = conexao.prepareStatement(sql);
            sqlUpdate.setInt(1, apartamento.getNumeroApartamento());
            sqlUpdate.setLong(2, apartamento.getIdMorador());
            sqlUpdate.setLong(3, apartamento.getIdCondominio());

            sqlUpdate.executeUpdate();
            sqlUpdate.close();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deletarApartamento(long idApartamento){
        String sql = "DELETE FROM tb_gm_apartamento WHERE id_apartamento = ?";

        try{
            PreparedStatement sqlDelete = conexao.prepareStatement(sql);
            sqlDelete.setLong(1, idApartamento);
            sqlDelete.execute();
            sqlDelete.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<ContaDeEnergia> buscarTodasAsContasDeEnergia(long idApartamento){
        String sql = "SELECT * FROM tb_gm_conta_energia WHERE id_apartamento = ?";
        List<ContaDeEnergia> listaDeContasDeEnergia = new ArrayList<ContaDeEnergia>();
        try {
            PreparedStatement sqlSelect = conexao.prepareStatement(sql);
            sqlSelect.setLong(1, idApartamento);

            ResultSet rs = sqlSelect.executeQuery();

        } catch (SQLException e){
            e.printStackTrace();
        }

        return listaDeContasDeEnergia;
    }

    public void fecharConexao(){
        try{
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
