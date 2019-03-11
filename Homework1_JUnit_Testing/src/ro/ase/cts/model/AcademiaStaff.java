package ro.ase.cts.model;

public class AcademiaStaff {
	private String fullName;
	private static int numberOfAcademicians;
	
	public AcademiaStaff() {
		
	}
	
	public AcademiaStaff(String fullName) {
		numberOfAcademicians++;
		this.fullName=fullName;
	}
	
	public int getNumberOfAcademicians() {
		return this.numberOfAcademicians;
	}
	
}
