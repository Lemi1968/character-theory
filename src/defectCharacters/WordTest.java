package defectCharacters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class WordTest {
	Word out = new Word( new int[] {1,2,3} );

	@Test
	void signum() {
		assertEquals(0,new Word( new int[] {} ).signum());
		assertEquals(0,new Word( new int[] {1} ).signum());
		assertEquals(1,new Word( new int[] {2,1} ).signum());
		assertEquals(-1,new Word( new int[] {1,2} ).signum());
		assertEquals(0,new Word( new int[] {1,2,2,1} ).signum());
		
		assertEquals(-1,out.signum());
		
	}	
	
	@Test
	void mirroredLetters() {
		assertArrayEquals(new int[] {3,2,1},out.getMirrored().getLetters());
		
		assertTrue(out.isMirrorOf(out.getMirrored()));
		assertFalse(out.isMirrorOf(new Word( new int[] {2})));
		assertFalse(new Word(new int[] {1,2,1,3,2}).isMirrorOf(new Word(new int[] {2,1,2,3,1})));
	}
	
	@Test
	void lastLetter() {
		assertEquals( 3, out.getLastLetter());
	}
	
	@Test
	void shortClone() {
		Word shortClone = out.getShortClone();
		assertNotEquals(shortClone, out);
		assertNotEquals(shortClone.getLetters(), out.getLetters());
		assertArrayEquals(shortClone.getLetters(), new int[] {1,2});
	}
	
	@Test
	void increaseLastLetter() {
		out.increaseLastLetter(5);
		assertArrayEquals(out.getLetters(), new int[] {1,2,8});
	}
	
	@Test
	void denominators() {
		Word a = new Word(new int[] {1,2,1,3,2});
		assertNull(out.getDenominatorWith(a));
		Word b = new Word(new int[] {1,3,2,1,2});
		assertArrayEquals(new int[] {1,2},a.getDenominatorWith(b).getLetters());
		
		b.setLetters(new int[] {3,2,2,1,1});
		assertNull(a.getDenominatorWith(b));

//		b.letters = new int[] {2,1,2,3,1};
//		assertNull(a.getDenominatorWith(b));
		
		b.setLetters(new int[] {1,2,1,2,3});
		assertNull(a.getDenominatorWith(b));
		
		a.setLetters(new int[] {5,6,1,5,1});
		b.setLetters(new int[] {5,1,5,6,1});
		assertArrayEquals(new int[] {5,1},a.getDenominatorWith(b).getLetters());
		
		a.setLetters(new int[] {1,1,2,1,1,3,1,2});
		b.setLetters(new int[] {1,1,3,1,2,1,1,2});
		assertArrayEquals(new int[] {1,1,2},a.getDenominatorWith(b).getLetters());
	
		a.setLetters(new int[] {1,1,2,1,3,1,1,2});
		b.setLetters(new int[] {1,1,3,1,2,1,1,2});
		assertNull(a.getDenominatorWith(b).getLetters());
	}

}
