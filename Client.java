/* 
 *  Program ShopApplication
 *  Autor: Kamil Kozak
 *  Data: 23 pazdziernika 2016 r.
 */

/**
 * 
 * 
 */

import java.io.Serializable;

/**
 * Klasa reprezentuje klient�w
 * 
 * @author Kamil Kozak
 * @version 30.10.2016
 */

public class Client implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Nazwa konta
	 */
	private String name;
	/**
	 * Has�o do konta
	 */
	private long passwordCode;
	/**
	 * Nr PESEL klienta
	 */
	private String id;
	/**
	 * Stan konta u klienta oraz utarg u sprzedawcy
	 */
	private double balance;
	/**
	 * Konstruktor nadaj�cy nazw� konta
	 * @param name Nazwa konta
	 */
	Client(String name){
		this.name=name;
		id="";
		passwordCode=0;
		balance=0;
	}
	/**
	 * Metoda podaje nazw� konta
	 * @return Nazwa konta
	 */
	public String getName(){
		return name;
	}
	/**
	 * Metoda zmienia nr PESEL
	 * @param id dotychczasowy nr PESEL
	 */
	public void setId(String id){
		this.id=id;
	}
	/**
	 * Metoda podaje aktualny nr PESEL
	 * @return Nr PESEL
	 */
	public String getId(){
		return id;
	}
	/**
	 * Metoda sprawdza czy podane has�o jest poprawne
	 * @param password Has�o do sprawdzenia
	 * @return Warto�� logiczna:
	 * true je�eli has�o poprawne
	 * false je�eli has�o b��dne
	 */
	public boolean checkPassword(String password) {
		if (password==null) return false;
		return password.hashCode()==passwordCode;
	}
	/**
	 * Metoda zmienia has�o
	 * @param oldPassword Dotychczasowe has�o
	 * @param newPassword Nowe has�o
	 * @throws Exception Sprawdza, czy podane dotychczasowe has�o jest poprawne
	 */
	public void setPassword(String oldPassword, String newPassword) throws Exception {
		if (!checkPassword(oldPassword)) throw new Exception("Błędne hasło");
		passwordCode = newPassword.hashCode(); 
	}
	/**
	 * Metoda podaje aktualny stan konta u klienta lub utarg u sprzedawcy
	 * @return Stan konta lub utarg
	 */
	public double getBalance(){
		return balance;
	}
	/**
	 * Metoda dodaje pieni�dze do konta
	 * @param amount Ilo�� pieni�dzy do dodania
	 * @throws Exception Ilo�� musi by� dodatnia
	 */
	public void payIn(double amount) throws Exception {
		if (amount<0) throw new Exception("Wpłata nie może być ujemna");
		balance += amount;
	}
	/**
	 * Metoda zwraca posta� konta w postaci tekstowej
	 * @return Tekstowa posta� konta
	 */
	public String toString(){
		return String.format("  %s <%s> ", name, id);
	}
	/**
	 * Metoda odejmuje pieni�dze z konta
	 * @param price Ilo�� pieni�dzy do odj�cia
	 * @param quantity Ilo�� dost�pnych produkt�w
	 * @return Zwraca logiczn� prawd�, je�li:
	 * stan konta jest wi�kszy ni� cena produtku oraz
	 * gdy jest co najmniej jeden produkt na stanie
	 */
	public boolean buy(double price, int quantity) {
		if (balance<price || quantity<1) return false;
		balance-=price;
		return true;
	}
	

}
