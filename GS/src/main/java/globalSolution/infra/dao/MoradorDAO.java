package globalSolution.infra.dao;

import globalSolution.dominio.Morador;
import globalSolution.dominio.RepositorioMorador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MoradorDAO implements RepositorioMorador {

    private Connection conexao;

    public MoradorDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }


    public void adicionarMorador(Morador morador) {
        String sql = "INSERT INTO tb_gm_morador (nome_morador, cpf, email, telefone) VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, new String[] {"id_morador"})) {
            pstmt.setString(1, morador.getNomeMorador());
            pstmt.setString(2, morador.getCpf());
            pstmt.setString(3, morador.getEmail());
            pstmt.setString(4, morador.getTelefone());
            pstmt.executeUpdate();

            try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    morador.setIdMorador(generatedKeys.getLong(1));
                } else {
                    throw new SQLException();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Morador buscarMoradorPorId(Long id) {
        Morador morador = null;
        String sql = "SELECT * FROM tb_gm_morador WHERE id_morador = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setLong(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    morador = new Morador();
                    morador.setIdMorador(rs.getLong("id_morador"));
                    morador.setNomeMorador(rs.getString("nome_morador"));
                    morador.setCpf(rs.getString("cpf"));
                    morador.setEmail(rs.getString("email"));
                    morador.setTelefone(rs.getString("telefone"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return morador;
    }

    public ArrayList<Morador> listarMorador() {
        ArrayList<Morador> clientes = new ArrayList<>();
        String sql = "SELECT * FROM tb_gm_morador";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Morador morador = new Morador();
                morador.setIdMorador(rs.getLong("id_morador"));
                morador.setNomeMorador(rs.getString("nome_morador"));
                morador.setCpf(rs.getString("cpf"));
                morador.setEmail(rs.getString("email"));
                morador.setTelefone(rs.getString("telefone"));
                clientes.add(morador);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public void atualizarMorador(long idMorador, Morador morador) {
        String sql = "UPDATE tb_gm_morador SET nome_morador = ?, cpf = ?, " +
                "email = ?, telefone = ? WHERE id_morador = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, morador.getNomeMorador());
            pstmt.setString(2, morador.getCpf());
            pstmt.setString(3, morador.getEmail());
            pstmt.setString(4, morador.getTelefone());
            pstmt.setLong(5, idMorador);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerMorador(Long idMorador) {
        String sqlDeleteCliente = "DELETE FROM tb_gm_morador WHERE id_morador = ?";

        try (PreparedStatement pstmt = conexao.prepareStatement(sqlDeleteCliente)) {
            pstmt.setLong(1, idMorador);
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

    public Morador buscarMoradorPorCpf(String cpf) {
        Morador morador = new Morador();
        String sqlSelectCliente = "SELECT * FROM tb_gm_morador where cpf = ?";
        try {
            PreparedStatement comandoDeSelecao = conexao.prepareStatement(sqlSelectCliente);
            comandoDeSelecao.setString(1, cpf);

            ResultSet rs = comandoDeSelecao.executeQuery();
            if (rs.next()) {
                morador.setIdMorador(rs.getLong("id_morador"));
                morador.setNomeMorador(rs.getString("nome_morador"));
                morador.setCpf(rs.getString("cpf"));
                morador.setEmail(rs.getString("email"));
                morador.setTelefone(rs.getString("telefone"));
            } else {
                morador = null;
            }


        } catch(SQLException e){
            e.printStackTrace();
        }

        return morador;
    }

}
