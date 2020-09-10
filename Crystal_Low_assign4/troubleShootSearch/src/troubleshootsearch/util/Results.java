package troubleshootsearch.util;

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
public class Results implements StdoutDisplayInterface, FileDisplayInterface {
	File toWrite;
	private List<String> r = new ArrayList<>();

	public Results() {}

	public void record(String input) {
		r.add(input);
	}

	public void writeToFile(String f){
		try(Writer w = new BufferedWriter(new OutputStreamWriter(
				new FileOutputStream(f), "utf-8"))){
				for (String s : r) {
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


	public void writeToConsole() {
		for (String line : r) {
			System.out.println(line);
		}
	}

}
