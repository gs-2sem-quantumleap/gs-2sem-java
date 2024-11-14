package globalSolution.infra.dao;

import globalSolution.dominio.ContaDeEnergia;
import globalSolution.dominio.Morador;

import java.sql.*;
import java.time.LocalDate;

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
}
