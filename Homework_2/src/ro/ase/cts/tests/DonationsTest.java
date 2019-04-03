package ro.ase.cts.tests;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.util.Collections;
import javax.naming.InvalidNameException;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ro.ase.cts.exception.AccountOverflowException;
import ro.ase.cts.exception.InvalidIBANException;
import ro.ase.cts.exception.InvalidSumException;
import ro.ase.cts.models.Donation_List;
import ro.ase.cts.models.Donor;

public class DonationsTest {
	private Donation_List donationList;
	
	@Before
	public void setUp() {
		donationList = new Donation_List();
	}
	
	@Test
	public void testAddDonationNormalSum() {
		Donor donor = new Donor("Nico", "RO1234567891234567891234", 140.50);
		try {
			donationList.addDonation(donor);
		} catch (InvalidSumException | InvalidIBANException | InvalidNameException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testAddDonationLowerBoundary() {
		Donor donor = new Donor("Nico", "RO1234567891234567891234", 0);
		try {
			donationList.addDonation(donor);
		} catch (InvalidSumException | InvalidIBANException | InvalidNameException e) {
			System.out.println(e.getMessage());
		}
	}
	@Test
	public void testAddDonationUpperBoundary() {
		Donor donor = new Donor("Nico", "RO1234567891234567891234", 5000);
		try {
			donationList.addDonation(donor);
		} catch (InvalidSumException | InvalidIBANException | InvalidNameException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testAddDonationWithNegativeSum() {
		Donor donor = new Donor("Mia", "RO1234567891234567891234", -20);
		try {
			this.donationList.addDonation(donor);
			fail();
		}catch (InvalidSumException | InvalidIBANException | InvalidNameException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	@Test
	public void testAddDonationWithRightIBAN() {
		Donor donor = new Donor("Mia", "RO1234567891234567891234", 50);
		try {
			this.donationList.addDonation(donor);
		}catch (InvalidSumException | InvalidIBANException | InvalidNameException e) {
			System.out.println(e.getMessage());
		} 
	}
	
	@Test 
	public void testAddDonationWithInvalidIBAN() {
		Donor donor = new Donor("Nina", "RO12345.!4567891234", 190);
		try {
			this.donationList.addDonation(donor);
			fail("Invalid IBAN exception was not thrown.");
		} catch (InvalidSumException | InvalidIBANException | InvalidNameException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test 
	public void testAddDonationWithNullName() {
		Donor donor = new Donor(null, "RO1234567891234567891234", 190);
		try {
			this.donationList.addDonation(donor);
			fail("Invalid name exception was not thrown.");
		} catch (InvalidSumException | InvalidIBANException | InvalidNameException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test 
	public void testAddDonationWithEmptyName() {
		Donor donor = new Donor("", "RO1234567891234567891234", 190);
		try {
			this.donationList.addDonation(donor);
			fail("Invalid name exception was not thrown.");
		} catch (InvalidSumException | InvalidIBANException | InvalidNameException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testTotalSumForDonorByName() {
		Donor donor1 = new Donor("Mia", "RO1234567891234567891234", 100);
		Donor donor2 = new Donor("Mia", "RO1234567891234567891LEI", 50.5);
		double expected = 150.5;
		try {
			donationList.addDonation(donor1);
			donationList.addDonation(donor2);
			assertEquals(expected, donationList.getTotalSumPerDonor("Mia"), 0.001);
		} catch (InvalidSumException | InvalidIBANException | InvalidNameException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDonorExistence() {
		Donor donor = new Donor("Lara", "RO1234567891234567891RON", 400);
		try {
			donationList.addDonation(donor);
			if(donationList.searchForDonorInList(donor))
				assertTrue(true);
		} catch (InvalidSumException | InvalidIBANException | InvalidNameException  e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testInverseRelationship() {
		Donor d1 = new Donor("Lara", "RO1234567891234567891RON", 400);
		Donor d2 = new Donor("Mia", "UK1234567800004567891RON", 900);
		try {
			boolean exists = false;
			donationList.addDonation(d1);
			exists = donationList.searchForDonorInList(d2);
			assertNotEquals("We shouldn't find the donor d2 in list since it was not added", true, exists);
		}catch (InvalidSumException | InvalidIBANException | InvalidNameException  e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void crossCheckDonorFrequency() {
		Donor donor1 = new Donor("Paul", "RO1234567891234567891LEI", 100);
		Donor donor2 = new Donor("Paul", "RO1234567891234567891LEI", 200);
		Donor donor3 = new Donor("Paul", "RO1234567891234567891LEI", 300);
		
		try {
			donationList.addDonation(donor1);
			donationList.addDonation(donor2);
			donationList.addDonation(donor3);
			int expectedValue = Collections.frequency(donationList.getAllDonations(), new Donor("Paul", "RO1234567891234567891LEI", 400));
			assertEquals(expectedValue, donationList.computeDonorFrequencyInList(new Donor("Paul", "RO1234567891234567891LEI", 400)));
		} catch (InvalidNameException | InvalidSumException | InvalidIBANException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testConstructorPerformace() {
		Donor donor  = null;
		long startTime = System.currentTimeMillis();
		for(int i=0;i<2000;i++) {
			donor = new Donor("Name "+i, "RO1234567891234567891LEI",i+10);
			try {
				donationList.addDonation(donor);
			} catch (InvalidNameException | InvalidSumException | InvalidIBANException e) {
				e.printStackTrace();
			}
		}
		long stopTime = System.currentTimeMillis();
		assertTrue(stopTime - startTime < 2000);
		
		
	}

	
}
