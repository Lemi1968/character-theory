package defectCharacters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitionTest {
	Partition a;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		a = new Partition( new int[] {3} );
	}
	
	void assertIsSame(Partition a, Partition b) {
		assertEquals(0,a.compareTo(b));
		assertEquals(a,b);
	}
	void assertIsSmaller(Partition a, Partition b) {
		assertEquals(-1,a.compareTo(b));
	}

	@Test
	void equality() {
		assertIsSame(a,a);
		Partition b = new Partition (new int[] {3});
		assertIsSame(a,b);
	}
	
	@Test
	void comparison() {
		Partition b = new Partition( new int[] {2,1} );
		assertIsSmaller(a,b);
		Partition c = new Partition( new int[] {0,0,0,1});
		assertIsSmaller(a,c);
	}

}
