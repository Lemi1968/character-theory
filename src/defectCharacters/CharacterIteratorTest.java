package defectCharacters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

class CharacterIteratorTest {

	@Test
	void test() {
		int n = 6;
		Iterator<DefectCharacter> iterator = new CharacterIterator(n);
		assertNotNull(iterator);
		

		int number;
		for (number = 0; iterator.hasNext();number++)
			assertNotNull(iterator.next());
		
		assertEquals(32,number);
	}

}
