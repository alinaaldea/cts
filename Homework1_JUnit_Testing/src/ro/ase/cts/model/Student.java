package ro.ase.cts.model;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter.DEFAULT;

import ro.ase.cts.exception.*;

public class Student extends AcademiaStaff {
	private float averageGrade;
	private StudentTitle studentTitle;
	
	public Student() {
		
	}
	
	public Student(String fullName, StudentTitle studentTitle, float averageGrade) {
		super(fullName);
		this.studentTitle = studentTitle;
		this.averageGrade = averageGrade;
	}

	public float getAverageGrade() {
		return averageGrade;
	}

	public void setAverageGrade(float averageGrade) {
		this.averageGrade = averageGrade;
	}

	public StudentTitle getStudentTitle() {
		return studentTitle;
	}

	public void setStudentTitle(StudentTitle studentTitle) {
		this.studentTitle = studentTitle;
	}

	public int computeScholarshipByUniversity() throws IneligibleForScholarshipException, InvalidAverageGradeException, StudentTitleNotSupportedException {
		int scholarship = this.computeScholarship();
		if (scholarship != 0) {
			switch(this.studentTitle) {
			case ASE: 
				scholarship = this.computeScholarship() + 100;
				break; 
			case POLITECHNICS:
				scholarship = this.computeScholarship() + 150;
				break;
			case MEDICINE:
				scholarship = this.computeScholarship();
				break;
			case MILITARY_ACADEMY:
				scholarship = this.computeScholarship() - 100;
				break;
			default:
				throw new StudentTitleNotSupportedException("Please implement the logic for " + this.studentTitle.toString());
			}	
		}
		else {
			throw new IneligibleForScholarshipException("This student doesn't have the minimum average grade to receive scholarship.");
		}
		return scholarship;
	}
	
	private int computeScholarship() throws InvalidAverageGradeException{
		int scholarship = 0;
		if (this.averageGrade <= 0) {
			throw new InvalidAverageGradeException ("The average grade must be a positive number.");
		}
		if ( this.averageGrade > 10 ) {
			throw new InvalidAverageGradeException ("The average grade must be a number between 0 and 10.");
		}
		if (this.averageGrade >= 9.75 && this.averageGrade <= 10.0) {
			scholarship = 1000;
		}else if (this.averageGrade >= 9.50 && this.averageGrade < 9.75) {
			scholarship = 800;
		}else if (this.averageGrade >= 9.00 && this.averageGrade < 9.50) {
			scholarship = 500;
		}
		return scholarship;
	}
	
}
