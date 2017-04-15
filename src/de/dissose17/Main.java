package de.dissose17;

import de.dissose17.data.EstateAgent;

/**
 * Hauptklasse
 */
public class Main {
	/**
	 * Startet die Anwendung
	 */
	public static void main(String[] args) {
		showMainMenu();
	}
	
	/**
	 * Zeigt das Hauptmenü
	 */
	public static void showMainMenu() {
		//Menüoptionen
		final int MENU_MAKLER = 0;
		final int QUIT = 1;
		
		//Erzeuge Menü
		Menu mainMenu = new Menu("Hauptmenü");
		mainMenu.addEntry("EstateAgent-Verwaltung", MENU_MAKLER);
		mainMenu.addEntry("Beenden", QUIT);
		
		//Verarbeite Eingabe
		while(true) {
			int response = mainMenu.show();
			
			switch(response) {
				case MENU_MAKLER:
					showMaklerMenu();
					break;
				case QUIT:
					return;
			}
		}
	}
	
	/**
	 * Zeigt die Maklerverwaltung
	 */
	public static void showMaklerMenu() {
		//Menüoptionen
		final int NEW_MAKLER = 0;
		final int BACK = 1;
		
		//Maklerverwaltungsmenü
		Menu maklerMenu = new Menu("EstateAgent-Verwaltung");
		maklerMenu.addEntry("Neuer EstateAgent", NEW_MAKLER);
		maklerMenu.addEntry("Zurück zum Hauptmenü", BACK);
		
		//Verarbeite Eingabe
		while(true) {
			int response = maklerMenu.show();
			
			switch(response) {
				case NEW_MAKLER:
					newMakler();
					break;
				case BACK:
					return;
			}
		}
	}
	
	/**
	 * Legt einen neuen EstateAgent an, nachdem der Benutzer
	 * die entprechenden Daten eingegeben hat.
	 */
	public static void newMakler() {
		EstateAgent m = new EstateAgent();
		
		m.setName(FormUtil.readString("Name"));
		m.setAddress(FormUtil.readString("Adresse"));
		m.setLogin(FormUtil.readString("Login"));
		m.setPassword(FormUtil.readString("Passwort"));
		m.save();
		
		System.out.println("EstateAgent mit der ID "+m.getId()+" wurde erzeugt.");
	}
}
