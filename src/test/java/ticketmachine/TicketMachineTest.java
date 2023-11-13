package ticketmachine;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@BeforeEach
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de
	// l'initialisation
	// S1 : le prix affiché correspond à l’initialisation.
	void priceIsCorrectlyInitialized() {
		// Paramètres : valeur attendue, valeur effective, message si erreur
		assertEquals(PRICE, machine.getPrice(), "Initialisation incorrecte du prix");
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		// Les montants ont été correctement additionnés
		assertEquals(10 + 20, machine.getBalance(), "La balance n'est pas correctement mise à jour");
	}

	@Test
	// S3 : on imprime pas le ticket si le montant inséré est insuffisant
	void test3() {
		machine.insertMoney(PRICE - 1);
		// Les montants ont été correctement additionnés
		assertFalse(machine.printTicket(), "Pas assez d'argent, la machine ne doit pas imprimer");
	}

	@Test
	// S4 : on imprime le ticket si le montant inséré est suffisant
	void test4() {
		machine.insertMoney(PRICE + 10);
		// Les montants ont été correctement additionnés
		assertTrue(machine.printTicket(), "Le ticket devrait être imprimé car le montant inséré est suffisant.");
	}

	@Test
	// S5 : on imprime le ticket si le montant inséré est suffisant
	void test5() {
		machine.insertMoney(PRICE + 5);
		machine.printTicket();
		// Les montants ont été correctement additionnés
		assertEquals(5, machine.getBalance(), "Le ticket devrait être imprimé car le montant inséré est suffisant.");
	}

	@Test
	// S6 : Le montant collecté est mis à jour quand on imprime un ticket
	void test6() {
		machine.insertMoney(PRICE);
		machine.printTicket();
		int expectedTotal = 50; // Le montant total doit être collecté
		int actualTotal = machine.getTotal();
		// Les montants ont été correctement additionnés
		assertEquals(expectedTotal, actualTotal,
				"Le montant total doit être correctement collecté après l'impression du ticket.");
	}

	@Test
	// S7 : rend correctement la monnaie
	void test7() {
		machine.insertMoney(PRICE + 10);
		machine.printTicket();

		int expectedChange = 10;
		// Les montants ont été correctement additionnés
		assertEquals(expectedChange, machine.refund(), "La monnaie rendue doit être correcte.");
	}

	@Test
	// S8 : remet à 0
	void test8() {
		machine.insertMoney(PRICE + 10);
		machine.printTicket();
		machine.refund();

		int expected = 0;
		// Les montants ont été correctement additionnés
		assertEquals(expected, machine.getBalance(), "La monnaie rendue doit être correcte.");

	}

	@Test
	// S9 : on ne peut pas insérerun montant négatif
	public void testForException9() {
		
		try {

		machine.insertMoney(-5); 
		
		} catch (IllegalArgumentException e) {
		// Si on arrive ici c'est normal, le test est réussi
		}
	}

	@Test 
	// S10 : on ne peut pas insérerun montant négatif
	public void testForException10() {
		try {

		TicketMachine machine = new TicketMachine(-5);

		
		} catch (IllegalArgumentException e) {
		// Si on arrive ici c'est normal, le test est réussi
		}
	}



}
