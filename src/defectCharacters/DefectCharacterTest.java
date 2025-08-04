package defectCharacters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DefectCharacterTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
	}
	
	@Test
	void testBoundaries() {
		Word word1213 = new Word(new int[] {1,2,1,3,0});

	
	}
	
	@Test
	void testProduct() {
		Word word12132 = new Word(new int[] {1,2,1,3,2});
		Word word13121 = new Word(new int[] {1,3,2,1,2});
		
		assertEquals( new DefectCharacter( word12132), 
				      new DefectCharacter( word13121) );
	}
	
	@Test
	void testBigProduct() {
		Word word12132 = new Word(new int[] {1,2,1,3,2,1,3,3,2});
		Word word13121 = new Word(new int[] {1,3,3,2,1,3,2,1,2});
		
		assertEquals( new DefectCharacter( word12132), 
				      new DefectCharacter( word13121) );
	}
	
	@Test
	void testHugeProduct() {
		//                               2 1 3 * 2 3
		//                               2 1 3 * 3 2
		Word word1 = new Word(new int[] {2,1,5,1,5,1,3,2,1,5,1,5,1,5,1,3});
		Word word2 = new Word(new int[] {2,1,5,1,5,1,5,1,3,2,1,5,1,5,1,3});
		
		assertEquals( new DefectCharacter( word1), 
				      new DefectCharacter( word2) );
	}

	@Test
	void testSimplestWords() {
		Word word1 = new Word(new int[] {1});
		Word word2 = new Word(new int[] {2});

		DefectCharacter charUnderTest = new DefectCharacter( word1 );
		DefectCharacter sameChar = new DefectCharacter( word1 );
		assertEquals( charUnderTest, sameChar );
		
		DefectCharacter differentChar = new DefectCharacter( word2 );
		assertNotEquals( charUnderTest, differentChar );
	}

	@Test
	void testSimpleWords() {
		Word word12 = new Word(new int[] {1,2});
		Word word21 = new Word(new int[] {2,1});
		
		DefectCharacter charUnderTest = new DefectCharacter( word12 );
		DefectCharacter mirroredChar = new DefectCharacter( word21 );
		
		assertEquals(mirroredChar, charUnderTest);
		
		Word word1112 = new Word(new int[] {1,1,1,2});
		Word word1121 = new Word(new int[] {1,1,2,1});
		
		charUnderTest = new DefectCharacter( word1112 );
		DefectCharacter similar = new DefectCharacter(word1121);
		
		assertNotEquals(similar, charUnderTest);
		
		
	}
	
	@Test
	void testModerateWords() {
		Word word12 = new Word(new int[] {1,2,3});
		Word word21 = new Word(new int[] {3,2,1});
		
		DefectCharacter charUnderTest = new DefectCharacter( word12 );
		DefectCharacter mirroredChar = new DefectCharacter( word21 );
		
		assertEquals(mirroredChar, charUnderTest);
	}
	
	@Test
	void counterexample() {
		int[] lettersA = new int[] {4, 2, 2, 2, 4, 2, 1, 2, 1, 1};
		int[] lettersB = new int[] {2, 4, 2, 2, 2, 1, 4, 2, 1, 1};
		
		DefectCharacter charA = new DefectCharacter(new Word(lettersA));
		DefectCharacter charB = new DefectCharacter(new Word(lettersB));
		
		assertEquals(charA, charB);
		
		Vector vecA = charA.getVector();
		Vector vecB = charB.getVector();
		assertEquals(vecA.hashCode(), vecB.hashCode());
		
		assertEquals(charA.getVector(), charB.getVector());
	}

}
