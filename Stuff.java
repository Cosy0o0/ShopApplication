import java.io.Serializable;
/**
 * Klasa reprezentuje samochody
 * @author Kamil Kozak
 * @version 30.10.2016
 */
public class Stuff implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * Nazwa samochodu
	 */
	private String name;
	/**
	 * Ilo�� koni mechanicznych samochodu
	 */
	private int horsePower;
	/**
	 * Kolor samochodu
	 */
	private String colour;
	/**
	 * Cena samochodu
	 */
	private double price;
	/**
	 * Ilo�� dost�pnych sztuk
	 */
	private int quantity;
	/**
	 * Konstruktor nadaje wszystkie parametry samochodom
	 * @param name Nazw�
	 * @param horsePower Ilo�� koni mechanicznych
	 * @param colour Kolor
	 * @param price Cen�
	 * @param quantity Ilo��
	 */
	Stuff(String name, int horsePower, String colour, double price, int quantity){
		this.name = name;
		this.horsePower = horsePower;
		this.colour = colour;
		this.price = price;
		this.quantity = quantity;
	}
	
	/**
	 * Metoda podaje nazw� samochodu
	 * @return Nazwa samochodu
	 */
	public String getName(){
		return name;
	}
	
	/**
	 * Metoda podaje ilo�� dost�pnych samochod�w
	 * @return Ilo�� samochod�w
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * Metoda zmniejsza ilo�� samochod�w o 1
	 */
	public void sellStuff() {
		quantity--;
	}
	
	/**
	 * Metoda zwi�ksza ilo�� samochod�w
	 * @param amount Liczba samochod�w o jak� zostanie zwi�kszona ich ilo��
	 * @throws Exception Ilo�� mo�e zosta� zwi�kszona tylko o dodatni� liczb�
	 */
	public void addQuantity(int amount) throws Exception {
		if (amount<0) throw new Exception("Ilość musi być dodatnia");
		quantity += amount;
	}
	
	/**
	 * Metoda podaje cen� samochodu
	 * @return Cena samochodu
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Metoda zmienia cen� samochodu
	 * @param price Nowa cena samochodu
	 * @throws Exception Zmienia tylko na dodatni� cen�
	 */
	public void setPrice(double price) throws Exception {
		if(price<0) throw new Exception("Cena musi być dodatnia");
		this.price = price;
	}
	/**
	 * Metoda zwraca tekstow� posta� samochodu
	 * @return Tekstowa posta� samochodu
	 */
	public String toString(){
		return String.format("   %s , %s , %d HP , %d na stanie , %.2f zł  ",
				name, colour, horsePower, quantity, price );
	}
	
	

}
