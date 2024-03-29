package model.dao.lista01;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.dao.Banco;
import model.dao.BaseDAO;
import model.entity.lista01.Diretor;
import model.entity.lista01.Empregado;
import model.entity.lista01.EmpregadoOperacional;
import model.entity.lista01.Gerente;

public class EmpregadoDAO implements BaseDAO<Empregado> {

	public static final String DESCRICAO_TIPO_EMPREGADO_OPERACIONAL = "Operacional";
	public static final String DESCRICAO_TIPO_EMPREGADO_DIRETOR = "Diretor";
	public static final String DESCRICAO_TIPO_EMPREGADO_GERENTE = "Gerente";
	public static final String TIPO_EMPREGADO_OPERACIONAL = "O";
	public static final String TIPO_EMPREGADO_DIRETOR = "D";
	public static final String TIPO_EMPREGADO_GERENTE = "G";

	public Empregado salvar(Empregado emp) {
		String sql = " INSERT INTO EMPREGADO (NOME, CPF, SEXO, "
				+ " IDADE, SALARIOBRUTO, DESCONTOIR, DESCONTOPREVIDENCIA, " + " SALARIOBASE, SALARIO, COMISSAO, TIPO) "
				+ " VALUES(?,?,?,?,?,?,?,?,?,?,?) ";
		Connection conn = Banco.getConnection();
		PreparedStatement stmt = Banco.getPreparedStatement(conn, sql, PreparedStatement.RETURN_GENERATED_KEYS);

		try {
			stmt.setString(1, emp.getNome());
			stmt.setString(2, emp.getCpf());
			stmt.setString(3, emp.getSexo() + "");
			stmt.setInt(4, emp.getIdade());
			stmt.setDouble(5, emp.getSalarioBruto());
			stmt.setDouble(6, emp.getDescontoImpostoRenda());
			stmt.setDouble(7, emp.getDescontoPrevidencia());
			stmt.setDouble(8, emp.getSalarioBase());
			stmt.setDouble(9, emp.calcularSalario());

			if (emp instanceof Gerente) {
				Gerente ger = (Gerente) emp;
				stmt.setDouble(10, ger.getComissao());
				stmt.setString(11, TIPO_EMPREGADO_GERENTE);
			} else if (emp instanceof Diretor) {
				Diretor dir = (Diretor) emp;
				stmt.setDouble(10, dir.getComissao());
				stmt.setString(11, TIPO_EMPREGADO_DIRETOR);
			} else if (emp instanceof EmpregadoOperacional) {
				stmt.setDouble(10, 0);
				stmt.setString(11, TIPO_EMPREGADO_OPERACIONAL);
			}
			stmt.execute();

			ResultSet rs = stmt.getGeneratedKeys();
			if (rs.next()) {
				emp.setId(rs.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir novo empregado.");
			System.out.println("Erro: " + e.getMessage());
		}

		return emp;
	}

	public boolean excluir(int id) {
		Connection conexao = Banco.getConnection();
		Statement statement = Banco.getStatement(conexao);
		String sql = " DELETE FROM EMPREGADO WHERE ID = " + id;

		int quantidadeRegistrosExcluidos = 0;
		try {
			quantidadeRegistrosExcluidos = statement.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir empregado.");
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(statement);
			Banco.closeConnection(conexao);
		}

		return quantidadeRegistrosExcluidos > 0;
	}

	public boolean alterar(Empregado entidade) {
		// TODO Auto-generated method stub
		return false;
	}

	public Empregado consultarPorId(int id) {
		String sql = " SELECT * FROM EMPREGADO E " + " WHERE E.ID = " + id;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		Empregado emp = null;
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				String tipo = rs.getString("tipo");
				String nome = rs.getString("nome");
				char sexo = rs.getString("sexo").charAt(0);
				String cpf = rs.getString("cpf");
				int idade = rs.getInt("idade");
				double salarioBruto = rs.getDouble("salariobruto");
				double comissao = rs.getDouble("comissao");

				if (tipo.equals(TIPO_EMPREGADO_DIRETOR)) {
					emp = new Diretor(nome, cpf, sexo, idade, salarioBruto, comissao);
				} else if (tipo.equals(TIPO_EMPREGADO_GERENTE)) {
					emp = new Gerente(nome, cpf, sexo, idade, salarioBruto, comissao);
				} else if (tipo.equals(TIPO_EMPREGADO_OPERACIONAL)) {
					emp = new EmpregadoOperacional(nome, cpf, sexo, idade, salarioBruto);
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao consultar empregado por id = " + id);
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}
		return emp;
	}

	public ArrayList<Empregado> consultarTodos() {
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM EMPREGADO";
		ResultSet resultadoDaConsulta = null;
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		ArrayList<Empregado> empregados = new ArrayList<Empregado>();

		try {
			resultadoDaConsulta = stmt.executeQuery();
			while (resultadoDaConsulta.next()) {
				Empregado empregadoBuscado = construirDoResultSet(resultadoDaConsulta);
				empregados.add(empregadoBuscado);
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao consultar empregados cadastrados ");
			System.out.println("Erro: " + ex.getMessage());
		} finally {
			Banco.closeResultSet(resultadoDaConsulta);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return empregados;
	}

	private Empregado construirDoResultSet(ResultSet rs) {
		Empregado emp = null;

		try {
			int id = rs.getInt("id");
			String tipo = rs.getString("tipo");
			String nome = rs.getString("nome");
			String cpf = rs.getString("cpf");
			int idade = rs.getInt("idade");
			char sexo = rs.getString("sexo").charAt(0);
			double salarioBruto = rs.getDouble("salarioBruto");
			double comissao = rs.getDouble("comissao");

			switch (tipo) {
			case TIPO_EMPREGADO_OPERACIONAL:
				emp = new EmpregadoOperacional(nome, cpf, sexo, idade, salarioBruto);
				break;

			case TIPO_EMPREGADO_GERENTE:
				emp = new Gerente(nome, cpf, sexo, idade, salarioBruto, comissao);
				break;

			case TIPO_EMPREGADO_DIRETOR:
				emp = new Diretor(nome, cpf, sexo, idade, salarioBruto, comissao);
				break;
			}
			emp.setId(id);

		} catch (SQLException e) {
			System.out.println("Erro ao construir empregado do ResultSet ");
			System.out.println("Erro: " + e.getMessage());
		}

		return emp;
	}

	public boolean temCPFCadastrado(String cpf) {
		String sql = " SELECT ID FROM EMPREGADO E " + " WHERE E.CPF = " + cpf;
		Connection conn = Banco.getConnection();
		Statement stmt = Banco.getStatement(conn);

		ResultSet rs = null;
		boolean temCPFCadastrado = false;
		try {
			rs = stmt.executeQuery(sql);
			temCPFCadastrado = rs.next();

		} catch (SQLException e) {
			System.out.println("Erro ao verificar empregado por CPF " + cpf);
			System.out.println("Erro: " + e.getMessage());
		} finally {
			Banco.closeResultSet(rs);
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conn);
		}

		return temCPFCadastrado;
	}
}