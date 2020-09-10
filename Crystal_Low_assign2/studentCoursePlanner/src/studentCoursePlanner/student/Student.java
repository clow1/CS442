package studentCoursePlanner.student;
import studentCoursePlanner.state.CoursePlannerStateI;
import studentCoursePlanner.state.State;
import studentCoursePlanner.courses.Group;
import studentCoursePlanner.util.FileProcessor;


import java.util.ArrayList;	
import java.util.LinkedList;
import java.util.HashSet;

public class Student {
	int sID;
	int semesters;
	ArrayList<Character> parsedData;
	ArrayList<Character> courseList;
	CoursePlannerStateI state;

	int record[];

	public Student(){
		record = new int[5];
		state = new CoursePlannerStateI();
		parsedData = new ArrayList<>();
		courseList = new ArrayList<>();

	}
	

	public String toString() {
		if (state.getState() == state.getNoGrad()) {
			return "Student cannot graduate. " + semesters;
		}
		String cListAsString = courseList.toString();
		cListAsString = cListAsString.replace("[", "").replace("]","");
		return getID() + ": " + cListAsString + " -- " + state.getChanges() + " " +
		semesters;
	}

	public void setInfo(FileProcessor f) {
		f.parseFile();
		if (!f.validateData()) {
			System.out.println("Data not valid");
			parsedData = null;
			System.exit(0);
		}
		
		else {
			sID = f.getID();
			for (int i = 0; i < f.getData().length; i++) {
				parsedData.add(f.getData()[i]);
			}
		}
	}

	void setFinalList(ArrayList<Character> courses) {
		courseList = courses;
	}


	int getID() {
		return sID;
	}
	//RECORD KEEPING FUNCTIONS //

	int getTotal(){ //total classes successfully taken
		int tot = 0;
		for (int i = 0; i < record.length; i++) {
			tot += record[i];
		} return tot;
	}

	void rState() {
		int index = -1;
		int max = record[0];
		State s;
		for (int i = 0; i < record.length; i++) {
			if (max < record[i]) {
				max = record[i];
				index = i;
			}
		} 

		if (index == 0) {
			state.s1();
		} else if (index == 1) {
			state.s2();
		} else if (index == 2) {
			state.s3();
		} else if (index == 3) {
			state.s4();
		} else if (index == 4) {
			state.s5();
		}

		///END RECORD KEEPING METHODS//

	}

	public void audit(Group courseData) {
		if (parsedData == null){
			System.out.println("Cannot audit invalid data.");

			System.exit(0);
		}


		LinkedList<Character>cData[] = courseData.getCourses();
		ArrayList<Character> take = new ArrayList<>();

		HashSet<Character> hold = new HashSet<Character>();
		
		char compare;
		//state.setState(new StateOne());

		while (getTotal() < 3) {

			compare = parsedData.get(0);	
			for (int i= 0; i < cData.length; i++) {
				if (compare ==cData[i].getFirst()) {
					hold.remove(compare);
					record[i]++;
					take.add(compare);
					rState();

					cData[i].pop();							

					if (parsedData.contains(cData[i].getFirst())){
						hold.add(cData[i].getFirst());
					}
					parsedData.remove(0);
					break;
				}else if (!(hold.contains(compare))) {
						hold.add(compare);
						parsedData.remove(0);	
				} 
			}		
		}

		semesters++;


		while (getTotal() < 10) {

			if ((getTotal() % 3 == 0)) {
				semesters++;

			}		
			for (int i = 0; i < record.length; i++) {
				if (i < 4) {
					if (record[i] < 2) {
						if (hold.contains(cData[i].getFirst())) {
							take.add(cData[i].getFirst());			
							hold.remove(cData[i].getFirst());

							cData[i].pop();
							record[i]++;
							rState();
							//break;
						} else if (parsedData.contains(cData[i].getFirst())) {
							take.add(cData[i].getFirst());
							parsedData.remove(cData[i].getFirst());
							cData[i].pop();
							record[i]++;
							rState();
						} else {
							state.noGrad();
							semesters = 0;
							System.exit(0);
								
						}
					}
				} 
				else {
						
					for (int j = 0; j < cData[i].size(); j++) {
						if (hold.contains(cData[i].getFirst())) {
							take.add(cData[i].getFirst());
							hold.remove(cData[i].getFirst());
							record[i]++;
							cData[i].pop();
							rState();
							break;

						}else if (parsedData.contains(cData[i].getFirst())) {
							take.add(cData[i].getFirst());
							parsedData.remove(cData[i].getFirst());
							record[i]++;
							cData[i].pop();
							rState();
							break;
						} 
						cData[i].pop();
					}
				}
			} 
		}



		
		setFinalList(take);


	}
	
}