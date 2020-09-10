package troubleshootsearch.util;
import troubleshootsearch.element.ElementI;
import troubleshootsearch.element.DataAL;
import troubleshootsearch.element.DataTree;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;

public class FileProcessor {
  String parsed;
  String techName; //technical info txt file name
  String userIName;
  List<String> fileNames = new ArrayList<>();
  FileReader fr;
  BufferedReader br;

  public FileProcessor(String... namesIn){

    for (String name : namesIn) {

      fileNames.add(name);
    }
    if (fileNames.size() !=4) {
      System.out.println("Incorrect # args.");
      System.exit(0);
    }
  } //to do: make template method

  public List<String> getArgs(){
    return fileNames;
  }
  public BufferedReader getReader() {
    if (br == null) {
      System.out.println("WARNING: Reader not initialized.");
    }
    return br;
  }

  public void populate(ElementI element) {
    try {
      fr = new FileReader(fileNames.get(0));
      br = new BufferedReader(fr);

      String line;
      if (element instanceof DataAL) {
        while ((line = br.readLine()) != null) {
            element.addData(line);
        }
      }
      else if (element instanceof DataTree) {
        int lineNum = 0;
        while ((line = br.readLine()) != null) {

          lineNum++;
          String[] linebuf = line.split("\\s+");
        //  line= Integer.toString(lineNum) + " " + line;
        //  element.addData(Integer.toString(lineNum));
        //  System.out.println(line);
          for (String word : linebuf) {
            word = Integer.toString(lineNum) + ":" + word;
            element.addData(word);

          }
        }
      }
    }
    catch(FileNotFoundException e) {
      System.out.println("File could not be found.");
      e.printStackTrace();
      System.exit(0);
    }
    catch(IOException e) {
      System.out.println("An IOException has occured.");
      e.printStackTrace();

    } finally{}
  }

  public void openFile(String fileName) {
    try {
      fr = new FileReader(fileName);
      br = new BufferedReader(fr);
    } catch(FileNotFoundException e) {
      e.printStackTrace();
    }
  }

  public void openUFile(){
    try {
      fr = new FileReader(fileNames.get(1));
      br = new BufferedReader(fr);
    }
    catch(FileNotFoundException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }




}
