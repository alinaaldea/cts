package ro.ase.cts.models;

import ro.ase.cts.exception.InvalidSumException;

public class Donor {
	private String name;
	private String IBAN;
	private double sum;
	
	public Donor() {
		
	}

	public Donor(String name, String IBAN, double sum) {
		super();
		this.name = name;
		this.IBAN = IBAN;
		this.sum = sum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIBAN() {
		return IBAN;
	}

	public void setIBAN(String IBAN) {
		this.IBAN = IBAN;
	}

	public double getSum() {
		return sum;
	}

	public void setSum(double sum) throws InvalidSumException{
		this.sum = sum;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Donor other = (Donor) obj;
		return (this.name.equals(other.name) && other.name!=null 
				&& this.IBAN.equals(other.IBAN) && other.IBAN!=null);
	}

	public void displayDonorDetails() throws InvalidSumException{
		System.out.println("Donor name=" + name + ", IBAN=" + IBAN + ", sum=" + sum);
	}

}
