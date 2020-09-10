package loadbalancer.util;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;
import java.util.ArrayList;

public class Results implements FileDisplayInterface, StdoutDisplayInterface {
	
	File toWrite;
	List<String> resultInfo;
	public Results(){
		resultInfo = new ArrayList<>();	
	}

	public void setRecords(List<String> results) {
		resultInfo.addAll(results);
	}

	public void writeToFile(String f){
		//FileWriter writer = null;

		try(Writer w = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(f), "utf-8"))){
				for (String s : resultInfo) {
					w.write(s+"\n");
				}
			
		}catch(FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
			System.exit(0);
		} catch(IOException e) {
			System.out.println("IOException.");
			e.printStackTrace();
			System.exit(0);
		}catch(Exception e) {

		}
	}



	public void printToConsole(){
		for (int i = 0; i < resultInfo.size(); i++) {
			System.out.println(resultInfo.get(i));
		}
	}

}
