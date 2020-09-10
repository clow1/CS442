package loadbalancer.observer;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import loadbalancer.entities.Service;

public class ServiceManager implements ObserverI {

	//private String key;
	private String url;
	private String serviceName;
	private List<String> hostNames; //hostname of machines
	private List<String> unavailHosts;

	public ServiceManager(String inUrl){
		url = inUrl;
		hostNames = new ArrayList<>();
		unavailHosts = new ArrayList<>();
	}
	public void update(String[] op){
		String scaleD = "CLUSTER_OP__SCALE_DOWN";
		String addInst = "SERVICE_OP__ADD_INSTANCE";
		String param = op[0];
		if (param.equals(scaleD)) {
				
			hostNames.remove(op[1]);
			
		}

		else if (param.equals(addInst)) {
			String sName = op[1];
			hostNames.add(op[2]);
		}
	
	}

	public List<String> getHostNames() {
		return hostNames;
	}

	public String getURL() {
		return url;
	}

	public void initHN(List<String> hNames) {
		hostNames.addAll(hNames);

		//for (int i = 0; i < hNames.size(); i++) {
		//WORKS
			//System.out.println("HI" + hostNames.get(i));
		//}
	}
	
	public String delegateHost() {
		String delegated ="";
		if (!hostNames.isEmpty()) {
			delegated = hostNames.get(0);
			hostNames.remove(0);
			unavailHosts.add(delegated);
		} 
		return delegated;
	}
}