package troubleshootsearch.element;
import java.util.List;
import java.util.ArrayList;

public class Node {
  private String word;
  private List<Integer> lineNums = new ArrayList<>(); //line numbers the word is found in.
  //the line numbers = data
  private Node left, right;


  public Node(String wordIn, int lineFoundIn) {
    word = wordIn;
    lineNums.add(lineFoundIn);

    left = null;
    right = null;
  }
  public String getWord(){
    return word;
  }
  public Node getLeft() {
    return left;
  }
  public Node getRight() {
    return right;
  }

  public List<Integer> getLNS(){
    System.out.println(word);
    return lineNums;
  }
  public void setLeft(Node leftIn) {
    left = leftIn;
  //  left.addLN(lineNum);
  }
  public void setRight(Node rightIn) {
    right = rightIn;
  //  right.addLN(lineNum);
  }
  public void addLN(int lineNumIn) {
    lineNums.add(lineNumIn);
  }
@Override
  public String toString() {
      String retVal = "";
      for (int num : lineNums) {
        retVal += num;
      }

      return retVal;
  }
}
