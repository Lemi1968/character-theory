package defectCharacters;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamDuplicateDetector implements DuplicateDetector {
	boolean ignoreMirrored = false;
	
	public StreamDuplicateDetector(boolean filter) {
		this.ignoreMirrored = filter;
	}
	
	@Override
	public List<Map.Entry<Integer, List<DefectCharacter>>> groupCharactersByHashes(Iterator<DefectCharacter> iterator) {
		Iterable<DefectCharacter> iterable = () -> iterator;

		Stream<DefectCharacter> targetStream;
		if (ignoreMirrored)
			targetStream = StreamSupport.stream(iterable.spliterator(), true)
			                            .filter(c -> c.word.signum() == 1);
		else
			targetStream = StreamSupport.stream(iterable.spliterator(), true);			

		Map<Integer, List<DefectCharacter>> groups = targetStream.collect(Collectors.groupingBy(Object::hashCode));
		
		return groups.entrySet().stream().filter(entry -> entry.getValue().size() > 1).toList();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return "Per teil-parallelisiertem Stream";
	}

}
