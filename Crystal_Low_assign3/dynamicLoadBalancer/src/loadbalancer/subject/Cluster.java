package loadbalancer.subject;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;
import java.lang.NullPointerException;
import loadbalancer.util.FileProcessor;
import loadbalancer.entities.Machine;
import loadbalancer.entities.Service;
import loadbalancer.observer.ObserverI;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.observer.ServiceManager;
import loadbalancer.util.Results;

//MAKE SURE -> PRINT STATEMENTS TURN INTO PRINT TO RESULTS

public class Cluster implements SubjectI{



	//hostnames => machine instances.
	private Map<String, Machine> machines;
	private List<ObserverI> observers;
	
	public Cluster(){
		//double check to see which map data structure is best, defaulted to HashMap to have things initially compile
		machines = new HashMap<>(); 
		observers = new ArrayList<>();
		
	}

	
	public void regObserver(ObserverI in){
		//System.out.println("WOAH");
		//System.out.println(in);	
		if (in != null) {
			observers.add(in);
		}
		
	}

	public void rmObserver(ObserverI in){
		observers.remove(in);
	}


	public void notifyAllObs(String[] operation){
		for (ObserverI o : observers) {
			o.update(operation);
		}
	}


	public void printObservers() {
		System.out.println(observers.size());
	}
	//Call these methods when user scales up/down
	public String scaleUp(String machineName) {
		//System.out.println("HEY");
		//String res = "";
		boolean stateChange = true;

		for(String name : machines.keySet()) {
			if (machineName.equals(name)){
				stateChange = false;
				
				break;
			}
		} if (stateChange){
			
			machines.put(machineName, new Machine(machineName));
			return("Cluster scaled up.");
			//res = "Cluster scaled up.";
			//records.addI(res);
		} else {
			return("Cannot scale up, machine exists.");
		}

	}


	public String scaleDown(String machineName) {
		boolean stateChange = false;

		for (String name : machines.keySet()) {
			if (machineName.equals(name)) {
				stateChange = true;
				break;
			}

		} if (stateChange) {
			machines.remove(machineName);
			return ("Cluster scaled down.");
			/*must notify. todo: work on the observers 
			so that we may develop a means to efficiently
			notify them, given that we have all the
			instructions inside our array opData rn.*/



			

			//NOTIFY SERVICE MANAGERS


		} else {
			return ("Cannot scale down; machine DNE.");
		}
	}

	
	public String addService(String serviceName, String url, List<String> hostNames) {
		

		boolean stateChange = false;


		for (Map.Entry<String, Machine> entry : machines.entrySet()) {
			
			String entryName = entry.getKey();
			
				// Systemm.out.println(entryName);
			for (String name : hostNames) {
				if (name.equals(entryName)) {
					stateChange = true;

					entry.getValue()
						.addHostedSvc(new Service(url, serviceName));


				} 
			
			}
		
		} if (!stateChange) {
			return("Service " +
			serviceName + " cannot be added to Machine."); //print this to results
		} else {
			return("Service added.");
		}
			

	}

	public String removeService(String serviceName){

		boolean stateChange = false;
		
		for (Map.Entry<String, Machine> entry : machines.entrySet()) {

			Machine m = entry.getValue();
			
			for (Map.Entry<String, Service> svcName :
			 m.getSvcs().entrySet()) {
				if (svcName.getKey().equals(serviceName)) {
					m.rmHostedSvc(svcName.getValue());
					stateChange = true;
					//System.out.println("WOAH");
					break;
				}
			}
		}
		if (stateChange) {
			return("Service removed.");
		} 

		return("Service could not be removed.");
	}



	public String addInstance(String serviceName, String hostName) {
		boolean stateChange = false;
		boolean serviceNameMatch = false;
		String svmURL = "";
		String svName = "";
		for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i) instanceof LoadBalancer) {
				LoadBalancer loadRef = (LoadBalancer) observers.get(i);
				for (Map.Entry<ServiceManager, Service> s :
					loadRef.getSvcMap().entrySet()) {
						//System.out.println("hey" + s.getValue().getSvcName());
					svName = s.getValue().getSvcName();
					svmURL = s.getKey().getURL(); 
					if (svName.equals(serviceName)) {
						serviceNameMatch = true;
					}
						
				}
			}
		}
		if (serviceNameMatch) {
			for (String mName : machines.keySet()) {
				if (hostName.equals(mName)) {
					Machine m = machines.get(mName);
				m.addHostedSvc(new Service(svmURL, serviceName));
					stateChange = true;
					break;
			}
		}
			if (stateChange) {
				return("Instance added.");
			} else {
				return("Service not added.");
			}
		}
		else {
			return("No service found.");

		}
	
	}

	public String removeInstance(String serviceName, String hostName) {
		boolean stateChange = false;
		boolean serviceNameMatch = false;
		String svmURL = "";
		String svName = "";
		for (int i = 0; i < observers.size(); i++) {
			if (observers.get(i) instanceof LoadBalancer) {
				LoadBalancer loadRef = (LoadBalancer) observers.get(i);
				for (Map.Entry<ServiceManager, Service> s :
					loadRef.getSvcMap().entrySet()) {
						//System.out.println("hey" + s.getValue().getSvcName());
					svName = s.getValue().getSvcName();
					svmURL = s.getKey().getURL(); 
					if (svName.equals(serviceName)) {
						serviceNameMatch = true;
					}
						
				}
			}
		}
		if (serviceNameMatch) {
			for (String mName : machines.keySet()) {
				if (hostName.equals(mName)) {
					Machine m = machines.get(mName);
				//m.addHostedSvc(new Service(serviceName));
					stateChange = true;
					break;
			}
		}
			if (stateChange) {
				return("Instance removed.");
			} else {
				return("Service not exist.");
			}
		}
		
			return("No service found.");

		
	}
	
}
