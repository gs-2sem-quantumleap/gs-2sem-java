package globalSolution.infra.dao;

import globalSolution.dominio.ContaDeEnergia;
import globalSolution.dominio.Morador;
import globalSolution.dominio.RepositorioContaDeEnergia;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContaDeEnergiaDAO implements RepositorioContaDeEnergia{

    private Connection conexao;

    public ContaDeEnergiaDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void adicionarConta(ContaDeEnergia contaDeEnergia) {
        String sqlBuscarIdApartamento = "SELECT id_apartamento FROM tb_gm_apartamento WHERE numero_apartamento = ?";
        String sqlInserirConta = "INSERT INTO tb_gm_conta_energia (valor_conta, data_conta, consumo_kwh, id_apartamento) VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection()) {
            long idApartamento = 0;
            try (PreparedStatement pstmtBuscarId = conn.prepareStatement(sqlBuscarIdApartamento)) {
                pstmtBuscarId.setLong(1, contaDeEnergia.getIdApartamento());
                try (ResultSet rs = pstmtBuscarId.executeQuery()) {
                    if (rs.next()) {
                        idApartamento = rs.getLong("id_apartamento");
                    } else {
                        throw new SQLException("Apartamento não encontrado");
                    }
                }
            }
            try (PreparedStatement pstmt = conn.prepareStatement(sqlInserirConta, new String[] {"id_conta_energia"})) {
                pstmt.setDouble(1, contaDeEnergia.getValorConta());
                pstmt.setDate(2, Date.valueOf(contaDeEnergia.getDataConta()));
                pstmt.setDouble(3, contaDeEnergia.getConsumoKwh());
                pstmt.setLong(4, idApartamento);
                pstmt.executeUpdate();

                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        contaDeEnergia.setIdContaDeEnergia(generatedKeys.getLong(1));
                    } else {
                        throw new SQLException("Falha ao obter o ID gerado para a conta de energia.");
                    }
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
        String sql = "UPDATE tb_gm_conta_energia SET valor_conta = ?, data_conta = ?, " +
                "consumo_kwh = ?, id_apartamento = ? WHERE id_conta_energia = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setDouble(1, contaDeEnergia.getValorConta());
            java.sql.Date sqlDate = java.sql.Date.valueOf(contaDeEnergia.getDataConta());
            pstmt.setDate(2, sqlDate);

            pstmt.setDouble(3, contaDeEnergia.getConsumoKwh());
            pstmt.setLong(4, contaDeEnergia.getIdApartamento());
            pstmt.setLong(5, idConta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerConta(Long idConta) {
        String sql = "DELETE FROM tb_gm_conta_energia WHERE id_conta_energia = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, idConta);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ContaDeEnergia> buscarContaPorCpf(String cpf) {
        String sql = """
        SELECT 
            cl.id_conta_energia, 
            cl.valor_conta, 
            cl.data_conta, 
            cl.consumo_kwh, 
            cl.id_apartamento
        FROM 
            TB_GM_MORADOR m
        JOIN 
            TB_GM_APARTAMENTO a ON m.ID_MORADOR = a.ID_MORADOR
        JOIN 
            TB_GM_CONTA_ENERGIA cl ON a.ID_APARTAMENTO = cl.ID_APARTAMENTO
        WHERE 
            m.CPF = ?
    """;

        List<ContaDeEnergia> contas = new ArrayList<>();

        try (
                Connection conn = new ConnectionFactory().getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, cpf);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    ContaDeEnergia conta = new ContaDeEnergia(
                            rs.getDouble("valor_conta"),
                            rs.getDate("data_conta").toLocalDate(),
                            rs.getDouble("consumo_kwh"),
                            rs.getLong("id_apartamento")
                    );
                    conta.setIdContaDeEnergia(rs.getLong("id_conta_energia"));
                    contas.add(conta);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar contas de energia pelo CPF: " + cpf);
            e.printStackTrace();
        }

        return contas;
    }




    public void fecharConexao(){
        try{
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
