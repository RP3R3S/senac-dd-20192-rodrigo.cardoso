package controller.aula05;

import model.dao.aula05.ClienteDAO;
import model.entity.aula05.Cliente;
import model.entity.aula05.Endereco;

public class ClienteController {

	// Para SALVAR
	// Os campos Nome (>3), Sobrenome (>3), CPF (11) do Cliente devem estar
	// preenchidos
	// O cliente deve possuir endereço
	// Depois que Salvar, Habilitar Botão "Adicionar Telefone" para adição de
	// TElefone

	private ClienteDAO dao = new ClienteDAO();

	public String validarCamposSalvar(String nomeDigitado, String sobrenomeDigitado, String cpfDigitado,
			Endereco enderecoSelecionado) {

		String mensagem = "";

		if (nomeDigitado.isEmpty() || nomeDigitado.trim().length() < 3) {
			mensagem += "Nome deve possuir pelo menos 3 letras \n";
		}

		if (sobrenomeDigitado.isEmpty() || sobrenomeDigitado.trim().length() < 3) {
			mensagem += "Sobrenome deve possuir pelo menos 3 letras \n";
		}

		if (cpfDigitado.isEmpty() || cpfDigitado.trim().length() != 11) {
			mensagem += "CPF deve possuir 11 d�gitos \n";
		}

		if (enderecoSelecionado == null) {
			mensagem += "Selecione um endere�o \n";
		}

		return mensagem;
	}

	public Cliente salvar(Cliente novoCliente) {
		return dao.salvar(novoCliente);
	}

}
