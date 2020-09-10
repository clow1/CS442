package troubleshootsearch.element;

import troubleshootsearch.visitor.VisitorI;
import troubleshootsearch.element.Node;
import java.util.ArrayList;
import java.util.List;

public class DataTree implements ElementI {
  private Node root;
  private int nodeCount;
  public DataTree(){
    root = null;
    nodeCount = 0;
  }

  public void addData(String input) {

    String[] wbuff= input.split(":");
    String word = wbuff[1];
    int lineNum = Integer.parseInt(wbuff[0]);
    //System.out.println("Hey " + wbuff[0]);

    if (root == null) {
      root = new Node(word, lineNum);
    //  root.addLN(line number); get the line number too

    } else {
      Node curr = root;
      Node parent;
    //  System.out.println(word + "HEY");

      while (true) {
        parent = curr;
        String currVal = curr.getWord();

        if (word.compareTo(currVal) < 0)  {
          curr = curr.getLeft();
          if (curr == null) {
            parent.setLeft(new Node(word, lineNum));
            nodeCount++;
            return;
          }
        }
        else if (word.compareTo(currVal) == 0) {
      //    System.out.println("Word '" + word + "'  found.");
            modify(word, lineNum);
            nodeCount++;
            return;
        }
        else{
          curr = curr.getRight();
          if (curr == null) {
            parent.setRight(new Node(word, lineNum));
            nodeCount++;
            return;
          }
        }
      }
    }
  }

  public void modify(String toFind, int lineNumIn) {
    if (root == null) return;
    Node curr  = root;
    Node parent;
    while (true) {
      parent = curr;
      String currVal = curr.getWord();
      if (toFind.compareTo(currVal) < 0) {
        parent = curr;
        curr = curr.getLeft();
        if (curr == null){
          return;
        }
      }
      else if (toFind.compareTo(currVal) > 0) {
        parent = curr;
        curr = curr.getRight();
        if (curr == null) return;
      }

      else if (toFind.compareTo(currVal) == 0) {

      //  System.out.println("Found @ Lines: " + curr);
        //works, but need to count first time the word is introduced too.
        curr.addLN(lineNumIn);

        return;
      }
    }
  }

  public void accept(VisitorI visitor) {
    visitor.visit(this);
  }

  public void display() {
    display(root);
  }

  private void display(Node nodeIn) {
    if (nodeIn == null) return;
    display(nodeIn.getLeft());
    System.out.println(nodeIn.getWord() + " ");
    display(nodeIn.getRight());
  }

  public Node getRoot() {
    return root;
  }

}
