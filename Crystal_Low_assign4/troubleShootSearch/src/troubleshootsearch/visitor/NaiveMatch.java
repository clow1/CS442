package troubleshootsearch.visitor;
import troubleshootsearch.element.DataTree;
import troubleshootsearch.element.DataAL;
import troubleshootsearch.element.Node;
import java.util.ArrayList;
import java.util.List;


public class NaiveMatch implements VisitorI {
  private Node treeRoot;
  List<Node> matches;

  @Override
  public void visit(DataTree element) {
    matches = new ArrayList<>();
    treeRoot = element.getRoot();
  }

  @Override
  public void visit(DataAL element) {}

  public Node getNode() {
    return treeRoot;
  }
  @Override
  public void visit(DataAL listIn, DataTree treeIn) {}

  public void search(String keyPhrase, Node n) {
    //matches = new ArrayList<>();
    if (n == null) return;
    String nodeVal = n.getWord();
    if ( nodeVal.contains(keyPhrase)) {
      matches.add(n);
    //  System.out.println(n);
    }
    if (keyPhrase.compareTo(nodeVal) < 0) {
       search(keyPhrase, n.getLeft());
    }
       search(keyPhrase, n.getRight());
  }

  public List<Node> getResults() {
    if (matches.size() == 0) {
      System.out.println("WARNING: matches unitialized.");
    }
    return matches;
  }
///  public List<Integer> getLineNums() {
  //  System.out.println(matches);

//  }


}
