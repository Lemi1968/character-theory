package defectCharacters;

import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public class ClusterStreamDetector implements DuplicateDetector {

	@Override
	public String getTitle() {
		return "Streams clustered by partitions";
	}

	@Override
	public List<Entry<Integer, List<DefectCharacter>>> groupCharactersByHashes(Iterator<DefectCharacter> iterator) {
		// TODO Auto-generated method stub
		return null;
	}

}
