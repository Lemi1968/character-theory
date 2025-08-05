package defectCharacters;

import java.util.ArrayList;
import java.util.List;

/**
 * Allows the collection and analysis of the runtime across multiple steps of a process
 */
public class ProcessRuntime {
	private class Entry {
		public String stepTitle;
		public long startTime;
		public long stopTime;		
	}
	private List<Entry> runtimes = new ArrayList<>();
	Boolean started = false;
    long startTime;
    long stopTime;
    String stepTitle;

	/**
	 * Starts the collection of the runtime
	 */
	public void start( ) throws Exception{
		if (started)
			throw new Exception("was already started");
		started = true;
		startTime = System.currentTimeMillis();
	}
	
	/**
	 * Starts the collection of the runtime of a specific step
	 * @throws Exception 
	 */
	public void start(String stepTitle) throws Exception {
		this.stepTitle = stepTitle;
		start();
	}
	
	/**
	 * Stops the collection of the runtime
	 */
	public void stop() throws Exception{
		if (!started)
			throw new Exception("was not started");
		stopTime = System.currentTimeMillis();
	}
	
	/**
	 * Returns the result of the analysis as a string containing the results in a table format
	 */
	
	public String getAnalysis() throws Exception{
		if (!started)
			throw  new Exception("was not started");
		if (stopTime == 0)
			throw  new Exception("was not stopped");
		
		String result = "Analysis";
		if (stepTitle != "")
			result = result + " for step " + stepTitle;
		result = result + ":" + Long.toString(stopTime - startTime);  
		return result;
	}
}
