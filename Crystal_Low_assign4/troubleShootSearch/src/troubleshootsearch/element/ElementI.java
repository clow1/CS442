package troubleshootsearch.element;
import troubleshootsearch.visitor.VisitorI;

public interface ElementI {

  void addData(String info);
  public abstract void accept(VisitorI visitor);

}
