package defectCharacters;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DuplicateDetectorTest {
	private static CharacterFactory factory = new CharacterFactory();
	private static DuplicateDetector outIncludingMirrors = new StreamDuplicateDetector(false);
	private static DuplicateDetector outExcludingMirrors = new StreamDuplicateDetector(true);
	private static DuplicateDetector outClustered = new ClusterDetector();
	
	@BeforeEach
	void setUp() throws Exception {

	}
	
	void assertResultSize(DuplicateDetector out, int expectedSize, int n) {		
		Iterator<DefectCharacter> iterator = factory.getAll(n);
		List<Map.Entry<Integer, List<DefectCharacter>>> result = out.groupCharactersByHashes(iterator);
		assertEquals(expectedSize, result.size());
	}

	void assertResultSize(DuplicateDetector out, int expectedSize, int[] content) {
		Iterator<DefectCharacter> iterator = factory.getForPartition(new Partition(content));
		List<Map.Entry<Integer, List<DefectCharacter>>> result = out.groupCharactersByHashes(iterator);
		assertEquals(expectedSize, result.size());
	}

	@Test
	void returnsTitle() {
		assertNotNull(outIncludingMirrors.getTitle());
		assertNotNull(outClustered.getTitle());
	}
	@Test
	void findsMany() {
		assertResultSize(outIncludingMirrors, 3768, new int[] {3,2,2,2});
		assertResultSize(outExcludingMirrors, 0, new int[] {3,2,2,2});
	}
	@Test
	void finds13In32211() {
		// {32211,11223}, {32121, 12123}, {32112, 21123}, {31212, 21213}, {31122, 22113}
		// {23211,11232}, {23121, 12132}, {23112, 21132}, 
		//   {22311, 11322}, {22131, 13122}
		//   {21321, 12312}, {21231, 13212}
		// {13221, 12231}
		assertResultSize(outIncludingMirrors, 13, new int[] {2,2,1});
		// 1 3 2 1 2, 1 2 1 3 2
		assertResultSize(outExcludingMirrors, 1, new int[] {2,2,1});
	}
	

	@Test
	void finds1In211() {
		// {211, 112}
		assertResultSize(outIncludingMirrors, 1, new int[] {2,1});
		assertResultSize(outExcludingMirrors, 0, new int[] {2,1});
	}
	
	@Test
	void finds1In21() {
		// 2 1, 1 2
		assertResultSize(outIncludingMirrors, 1, new int[] {1,1});
		assertResultSize(outExcludingMirrors, 0, new int[] {2,1});
	}

	@Test
	void finds0In2() {
		// 
		assertResultSize(outIncludingMirrors, 0, 2);
		assertResultSize(outExcludingMirrors, 0, new int[] {2,1});
	}

	@Test
	void finds1In3() {
		// 2 1, 1 2
		assertResultSize(outIncludingMirrors, 1, 3);
		assertResultSize(outExcludingMirrors, 0, new int[] {2,1});
	}
	
	@Test
	void finds2In4() {
		// {3 1, 1 3}, {2 1 1, 1 1 2}
		assertResultSize(outIncludingMirrors, 2, 4);
		assertResultSize(outExcludingMirrors, 0, new int[] {2,1});
	}

	@Test
	void finds6In5() {
		// {4 1,1 4}, {3 2, 2 3}, {3 1 1, 1 1 3}, {2 2 1, 1 2 2}, 
		// {2 1 1 1, 1 1 1 2},{1 2 1 1, 1 1 2 1}
		assertResultSize(outIncludingMirrors, 6, 5);
		assertResultSize(outExcludingMirrors, 0, new int[] {2,1});
	}
	
	
}
