package project;

public class Movie extends Media implements Comparable<Media> {

	private String rating;

	public Movie(int code,String title, int nomberOfCopies, String rating) {
		super(code,title, nomberOfCopies);

		this.rating = rating;
	}

	public String getTitle() {
		return super.getTitle();
	}

	public int getCode() {
		return super.getCode();
	}
	
	public int getNomberOfCopies() {
		return super.getNomberOfCopies();
	}

	public String toString() {

		return String.format("%-6s | code= %-15d| title= %-15s | # of copies = %-5d | rating= %-15s |\n", "Movie",this.getCode(), this.getTitle(),
				this.getNomberOfCopies(), this.rating);
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	@Override
	public int compareTo(Media o) {

		return super.getTitle().compareToIgnoreCase(o.getTitle());

	}

	public String getRating() {
		return rating;
	}

	@Override
	public String toFile() {
		
		return ("Movie/"+this.getCode()+"/"+this.getTitle()+"/"+this.getNomberOfCopies()+"/"+this.rating);
	}

}
