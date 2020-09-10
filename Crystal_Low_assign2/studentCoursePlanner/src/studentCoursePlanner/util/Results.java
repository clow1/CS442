package studentCoursePlanner.util;
import studentCoursePlanner.student.Student;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileWriter;
import java.io.FileNotFoundException;


public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	
	String fileName;
	Student s;
	public Results(Student student){
		s = student;
	}

	public void writeToFile(String name){
		FileWriter writer = null;
		try {
			fileName = name;
			String studentData = s.toString();
			writer = new FileWriter(fileName);
			writer.write(studentData, 0, studentData.length());

		} catch(FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
			System.exit(0);
			

		} catch(IOException e) {
			System.out.println("IOException!");
			e.printStackTrace();
			System.exit(0);
		} finally {
			try {
				writer.close();
			} catch(IOException e) {
				System.out.println("FileWriter cannot close because it doesn't exist.");
				e.printStackTrace();
				System.exit(0);
			}finally{}
			
		}
	}

	public void printToConsole(){
		String studentData = s.toString();
		System.out.println(s.toString());
		
	}
}
