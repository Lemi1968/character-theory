package defectCharacters;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface DuplicateDetector {
	public String getTitle();
	public List<Map.Entry<Integer, List<DefectCharacter>>> groupCharactersByHashes(Iterator<DefectCharacter> iterator);
	
// from https://www.geeksforgeeks.org/java/how-to-find-duplicate-elements-in-a-stream-in-java/
//    {
//
//        // Set to store the duplicate elements
//        Set<T> items = new HashSet<>();
//
//        // Return the set of duplicate elements
//        return stream
//
//            // Set.add() returns false
//            // if the element was
//            // already present in the set.
//            // Hence filter such elements
//            .filter(n -> !items.add(n))
//
//            // Collect duplicate elements
//            // in the set
//            .collect(Collectors.toSet());
//    }
}
