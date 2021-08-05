package Lab3_VANDERSON;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;


/**
 * Interface com menus texto para manipular uma agenda de contatos.
 * 
 * @author vanderson
 *
 */
public class MainAgenda {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();

		System.out.println("Carregando agenda inicial");
		try {
			/*
			 * Essa é a maneira de lidar com possíveis erros por falta do arquivo. 
			 */
			carregaAgenda("agenda_inicial.csv", agenda);
		} catch (FileNotFoundException e) {
			System.err.println("Arquivo não encontrado: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Erro lendo arquivo: " + e.getMessage());
		}

		Scanner scanner = new Scanner(System.in);
		String escolha = "";
		while (true) {
			escolha = menu(scanner);
			comando(escolha, agenda, scanner);
		}

	}

	/**
	 * Exibe o menu e captura a escolha do/a usuário/a.
	 * 
	 * @param scanner Para captura da opção do usuário.
	 * @return O comando escolhido.
	 */
	private static String menu(Scanner scanner) {
		System.out.println(
				"\n---\nMENU\n" + 
						"(C)-Cadastrar Contato\n" + 
						"(RC)-Remover Contato\n" + 
						"(L)-Listar Contatos\n" + 
						"(E)-Exibir Contato\n" + 
						"(F)-Listar Favoritos\n" +
						"(A)-Adicionar Favorito\n" +
						"(RF)-Remove favorito\n" +
						"(T)-Tags\n" +
						"(RE)-Remover tags\n" +
						"(CN)-Consultar por nome\n" +
						"(CS)-Consultar por sobrenome\n" +
						"(CT)-Consultar por telefone\n" +
						"(S)l-Sair\n" + 
						"\n" + 
						"Opção> ");
		return scanner.next().toUpperCase();
	}

	/**
	 * Interpreta a opção escolhida por quem está usando o sistema.
	 * 
	 * @param opcao   Opção digitada.
	 * @param agenda  A agenda que estamos manipulando.
	 * @param scanner Objeto scanner para o caso do comando precisar de mais input.
	 */
	private static void comando(String opcao, Agenda agenda, Scanner scanner) {
		switch (opcao) {
		case "C":
			cadastraContato(agenda, scanner);
			break;
		case "RC":
			removerContato(agenda, scanner);
			break;
		case "L":
			listaContatos(agenda);
			break;
		case "E":
			exibeContato(agenda, scanner);
			break;
		case "F":
			favoritos(agenda);
			break;
		case "RF":
			removeFavoritos(agenda, scanner);
			break;
		case "A":
			addFavoritos(agenda, scanner);
			break;
		case "T":
			addTags(agenda, scanner);
			break;
		case "RE":
			removeTags(agenda, scanner);
			break;
		case "ED":
			editaTelefone(agenda, scanner);
			break;
		case "CN":
			consultaNome(agenda, scanner);
			break;
		case "CS":
			consultaSobrenome(agenda, scanner);
			break;
		case "CT":
			consultaTelefone(agenda, scanner);
			break;
		case "S":
			sai();
			break;
		default:
			System.out.println("Opção inválida!");
		}
	}

//______________________listar contatos______________________________________
	
	/**
	 * Imprime lista de contatos da agenda.
	 * 
	 * @param agenda A agenda sendo manipulada.
	 */
	private static void listaContatos(Agenda agenda) {
		System.out.println("\nLista de contatos: ");
		Object[] contatos = agenda.getContatos(); 
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				Contato contato = (Contato) contatos[i];
				System.out.println(formataContato(i, contato));
			}
		}
	}
	
	/**
	 * Formata um contato para impressão na interface. 
	 * 
	 * @param posicao A posição do contato (que é exibida)/
	 * @param contato O contato a ser impresso.
	 * @return A String formatada.
	 */
	
	private static String formataContato(int posicao, Contato contato) {
		return posicao+1 + " - " + contato.getNome()+" "+contato.getSobrenome();
	}


