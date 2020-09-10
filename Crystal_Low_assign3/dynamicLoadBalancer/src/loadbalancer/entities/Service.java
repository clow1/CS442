package loadbalancer.entities;

public class Service {

	private String URL;
	private String name;

	public Service(String sURL, String sName) {
		//put 'this' as a precaution
		this.URL = sURL;
		name = sName;
	}

	public String getSvcName(){
		return name;
	}

	
	
}