package defectCharacters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.Test;

public class PartitionIteratorTest {
	private PartitionIterator out;
	
	private void assertHasNoNext(Iterator<Partition> out) {
		assertFalse(out.hasNext());
	}
	
	private void assertNextContentEquals(int[] expected){
		assertEquals(true, out.hasNext());
		Partition actual = out.next();
		assertNotNull(actual);
		assertArrayEquals(expected,actual.content);
	}
	
	@Test
	void testZero() {
		out = new PartitionIterator(0);
		assertHasNoNext(out);
		assertNull( out.next() );
		assertNull( out.next() );
	}
	
	@Test
	void test1() {
		out = new PartitionIterator(1);
		assertNextContentEquals(new int[] {1}); // 1
		assertHasNoNext(out);
	}
	
	@Test
	void test2() {
		out = new PartitionIterator(2);
		assertNextContentEquals(new int[] {0,1}); 	// 2
		assertNextContentEquals(new int[] {2});     // 1 1
		assertHasNoNext(out);
	}
		
	@Test
	void test6() {
	
		out = new PartitionIterator(6); 
		assertNextContentEquals(new int[] {0,0,0,0,0,1}); 	// 6
		assertNextContentEquals(new int[] {1,0,0,0,1});	 	// 5 1
		assertNextContentEquals(new int[] {0,1,0,1});	 	// 4 2 
		assertNextContentEquals(new int[] {2,0,0,1});		// 4 1 1 
		assertNextContentEquals(new int[] {0,0,2});			// 3 3 
		assertNextContentEquals(new int[] {1,1,1});			// 3 2 1 
		assertNextContentEquals(new int[] {3,0,1});			// 3 1 1 1
		assertNextContentEquals(new int[] {0,3});			// 2 2 2
		assertNextContentEquals(new int[] {2,2});			// 2 2 1 1
		assertNextContentEquals(new int[] {4,1});			// 2 1 1 1 1 
		assertNextContentEquals(new int[] {6});				// 1 1 1 1 1 1
		assertHasNoNext(out);
	}
	
	@Test
	void testLarge() {	
		out = new PartitionIterator(20);
		assertNextContentEquals(new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1});
	}

}
