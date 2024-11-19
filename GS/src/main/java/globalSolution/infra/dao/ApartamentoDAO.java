package globalSolution.infra.dao;

import globalSolution.dominio.*;

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

    public List<ConsumoMorador> buscarDadosConsumoEnergiaTotal() {
        String sql = """
                SELECT 
                m.nome_morador, 
                a.numero_apartamento, 
                COALESCE(SUM(c.consumo_kwh), 0) AS consumo_total_kwh
                FROM tb_gm_apartamento a
                JOIN tb_gm_morador m ON a.id_morador = m.id_morador
                LEFT JOIN tb_gm_conta_energia c ON a.id_apartamento = c.id_apartamento
                GROUP BY m.nome_morador, a.numero_apartamento
                ORDER BY a.numero_apartamento
                """;

        List<ConsumoMorador> resultado = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String nomeMorador = rs.getString("nome_morador");
                int numeroApartamento = rs.getInt("numero_apartamento");
                double consumoTotalKwh = rs.getDouble("consumo_total_kwh");

                // Cria uma nova instância de ConsumoMorador
                ConsumoMorador consumo = new ConsumoMorador(nomeMorador, numeroApartamento, consumoTotalKwh);
                resultado.add(consumo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }


    public List<ConsumoMorador> buscarTop3MenoresConsumos() {
        String sql = """
        SELECT 
            m.nome_morador, 
            a.numero_apartamento, 
            COALESCE(SUM(c.consumo_kwh), 0) AS consumo_total_kwh
        FROM tb_gm_apartamento a
        JOIN tb_gm_morador m ON a.id_morador = m.id_morador
        LEFT JOIN tb_gm_conta_energia c ON a.id_apartamento = c.id_apartamento
        GROUP BY m.nome_morador, a.numero_apartamento
        HAVING SUM(c.consumo_kwh) > 0
        ORDER BY consumo_total_kwh ASC
        FETCH FIRST 3 ROWS ONLY
    """;

        List<ConsumoMorador> resultado = new ArrayList<>();
        try (PreparedStatement pstmt = conexao.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String nomeMorador = rs.getString("nome_morador");
                int numeroApartamento = rs.getInt("numero_apartamento");
                double consumoTotalKwh = rs.getDouble("consumo_total_kwh");
                ConsumoMorador consumo = new ConsumoMorador(nomeMorador, numeroApartamento, consumoTotalKwh);
                resultado.add(consumo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }






    public void fecharConexao(){
        try{
            conexao.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
