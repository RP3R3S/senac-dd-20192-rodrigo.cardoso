package model.entity.lista01;

public abstract class Funcionario {

	private int id;
	private String nome;
	private String cpf;
	private char sexo;
	private int idade;
	private double salarioBruto;
	private Lotacao lotacao;
	private double descontoImpostoRenda;
	private double descontoPrevidencia;
	private double salarioBase;

	public Funcionario(int id, String nome, String cpf, char sexo, int idade, double salarioBruto, Lotacao lotacao) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.sexo = sexo;
		this.idade = idade;
		this.salarioBruto = salarioBruto;
		this.lotacao = lotacao;
		calcularSalarioBase();
	}

	public abstract double calcularSalario();

	private void calcularSalarioBase() {
		calcularDescontoIR();
		calcularDescontoPrevidencia();
		salarioBase = this.salarioBruto - (this.descontoImpostoRenda + this.descontoPrevidencia);
	}

	public void calcularDescontoIR() {
		double descontoImpostoRendaCalculado = 0;

		if (this.salarioBruto >= 1800 && this.salarioBruto <= 3000) {
			descontoImpostoRendaCalculado = this.salarioBruto * 0.1;
		} else if (this.salarioBruto > 3000) {
			descontoImpostoRendaCalculado = this.salarioBruto * 0.15;
		}
		this.descontoImpostoRenda = descontoImpostoRendaCalculado;
	}

	public void calcularDescontoPrevidencia() {
		if (this.idade < 45) {
			setDescontoPrevidencia(this.getSalarioBruto() * 0.12);
		} else if (this.salarioBruto > 3000) {
			setDescontoPrevidencia(this.getSalarioBruto() * 0.08);
		}
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
		calcularSalarioBase();
	}

	public double getSalarioBruto() {
		return salarioBruto;
	}

	public void setSalarioBruto(double salarioBruto) {
		this.salarioBruto = salarioBruto;
		this.calcularDescontoIR();
	}

	public double getDescontoImpostoRenda() {
		return descontoImpostoRenda;
	}

	public double getDescontoPrevidencia() {
		return descontoPrevidencia;
	}

	public void setDescontoPrevidencia(double descontoPrevidencia) {
		this.descontoPrevidencia = descontoPrevidencia;
	}

	public double getSalarioBase() {
		return salarioBase;
	}

	public void setSalarioBase(double salarioBase) {
		this.salarioBase = salarioBase;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Lotacao getLotacao() {
		return lotacao;
	}

	public void setLotacao(Lotacao lotacao) {
		this.lotacao = lotacao;
	}

	public void setDescontoImpostoRenda(double descontoImpostoRenda) {
		this.descontoImpostoRenda = descontoImpostoRenda;
	}

}
