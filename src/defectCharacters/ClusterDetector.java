package defectCharacters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ClusterDetector implements DuplicateDetector {
	private int n;
	
	public ClusterDetector(int n) {
		this.n = n;
	}
	
	@Override
	public String getTitle() {
		return "Clustered by Partitions";
	}

	@Override
	public List<Entry<Integer, List<DefectCharacter>>> groupCharactersByHashes(Iterator<DefectCharacter> iterator) {
		List<Map.Entry<Integer, List<DefectCharacter>>> result = new ArrayList<Map.Entry<Integer, List<DefectCharacter>>>();
		DuplicateDetector streamDetector = new StreamDuplicateDetector(true);
		PartitionIterator partitionIterator = new PartitionIterator(n);
		
		while (partitionIterator.hasNext()) {
			Partition p = partitionIterator.next();
			PartitionedWordIterator wordIterator = new PartitionedWordIterator(p);
			CharacterIterator characterIterator = new CharacterIterator(wordIterator);
			result.addAll( streamDetector.groupCharactersByHashes(characterIterator) );
		}

		
		return result;
	}

}
