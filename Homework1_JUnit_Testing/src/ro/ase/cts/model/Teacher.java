package ro.ase.cts.model;

import ro.ase.cts.exception.InvalidTeacherDepartmentException;

public class Teacher extends AcademiaStaff {
	private TeacherTitle teacherTitle;
	private float salary;
	
	public Teacher() {
	}
	
	public Teacher(String fullName, TeacherTitle teacherTitle, float salary) {
		super(fullName);
		this.teacherTitle = teacherTitle;
		this.salary=salary;
	}
	
	public TeacherTitle getTeacherTitle() {
		return teacherTitle;
	}

	public void setTeacherTitle(TeacherTitle teacherTitle) {
		this.teacherTitle = teacherTitle;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public float computeFinalSalary() throws InvalidTeacherDepartmentException {
		return this.salary + computeBonusBaseOnDepartment();
	}

	public float computeBonusBaseOnDepartment() throws InvalidTeacherDepartmentException {
		float bonus;
		switch (this.teacherTitle) {
			case CSIE:
				bonus = (float) (this.salary * 0.09);
				break;
			case ETA:
				bonus = (float) (this.salary * 0.06);
				break;
			case REI:
				bonus = (float) (this.salary * 0.08);
				break;
			default:
				throw new InvalidTeacherDepartmentException("The teacher must have have between 1 and 40 years of work.");		
		}
		return bonus;
	}
	


	
	
}
