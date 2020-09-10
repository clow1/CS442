package troubleshootsearch.search;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.Results;
import troubleshootsearch.element.ElementI;
import troubleshootsearch.element.DataAL;
import troubleshootsearch.element.DataTree;
import troubleshootsearch.element.Node;
import troubleshootsearch.visitor.VisitorI;
import troubleshootsearch.visitor.ExactMatch;
import troubleshootsearch.visitor.NaiveMatch;
import troubleshootsearch.visitor.SemanticMatch;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;

public class TroubleShooter {
  FileProcessor processor;
  List<String> fileNames;
  Map<String, String> synonyms;
  Results keeper;

  public TroubleShooter(FileProcessor fIn,Results resultsIn){
    processor = fIn;
    fileNames = fIn.getArgs();
    keeper = resultsIn;

  }


  public void exact(ElementI listData, VisitorI v1) {
    try {
      if (!(v1 instanceof ExactMatch)) {
        System.out.println("Cannot use non-exact visitor.");
        System.exit(0);
      }
      if (!(listData instanceof DataAL)) {
        System.out.println("Cannot perform an exact match on tree.");
        System.exit(0);
      }
      processor.openUFile(); //FNF exception handled in FileProcessor
      String keyWord;
      //Concrete VisitorI
    keeper.record("===Exact Match===\n");
      Map<String, List<String>> results = new HashMap<>(); //REPLACE W/ RESULTS class
      List<String> resultsPerKW;
      listData.accept(v1);
    //  v1.visit((DataAL)listData);

      while ((keyWord = processor.getReader().readLine()) != null) {
        resultsPerKW = ((ExactMatch) v1).getPhrase(keyWord);
          keeper.record("user input: " + keyWord);
        if (resultsPerKW.size() != 0) {
          for (String res : resultsPerKW) {
            keeper.record(res);
          }
        } else {

            String notFound = "No exact match found for '" + keyWord +"'.";
            keeper.record(notFound);
        }

          keeper.record("--------------------");
      }

    }
    catch(IOException e) {
        e.printStackTrace();
        System.exit(0);
      }
  }

  public void naive(ElementI treeData, VisitorI v1) {
    try {
      if (!(treeData instanceof DataTree)) {
        System.out.println("Cannot perform search on list.");
        System.exit(0);
      }
      if (!(v1 instanceof NaiveMatch)) {
        System.out.println("Cannot do naive match with non-naive visitor.");
        System.exit(0);
      }
      NaiveMatch naiveV = (NaiveMatch)v1;


      processor.openUFile();
      String lineNum;
      String keyPhrase;
      String[] buff;
      keeper.record("===Naive Stemming Match===\n");
      while ((keyPhrase = processor.getReader().readLine()) != null) {
          lineNum = "";

        //  treeData.accept(naiveV);
        treeData.accept(naiveV);
        Node r = ((NaiveMatch) naiveV).getNode();
        buff = keyPhrase.split("\\s+");
        naiveV.search(buff[0], r);
        List<Node> res = ((NaiveMatch)naiveV).getResults();
          //RESULTS CLASS NEEDED:
        lineNum = res.toString().replace("[", "").replace("]","");
          //for (Node n : res) {
            ////lineNum+= n.toString() + ",";
        //  }
      keeper.record("user input - " + keyPhrase);
      keeper.record("Word count = " + res.size() + '\n' + "Line Numbers = " + lineNum);
      keeper.record("-----------------");
      }
    }
    catch(IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void semantic(ElementI data, VisitorI v) {
    try {
      if (!(v instanceof SemanticMatch)) {
        System.out.println("Cannot use a non-semantic visitor.");
        System.exit(0);
      }
      if (!(data instanceof DataAL)) {
        System.out.println("Cannot perform search on non-list.");
        System.exit(0);
      }


      SemanticMatch v1 = (SemanticMatch) v;
      String synFile = processor.getArgs().get(2);
      processor.openFile(synFile);
      synonyms = new HashMap<>();
      String line;
      String[] lb;

      //PARSE IN SYNONYMS
      while ((line = processor.getReader().readLine()) != null) {
        lb = line.split("=");
        synonyms.put(lb[0], lb[1]);

      }
      v1.defineSynonyms(synonyms);
      List<String> resultsPerKW;


      data.accept(v1);
      processor.openUFile();
      String keyWord;
      Map<String, List<String>> results = new HashMap<>(); //REPLACE
      keeper.record("===Semantic Match===\n");
      while ((line = processor.getReader().readLine()) != null) {
      //  System.out.println(line);
        lb = line.split("\\s+");
        keyWord = lb[lb.length-1];
        resultsPerKW = ((SemanticMatch) v1).getSynonymousPhrase(keyWord);
        if (resultsPerKW.size() != 0) {
          results.put(keyWord, resultsPerKW);
          keeper.record("user input: " +keyWord + ":");
          for (String res : resultsPerKW) {
            keeper.record(res);
          }
        } else {
          keeper.record("No semantic match found for '" + keyWord +"'.");
        }
          keeper.record("--------------------");

      }

    }catch(IOException e) {
      System.out.println("IO Exception @ semantic function.");
    }


  }

}
