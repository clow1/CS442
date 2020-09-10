package troubleshootsearch.element;

import troubleshootsearch.visitor.VisitorI;
import java.util.List;
import java.util.ArrayList;

public class DataAL implements ElementI { //is wrapper for arraylist w/ product info
  private List<String> storage = new ArrayList<>();

  public DataAL(){}

  public void addData(String input) {
    storage.add(input);
  }


  public void accept(VisitorI visitor) {
    visitor.visit(this);
  }

  public List<String> getData(){
    return storage;
  }

  @Override
  public String toString(){
    String hold="";

    for (String info : storage) {
      hold += info + " " ;
    }
    return hold;
  }

}
