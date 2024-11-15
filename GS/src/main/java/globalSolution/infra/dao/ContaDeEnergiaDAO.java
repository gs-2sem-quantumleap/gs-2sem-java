package globalSolution.infra.dao;

import globalSolution.dominio.ContaDeEnergia;
import globalSolution.dominio.Morador;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContaDeEnergiaDAO {

    private Connection conexao;

    public ContaDeEnergiaDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionarConta(ContaDeEnergia contaDeEnergia) {
        String sql = "INSERT INTO tb_gm_conta_energia (valor_conta, data_conta, consumo_kwh, id_apartamento) VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id_conta_energia" +
                     ""})) {
            pstmt.setDouble(1, contaDeEnergia.getValorConta());
            pstmt.setDate(2, Date.valueOf(contaDeEnergia.getDataConta()));
            pstmt.setDouble(3, contaDeEnergia.getConsumoKwh());
            pstmt.setLong(4, contaDeEnergia.getIdApartamento());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    contaDeEnergia.setIdContaDeEnergia(generatedKeys.getLong(1));
                } else {
                    throw new SQLException();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ContaDeEnergia buscarContaPorId(Long id) {
        ContaDeEnergia contaDeEnergia = null;
        String sql = "SELECT * FROM tb_gm_conta_energia WHERE id_conta_energia = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    contaDeEnergia = new ContaDeEnergia();
                    contaDeEnergia.setIdContaDeEnergia(rs.getLong("id_conta_energia"));
                    contaDeEnergia.setValorConta(rs.getDouble("valor_conta"));
                    contaDeEnergia.setDataConta(rs.getDate("data_conta").toLocalDate());
                    contaDeEnergia.setConsumoKwh(rs.getDouble("consumo_kwh"));
                    contaDeEnergia.setIdApartamento(rs.getLong("id_apartamento"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contaDeEnergia;
    }

    public ArrayList<ContaDeEnergia> listarContas() {
        ArrayList<ContaDeEnergia> contas = new ArrayList<>();
        String sql = "SELECT * FROM tb_gm_conta_energia";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                ContaDeEnergia contaDeEnergia = new ContaDeEnergia();
                contaDeEnergia.setIdContaDeEnergia(rs.getLong("id_conta_energia"));
                contaDeEnergia.setValorConta(rs.getDouble("valor_conta"));
                contaDeEnergia.setDataConta(rs.getDate("data_conta").toLocalDate());
                contaDeEnergia.setConsumoKwh(rs.getDouble("consumo_kwh"));
                contaDeEnergia.setIdApartamento(rs.getLong("id_apartamento"));
                contas.add(contaDeEnergia);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return contas;
    }

    public void atualizarConta(long idConta, ContaDeEnergia contaDeEnergia) {
        String sql = "UPDATE tb_gm_morador SET valor_conta = ?, data_conta = ?, " +
                "consumo_kwh = ? WHERE id_conta_energia = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, contaDeEnergia.getNomeMorador());
            pstmt.setString(2, contaDeEnergia.getCpf());
            pstmt.setString(3, contaDeEnergia.getEmail());
            pstmt.setString(4, contaDeEnergia.getTelefone());
            pstmt.setLong(5, idConta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }






}
