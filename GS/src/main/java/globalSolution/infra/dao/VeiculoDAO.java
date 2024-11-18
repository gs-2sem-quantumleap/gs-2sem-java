package globalSolution.infra.dao;

import globalSolution.dominio.RepositorioVeiculo;
import globalSolution.dominio.Veiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO implements RepositorioVeiculo {

    private Connection conexao;

    public VeiculoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionarVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO tb_gm_veiculo (placa_veiculo, ano_veiculo, is_eletrico, id_apartamento) VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id_veiculo"})) {
            pstmt.setString(1, veiculo.getPlacaVeiculo());
            pstmt.setInt(2, veiculo.getAnoVeiculo());
            pstmt.setBoolean(3, veiculo.isEletrico());
            pstmt.setLong(4, veiculo.getIdApartamento());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    veiculo.setIdVeiculo(generatedKeys.getLong(1));
                } else {
                    throw new SQLException();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Veiculo buscarVeiculoPorId(Long id) {
        Veiculo veiculo = null;
        String sql = "SELECT * FROM tb_gm_veiculo WHERE id_veiculo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    veiculo = new Veiculo();
                    veiculo.setIdVeiculo(rs.getLong("id_veiculo"));
                    veiculo.setPlacaVeiculo(rs.getString("placa_veiculo"));
                    veiculo.setAnoVeiculo(rs.getInt("ano_veiculo"));
                    veiculo.setEletrico(rs.getBoolean("is_eletrico"));
                    veiculo.setIdApartamento(rs.getLong("id_apartamento"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculo;
    }

    public List<Veiculo> buscarVeiculosPorApartamento(Long idApartamento) {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM tb_gm_veiculo WHERE id_apartamento = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, idApartamento);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Veiculo veiculo = new Veiculo();
                    veiculo.setIdVeiculo(rs.getLong("id_veiculo"));
                    veiculo.setPlacaVeiculo(rs.getString("placa_veiculo"));
                    veiculo.setAnoVeiculo(rs.getInt("ano_veiculo"));
                    veiculo.setEletrico(rs.getBoolean("is_eletrico"));
                    veiculo.setIdApartamento(rs.getLong("id_apartamento"));
                    veiculos.add(veiculo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }

    public ArrayList<Veiculo> listarVeiculo() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM tb_gm_veiculo";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(rs.getLong("id_veiculo"));
                veiculo.setPlacaVeiculo(rs.getString("placa_veiculo"));
                veiculo.setAnoVeiculo(rs.getInt("ano_veiculo"));
                veiculo.setEletrico(rs.getBoolean("is_eletrico"));
                veiculo.setIdApartamento(rs.getLong("id_apartamento"));
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    public void atualizarVeiculo(long idVeiculo, Veiculo veiculo) {
        String sql = "UPDATE tb_gm_veiculo SET placa_veiculo = ?, ano_veiculo = ?, " +
                "is_eletrico = ?, id_apartamento = ? WHERE id_veiculo = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, veiculo.getPlacaVeiculo());
            pstmt.setInt(2, veiculo.getAnoVeiculo());
            pstmt.setBoolean(3, veiculo.isEletrico());
            pstmt.setLong(4, veiculo.getIdApartamento());
            pstmt.setLong(5, idVeiculo);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerVeiculo(Long idVeiculo) {
        String sqlDeleteVeiculo = "DELETE FROM tb_gm_veiculo WHERE id_veiculo = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sqlDeleteVeiculo)) {
            pstmt.setLong(1, idVeiculo);
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
