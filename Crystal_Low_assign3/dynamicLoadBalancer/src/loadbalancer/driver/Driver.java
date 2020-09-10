package loadbalancer.driver;
import loadbalancer.subject.Cluster;
import loadbalancer.subject.Operation;
import loadbalancer.entities.Machine;
import loadbalancer.observer.ServiceManager;
import loadbalancer.observer.ObserverI;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.util.FileProcessor;
import loadbalancer.util.Results;

import java.util.List;
import java.util.Arrays;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Crystal Low
 *
 */
public class Driver {
	public static void main(String[] args) {


		if (args.length != 5 || args[0].equals("${arg0}") || args[1].equals("${arg1}")) {
			System.err.println("Error: Incorrect number of arguments. Program accepts 3 arguments.");
			System.exit(0);
		}


		FileProcessor f = new FileProcessor(args[0]);
		Cluster c = new Cluster();


		ObserverI loadb = new LoadBalancer(c);

		c.regObserver(loadb);


		f.process(c, (LoadBalancer) loadb);


		Results r = new Results();
		r.setRecords(f.sendToResults());
		r.printToConsole();
		
		String txtName = args[1];
		r.writeToFile(txtName);

			
		

		
		//Cluster clusterS = new Cluster();
		
		

		/*TENTATIVE PLAN: 
			The switch statement below needs to be inside
			a while loop. We want to keep op constant and perhaps
			remove the first value of list ref after every parsed
			operation is wrung through the switch statement. We can
			consider moving things to an enum later, but for now this
			might be the most straight forward way.

			Maybe later we can move this while loop/switch to a different
			file, so that the driver class aka entry point/main can be cleaner,
			and show less guts of the written code.
		*/
		
		//String op = ref.get(0).get(0);


		//SWITCH TODO: Encapsulate it inside a SEPARATE method. 
		//Maybe can move back to Cluster. Also need to find way to remove
		//first element of ref.get(0) list. Or just a way to copy everything
		//but the first element. 

	/*	switch(op) {
			case "CLUSTER_OP__SCALE_UP":
				hostName = params.get(1);
				clusterS.scaleUp(hostName);
				break;

			case "CLUSTER_OP__SCALE_DOWN":
				hostName = params.get(1);
				clusterS.scaleDown(hostName);
				break;

			default:
			System.out.println("Invalid operation.");
			
		}
*/
		
			
	}
}
