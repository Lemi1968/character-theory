package defectCharacters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PartitionFactoryTest {
	private PartitionFactory out;
	
	@BeforeEach
	void setUp() throws Exception {
		out = new PartitionFactory(0);
	}
	
	private void assertContentEquals(int[] expected,Partition actual){
		assertNotNull(actual);
		assertArrayEquals(expected,actual.content);
	}
	
	@Test
	void testLargest() {
	
		assertNull( out.getLargest(0) );
		
		assertContentEquals(new int[] {1},out.getLargest(1));
		assertContentEquals(new int[] {0,0,0,0,0,1},out.getLargest(6));
		assertContentEquals(new int[] {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},out.getLargest(20));
	}
	
	@Test
	void testNext() {
		assertNull(out.getNext(out.getLargest(1)));
		
		Partition largest;
				
		largest = out.getLargest(2);

		Partition next = out.getNext(largest);
		assertContentEquals(new int[] {2},next);
		next = out.getNext(next);
		assertNull(next);
	
		// 6 => 5 1
		largest = out.getLargest(6);
		next = out.getNext(largest);
		assertContentEquals(new int[] {1,0,0,0,1},next);
		
		// 5 1 => 4 2
		next = out.getNext(next);
		assertContentEquals(new int[] {0,1,0,1},next);		

		// 4 2 => 4 1 1 
		next = out.getNext(next);
		assertContentEquals(new int[] {2,0,0,1},next);	
		
		// 4 1 1 => 3 3 
		next = out.getNext(next);
		assertContentEquals(new int[] {0,0,2},next);	
		
		// 3 3 => 3 2 1 
		next = out.getNext(next);
		assertContentEquals(new int[] {1,1,1},next);
		
		// 3 2 1 => 3 1 1 1 
		next = out.getNext(next);
		assertContentEquals(new int[] {3,0,1},next);
		
		// 3 1 1 1 => 2 2 2
		next = out.getNext(next);
		assertContentEquals(new int[] {0,3},next);
		
		// 2 2 2 => 2 2 1 1
		next = out.getNext(next);
		assertContentEquals(new int[] {2,2},next);
		
		// 2 2 1 1 => 2 1 1 1 1
		next = out.getNext(next);
		assertContentEquals(new int[] {4,1},next);
		
		// 2 1 1 1 1 => 1 1 1 1 1 1 
		next = out.getNext(next);
		assertContentEquals(new int[] {6},next);
		
		assertNull(out.getNext(next));
	}	
}
