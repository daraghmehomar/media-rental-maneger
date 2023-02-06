package project;

abstract class  Media implements Comparable<Media>{
	private String title;
	private int nomberOfCopies ;
	private int code;
	public Media(int code,String title, int nomberOfCopies) {
		super();
		this.code=code;
		this.title = title;
		this.nomberOfCopies = nomberOfCopies;
	}

	public int getCode() {
		return code;
	}
	public void setTitle(String title) {
		this.title=title;
	}
	
	public String getTitle() {
		return title;
	}

	public int getNomberOfCopies() {
		return nomberOfCopies;
	}
	
	public void setNomberOfCopies(int numberOfcopies) {
		this.nomberOfCopies=numberOfcopies;
	}
	public abstract String toString();
	public abstract String toFile();
}
