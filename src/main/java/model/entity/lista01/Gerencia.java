package model.entity.lista01;

import java.util.ArrayList;

public class Gerencia extends Lotacao {
	private ArrayList<Operacional> operacionais;

	public Gerencia() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Gerencia(int id, String nome, Lotacao lotacaoSuperior, Funcionario responsavel,
			ArrayList<Operacional> operacionais) {
		super(id, nome, lotacaoSuperior, responsavel);
		this.operacionais = operacionais;
	}

	public ArrayList<Operacional> getOperacionais() {
		return operacionais;
	}

	public void setOperacionais(ArrayList<Operacional> operacionais) {
		this.operacionais = operacionais;
	}

}
