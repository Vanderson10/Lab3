package Lab3_VANDERSON;

/**
 * Uma agenda que mantém uma lista de contatos com posições. Podem existir 100 contatos. 
 * 
 * @author vanderson
 *
 */
public class Agenda {
	
	private static final int TAMANHO_AGENDA = 100;
	
	private Object[] contatos;
	
	/**
	 * cria um array de objetos para colocar os objetos de contatos favoritos
	 */
	private Object[] favoritos = new Object[10];

	/**
	 * Cria uma agenda.
	 */
	
	public Agenda() {
		this.contatos = new Object[TAMANHO_AGENDA];
	}
	
	/**
	 * Acessa a lista de contatos mantida.
	 * @return O array de contatos.
	 */
	public Object[] getContatos() {
		return this.contatos.clone();
	}

	/**
	 * Acessa os dados de um contato específico.
	 * @param posicao Posição do contato na agenda.
	 * @return Dados do contato. Null se não há contato na posição.
	 */
	public Object getContato(int posicao) {
		return contatos[posicao-1];
	}

//___________________________cadastrar o contato____________________________________________

	/**
	 * Cadastra um contato em uma posição. Um cadastro em uma posição que já existe sobrescreve o anterior. 
	 * @param posicao Posição do contato.
	 * @param Nome do contato.
	 */
	public void cadastraContato(int posicao, String nome, String sobrenome, String telefone) {
		Contato ct = new Contato(nome, sobrenome, telefone);
		this.contatos[posicao-1] = ct;
		
	}
	
//__________________________remove contato ___________________________
	/**
	 * remove o contato pela posição dele 
	 * @param posicao
	 */
	
	public void removeContato(int posicao) {
		if (contatos[posicao-1]==null) {
			throw new IllegalArgumentException("CONTATO inexistente");
		}
		for (int i=0; i<favoritos.length;i++) {
			if (contatos[posicao-1]==favoritos[i]) {
				favoritos[i]=null;
				contatos[posicao-1]=null;
				break;
			}
		}
	}
	
//____________________________Verificações de cadastrar contato__________________________________________________
	/**
	 * Analisa se a posição está entre 1 e 100
	 * @param posicao
	 * @return true se a posição não pode ser aceita e false o contrário
	 */
	
	public boolean posicaoErrada(int posicao) {
		if (posicao>100 || posicao<1) {
			System.out.print("\nPOSIÇÃO INVÁLIDA> ");
			return true;
		}return false;
	}
	
	/**
	 * Verifica se já existe um usuário com o mesmo nome e sobrenome, independente da posição que ele for cadastrado
	 * @param nome
	 * @param sobrenome
	 * @return true caso ja exista um usuário na agenda
	 */
	
	public boolean jaExiste(String nome, String sobrenome) {
		for (int i = 0; i < contatos.length; i++) { 			
			if (contatos[i]!=null){
				Contato contato = (Contato) contatos[i];
				if (contato.getNome().equals(nome) && contato.getSobrenome().equals(sobrenome)) {
					return true;
				}
			}
		}return false;
	}
	
	/**
	 * Analisa se existe um contato na posição que foi escolhida para exibir o contato
	 * @param posicao
	 * @return true se não tem contato na posição indicada. 
	 */
	
	public boolean semContato(int posicao) {
		if (contatos[posicao-1]==null) {
			return true;
		}return false;
	}
//___________________________favoritos____________________________________________
	/**
	 * Adiciona na lista favoritos o contato da agenda na posição do array de favoritos definido na interface
	 * @param agenda
	 * @param posicaoAgenda
	 * @param posicaoFavoritos
	 */
	
	public void ehFavorito(int posicaoAgenda, int posicaoFavoritos) {
		Contato contato = (Contato) contatos[posicaoAgenda-1];
		favoritos[posicaoFavoritos-1] = contato;
	}
	
	/**
	 * remove o contato da posição de favorito pela sua posição
	 * @param posicaoFavorito
	 */
	public void removeFavorito(int posicaoFavorito) {
		if (favoritos[posicaoFavorito-1] == null) {
			throw new IllegalArgumentException("Favorito não encontrado");
		}else {
			favoritos[posicaoFavorito-1] = null;
		}
	}
	
