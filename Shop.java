import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
/**
 * Klasa reprezentuje sklep
 * @author Kamil Kozak
 * @verison 30.10.2016
 */
class Shop implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Tablica zawieraj�ca klient�w
	 */
	private ArrayList<Client> listOfClients = new ArrayList<Client>();
	/**
	 * Tablica zawieraj�ca samochody
	 */
	private ArrayList<Stuff>  listOfStuff = new ArrayList<Stuff>();
	/**
	 * Metoda tworz�ca nowego klienta
	 * @param name Nazwa
	 * @return Nowy obiekt-klient
	 * @throws Exception Nazwa nie mo�e by� pusta oraz nie mo�e ju� istnie�
	 */
	public Client createClient(String name) throws Exception {
		if (name==null || name.equals("")) throw(new Exception("Nazwa konta nie może być pusta"));
		if (findClient(name)!=null) throw(new Exception("Konto już istnieje"));
		Client newClient = new Client(name);
		listOfClients.add( newClient );
		return newClient;
	}
	/**
	 * Metoda tworzy nowy samoch�d
	 * @param name Nazwa
	 * @param horsePower Ilo�� koni mechanicznych
	 * @param colour Kolor
	 * @param price Cena
	 * @param amount Ilo��
	 * @return Zwraca nowy obiekt-samoch�d
	 * @throws Exception Nazwa nie mo�e by� pusta oraz nazwa nie mo�e by� zaj�ta
	 * oraz ilo�� koni mechanicznych, cena i ilo�� musz� by� dodatnie
	 */
	public Stuff createStuff(String name, int horsePower, String colour, double price, int amount) throws Exception {
		if (name==null || name.equals("")) throw(new Exception("Nazwa samochodu nie może być pusta"));
		if (findStuff(name)!=null) throw(new Exception("Samochód już istnieje"));
		if (horsePower<0 || price<0 || amount<0) throw (new Exception("Błędne dane"));
		Stuff newStuff = new Stuff(name, horsePower, colour, price,amount);
		listOfStuff.add( newStuff );
		return newStuff;
	}
	/**
	 * Metoda zapisuje klient�w do pliku
	 * @param fileName Nazwa pliku
	 * @throws Exception Wyj�tek zwracany przy b��dzie zapisu
	 */
	void saveClientsToFile(String fileName) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(listOfClients);
		out.close();
	}
	
	/**
	 * Metoda wczytuje klient�w z pliku
	 * @param fileName Nazwa pliku
	 * @throws Exception Wyj�tek zwracany przy bl�dzie wczytywania
	 */
	void loadClientsFromFile(String fileName) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		listOfClients = (ArrayList<Client>)in.readObject();
		in.close();
	}
	/**
	 * Metoda zapisuje samochody do pliku
	 * @param fileName Nazwa pliku
	 * @throws Exception Zwraca wyj�tek przy b��dzie zapisu
	 */
	void saveStuffToFile(String fileName) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
		out.writeObject(listOfStuff);
		out.close();
	}
	
	/**
	 * Metoda wczytuje samochody z pliku
	 * @param fileName Nazwa pliku
	 * @throws Exception Wyj�tek zwracany przy b��dzie wczytywania
	 */
	void loadStuffFromFile(String fileName) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
		listOfStuff = (ArrayList<Stuff>)in.readObject();
		in.close();
	}
	
	/**
	 * Metoda usuwa samoch�d
	 * @param stuff Samoch�d
	 * @throws Exception Wyj�tek zwracany gdy przedmiot nie istnieje lub gdy jego
	 * ilo�� na stanie jest wi�ksza od zera
	 */
	public void removeStuff(Stuff stuff) throws Exception {
		if (stuff==null) throw(new Exception("Brak towaru"));
		if (stuff.getQuantity()!= 0) throw(new Exception("Liczba dostępnych samochodów nie jest zerowa"));
		listOfStuff.remove(stuff);
	}
	 
	/**
	 * Metoda sprawdza czy istnieje klient o podanej nazwie
	 * @param name Nazwa klienta
	 * @return Zwraca obiekt-klienta
	 */
	public Client findClient(String name) {
		for (Client client : listOfClients)
			if (client.getName().equals(name)) return client;
		return null;
	}
	
	/**
	 * Metoda sprawdza czy isnieje klient o podanej nazwie
	 * i sprawdza poprawno�� has�a
	 * @param name Nazwa klienta
	 * @param password Has�o
	 * @return Zwraca obiekt-klienta
	 */
	public Client findClient(String name, String password) {
		Client client = findClient(name);
		if (client!=null){
			if (!client.checkPassword(password)) client = null;
		}
		return client;
	}
	
	/**
	 * Metoda sprawdza czy istnieje samoch�d
	 * @param name Nazwa samochodu
	 * @return Zwraca obiekt-samoch�d
	 */
	public Stuff findStuff(String name) {
		for (Stuff stuff : listOfStuff)
			if (stuff.getName().equals(name)) return stuff;
		return null;
	}
	
	/**
	 * Metoda tworzy list� klient�w
	 * @return Zwraca list� klient�w
	 */
	public String listClients(){
		StringBuilder sb = new StringBuilder();
		int n = 0;
		for (Client client : listOfClients){
			if (n++ != 0) sb.append("\n");		
			sb.append(client.toString());
		}
		return sb.toString();
	}
	/**
	 * Metoda tworzy list� samochod�w
	 * @return Zwraca list� samochod�w
	 */
	public String listStuff(){
		StringBuilder sb = new StringBuilder();
		int n = 0;
		for (Stuff stuff : listOfStuff){
			if (n++ != 0) sb.append("\n");		
			sb.append(stuff.toString());
		}
		return sb.toString();
	}

}
