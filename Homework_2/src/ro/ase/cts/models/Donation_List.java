package ro.ase.cts.models;
import java.util.ArrayList;
import java.util.List;
import javax.naming.InvalidNameException;
import ro.ase.cts.exception.AccountOverflowException;
import ro.ase.cts.exception.InvalidIBANException;
import ro.ase.cts.exception.InvalidSumException;
import ro.ase.cts.utils.DonationsUtils;

public class Donation_List {
	private List<Donor> donations;
	
	public Donation_List() {
		this.donations = new ArrayList<>();
	}
	
	public void addDonation (Donor c) throws InvalidSumException, InvalidIBANException, InvalidNameException {
		if(c.getName() == null || c.getName() == "") 
			throw new InvalidNameException("Name should not be empty. Please enter your name.");
		
		if(c.getSum()<=0 || c.getSum() >= 5000)
			throw new InvalidSumException("The sum should be more than $0 to make the transfer and less than $5000.");
		
		if(!DonationsUtils.checkIBAN(c.getIBAN()) || c.getIBAN().length() != 24)
			throw new InvalidIBANException("The IBAN must have 24 character. No special characters allowed.");
		
		this.donations.add(c);
	}
	
	public void removeDonation(Donor c) {
		this.donations.remove(c);
	}
	
	public double getTotalSumPerDonor(String name) {
		double sum = 0;
		for(Donor d : this.donations) {
			if(d.getName().equals(name))
				sum += d.getSum();
		}
		return sum;
	}
	
	public int computeDonorFrequencyInList(Donor donor) {
		int frequency = 0;
		for(Donor d: donations) {
			if(d.equals(donor))
				frequency++;
		}
		return frequency;
	}
	
	public void showDonations() {
		for(Donor donor : donations) {
			System.out.println(String.format("Donor %s with IBAN: %s donated $%5.2f\n", donor.getName(), donor.getIBAN(), donor.getSum()));
		}
	}
	
	public boolean searchForDonorInList(Donor d) {
		boolean found = false;
		for(Donor donor : donations) {
			if(donor.getName().equals(d.getName()) && donor.getIBAN().equals(d.getIBAN()) && donor.getSum() == d.getSum() && found == false) {
				found = true;
			}
		}
		return found;
	}
	
	public double computeTotalMoneyFromDonations() throws AccountOverflowException {
		double totalSum = 0;
		for(Donor d : this.donations) {
			totalSum += d.getSum();
		}
		return totalSum;
	}

	public List<Donor> getAllDonations() {
		return donations;
	}
	
	
}
