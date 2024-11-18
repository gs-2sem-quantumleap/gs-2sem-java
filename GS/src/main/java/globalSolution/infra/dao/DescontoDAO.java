package globalSolution.infra.dao;

import globalSolution.dominio.Desconto;
import globalSolution.dominio.RepositorioDesconto;

import java.sql.*;
import java.util.ArrayList;

public class DescontoDAO implements RepositorioDesconto {
    private Connection conexao;

    public DescontoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionarDesconto(Desconto desconto) {
        String sql = "INSERT INTO tb_gm_desconto (valor_desconto, descricao_desconto, data_desconto, id_apartamento) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id_desconto"})) {
            pstmt.setDouble(1, desconto.getValorDesconto());
            pstmt.setString(2, desconto.getDescricaoDesconto());
            pstmt.setDate(3, Date.valueOf(desconto.getDataDesconto()));
            pstmt.setLong(4, desconto.getIdApartamento());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    desconto.setIdDesconto(generatedKeys.getLong(1));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Desconto> buscandoTodosDescontos(){
        ArrayList<Desconto> descontos = new ArrayList<>();
        String sql = "SELECT * FROM tb_gm_desconto";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Desconto desconto = new Desconto();
                desconto.setIdDesconto(rs.getLong("id_desconto"));
                desconto.setValorDesconto(rs.getDouble("valor_desconto"));
                desconto.setDescricaoDesconto(rs.getString("descricao_desconto"));
                desconto.setDataDesconto(rs.getDate("data_desconto").toLocalDate());
                desconto.setIdApartamento(rs.getLong("id_apartamento"));
                descontos.add(desconto);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return descontos;
    }

    public void atualizarDesconto(long idDesconto, Desconto desconto) {
        String sql = "UPDATE tb_gm_desconto SET valor_desconto = ?, descricao_desconto = ?, " +
                "data_desconto = ?, id_apartamento = ? WHERE id_desconto = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setDouble(1, desconto.getValorDesconto());
            pstmt.setString(2, desconto.getDescricaoDesconto());

            java.sql.Date sqlDate = java.sql.Date.valueOf(desconto.getDataDesconto());
            pstmt.setDate(3, sqlDate);

            pstmt.setLong(4, desconto.getIdApartamento());
            pstmt.setLong(5, idDesconto);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerDesconto(long idDesconto){
        String sql = "DELETE FROM tb_gm_desconto WHERE id_desconto = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, idDesconto);
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
