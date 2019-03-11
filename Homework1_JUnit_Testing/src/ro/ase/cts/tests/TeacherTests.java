package ro.ase.cts.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import ro.ase.cts.exception.InvalidTeacherDepartmentException;
import ro.ase.cts.model.Teacher;
import ro.ase.cts.model.TeacherTitle;

public class TeacherTests {

	@Test
	public void testCsieTeacherBonus() {
		Teacher teacher = new Teacher("Jane Doe", TeacherTitle.CSIE, 3000);
		try {
			assertEquals(270, teacher.computeBonusBaseOnDepartment(), 0.001);
		} catch (InvalidTeacherDepartmentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testEtaTeacherBonus() {
		Teacher teacher = new Teacher("Jane Doe", TeacherTitle.ETA, 3000);
		try {
			assertEquals(180, teacher.computeBonusBaseOnDepartment(), 0.001);
		} catch (InvalidTeacherDepartmentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testReiTeacherBonus() {
		Teacher teacher = new Teacher("Jane Doe", TeacherTitle.REI, 5000);
		try {
			assertEquals(400, teacher.computeBonusBaseOnDepartment(), 0.001);
		} catch (InvalidTeacherDepartmentException e) {
			System.out.println(e.getMessage());
		}
	}
	
	@Test
	public void testCsieTeacherSalary() {
		Teacher teacher = new Teacher("Jane Doe", TeacherTitle.CSIE, 1000);
		try {
			assertEquals(1090, teacher.computeFinalSalary(), 0.0001);
		} catch (InvalidTeacherDepartmentException e) {
			System.out.println(e.getMessage());
		}
	}
}