//______________________exibir os contatos__________________________________________
	
	/**
	 * Imprime os detalhes de um dos contatos da agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para capturar qual contato.
	 */
	private static void exibeContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual contato> ");
		int posicao = scanner.nextInt();
		if (agenda.semContato(posicao)==true) {
			System.out.println("POSIÇÃO INVÁLIDA!");
			return ;
		}
		if(agenda.verificaFavorito(posicao)==true) {
			Object contato = agenda.getContato(posicao);
			Contato ct = (Contato) contato;
			ct.exibiStringTag(ct);
			System.out.println("Dados do contato:\n" +"❤️ "+formataExibicaoContato(ct));
		}else {
			Object contato = agenda.getContato(posicao);
			Contato ct = (Contato) contato;
			System.out.println("Dados do contato:\n" +formataExibicaoContato(ct));
		}
	}
	
	private static String formataExibicaoContato(Contato contato) {
		return contato.getNome()+" "+contato.getSobrenome()+"\n"+contato.getTelefone()+"\n"+contato.exibiStringTag(contato);
	}
	
//________________________cadastrar e remover os contatos____________________________
	
	/**
	 * Cadastra um contato na agenda. 
	 * 
	 * @param agenda A agenda.
	 * @param scanner Scanner para pedir informações do contato.
	 */
	
	private static void cadastraContato(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosição na agenda> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		/**
		 * Analisa se a posição escolhida é entre 1 e 100, caso contrário a posição é inválida e volta o menu
		 */
		if (agenda.posicaoErrada(posicao)==true) {
			return ;
		}
		System.out.print("\nNome> ");
		String nome = scanner.nextLine();
		/**
		 * Caso o nome seja vazio o cadastro deve retornar um erro de nome inválido
		 */
		if (nome.isEmpty()){
			throw new IllegalArgumentException("CONTATO INVALIDO");
			
		}
		System.out.print("\nSobrenome> ");
		String sobrenome = scanner.nextLine();
		/**se já existe um contato
		 * 
		 */
		
		if (agenda.jaExiste(nome, sobrenome)==true) {
			throw new IllegalArgumentException("CONTATO JA CADASTRADO");
		}
		System.out.print("\nTelefone> ");
		String telefone = scanner.nextLine();
		/**
		 * Caso o telefone seja vazio será retornado um erro de contato inválido
		 */
		if(telefone.isEmpty()) {
			throw new IllegalArgumentException("CONTATO INVALIDO");
		}
		
		agenda.cadastraContato(posicao, nome, sobrenome, telefone);
		System.out.print("\nCADASTRO REALIZADO>");
	}
	
	/**
	 * recebe a posição desejada para remover o contato que vai ser removido na agenda
	 * @param agenda
	 * @param scanner
	 */
	private static void removerContato(Agenda agenda,Scanner scanner) {
		System.out.print("\nPosição na agenda> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		/**
		 * Analisa se a posição escolhida é entre 1 e 100, caso contrário a posição é inválida e volta o menu
		 */
		if (agenda.posicaoErrada(posicao)==true) {
			return ;
		}
		agenda.removeContato(posicao);
		
	}
	
//________________________adicionar aos favoritos_____________________________________
	
	/**
	 * recebe a posição de um contato na agenda e coloca na posição exigida pelo usuário o contato em favoritos
	 * @param agenda
	 * @param scanner
	 */
	private static void addFavoritos(Agenda agenda, Scanner scanner ){
		System.out.print("\nContato> ");
		int posicaoAgenda = scanner.nextInt();
		System.out.print("\nPosicao> ");
		int posicaoFavoritos = scanner.nextInt();
		if (agenda.jaExisteFavorito(posicaoAgenda)==true) {
			throw new IllegalArgumentException("CONTATO JA É FAVORITO");
		}
		agenda.ehFavorito(posicaoAgenda, posicaoFavoritos);
		System.out.println("\nCONTATO FAVORITADO NA POSIÇÃO "+posicaoFavoritos+"!");
	}
	
	private static void removeFavoritos(Agenda agenda, Scanner scanner) {
		System.out.print("\nPosicao> ");
		int posicaoFavoritos = scanner.nextInt();
		agenda.removeFavorito(posicaoFavoritos);
	}
	
	/**
	 * Passa pelo array de favoritos e imprime os contatos favoritos
	 * @param agenda
	 */
	
	private static void favoritos(Agenda agenda) {
		Object[] favoritos = agenda.getContatosFavoritos(); 
		for (int i = 0; i < favoritos.length; i++) {
			if (favoritos[i] != null) {
				Contato favorito = (Contato) favoritos[i];
				System.out.println(formataContatoFavorito(i, favorito));
			}
		}
	}
	
	/**
	 * formata a saída do contato favorito
	 * @param posicao
	 * @param contato
	 * @return posição - nome sobrenome
	 */
	
	private static String formataContatoFavorito(int posicao, Contato contato) {
		return posicao+1 + " - " + contato.getNome()+" "+contato.getSobrenome();
	}
	
//____________________________adiciona tags_____________________________________
	/**
	 * adiciona a tag que vai receber no main e adiciona a tag no array do contato que tá na agenda 
	 * @param agenda
	 * @param scanner
	 */
	
	private static void addTags(Agenda agenda, Scanner scanner) {
		scanner.nextLine();
		System.out.print("\nContato> ");
		String posicaoContatosAgenda = scanner.nextLine();
		System.out.print("Tag> ");
		String tag = scanner.next();
		System.out.print("\nPosicao tag> ");
		int posicaoTag = scanner.nextInt();
		agenda.addTag(posicaoContatosAgenda, tag, posicaoTag);
	}

//____________________________remover tags_____________________________
	/**
	 * recebe a posição o contato na agenda e escolhe qual tag deste contato o usuário quer remover
	 * @param agenda
	 * @param scanner
	 */
	
	private static void removeTags(Agenda agenda,Scanner scanner) {
		System.out.print("\nQual a posicao do contato que você quer remover a tag> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		if (agenda.contatoInvalido(posicao)==true) {
			return ;
		}
		Contato contato = (Contato) agenda.getContato(posicao);
		System.out.print("\nDigite a tag que deseja remover> ");
		String tag = scanner.next(); 
		contato.removeTag(tag);
	}
	
//____________________________Edita telefone___________________________
	/**
	 * Edita o telefone de um usuário da agenda pela sua posição
	 * @param agenda
	 * @param scanner
	 */
	
	private static void editaTelefone(Agenda agenda, Scanner scanner) {
		System.out.print("\nQual a posição do contato> ");
		int posicao = scanner.nextInt();
		scanner.nextLine();
		if (agenda.contatoInvalido(posicao)==true) {
			return ;
		}
		Contato contato = (Contato) agenda.getContato(posicao);
		System.out.print("\nQual o novo telefone> ");
		String telefone = scanner.nextLine();
		contato.setTelefone(telefone);
		System.out.println("Telefone editado com sucesso");
	}
	
//_____________________________consultas______________________________
	private static String consultaNome(Agenda agenda, Scanner scanner) {
		scanner.nextLine();
		System.out.print("\nDigite o nome> ");
		String nome = scanner.nextLine();
		return agenda.consultaNome(nome);
	}
	private static void consultaSobrenome(Agenda agenda, Scanner scanner) {
		scanner.nextLine();
		System.out.print("\nDigite o Sobrenome> ");
		String sobrenome = scanner.nextLine();
		agenda.consultaSobrenome(sobrenome);
	}
	private static void consultaTelefone(Agenda agenda, Scanner scanner) {
		scanner.nextLine();
		System.out.print("\nDigite o telefone> ");
		String telefone = scanner.nextLine();
		agenda.consultaTelefone(telefone);
	}
	
//_________________________________sair_____________________________________________
	/**
	 * Sai da aplicação.
	 */
	private static void sai() {
		System.out.println("\nVlw flw o/");
		System.exit(0);
	}

//___________________________carrega o arquivo csv___________________________________
	
	/**
	 * Lê uma agenda de um arquivo csv. 
	 * 
	 * @param arquivoContatos O caminho para o arquivo.
	 * @param agenda A agenda que deve ser populada com os dados. 
	 * @throws IOException Caso o arquivo não exista ou não possa ser lido.
	 */
	private static void carregaAgenda(String arquivoContatos, Agenda agenda) throws FileNotFoundException, IOException {
		LeitorDeAgenda leitor = new LeitorDeAgenda();
		
		int carregados =  leitor.carregaContatos(arquivoContatos, agenda);
		System.out.println("Carregamos " + carregados + " registrosz");
	}

}
