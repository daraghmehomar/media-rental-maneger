package project;

public class Game extends Media implements Comparable<Media> {
	
	public double getWieght() {
		return wieght;
	}
	private double wieght;

	public Game(int code,String title, int nomberOfCopies, double wieght) {
		super(code,title, nomberOfCopies);
		this.wieght = wieght;
	}

	public int getCode() {
		return super.getCode();
	}
	public String getTitle() {
		return super.getTitle();
	}

	public int getNomberOfCopies() {
		return super.getNomberOfCopies();
	}

public void setWieght(double wieght) {
		this.wieght = wieght;
	}

public String toString() {
		
		return String.format("%-6s | code= %-15d | title= %-15s | # of copies = %-5d | wieght= %-15s |\n","Game",this.getCode(),this.getTitle(),this.getNomberOfCopies(),this.wieght);
	}
	@Override
	public int compareTo(Media o) {

		return super.getTitle().compareToIgnoreCase(o.getTitle());

	}

	@Override
	public String toFile() {
		return ("Game/"+this.getCode()+"/"+this.getTitle()+"/"+this.getNomberOfCopies()+"/"+this.wieght);
	}
}
