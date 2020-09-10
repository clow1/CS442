package loadbalancer.subject;
import loadbalancer.observer.ObserverI;


public interface SubjectI {

	public void regObserver(ObserverI observer);

	public void rmObserver(ObserverI observer);

	public void notifyAllObs(String[] update);


}