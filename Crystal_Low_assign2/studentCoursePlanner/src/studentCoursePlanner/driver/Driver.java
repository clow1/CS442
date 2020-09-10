package studentCoursePlanner.driver;
import studentCoursePlanner.util.FileProcessor;
import studentCoursePlanner.state.State;
import studentCoursePlanner.courses.Group;
import studentCoursePlanner.courses.Course;
import studentCoursePlanner.student.Student;
import studentCoursePlanner.util.Results;


/**
 * @author Crystal Low
 *
 */
public class Driver {
	public static void main(String[] args) {

		/*
		 * As the build.xml specifies the arguments as argX, in case the
		 * argument value is not given java takes the default value specified in
		 * build.xml. To avoid that, below condition is used
		 */
		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
			System.exit(0);
		}

		char asciiVal = 'A';
		
		Group group = new Group(5);
	
		for (int g = 0; g < 5; g++) { // g < number of groups
			
			for (int inGroup = 0; inGroup < 4; inGroup++) {
				if (g == 0) {
					group.addEdge(g, asciiVal);	
				} else if (g == 1) {
					group.addEdge(g, asciiVal);
				} else if ( g== 2) {
					group.addEdge(g, asciiVal);
				} else if (g == 3) {
					group.addEdge(g, asciiVal);
				} else if (g == 4) {
					for (int i = 0; i < 10; i ++ ) {
						group.addEdge(g, asciiVal);
						asciiVal++;
					} break;
				}

				asciiVal++;
			}	
		}

		FileProcessor f = new FileProcessor(args[0]);
		Student student = new Student();
		student.setInfo(f);
		student.audit(group);
		student.toString();

		////RESULTS HERE////
		Results r = new Results(student);

		r.printToConsole();
		r.writeToFile(args[1]);
			
	}
}
