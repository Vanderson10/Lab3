package teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Lab3_VANDERSON.Contato;

class ContatoTest {

	 private Contato contatoBase;
	 
	    @BeforeEach
	    void preparaContatos() {
	        this.contatoBase = new Contato("Matheus", "Gaudencio", "555-5551");
	    }
		@Test
	    void testNomeCompleto() {
	        assertEquals("Matheus", this.contatoBase.getNome());
		}
		
		@Test
		void testAddTag() {
			contatoBase.addTags(contatoBase, 1, "professor");
			assertEquals(contatoBase.exibiStringTag(contatoBase),"professor ");
		}
		
}

