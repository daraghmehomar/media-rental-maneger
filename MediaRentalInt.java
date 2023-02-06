package project;
import java.util.ArrayList;

public interface MediaRentalInt {
	void addCustomer(int id,String name,String address,String plan);
	void addMovie(int code,String title, int copiesAvailable,String rating);
	void addGame(int code,String title, int copiesAvailable,double weight);
	void addAlbum(int code,String title,int copiesAvailable,String artist,String songs);
	void setLimitedPlanLimit(int value);
	String getAllCustomersInfo();
	String getAllMediaInfo();
	boolean addToCart(int id,int code);
	boolean removeFromCart(int id, int code);
	String processRequests();
	boolean returnMedia(int id,int code );
	ArrayList<String> searchMedia(String title,String rating, String artist,String songs);

}
