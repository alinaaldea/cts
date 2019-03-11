package ro.ase.cts.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import ro.ase.cts.exception.IneligibleForScholarshipException;
import ro.ase.cts.exception.InvalidAverageGradeException;
import ro.ase.cts.exception.StudentTitleNotSupportedException;
import ro.ase.cts.model.Student;
import ro.ase.cts.model.StudentTitle;

public class StudentTests {

	@Test
	public void testScholarshipForAseStudents() {
		Student stud = new Student("John Doe", StudentTitle.ASE, 9.30f);
		try {
			assertEquals(600, stud.computeScholarshipByUniversity(), 0.001);
		} catch (IneligibleForScholarshipException e) {
			System.out.println(e.getMessage());
		} catch (InvalidAverageGradeException e) {
			System.out.println(e.getMessage());
		} catch (StudentTitleNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testScholarShipPolitechniscStudent() {
		Student stud = new Student("Jane", StudentTitle.POLITECHNICS, 9.60f);
		try {
			assertEquals(950, stud.computeScholarshipByUniversity(), 0.001);
		} catch (IneligibleForScholarshipException e) {
			System.out.println(e.getMessage());
		}catch (InvalidAverageGradeException e) {
			System.out.println(e.getMessage());
		} catch (StudentTitleNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testScholarshipMedicineStudent() {
		Student stud = new Student("Mia", StudentTitle.MEDICINE, 10);
		try {
			assertEquals(1000,stud.computeScholarshipByUniversity(), 0.001);
		} catch (IneligibleForScholarshipException e) {
			System.out.println(e.getMessage());
		}catch (InvalidAverageGradeException e) {
			System.out.println(e.getMessage());
		} catch (StudentTitleNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testScholarshipMilitaryAcademyStudent() {
		Student stud = new Student("Mia", StudentTitle.MILITARY_ACADEMY, 9.50f);
		try {
			assertEquals(700,stud.computeScholarshipByUniversity(), 0.001);
		} catch (IneligibleForScholarshipException e) {
			System.out.println(e.getMessage());
		}catch (InvalidAverageGradeException e) {
			System.out.println(e.getMessage());
		} catch (StudentTitleNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testInvalidStudent() {
		Student stud = new Student("Mia", StudentTitle.JOURNALISM, 9.50f);
		try {
			assertEquals(700,stud.computeScholarshipByUniversity(), 0.001);
		} catch (IneligibleForScholarshipException e) {
			System.out.println(e.getMessage());
		}catch (InvalidAverageGradeException e) {
			System.out.println(e.getMessage());
		} catch (StudentTitleNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testStudentForNegativeAverageGrade() {
		Student stud = new Student("Mary", StudentTitle.ASE, -10);
		try {
			System.out.println(stud.computeScholarshipByUniversity());
			fail("The method should not support negative values or greater than 10 for the scholarship.");
		} catch (IneligibleForScholarshipException | InvalidAverageGradeException e) {
			System.out.println(e.getMessage());
		} catch (StudentTitleNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testStudentForOutOfBoundAverageGrade() {
		Student stud = new Student("Mary", StudentTitle.ASE, 23);
		try {
			System.out.println(stud.computeScholarshipByUniversity());
			fail("The method should not support values outside 0-10.");
		} catch (IneligibleForScholarshipException | InvalidAverageGradeException e) {
			System.out.println(e.getMessage());
		} catch (StudentTitleNotSupportedException e) {
			System.out.println(e.getMessage());
		}
	}
		
}
