package troubleshootsearch.visitor;
import troubleshootsearch.element.ElementI;
import troubleshootsearch.element.DataAL;
import troubleshootsearch.element.DataTree;

public interface VisitorI {
  void visit(DataAL element);
  void visit(DataTree element);
  void visit(DataAL listIn, DataTree treeIn);
}
