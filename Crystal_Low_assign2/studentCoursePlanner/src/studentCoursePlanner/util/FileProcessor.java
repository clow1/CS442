package studentCoursePlanner.util;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.ArrayList;

public class FileProcessor {
	String fileName;
	String data[];
	char cData[]; //CHOPPED OFF THE ID
	int id;
	public FileProcessor(String f){fileName = f;}

	public void parseFile(){
			try{

				FileReader in = new FileReader(fileName);
				BufferedReader bIn = new BufferedReader(in);
				String line;
				String lineSub;

				if ((line = bIn.readLine()) != null) {
					data = line.split("([\\s(:)\\W]+)");	
					
				
				} 
			}
			catch (FileNotFoundException e) {
				System.out.println("FileNotFoundError:"+e.getMessage());
				e.printStackTrace();
				System.exit(0);
			}
			catch (IOException e) {
				System.out.println("IOError:"+ e.getMessage()); 
				e.printStackTrace();
				System.exit(0);
			}
	}

	public boolean validateData() { //checks and see if data from the file is valid
	
			try{
				id = Integer.parseInt(data[0]);
				
				if (id < 1000 || 9999 < id) {
					System.out.println("Student ID out of bounds.");
					return false;
				}
		
			} catch(NumberFormatException e) {
				System.out.println("NumberFormatError:"+ e.getMessage());
				e.printStackTrace();
				System.exit(0);
			}

			String cName;
			for (int i = 1; i < data.length; i++) {
				cName = data[i];
				if (cName.matches("[^A-Z].")) return false;
			}
			
				
			return true;

			
	}

	public char[] getData() {
		cData = new char[data.length-1];
		for (int i = 1; i < data.length; ++i) {
			cData[i-1] = data[i].charAt(0);
			//System.out.println(cData[i-1]);
		} 
		//cData.
		return cData;
	}

	public int getID() {
		return id;
	}
	
}

	