package teste;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Lab3_VANDERSON.Agenda;
import Lab3_VANDERSON.Contato;

class AgendaTest {
	
private Agenda agendaBase;

	@BeforeEach
	void iniciaAgenda() {
		this.agendaBase = new Agenda();
	}

	/** 
	 * Testes de cadastro na agenda
	 */
	
	@Test 
    void testCadastoContatoAgendaUm() {
        agendaBase.cadastraContato(1, "Van","araujo", "76487658");
        Contato contato = (Contato) agendaBase.getContato(1); 
        assertEquals(contato.getNome(), "Van");
        
    }
	
	@Test 
    void testCadastoContatoAgendaCem() {
        agendaBase.cadastraContato(100, "Fulano","da silva", "7648438748");
        Contato contato = (Contato) agendaBase.getContato(100); 
        assertEquals(contato.getNome(), "Fulano");
        
    }
	
	/**
	 * Caso o método posicaoErrada return true, o cadastro não será realizado e volta o menu.
	 */
	
	@Test
	public void testPosicaoCemUm() {
	     assertEquals(agendaBase.posicaoErrada(101),true);
	}
	
	@Test
	public void testPosicaoZero() {
	     assertEquals(agendaBase.posicaoErrada(0),true);
	}
	
}