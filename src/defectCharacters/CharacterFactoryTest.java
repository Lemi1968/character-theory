package defectCharacters;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Iterator;
import org.junit.jupiter.api.Test;

public class CharacterFactoryTest {
	
	@Test
	void filtersBySignum() {
		int n = 6;
		CharacterFactory out = CharacterFactory.getInstance();
		Iterator<DefectCharacter> iterator = out.getAll(n);
		//Iterator<DefectCharacter> filteredIterator = new FilteredIterator(iterator);
	}
	
	@Test
	void iteratesOverAll() {
		int n = 6;
		CharacterFactory out = CharacterFactory.getInstance();
		Iterator<DefectCharacter> iterator = out.getAll(n);
		assertNotNull(iterator);
		

		int number;
		for (number = 0; iterator.hasNext();number++)
			assertNotNull(iterator.next());
		
		assertEquals(32,number);

	}
	
	void assertNextLetters(int[] exp, Iterator<DefectCharacter> out) {
		assertTrue(out.hasNext());
		assertArrayEquals(exp,out.next().word.getLetters());
	}
	
	@Test
	void iteratesOverPartitions() {
		CharacterFactory out = CharacterFactory.getInstance();
		
		// 3 2 2 1  => 
		// 3 2 1 2, 3 1 2 2, 2 3 2 1, 2 3 1 2, 2 2 3 1, 2 2 1 3,
		// 1 3 2 2, 1 2 3 2, 1 2 2 3
		Partition p3221 = new Partition( new int[] {1,2,1} );
		Iterator<DefectCharacter> iterator = out.getForPartition(p3221);
		assertNotNull(iterator);
		
		assertNextLetters(new int[] {3,2,2,1}, iterator);
		assertNextLetters(new int[] {3,2,1,2}, iterator);		
		assertNextLetters(new int[] {3,1,2,2}, iterator);	
		assertNextLetters(new int[] {2,3,2,1}, iterator);
		assertNextLetters(new int[] {2,3,1,2}, iterator);
		assertNextLetters(new int[] {2,2,3,1}, iterator);
		assertNextLetters(new int[] {2,2,1,3}, iterator);
		assertNextLetters(new int[] {2,1,3,2}, iterator);
		assertNextLetters(new int[] {2,1,2,3}, iterator);
		assertNextLetters(new int[] {1,3,2,2}, iterator);
		assertNextLetters(new int[] {1,2,3,2}, iterator);
		assertNextLetters(new int[] {1,2,2,3}, iterator);
		
		assertFalse(iterator.hasNext());
	}

}
