/**
 * Klasa reprezentuje aplikację salonu
 * 
 *	Program jest symulacją salonu samochodowego, 
 *	demonstruje następujące zagadnienia:
 * <ul>
 *  <li> tworzenie konta klienta</li>
 *  <li> logowanie klientów lub sprzedawcy</li>
 *  <li> dodawanie lub usuwanie samochodów</li>
 *  <li> zmianę haseł lub nr PESEL</li>
 *  <li> zmianę ceny oraz ilości samochodów</li>
 *  <li> kupowanie samochodów</li>
 *  <li> spawdzaie utargu</li>
 *  <li> zapis danych do pliku</li>
 *  <li> wyswietlanie wszystkich klientow i samochodów</li>
 * </ul>
 * 
 * @author Kamil Kozak
 * @verision 30.10.2016
 */
class ShopApplication {
	
	public static void main(String[] args) {
		new ShopApplication();
	}
	
	/**
	 * Określenie sposobu komunikacji z użytwkonikiem
	 */
	//private UserDialog UI = new ConsoleUserDialog();
	private UserDialog UI = new JOptionPaneUserDialog();
	/**
	 * Wiadomość powitalna
	 */
	private static final String WELCOME_MESSAGE =
			"Program SHOP\n" +
			"Autor: Kamil Kozak\n" +
			"Data: 23 pazdziernika 2016 r.\n";
	/**
	 * Menu główne
	 */
	private static final String SHOP_MENU =
			"SALON MENU GLOWNE\n" +
			"1. Zaloguj jako sprzedawca\n" +
			"2. StwĂłrz konto klienta\n" +
			"3. Zaloguj jako klient\n" +
			"4. Zapisz postÄ™py\n" +
			"5. WyĹ›wietl klientĂłw\n" +
			"6. WyĹ›wietl dostÄ™pne samochody\n" +
			"0. WyĹ‚Ä…cz program\n";
	/**
	 * Menu klienta
	 */
	private static final String CLIENT_MENU =
			"1. Dodaj fundusze\n" +
			"2. Zobacz dostÄ™pne samochody\n" +
			"3. Kup towar\n" +
			"4. ZmieĹ„ hasĹ‚o\n" + 
			"5. ZmieĹ„ numer pesel\n" +
			"0. Wyloguj siÄ™ z konta\n";
	/**
	 * Menu sprzedawcy
	 */
	private static final String SELLER_MENU =
			"1. Dodaj samochĂłd do magazynu\n" +
			"2. Zobacz dostÄ™pne samochody\n" +
			"3. UzupeĹ‚nij iloĹ›Ä‡ samochodĂłw\n" +
			"4. ZmieĹ„ cenÄ™ samochodu\n" +
			"5. Wycofaj samochĂłd z oferty\n" +
			"6. PokaĹĽ utarg\n" +
			"7. PokaĹĽ listÄ™ klientĂłw\n" +
			"8. ZmieĹ„ hasĹ‚o\n" +
			"0. Wyloguj siÄ™ z konta\n";
	/**
	 * Plik z klientami
	 */
	private static final String CLIENTS_FILE_NAME = "CLIENTS.BIN";
	/**
	 * Plik z samochodami
	 */
	private static final String STUFF_FILE_NAME = "STUFF.BIN";
	/**
	 * Obiekt typu Shop
	 */
	private Shop shop = new Shop();
	/**
	 * Konstruktor wyświetlający wiadomość powitalną, wczytujący dane z plików
	 * oraz wyświetlający menu główne
	 */
	public ShopApplication() {
		UI.printMessage(WELCOME_MESSAGE);
		
		try{
			shop.loadClientsFromFile(CLIENTS_FILE_NAME);
			UI.printMessage("Klienci zostali wczytani z pliku " + CLIENTS_FILE_NAME);
		} catch (Exception e) {
			UI.printErrorMessage(e.getMessage());
		}
		
		try{
			shop.loadStuffFromFile(STUFF_FILE_NAME);
			UI.printMessage("Samochody zostaĹ‚y wczytane z pliku " + STUFF_FILE_NAME);
		} catch (Exception e) {
			UI.printErrorMessage(e.getMessage());
		}
		
		
		while (true) {
			UI.clearConsole();

			try {
				
				switch (UI.enterInt(SHOP_MENU + "----> ")) {
				case 1:
					loginSeller();
					break;
				case 2:
					createNewClient();
					break;
				case 3:
					loginClient();
					break;
				case 4:
					saveAll();
					break;
				case 5:
					listAllClients();
					break;
				case 6:
					listAllStuff();
					break;
				case 0:
					try {
						shop.saveClientsToFile(CLIENTS_FILE_NAME);
						UI.printMessage("\nKlienci zostali zapisani w pliku " + CLIENTS_FILE_NAME);
					} catch (Exception e) {
						UI.printErrorMessage(e.getMessage());
					}
					
					try {
						shop.saveStuffToFile(STUFF_FILE_NAME);
						UI.printMessage("\nSamochody zostaĹ‚y zapisane w pliku " + STUFF_FILE_NAME);
					} catch (Exception e) {
						UI.printErrorMessage(e.getMessage());
					}

					UI.printInfoMessage("\nProgram zakoĹ„czyĹ‚ dziaĹ‚anie!");
					System.exit(0);
				}
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	/**
	 * Metoda zapisująca klientów oraz samochody w pliku
	 */
	public void saveAll() {
		try {
			shop.saveClientsToFile(CLIENTS_FILE_NAME);
			UI.printMessage("\nKlienci zostali zapisani w pliku " + CLIENTS_FILE_NAME);
		} catch (Exception e) {
			UI.printErrorMessage(e.getMessage());
		}
		
		try {
			shop.saveStuffToFile(STUFF_FILE_NAME);
			UI.printMessage("\nSamochody zostaĹ‚y zapisane w pliku " + STUFF_FILE_NAME);
		} catch (Exception e) {
			UI.printErrorMessage(e.getMessage());
		}
	}
	/**
	 * Metoda wypisująca klientów
	 */
	public void listAllClients() {
		StringBuilder list = new StringBuilder("\nLISTA KLIENTĂ“W:\n");
		list.append(shop.listClients());		
		UI.printMessage(list.toString());
	}
	/**
	 * Metoda wypisująca samochody
	 */
	public void listAllStuff() {
		StringBuilder list = new StringBuilder("\nLISTA SAMOCHODĂ“W:\n");
		list.append(shop.listStuff());		
		UI.printMessage(list.toString());
	}
	/**
	 * Metoda tworząca nowego klienta
	 */
	public  void createNewClient() {	
		String newNick;
		String newPassword;
		Client newClient;
		
		UI.printMessage("\nTWORZENIE NOWEGO KONTA\n");
		while(true) {
			newNick = UI.enterString("Wybierz nazwÄ™ konta:");
			if (newNick.equals("") || newNick.equals("seller")) return;
			if (shop.findClient(newNick)!=null) {
				UI.printErrorMessage("Konto juĹĽ istnieje");
				continue;
			}
			
			newPassword = UI.enterString("Podaj hasĹ‚o:");	
			try {
				newClient = shop.createClient(newNick);
				newClient.setPassword("", newPassword);
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
				continue;
			}
			UI.printMessage("Konto zostaĹ‚o utworzone");
			break;
		}
	}
	
	/**
	 * Metoda logująca klienta do konta oraz wyświetlająca menu klienta
	 */
	public void loginClient() {
		String nick, password;
		Client client;

		UI.printMessage("\nLOGOWANIE DO KONTA\n");

		nick = UI.enterString("Podaj nazwÄ™ konta:");
		password = UI.enterString("Podaj hasĹ‚o:");
		client = shop.findClient(nick, password);
		if (client == null || nick.equals("seller")) {
			UI.printErrorMessage("BĹ‚Ä™dne dane");
			return;
		}

		while (true) {
			UI.printMessage("\nJESTEĹš ZALOGOWANY DO KONTA ");
			UI.printMessage("     Nazwa konta: " + client.getName());
			UI.printMessage("     PESEL: " + client.getId());
			UI.printMessage("     Saldo konta: " + client.getBalance());

			try {

				switch (UI.enterInt(CLIENT_MENU + "----> ")) {
				case 1:
					payInMoney(client);
					break;
				case 2:
					listAllStuff();
					break;
				case 3:
					buyStuff(client);
					break;
				case 4:
					password = changePassword(client, password);
					break;
				case 5:
					changeClientId(client);
					break;
				case 0:
					client = null;
					UI.printMessage("NastÄ…piĹ‚o wylogowanie z konta");
					return;
				}
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	/**
	 * Metoda która wpłaca pieniądze na konto
	 * @param client Klient na którego konto wpłacamy pieniądze
	 * @throws Exception Wyjątek zwracany przy błędzie wpłaty, gdy
	 * kwota jest ujemna
	 */
	public void payInMoney(Client client)throws Exception {
		double amount;
		UI.printMessage("\nWPĹ�ATA NA KONTO");
		amount = UI.enterDouble("Podaj kwotÄ™: ");
		client.payIn(amount);
	}
	/**
	 * Metoda zmienia hasło na nowe
	 * @param client Klient którego hasło zmieniamy
	 * @param password Dotychczasowe hasło
	 * @return Nowe hasło
	 * @throws Exception Wyjątek zwraca błąd zmiany hasła, gdy dotychczasowe hasło jest
	 * niepoprawne
	 */
	public  String changePassword(Client client, String password) throws Exception {
		String newPassword;
		
		UI.printMessage("\nZMIANA HASĹ�A DO KONTA");
		newPassword = UI.enterString("Podaj nowe hasĹ‚o: ");
		client.setPassword(password, newPassword);
		return newPassword;
	}
	/**
	 * Metoda służąca do kupowania samochodów
	 * Przekazuje pieniądze od klienta do sprzedawcy
	 * Zmniejsza ilość dostępnych samochodów
	 * Sprawdza czy samochód jest dostępny
	 * 
	 * @param client Klient który kupuje
	 * @throws Exception Wyjątek zwraca błąd przy zakupie, gdy:
	 * <ul>
	 * <li>błędna nazwa samochodu</li>
	 * <li>niewystarczająca ilość funduszy na koncie klienta</li>
	 * <li>brak dostępnych samochodów</li>
	 * </ul>
	 */
	public void buyStuff(Client client) throws Exception {
		String stuffName;
		Stuff destStuff;
		Client seller;
		
		seller = shop.findClient("seller");
		
		UI.printMessage("\nKUPOWANIE SAMOCHODU");
		stuffName = UI.enterString("Podaj nazwÄ™ samochodu: ");
		destStuff = shop.findStuff(stuffName);
		if (destStuff == null) {
			UI.printErrorMessage("Nieznany samochĂłd");
			return;
		}
		if(!client.buy(destStuff.getPrice(),destStuff.getQuantity())) { 
			UI.printErrorMessage("Nie moĹĽna kupiÄ‡ samochodu");
			return;
		}
		destStuff.sellStuff();
		seller.payIn(destStuff.getPrice());
		
		UI.printMessage("\nSamochĂłd zakupiony");
	}
	/**
	 * Metoda zmienia PESEL klienta
	 * @param client Klient któego PESEL zmieniamy
	 */
	public void changeClientId(Client client) {
		String newClientId;
		
			UI.printMessage("\nZMIANA PESELU KLIENTA");
			newClientId = UI.enterString("Podaj numer pesel: ");
			client.setId(newClientId);
	}
	/**
	 * Metoda loguje sprzedawcę oraz wyświetla menu sprzedawcy
	 */
	public void loginSeller() {
		String password;
		Client client;
		
		client = shop.findClient("seller");
		if (client == null) {
			String newPassword;
			UI.printMessage("\nTWORZENIE KONTA SPRZEDAWCY");
			newPassword = UI.enterString("Podaj hasĹ‚o: ");	
			try {
				client = shop.createClient("seller");
				client.setPassword("", newPassword);
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
				return;
			}
			UI.printMessage("Konto zostaĹ‚o utworzone");
		}
		
		UI.printMessage("\nLOGOWANIE DO KONTA SPRZEDAWCY\n");
		password = UI.enterString("Podaj hasĹ‚o: ");
		
		client = shop.findClient("seller", password);
		if (client == null) {
			UI.printErrorMessage("BĹ‚Ä™dne dane");
			return;
		}
		
		while (true) {
			UI.printMessage("\nJESTEĹš ZALOGOWANY JAKO SPRZEDAWCA ");

			try {

				switch (UI.enterInt(SELLER_MENU + "----> ")) {
				case 1:
					createNewStuff();
					break;
				case 2:
					listAllStuff();
					break;
				case 3:
					fillStuff();
					break;
				case 4:
					changePrice();
					break;
				case 5:
					removeStuff();
					break;
				case 6:
					showEarnings();
					break;
				case 7:
					listAllClients();
					break;
				case 8:
					password = changeSellersPassword(client,password);
				case 0:
					UI.printMessage("NastÄ…piĹ‚o wylogowanie z konta");
					return;
				}
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
			}
		}
	}
	/**
	 * Metoda dodaje nowy samochód
	 */
	public void createNewStuff() {	
		String newName;
		int newHorsePower;
		String newColour;
		double newPrice;
		int newAmount;
		Stuff newStuff;
		
		UI.printMessage("\nTWORZENIE NOWEGO SAMOCHODU\n");
		while(true) {
			newName = UI.enterString("Podaj nazwÄ™ samopchodu:");
			if (newName.equals("")) return;
			if (shop.findStuff(newName)!=null) {
				UI.printErrorMessage("SamochĂłd juĹĽ istnieje");
				continue;
			}
			
			newHorsePower = UI.enterInt("Podaj iloĹ›Ä‡ koni mechanicznych: ");
			newColour = UI.enterString("Podaj kolor: ");
			newPrice = UI.enterDouble("Podaj cenÄ™: ");
			newAmount = UI.enterInt("Podaj iloĹ›Ä‡: ");
			
				
			try {
				newStuff = shop.createStuff(newName,newHorsePower,newColour,newPrice,newAmount);
			} catch (Exception e) {
				UI.printErrorMessage(e.getMessage());
				continue;
			}
			UI.printMessage("SamochĂłd zostaĹ‚ utworzony");
			break;
		}
	}
	/**
	 * Metoda uzupełnia ilość samochodów
	 * @throws Exception Wyjątek zwraca błąd, gdy samochód nie jest dostępny
	 * lub gdy ilość jest ujemna
	 */
	public void fillStuff() throws Exception {
		int amount;
		String destName;
		Stuff destStuff;
		
		UI.printMessage("\nDOSTAWA SAMOCHODĂ“W");
		
		destName = UI.enterString("Podaj nazwÄ™ samochodu: ");
		destStuff = shop.findStuff(destName);
		if (destStuff == null) {
			UI.printErrorMessage("Nieznany samochĂłd");
			return;
		}
		amount = UI.enterInt("Podaj iloĹ›Ä‡: ");
		destStuff.addQuantity(amount);
	}
	/**
	 * Metoda zmienia cenę samochodu 
	 * @throws Exception Wyjątek zwraca błąd, gdy samochód nie jest dostępny
	 * lub gdy cena jest ujemna
	 */
	public void changePrice() throws Exception {
		double price;
		String destName;
		Stuff destStuff;
		
		UI.printMessage("\nZMIANA CENY SAOMCHODU");
		
		destName = UI.enterString("Podaj nazwÄ™ samochodu: ");
		destStuff = shop.findStuff(destName);
		if (destStuff == null) {
			UI.printErrorMessage("Nieznany samochĂłd");
			return;
		}
		price = UI.enterDouble("Podaj cenÄ™: ");
		destStuff.setPrice(price);
	}
	/**
	 * Metoda usuwa samochód
	 * @throws Exception Wyjątek zwraca błąd, gdy ilość dostępnych samochodów jest dodatnia
	 * lub gdy samochód nie istnieje
	 */
	public void removeStuff() throws Exception {
		String destName;
		Stuff destStuff;
		
		UI.printMessage("\nUSUWANIE SAMOCHODU");
		
		destName = UI.enterString("Podaj nazwÄ™ samochodu: ");
		destStuff = shop.findStuff(destName);
		if (destStuff == null) {
			UI.printErrorMessage("Nieznany samochĂłd");
			return;
		}
		
		shop.removeStuff(destStuff);
		
		UI.printInfoMessage("\nSamochĂłd zostaĹ‚ usuniety");
		
	}
	/**
	 * Metoda wyświetla aktualny utarg
	 */
	public void showEarnings() {
		Client client;
		client = shop.findClient("seller");
		UI.printMessage("Aktualny utarg to: " + client.getBalance());
	}
	/**
	 * Metoda zmienia hasło sprzedawcy
	 * @param client Sprzedawca
	 * @param password Dotychczasowe hasło
	 * @return Nowe hasło
	 * @throws Exception Wyjątek zgłaszany gdy hasło jest nieprawidłowe
	 */
	public  String changeSellersPassword(Client client, String password) throws Exception {
		String newPassword;
		
		UI.printMessage("\nZMIANA HASĹ�A DO KONTA SPRZEDAWCY");
		newPassword = UI.enterString("Podaj nowe hasĹ‚o: ");
		client.setPassword(password, newPassword);
		return newPassword;
	}

}
