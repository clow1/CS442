package loadbalancer.observer;


//Note that observers can be both svc mngrs 
//and loadblncr
public interface ObserverI {


	//update something for certain observers
	public void update(String[] msg);
	


}