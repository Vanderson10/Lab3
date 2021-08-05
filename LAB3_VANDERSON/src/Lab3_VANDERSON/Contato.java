package Lab3_VANDERSON;


/**
 * Classe que representa o contato com seus respectivos atributos.
 * Contatos cujo objetos serão armazenados na agenda.
 * @author vanderson
 *
 */
public class Contato {
	/**
	 * representa o nome de um contato
	 */
	private String nome;
	
	/**
	 * representa o sobrenome de um contato
	*/
	private String sobrenome;
	
	/**
	 * representa o telefone de um contato
	 */
	private String telefone;
	
	/**
	 * representa as tags que um usuário possui, um contato só pode ter no máximo 5 tags
	 */
	private String[] tags = new String[5];
	
	
//___________________construtor____________________________
	/**
	 * Construtor do contato, representa um objeto do tipo contrato
	 * @param nome
	 * @param sobrenome
	 * @param telefone
	 */
	public Contato(String nome, String sobrenome, String telefone) {
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.telefone = telefone;
		
	}
	
//_______________________metodos_____________________________________
	
	/**
	 * metodo para outras classes acessar o nome do contato 
	 * @param contato
	 * @return o nome do contato
	 */
	
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * metodo para outras classes acessar o sobrenome do contato 
	 * @return sobrenome
	 */
	
	public String getSobrenome() {
		return this.sobrenome;
	}
	
	/**
	 * metodo para outras classes acessar o telefone do contato 
	 * @return telefone
	 */
	
	public String getTelefone() {
		return this.telefone;
	}
	
	/**
	 * metodo para acessar as tags de um contato 
	 * @return o array de string com as tags de um contato
	 */
	public String[] getTags() {
		return this.tags;
	}
	
	/**
	 * Método que altera o valor de telefone
	 * @param fone
	 */
	public void setTelefone(String fone) {
		this.telefone = fone;
	}
	
	/**
	 * adiciona as tags de cada usuário
	 * @param posicao
	 * @param tag
	 */
	
	public void addTags(Contato contato, int posicao,String tag) {
		contato.tags[posicao-1] = tag;
	}
	
	/**
	 * Representa todas as tags em strings que um contato tem 
	 * @param contato
	 * @return string com todas as tags de um contato
	 */
	public String exibiStringTag(Contato contato) {
		String saida = "";
		for (int i=0; i<tags.length; i++) {
			if (tags[i]!=null) {
				saida += tags[i]+" ";
			}
		}
		return saida;
	}
	
//__________________remove tag____________________
	/**
	 * Remove a tag recebida pelo main
	 * @param tag
	 */
	
	public void removeTag(String tag) {
		for (int i=0; i<tags.length;i++) {
			if (tags[i]!=null) {
				if(tag.equals(tags[i])) {
					tags[i] = null;
					System.out.println("tag removida com sucesso");
				}else {
					System.out.println("tag não encontrada");
					System.exit(0);
				}
			}
		}
	}
	
	
}
