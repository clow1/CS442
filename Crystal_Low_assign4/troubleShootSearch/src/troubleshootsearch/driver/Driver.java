package troubleshootsearch.driver;
import troubleshootsearch.util.FileProcessor;
import troubleshootsearch.util.Results;
import troubleshootsearch.element.DataAL;
import troubleshootsearch.element.DataTree;
import troubleshootsearch.element.ElementI;
import troubleshootsearch.visitor.VisitorI;
import troubleshootsearch.visitor.ExactMatch;
import troubleshootsearch.visitor.SemanticMatch;
import troubleshootsearch.visitor.NaiveMatch;
import troubleshootsearch.search.TroubleShooter;

public class Driver{


	public static void main(String[] args) {


	if (args[0].equals("${arg0}") ){
	//|| args[1].equals("${arg1}") || args[2].equals("${arg2}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 5 arguments.");
			System.exit(0);
		}
		FileProcessor fp = new FileProcessor(args);
		Results tracker = new Results();
		ElementI listData = new DataAL();
		ElementI treeData = new DataTree();

		fp.populate(listData);
		fp.populate(treeData);

		TroubleShooter ts = new TroubleShooter(fp, tracker);
		VisitorI exactV = new ExactMatch();
		VisitorI naiveV = new NaiveMatch();
		VisitorI semV = new SemanticMatch();

		ts.exact(listData,exactV);
		ts.naive(treeData, naiveV);
		ts.semantic(listData, semV);

		tracker.writeToConsole();
		tracker.writeToFile(args[3]);
	}
}
