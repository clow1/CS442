package studentCoursePlanner.courses;
import java.util.Arrays;
import java.util.Set;
import java.util.LinkedList;

public class Group {
	private int numGroups;
	private LinkedList<Character> data[];

	public Group(int vertices) {
		numGroups = vertices;
		data = new LinkedList[numGroups];
		for (int i = 0; i < numGroups; i++) {
			data[i] = new LinkedList();
		}

	} 

	public void addEdge(int loc, char c) {
		data[loc].add(c);
	}

	public LinkedList<Character>[] getCourses() {
		return data;
	}


	public void printGroup() {
		for (int i = 0; i < numGroups; i++ ) {
			if (data[i].size() >0) {
				System.out.print("Group " + (i+1) +": " );

				for (int j = 0; j < data[i].size(); j++) {
					System.out.print(data[i].get(j)+ " ");
				}
			}System.out.println("\n");
		}
	}


 }
