package defectCharacters;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Vector {

    public HashMap<Integer, Integer> coordinates = new HashMap<>();
 
	public void add(int[] content) {
		addElement(coordinates, Arrays.hashCode(content));
//		System.out.println("New coordinates added:"+Vector.contentToString(content));
	}
	@Override 
	public int hashCode() {
		// Map.hashCode() returns too many false positives
		int hashSum = 0;
		
		for (Entry<Integer, Integer> entry: coordinates.entrySet()) {
			hashSum = hashSum + entry.getKey()*entry.getValue();
			//hashSum = hashSum + Integer.rotateLeft(entry.getKey(), entry.getValue());
		}
		
		return hashSum;		
	}
    // Helper method to add an element and track its count
    private static void addElement(Map<Integer, Integer> map, Integer key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }
    
    public static String contentToString(int[] content ) {
    	String text = "";
		for (int i = 0; i < content.length; i++) {
			for (int j = 0; j < content[i]; j++)
				text = text + (i+1) + " ";
		}
    	return text;
    }
}
