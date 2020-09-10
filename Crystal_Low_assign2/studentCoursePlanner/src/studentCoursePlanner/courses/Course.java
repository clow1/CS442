package studentCoursePlanner.courses;
import studentCoursePlanner.student.Student;


public class Course{
		char name;
		Course left;
		Course right;

		public Course(char cName) {
			name = cName;
			left = null;
			right = null;
		}
	}