	/**
	 * metodo get para o mainAgenda acessar a lista de favoritos que será acessada 
	 * @return array de favoritos
	 */
	
	public Object[] getContatosFavoritos() {
		return this.favoritos.clone();
	}
	
	/**
	 * verifica se um contato é favorito 
	 * @param posicaoContato
	 * @return true se o contato é favorito e false o contrário
	 */
	
	public boolean verificaFavorito(int posicaoContato) {
		for(int i=0; i<favoritos.length; i++) {
			if(favoritos[i]==contatos[posicaoContato-1]) {
				return true;
			}
		}return false;
	}
	
	/**
	 * Verifica se o usuario que vai ser colocado como favorito ja existe
	 * @param posicaoAgenda
	 * @return true se existe e false o contrário
	 */
	
	public boolean jaExisteFavorito(int posicaoAgenda) {
		for (int i = 0; i < favoritos.length; i++) { 			
			if (favoritos[i]!=null){
				Contato contatoFavorito = (Contato) favoritos[i];
				Contato contatoAgenda = (Contato) contatos[posicaoAgenda-1];
				if (contatoFavorito.getNome().equals(contatoAgenda.getNome()) && contatoFavorito.getSobrenome().equals(contatoAgenda.getSobrenome())) {
					return true;
				}
			}
		}return false;
	}
//____________________________________tags_________________________________________________
	/**
	 * recebe os dados e chama o metodo de adicionar a tag de contatos, para adicionar uma tag a um contato especifico.
	 * @param posicaoContatosAgenda
	 * @param tag
	 * @param posicaoTag
	 */
	
	public void addTag(String posicaoContatosAgenda,String tag,int posicaoTag) { 
		String[] posicoesString = posicaoContatosAgenda.split(" ");
		for (int i=0; i<posicoesString.length;i++) {
			int index = Integer.parseInt(posicoesString[i]);
			Contato contato = (Contato) contatos[index-1];
			contato.addTags(contato, posicaoTag,tag);
		}
	}
	
//__________________________Edição de telefone inválida_______________________
	/**
	 * Verifica se as posições de um contato estão corretas e se existe um contato na posição pedida
	 * @param posicao
	 * @return true caso tenha algo errado e false o contrário
	 */
	
	public boolean contatoInvalido(int posicao) {
		if (posicao<1 || posicao>100) {
			System.out.println("posição inválida");
			return true;
		}
		if (contatos[posicao-1]==null) {
			System.out.println("Contato não existe");
			return true;
		}return false;
	}
	
//_____________________________Consultas_______________________________
	/**
	 * recebe um nome de contato para ser consultado
	 * @param nome
	 * @return uma lista com todos os contatos com o nome consultado
	 */
	public String consultaNome(String nome) {
		String saida = "";
		for(int i=0; i<contatos.length;i++) {
			if(contatos[i]!=null) {
				Contato contato = (Contato) contatos[i];
				if(contato.getNome().equals(nome)) {
					saida += formataConsulta(i, contato);
				}
			}
		}if (saida.equals("")) {
			return "nome não encontrado";
		}return saida;
	}
	
	/**
	 * recebe um sobrenome de contato para ser consultado
	 * @param sobrenome
	 * @return uma lista com todos os contatos com o sobrenome consultado
	 */
	public String consultaSobrenome(String sobrenome) {
		String saida = "";
		for(int i=0; i<contatos.length;i++) {
			if(contatos[i]!=null) {
				Contato contato = (Contato) contatos[i];
				if(contato.getSobrenome().equals(sobrenome)) {
					saida += formataConsulta(i, contato);
				}
			}
		}return saida;
	}
	
	/**
	 * recebe um telefone de contato para ser consultado
	 * @param telefone
	 * @return uma lista com todos os contatos com o telefone consultado
	 */
	public String consultaTelefone(String telefone) {
		String saida = "";
		
		for(int i=0; i<contatos.length;i++) {
			if(contatos[i]!=null) {
				Contato contato = (Contato) contatos[i];
				if(contato.getTelefone().equals(telefone)) {
					saida += formataConsulta(i, contato);
				}
			}
		}return saida;
	}
	
	private String formataConsulta(int indice, Contato contato) {
		return indice+1+" "+contato.getNome()+" "+contato.getSobrenome()+" "+contato.getTelefone()+"/n";
	}
	
}
