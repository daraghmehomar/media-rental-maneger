package project;
import java.util.*;
public class Customer implements Comparable<Customer>{
	private String name;
	private String address;
	private String plan;
	private int id;
	protected ArrayList<Media> rented = new ArrayList<Media>();
	protected ArrayList<Media> cart = new ArrayList<Media>();
	
	public Customer(int id,String name, String address, String plan) {
		this.id= id;
		this.name = name;
		this.address = address;
		this.plan = plan;
	}
	public Customer (int id,String name, String address, String plan,ArrayList<Media> cart,ArrayList<Media> rented) {
		this.id=id;
		this.name = name;
		this.address = address;
		this.plan = plan;
		this.rented=rented;
		this.cart=cart;
	}

	public String getAddress() {
		return address;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setPlan(String plan) {
		this.plan = plan;
	}
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getPlan() {
		return plan;
	}

	@Override
	public String toString() {
		return String.format("id= %-15s | name= %-15s | address= %-15s | plan= %s \n", this.id,this.name,this.address,this.plan);
	}
	
	public String toFile() {
		String s=("Customer"+"/"+this.id+"/"+this.name+"/"+this.address+"/"+this.plan+"/");
		for (int i = 0; i < (cart.size()-1); i++)
			s+= this.cart.get(i).getTitle()+",";
		if (cart.size()>0)
			s+=this.cart.get(cart.size()-1).getTitle();
		else s+=("null");
		s+="/";

		for (int i = 0; i < (rented.size()-1); i++)
			s+= this.rented.get(i).getTitle()+",";
		if (rented.size()>0)
			s+=this.rented.get(rented.size()-1).getTitle();
		else s+=("null");
		return s;
	}

	@Override
	public int compareTo(Customer o) {
		// TODO Auto-generated method stub
		return name.compareToIgnoreCase(o.name);
	}
}
