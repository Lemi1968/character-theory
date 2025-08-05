package defectCharacters;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
	/**
	 * 1. Executes a certain procedure on defect characters, like the identification of duplicates
	 * 2. Allows the analysis of the results, by showing findings and runtime statistics
	 * 
	 */
	public static void main(String[] args) {
		
		int n=18;
		
        {
            System.out.println("Dubletten für n = "+n);
            

        	long start = System.nanoTime();
        	//listClusteredDuplicates(n);

        	listDuplicates(n,  new StreamDuplicateDetector(true));
        	long finish = System.nanoTime();
        	long timeElapsed = (finish - start)/1000000;
    		showMemoryStatistics();
        	System.out.println("Analyse wurde in "+timeElapsed+"ms abgeschlossen");
        	System.out.println("runtime/n="+timeElapsed/n);
        	System.out.println("ln(runtime)/n="+Math.log(timeElapsed)/n);

        }
	}
	
	private static void listDuplicates(int n, DuplicateDetector detector) {
		CharacterFactory factory = new CharacterFactory();
		Iterator<DefectCharacter> iterator = factory.getAll(n);	
    	System.out.println(detector.getTitle());
 
		List<Map.Entry<Integer, List<DefectCharacter>>> result = detector.groupCharactersByHashes(iterator);
		result.forEach(entry -> System.out.println(entry));
	}

	private static void showMemoryStatistics() {
		Runtime rt = Runtime.getRuntime();
		System.out.println("Amount of used memory: " + ((rt.totalMemory()-rt.freeMemory())/1000000+"MB"));
	}
	
	private static void listDuplicates(Iterator<DefectCharacter> iterator) {
		HashMap<Integer,Word> counts = new HashMap<Integer,Word>();

        while (iterator.hasNext()) {
            DefectCharacter value = iterator.next();
            Word duplicate = counts.get(value.hashCode());
            
            
            if ((duplicate != null) 
            		&& (!value.getWord().isMirrorOf(duplicate))
            		&& (value.word.getDenominatorWith(duplicate) == null)
            		&& (value.word.getDenominatorWith(duplicate.getMirrored()) == null)
            		&& (value.getVector() == new DefectCharacter(duplicate).getVector())
            		) {
            		System.out.println(duplicate + " is a duplicate of " + value);
            }
            else
            	counts.put(value.hashCode(), value.word);
        }
        
	}

}
