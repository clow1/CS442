package troubleshootsearch.visitor;
import troubleshootsearch.element.DataAL;
import troubleshootsearch.element.DataTree;
import troubleshootsearch.util.FileProcessor;
import java.util.List;
import java.util.ArrayList;

public class ExactMatch implements VisitorI {
  private List<String> techData = new ArrayList<>();
  //private List<String>

  @Override
  public void visit(DataAL element) {
    for (String data : element.getData()) {
      techData.add(data);
    }


  }

  @Override
  public void visit(DataTree element) {

  }
  @Override
  public void visit(DataAL listIn, DataTree treeIn) {

  }

  public List<String> getPhrase(String keyword) {
    List<String> lineMatches = new ArrayList<>();
    for (String line : techData) {
      if (line.contains(keyword) &&
      !(lineMatches.contains(line))) {
        lineMatches.add(line);
      }
    }
    return lineMatches;
  }

public int getListSize() {
  return techData.size();
}


}
