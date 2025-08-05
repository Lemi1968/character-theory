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
		public long memory;
	}
	private Runtime systemRuntime = Runtime.getRuntime();
	private List<Entry> data = new ArrayList<Entry>();
	private Entry currentEntry;
	Boolean started = false;

	/**
	 * Starts the collection of the runtime
	 */
	public void start( ) throws Exception{
		start("");
	}
	
	/**
	 * Starts the collection of the runtime of a specific step
	 * @throws Exception 
	 */
	public void start(String stepTitle) throws Exception {
		if (started)
			throw new Exception("was already started");
		
		started = true;
		
		currentEntry = new Entry();
		currentEntry.stepTitle = stepTitle;
		currentEntry.startTime = System.currentTimeMillis(); 
		data.add(currentEntry);
	}
	
	/**
	 * Stops the collection of the runtime
	 */
	public void stop() throws Exception{
		if (!started)
			throw new Exception("was not started");
		currentEntry.stopTime = System.currentTimeMillis();
		currentEntry.memory = (systemRuntime.totalMemory()-systemRuntime.freeMemory());
		started = false;
	}
	
	/**
	 * Returns the result of the analysis as a string containing the results in a table format
	 * | Step Title | Elapsed time | Used Memory |
	 */
	
	public String getAnalysis() throws Exception{
		if (currentEntry == null)
			throw  new Exception("was not started");
		if (currentEntry.startTime == 0)
			throw  new Exception("was not started");
		if (currentEntry.stopTime == 0)
			throw  new Exception("was not stopped");
		
		String format = "%-1s %-20s %-1s %-15s %-1s %-15s %-1s";
		String lineBreak = "\n";
		String delimiter = "|";
		String separator = "====================";
		String result = "Analysis: "+ lineBreak;
		result = result + String.format(format, delimiter, "Step Title ", delimiter, "Elapsed time", delimiter, "Used Memory", delimiter) + lineBreak; 
		result = result + String.format(format, delimiter, separator, delimiter, "===============", delimiter, "===============", delimiter) + lineBreak;
		for (Entry entry:data) {
			result = result 
					+ String.format(format, delimiter, entry.stepTitle, delimiter, 
								Long.toString(entry.stopTime - entry.startTime)+"ms", delimiter,
								Long.toString(entry.memory/1000000)+"MB", delimiter) 
					+ lineBreak;
		}
		return result;
	}
}
