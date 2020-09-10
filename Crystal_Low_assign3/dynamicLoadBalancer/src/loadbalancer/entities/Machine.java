package loadbalancer.entities;

import java.util.Map;
import java.util.HashMap;

public class Machine {


	private String hostName;
	private Map<String, Service> hostedService;

	public Machine(String name) {
		hostName = name;

		//double check to see which map data structure is best, defaulted to HashMap to have things initially compile
		hostedService = new HashMap<>();

	}

	public String getName(){
		return hostName; 
	}
	
	public Map<String, Service> getSvcs() {
		return hostedService;
	}
	//this is called in cluster's
	//addservice method
	public void addHostedSvc(Service in) {
		if (in != null &&
			!(hostedService.containsKey(in.getSvcName())) ) {
			hostedService.put(in.getSvcName(), in);
		} 
		
	}

	public void rmHostedSvc(Service in) {
		if (in != null &&
			hostedService.containsKey(in.getSvcName())) {
			hostedService.remove(in);

		}
	}
}