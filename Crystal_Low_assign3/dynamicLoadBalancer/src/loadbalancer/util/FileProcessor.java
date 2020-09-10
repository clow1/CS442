package loadbalancer.util;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileNotFoundException;

import java.util.List;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.stream.Collectors;

import java.util.regex.Pattern;

import loadbalancer.util.Results;
import loadbalancer.subject.Cluster;
import loadbalancer.observer.LoadBalancer;
import loadbalancer.observer.ObserverI;
import loadbalancer.observer.ServiceManager;


public class FileProcessor {
	public String fileName;
	private FileReader in;
	private BufferedReader bIn;
	private List<String> record;
	
	//private List argv;

	public FileProcessor(String f){
		fileName = f;
		record = new ArrayList<>();
		try {
			in = new FileReader(fileName);
			bIn = new BufferedReader(in);

		}catch(FileNotFoundException e) {
			System.out.println("FileNotFoundError:"+e.getMessage());
				e.printStackTrace();
				System.exit(0);	
		} 

		
	}

	public void process(Cluster cIn, LoadBalancer lb){
		try{

			//VARS USED TO PARSE EACH LINE//

			String line;
			String[] buff;
			String hostName; // a single param for scale up/down
			
			String servURL;
			String servName;
			List<String> hostNames;
			String parsedLine;
			//cIn.initRecords();
			while((line = bIn.readLine()) != null) {
					//operation.add(Arrays.asList(line.split("\\s")));
				buff = line.split("\\s+|(,)");
				String operation = buff[0];
				//System.out.println(Arrays.toString(buff));
				switch(operation) {
					case "CLUSTER_OP__SCALE_UP":
						
					hostName= (String)buff[1];
						
					parsedLine= cIn.scaleUp(hostName);
					record.add(parsedLine);
					break;

				case "CLUSTER_OP__SCALE_DOWN":
					hostName = (String)buff[1];
					parsedLine = cIn.scaleDown(hostName);
					record.add(parsedLine);

					cIn.notifyAllObs(buff);

					break;
				case "SERVICE_OP__ADD_SERVICE":
					
					hostNames = Arrays.asList(buff).subList(3, buff.length);

					//System.out.println(hostNames);
					servURL = buff[2]; //add checks to validate input
					servName = buff[1];

					parsedLine = cIn.addService(servName, servURL, hostNames);
					record.add(parsedLine);

					cIn.notifyAllObs(buff);
					ServiceManager s = lb.getSvcMngr(servURL);
					cIn.regObserver(s);
					//cIn.regObserver(observers.get(0).)
					break;
				case "SERVICE_OP__REMOVE_SERVICE":
					servName = buff[1];
					parsedLine = cIn.removeService(servName);
					record.add(parsedLine);
					cIn.notifyAllObs(buff);
					
					break;		
				case "REQUEST":
					servName = buff[1];
				//	lb = (LoadBalancer) lb;
					parsedLine = ("Processed request - "+
					lb.request(servName));
					record.add(parsedLine);

					//System.out.println("Request");
					break;

				case "SERVICE_OP__ADD_INSTANCE":
					servName = buff[1];
					hostName = buff[2];

					parsedLine = cIn.addInstance(servName, hostName);
					record.add(parsedLine);
					cIn.notifyAllObs(buff);
					break;
				case "SERVICE_OP__REMOVE_INSTANCE":
					servName = buff[1];
					hostName = buff[2];
					parsedLine = cIn.removeInstance(servName, hostName);
					record.add(parsedLine);
					//System.out.println("Instance Removed");
					break;
				default:

					parsedLine = ("Invalid operation."); 
					record.add(parsedLine);
				}
					
					
				
			}

		}
		catch (FileNotFoundException e) {
			System.out.println("FileNotFoundError:"+e.getMessage());
			e.printStackTrace();
			System.exit(0);
		}
		catch (IOException e) {
			System.out.println("IOError:"+ e.getMessage()); 
			e.printStackTrace();
			System.exit(0);
		}



	}


	public List<String> sendToResults() {
		return record;
	}
}
	