package ro.ase.cts;

import ro.ase.cts.exception.InvalidTeacherDepartmentException;
import ro.ase.cts.model.AcademiaStaff;
import ro.ase.cts.model.Student;
import ro.ase.cts.model.StudentTitle;
import ro.ase.cts.model.Teacher;
import ro.ase.cts.model.TeacherTitle;

public class Application {

	public static void main(String[] args) {

		AcademiaStaff academiaStaff = new AcademiaStaff();
		Student s1 = new Student("Mia", StudentTitle.ASE, 9.52f);
		Teacher t1 = new Teacher("Cotfas", TeacherTitle.CSIE, 3400);
		Teacher t2 = new Teacher("Zurini", TeacherTitle.ETA, 3000);
		Teacher t3 = new Teacher("Jane Doe", TeacherTitle.CSIE, 1000);
		
		try {
			System.out.println(t3.computeFinalSalary());
		} catch (InvalidTeacherDepartmentException e) {
			System.out.println(e.getMessage());
		}

	}

}
