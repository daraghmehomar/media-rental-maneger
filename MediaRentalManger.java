package project;

import java.io.*;
import java.util.*;

public class MediaRentalManger implements MediaRentalInt {
	private ArrayList<Media> media = new ArrayList<Media>();
	private ArrayList<Customer> customer = new ArrayList<Customer>();
	private int value = 2;
	File file = new File("file");

	public MediaRentalManger() {
		List<String> lines = new ArrayList<String>();
		Scanner sc;
		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				lines.add(sc.nextLine());
			}

		} catch (Exception e) {
			System.out.println("file wasnt found");
		}

		String[] s = new String[lines.size()];
		s = lines.toArray(s);
		for (int i = 0; i < s.length; i++) {
			ArrayList<Media> cartArray = new ArrayList<Media>();
			ArrayList<Media> rentedArray = new ArrayList<Media>();
			if (s[i].split("/")[0].equals("Customer")) {
				String[] cart = s[i].split("/")[5].split(",");
				String[] rented = s[i].split("/")[6].split(",");
				for (int j = 0; j < cart.length; j++) {
					for (int k = 0; k < media.size(); k++) {
						if (cart[j].equals(media.get(k).getTitle()))
							cartArray.add(media.get(k));
					}
				}
				for (int j = 0; j < rented.length; j++) {
					for (int k = 0; k < media.size(); k++) {
						if (rented[j].equals(media.get(k).getTitle()))
							rentedArray.add(media.get(k));
					}
				}
				customer.add(new Customer(Integer.parseInt(s[i].split("/")[1]), s[i].split("/")[2], s[i].split("/")[3],
						s[i].split("/")[4], cartArray, rentedArray));
			} else if (s[i].split("/")[0].equals("Game"))
				addGame(Integer.parseInt(s[i].split("/")[1]), s[i].split("/")[2], Integer.parseInt(s[i].split("/")[3]),
						Double.parseDouble(s[i].split("/")[4]));
			else if (s[i].split("/")[0].equals("Movie"))
				addMovie(Integer.parseInt(s[i].split("/")[1]), s[i].split("/")[2], Integer.parseInt(s[i].split("/")[3]),
						s[i].split("/")[4]);
			else if (s[i].split("/")[0].equals("Album")) {
				addAlbum(Integer.parseInt(s[i].split("/")[1].trim()), s[i].split("/")[2],
						Integer.parseInt(s[i].split("/")[3]), s[i].split("/")[4], s[i].split("/")[5]);
			}
		}
	}

	@Override
	public void addCustomer(int id, String name, String address, String plan) {
		Customer a = new Customer(id, name, address, plan);
		customer.add(a);
	}

	@Override
	public void addMovie(int code, String title, int copiesAvailable, String rating) {
		media.add(new Movie(code, title, copiesAvailable, rating));
	}

	@Override
	public void addGame(int code, String title, int copiesAvailable, double weight) {
		media.add(new Game(code, title, copiesAvailable, weight));
	}

	@Override
	public void addAlbum(int code, String title, int copiesAvailable, String artist, String songs) {
		media.add(new Album(code, title, copiesAvailable, artist, songs));
	}

	@Override
	public void setLimitedPlanLimit(int value) {
		this.value = value;
	}

	@Override
	public String getAllCustomersInfo() {
		Collections.sort(customer);
		String s = "";
		for (int i = 0; i < customer.size(); i++) {
			s += customer.get(i).toString();
		}
		return s;
	}

	public boolean updatecustomer(int id, String name, String address, String plan) {
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId() == id) {
				customer.get(i).setAddress(address);
				customer.get(i).setName(name);
				customer.get(i).setPlan(plan);
				return true;
			}
		}
		return false;
	}

	@Override
	public String getAllMediaInfo() {
		Collections.sort(media);
		String s = "";
		for (int i = 0; i < media.size(); i++) {
			s += media.get(i).toString();
		}
		return s;
	}

	@Override
	public boolean addToCart(int id, int code) {
		Customer cus = null;
		Media med = null;
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId() == id)
				cus = customer.get(i);
		}
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode() == code)
				med = media.get(i);
		}
		if (!(med == null || cus == null)) {
			cus.cart.add(med);
			return true;
		}

		return false;
	}

	@Override
	public boolean removeFromCart(int id, int code) {
		Customer cus = null;
		Media med = null;
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId() == id)
				cus = customer.get(i);
		}
		if (cus != null) {
			for (int i = 0; i < cus.cart.size(); i++) {
				if (cus.cart.get(i).getCode() == code) {
					cus.cart.remove(i);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String processRequests() {
		Collections.sort(media);
		Collections.sort(customer);
		Customer cus = null;
		String s = "";
		for (int i = 0; i < customer.size(); i++) {
			cus = customer.get(i);
			if (cus.getPlan().equalsIgnoreCase("unlimited")) {
				for (int j = 0; j < cus.cart.size(); j++) {

					Media m = equalsmedia(cus.cart.get(j).getTitle());
					if (m.getNomberOfCopies() > 0) {
						cus.rented.add(cus.cart.get(j));
						m.setNomberOfCopies(m.getNomberOfCopies() - 1);
						s += ("sending[" + m.getTitle() + "] to [" + cus.getName() + "]\n");
					}
				}
				int x = cus.cart.size();
				for (int j = 0; j < x; j++) {
					cus.cart.remove(0);
				}
			} else
				for (int j = 0; j < cus.cart.size(); j++) {
					Media m = equalsmedia(cus.cart.get(j).getTitle());
					if (m.getNomberOfCopies() > 0 && cus.rented.size() < this.value) {
						cus.rented.add(cus.cart.get(j));
						m.setNomberOfCopies(m.getNomberOfCopies() - 1);
						s += ("sending[" + m.getTitle() + "] to [" + cus.getName() + "]\n");
					}
				}
			int x = cus.cart.size();
			for (int j = 0; j < x; j++) {
				cus.cart.remove(0);
			}
		}
		return s;
	}

	public String processRequests1(int id , int code) {
		
		Customer cus = null;
		Media m= null;
		String s="";
		for (int j = 0; j < customer.size(); j++) 
			if (customer.get(j).getId()==id)
				cus = customer.get(j);
				
			if (cus.getPlan().equalsIgnoreCase("unlimited")) {
				for (int j = 0; j < cus.cart.size(); j++) {
					if (cus.cart.get(j).getCode()==code)
						 m = equalsmedia(cus.cart.get(j).getTitle());
					
					if (m.getNomberOfCopies() > 0) {
						cus.rented.add(cus.cart.get(j));
						m.setNomberOfCopies(m.getNomberOfCopies() - 1);
						cus.cart.remove(j);
						s += ("sending[" + m.getTitle() + "] to [" + cus.getName() + "]\n");
					}
				}
			} else
				for (int j = 0; j < cus.cart.size(); j++) {
					if (cus.cart.get(j).getCode()==code)
						 m = equalsmedia(cus.cart.get(j).getTitle());
					
					if (m.getNomberOfCopies() > 0 && cus.rented.size()<this.value) {
						cus.rented.add(cus.cart.get(j));
						m.setNomberOfCopies(m.getNomberOfCopies() - 1);
						cus.cart.remove(j);
						s += ("sending[" + m.getTitle() + "] to [" + cus.getName() + "]\n");
					}
				}
		
		return s;
	}
	@Override
	public boolean returnMedia(int id, int code) {
		Customer cus = null;
		Media med = null;
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId() == id)
				cus = customer.get(i);
		}
		if (cus != null) {
			for (int i = 0; i < cus.rented.size(); i++) {
				if (cus.rented.get(i).getCode() == code) {
					cus.rented.remove(i);
				}
				for (int j = 0; j < media.size(); j++) {
					if (media.get(i).getCode() == code)
						media.get(j).setNomberOfCopies(media.get(j).getNomberOfCopies() + 1);
				}
				return true;
			}
		}
		return false;
	}

	@Override
	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) {
		ArrayList<String> aftersearch = new ArrayList<String>();
		ArrayList<Movie> movie = new ArrayList<Movie>();
		ArrayList<Game> game = new ArrayList<Game>();
		ArrayList<Album> album = new ArrayList<Album>();
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i) instanceof Movie)
				movie.add((Movie) media.get(i));
			if (media.get(i) instanceof Game)
				game.add((Game) media.get(i));
			if (media.get(i) instanceof Album)
				album.add((Album) media.get(i));

		}
		if (rating != "") {
			for (int i = 0; i < movie.size(); i++) {
				if (movie.get(i).getRating().equals(rating) && movie.get(i).getTitle().contains(title))
					aftersearch.add(movie.get(i).toString());
			}
			Collections.sort(aftersearch);
			return aftersearch;
		} else if (artist != "" || songs != "") {
			System.out.println();
			for (int i = 0; i < album.size(); i++)
				if (album.get(i).getArtist().indexOf(artist) > -1 && album.get(i).getTitle().contains(title)
						&& album.get(i).getSongs().contains(songs))
					aftersearch.add(album.get(i).toString());
		} else if (title != "" && rating == "" && artist == "" && songs == "") {
			for (int i = 0; i < media.size(); i++) {
				if (media.get(i).getTitle().equals(title))
					aftersearch.add(media.get(i).toString());
			}
		}
		if (title == "" && rating == "" && artist == "" && songs == "") {
			System.out.println("cannot search");
			return null;
		}
		return aftersearch;
	}

	public String searchCustomer(int id) {
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId() == id)
				return customer.get(i).toString();
		}

		return "no one has this id";
	}

	public String searchMedia(int id) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode() == id)
				return media.get(i).toString();
		}

		return "no media has this code";
	}

	public boolean deleteMedia(int id) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode() == id) {
				media.remove(i);
				return true;
			}
		}
		return false;
	}

	public boolean updateAlbum(int code, String title, int noc, String artist, String songs) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode() == code && media.get(i) instanceof Album) {
				Album al = (Album) media.get(i);
				al.setArtist(artist);
				al.setNomberOfCopies(noc);
				al.setSongs(songs);
				al.setTitle(title);
				return true;
			}
		}
		return false;
	}

	public boolean updateMovie(int code, String title, int noc, String rating) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode() == code && media.get(i) instanceof Movie) {
				Movie al = (Movie) media.get(i);
				al.setNomberOfCopies(noc);
				al.setRating(rating);
				al.setTitle(title);
				return true;
			}
		}
		return false;
	}
	public Media media(int code) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode() == code)
				return media.get(i);
		}
		return null;
	}

	public boolean deleteCustomer(int id) {
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId() == id) {
				customer.remove(i);
				return true;
			}
		}
		return false;
	}

	public void exit() {
		try {
			PrintWriter pw = new PrintWriter(file);
			for (int i = 0; i < media.size(); i++) {
				pw.println(media.get(i).toFile());
			}
			for (int i = 0; i < customer.size(); i++) {
				pw.println(customer.get(i).toFile());
			}
			pw.close();
		} catch (FileNotFoundException e) {
			System.out.println("did find file");
		}
	}

	private Media equalsmedia(String s) {
		Media m = null;
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getTitle().equals(s))
				m = media.get(i);
		}
		return m;
	}

	public Customer customer(int id) {
		for (int i = 0; i < customer.size(); i++) {
			if (customer.get(i).getId() == id)
				return customer.get(i);
		}
		return null;
	}

	public boolean updateGame(int code, String title, int noc, double weight) {
		for (int i = 0; i < media.size(); i++) {
			if (media.get(i).getCode() == code && media.get(i) instanceof Game) {
				Game al = (Game) media.get(i);
				al.setWieght(weight);
				al.setNomberOfCopies(noc);
				al.setTitle(title);
				return true;
			}
		}
		return false;
	}
}
