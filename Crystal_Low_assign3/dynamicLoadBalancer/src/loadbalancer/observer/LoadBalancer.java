package loadbalancer.observer;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import loadbalancer.subject.Cluster;
import loadbalancer.entities.Service;
import loadbalancer.observer.ServiceManager;
public class LoadBalancer implements ObserverI {


//we keep a reference of the subject in the observer in case we want to remove the coupling between the
//instance of  cluster and  particular instance between load balancer

	private Cluster clusterRef; 
	private Map<ServiceManager, Service> svcToMngr;

	public LoadBalancer(Cluster cIn) {
		svcToMngr = new HashMap<>();
		clusterRef = cIn;
	}


	public Map<ServiceManager, Service> getSvcMap() {
		return svcToMngr;
	}
	public ServiceManager getSvcMngr(String url){
		ServiceManager returnVal = null;
		for (ServiceManager s : svcToMngr.keySet()) {
			if (s.getURL().equals(url)) {
				returnVal = s;
			}
		}
		return returnVal;
	}


	public void update(String[] op){

		String addSvc = "SERVICE_OP__ADD_SERVICE";
		String requestSvc = "REQUEST";
		String remSvc = "SERVICE_OP__REMOVE_SERVICE";

		String sURL;
		String sName;
		String param = op[0];
		ServiceManager mngr;
		if (param.equals(addSvc)) {
			sName = op[1];
			sURL = op[2];
			mngr = new ServiceManager(sURL);
			
			svcToMngr.put(mngr,
				new Service(sURL, sName));

			for (ServiceManager s : svcToMngr.keySet()) {
				String svmURL = s.getURL();
				if (sURL.equals(svmURL)) {
					s.initHN(Arrays.asList(op).subList(3, op.length));
					//clusterRef.regObserver(s);
					break;
					
				}
			} //clusterRef.regObserver(v);
		}

		else if (param.equals(requestSvc)) {
			sName = op[1];
			request(sName);
		}

		else if (param.equals(remSvc)) {

			sName = op[1];
			for (Map.Entry<ServiceManager, Service> s :
			 svcToMngr.entrySet()) {
				Service toFind = s.getValue();
				if (sName.equals(toFind.getSvcName())) {
					//svcToMngr.remove(s.getKey());
					svcToMngr.remove(s.getKey());
					svcToMngr.remove(s.getValue());
					//System.out.println("S:"+ s.getValue().getSvcName());

				}
			}
		}
	}


	public String request(String svcName){
		String hostInfo = "";
		String findSvc;
		boolean hasActive = false;
		boolean isValid = false; //service is valid
		for (Map.Entry<ServiceManager, Service> sv : svcToMngr.entrySet()) {
			ServiceManager svm = sv.getKey();
			findSvc = sv.getValue().getSvcName();
			//System.out.println(findSvc);

			if (findSvc.equals(svcName) && 
				!(findSvc.equals(""))) {
				isValid = true;
				hostInfo =  "Service_URL::" +
				svm.getURL() + " Host::" + svm.delegateHost();
				
				
				
				hasActive = hostInfo.isEmpty() ? false : true;
				break;
				
			} 


		}
		if (!isValid) {
			hostInfo = "Invalid Service.";
		}
		if (!hasActive) {
			hostInfo = "Service Inactive - Service::" + svcName;

		}

		return hostInfo;

	}

	
}