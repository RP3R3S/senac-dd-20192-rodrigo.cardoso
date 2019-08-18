package model.dao.lista01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.entity.lista01.Diretoria;
import model.entity.lista01.Gerencia;
import model.entity.lista01.Lotacao;
import model.entity.lista01.Operacional;

public class LotacaoDAO implements BaseDAO<Lotacao> {

//	@Override
	public Lotacao salvar(Lotacao novaLotacao) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO LOTACAO(NOME, SIGLA, IDLOTACAO_SUPERIOR, idfuncionario_responsavel) "
				+ " VALUES (?,?,?,?)";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql, PreparedStatement.RETURN_GENERATED_KEYS);
		try {
			stmt.setString(1, novaLotacao.getNome());
			if (novaLotacao instanceof Diretoria) {
				Diretoria novaDiretoria;
				novaDiretoria = (Diretoria) novaLotacao;
				stmt.setString(2, novaDiretoria.getSigla());
			}
			if (novaLotacao instanceof Diretoria) {
				stmt.setInt(3, 0);
			} else {
				stmt.setInt(3, novaLotacao.getLotacaoSuperior().getId());
			}
			stmt.setInt(4, novaLotacao.getResponsavel().getId());

			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				int idGerado = rs.getInt(1);
				novaLotacao.setId(idGerado);
			}

			LotacaoDAO lotacaoDAO = new LotacaoDAO();
			if (novaLotacao instanceof Diretoria) {
				Diretoria novaDiretoria;
				novaDiretoria = (Diretoria) novaLotacao;
				lotacaoDAO.subordinarGerencias(novaDiretoria, novaDiretoria.getGerencias());

			} else {
				Gerencia novaGerencia;
				novaGerencia = (Gerencia) novaLotacao;
				lotacaoDAO.subordinarOperacionais(novaGerencia, novaGerencia.getOperacionais());
			}

		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo endereço.");
			System.out.println("Erro: " + e.getMessage());
		}

		return novaLotacao;
	}

	private void subordinarOperacionais(Gerencia novaGerencia, ArrayList<Operacional> operacionais) {
		// TODO Auto-generated method stub

	}

	private void subordinarGerencias(Diretoria novaDiretoria, ArrayList<Gerencia> gerencias) {
		// TODO Auto-generated method stub

	}

//	@Override
	public boolean excluir(int id) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public boolean alterar(Lotacao entidade) {
		// TODO Auto-generated method stub
		return false;
	}

//	@Override
	public Lotacao consultarPorId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

//	@Override
	public ArrayList<Lotacao> consultarTodos() {
		// TODO Auto-generated method stub
		return null;
	}

}
