package troubleshootsearch.visitor;
import troubleshootsearch.element.ElementI;
import troubleshootsearch.element.DataAL;
import troubleshootsearch.element.DataTree;
import troubleshootsearch.element.Node;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


public class SemanticMatch implements VisitorI {

  private List<String> techData = new ArrayList<>();
  private Map<String, String> synData;

  @Override
  public void visit(DataTree element) {

  }

  @Override
  public void visit(DataAL element) {
    techData.addAll(element.getData());
  }

  @Override
  public void visit(DataAL listIn, DataTree treeIn) {



  }


  public void defineSynonyms(Map<String, String> s) {
    synData = s;
//    for (String z : synData.keySet()) {
    //  System.out.println(z);
  //  }
  }
  public List<String> getSynonymousPhrase(String keyword){
    List<String> lineMatches = new ArrayList<>();

    for (String z : synData.keySet()) {
      for (String line : techData) {
        String lower = line.toLowerCase();
    //  System.out.println(z + " : " + line.toLowerCase());
        if (lower.contains(z) || lower.contains(synData.get(z))) {
          lineMatches.add(line);

        }
      }
    }
     return lineMatches;
  }


}
