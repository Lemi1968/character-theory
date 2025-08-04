package defectCharacters;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ClusterDetector implements DuplicateDetector {

	@Override
	public String getTitle() {
		return "Clustered by Partitions";
	}

	@Override
	public List<Entry<Integer, List<DefectCharacter>>> groupCharactersByHashes(Iterator<DefectCharacter> iterator) {
		DuplicateDetector streamDetector = new StreamDuplicateDetector(false);
		PartitionFactory partitionFactory = new PartitionFactory();

		
		return null;
	}

}
