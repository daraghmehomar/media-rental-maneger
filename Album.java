package project;


public class Album extends Media implements Comparable<Media> {

	private String songs;
	private String artist;

	public Album(int code,String title, int nomberOfCopies, String artist, String songs) {
		super(code,title, nomberOfCopies);
		this.songs = songs;
		this.artist = artist;
	}

	public String getTitle() {
		return super.getTitle();
	}
	public void setSongs(String songs) {
		this.songs = songs;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public int getCode() {
		return super.getCode();
	}

	public int getNomberOfCopies() {
		return super.getNomberOfCopies();
	}

	@Override
	public String toString() {
		
		return String.format("%-6s | code= %-15d | title= %-15s | # of copies = %-5d | Artist= %-15s | Songs= %s\n", "Album",this.getCode(),this.getTitle(),this.getNomberOfCopies(),this.artist,this.songs);
	}

	@Override
	public int compareTo(Media o) {

		return super.getTitle().compareToIgnoreCase(o.getTitle());

	}

	public String getSongs() {
		return songs;
	}

	public String getArtist() {
		return artist;
	}

	@Override
	public String toFile() {
		return "Album"+"/"+this.getCode()+"/"+this.getTitle()+"/"+this.getNomberOfCopies()+"/"+this.artist+"/"+this.songs;
	}
	public void setTitle(String title) {
		super.setTitle(title);
	}
}
