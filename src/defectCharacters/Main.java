package defectCharacters;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
	/**
	 * 1. Executes a certain procedure on defect characters, like the identification of duplicates
	 * 2. Allows the analysis of the results, by showing findings and runtime statistics
	 * @throws Exception 
	 * 
	 */
	public static void main(String[] args) throws Exception {
		
		
		ProcessRuntime runtime = new ProcessRuntime();
		//int n=12;
		for (int n = 2;n<15;n++)
        {
			runtime.start("Dubletten für n = "+n);
            
        	listDuplicates(n,  new StreamDuplicateDetector(true));
        	
        	runtime.stop();
        }
    	System.out.println(runtime.getAnalysis());
	}
	
	private static void listDuplicates(int n, DuplicateDetector detector) {
		Iterator<DefectCharacter> iterator = new CharacterIterator(n);	
 
		List<Map.Entry<Integer, List<DefectCharacter>>> result = detector.groupCharactersByHashes(iterator);
		result.forEach(entry -> System.out.println(entry));
	}

	private static void listDuplicates(Iterator<DefectCharacter> iterator) {
		HashMap<Integer,Word> counts = new HashMap<Integer,Word>();

        while (iterator.hasNext()) {
            DefectCharacter next = iterator.next();
            Word duplicate = counts.get(next.hashCode());
            
            
            if ((duplicate != null) 
            		&& (!next.getWord().isMirrorOf(duplicate))
            		&& (next.word.getDenominatorWith(duplicate) == null)
            		&& (next.word.getDenominatorWith(duplicate.getMirrored()) == null)
            		&& (next.getVector() == new DefectCharacter(duplicate).getVector())
            		) {
            		System.out.println(duplicate + " is a duplicate of " + next);
            }
            else
            	counts.put(next.hashCode(), next.word);
        }
        
	}

}
