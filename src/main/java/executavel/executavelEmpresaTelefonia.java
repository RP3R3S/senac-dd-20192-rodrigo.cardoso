package executavel;

import java.util.ArrayList;

import model.entity.Cliente;
import model.entity.Endereco;
import model.entity.Telefone;

public class executavelEmpresaTelefonia {

	public static void main(String[] args) {

		Endereco end1 = new Endereco("Rua da Tim", "100", "Campeche", "Florianópolis", "Santa Catarina", "11111-111");
		Endereco end2 = new Endereco("Rua da Oi", "200", "Centro", "Florianópolis", "Santa Catarina", "22222-222");
		Endereco end3 = new Endereco("Rua da Claro", "300", "Ingleses", "Florianópolis", "Santa Catarina", "33333-333");
		Endereco end4 = new Endereco("Rua da Vivo", "400", "Barreiros", "São José", "Santa Catarina", "44444-444");
		Endereco end5 = new Endereco("Rua da Nextel", "500", "Pantanal", "Florianópolis", "Santa Catarina",
				"55555-555");

		ArrayList<Telefone> telefones = criarTelefones();

		Cliente cliente1 = new Cliente("Rodrigo", "Cardoso", "111.111.111-11", telefones, end1);
		Cliente cliente2 = new Cliente("Vanderlei", "Siqueira", "222.222.222-22", telefones, end2);
		Cliente cliente3 = new Cliente("Sebastiana", "Regina", "333.333.333-33", telefones, end3);
		Cliente cliente4 = new Cliente("Priscila", "Domingues", "444.444.444-44", telefones, end4);
		Cliente cliente5 = new Cliente("Melody", "Farias", "555.555.555-55", telefones, end5);

		ArrayList<Cliente> clientes = new ArrayList<Cliente>();
		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);
		clientes.add(cliente4);
		clientes.add(cliente5);

		for (Cliente c : clientes) {
			System.out.println("*******************************************************");
			System.out.println(c);
		}
		System.out.println("*******************************************************");

	}

	private static ArrayList<Telefone> criarTelefones() {
		ArrayList<Telefone> telefones = new ArrayList<Telefone>();
		telefones.add(new Telefone(0, 0, "55", "48", "1111-1111", "Móvel", true));
		telefones.add(new Telefone(0, 0, "55", "48", "2222-2222", "Fixo", true));
		telefones.add(new Telefone(0, 0, "55", "48", "3333-3333", "Satélite", true));
		telefones.add(new Telefone(0, 0, "55", "48", "4444-4444", "Móvel", true));
		telefones.add(new Telefone(0, 0, "55", "48", "5555-5555", "Fixo", true));

		return telefones;
	}

}
