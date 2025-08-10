package defectCharacters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class PartitionedWordIteratorTest {
	PartitionedWordIterator out;

	private void assertReturnsNothing() {
		assertFalse( out.hasNext() );
		Exception exception = assertThrows(NoSuchElementException.class, () -> out.next() );
	}

	private void assertNextIs(int[] letters) {
		assertTrue( out.hasNext() );
		assertArrayEquals( letters, out.next().getLetters());
	}
	
	private void assertLastIs(int[] letters) {
		assertNextIs( letters );
		assertReturnsNothing();
	}
	
	@Test
	void testNull() {
		out = new PartitionedWordIterator(null);
		assertReturnsNothing();
	}


	@Test
	void test0() {
		out = new PartitionedWordIterator(new Partition(new int[] {} ));
		assertReturnsNothing();
	}

	@Test
	void test1() {
		out = new PartitionedWordIterator(new Partition(new int[] {1} ));
		assertLastIs( new int[] {1});
		assertReturnsNothing();
	}
	
	@Test
	void test21() {
		out = new PartitionedWordIterator(new Partition(new int[] {1,1} ));
		
		assertNextIs( new int[] {2,1});
		assertLastIs( new int[] {1,2});
	}
	
	@Test
	void test211() {
		out = new PartitionedWordIterator(new Partition(new int[] {2,1} ));
		assertNextIs( new int[] {2,1,1});
		assertNextIs( new int[] {1,2,1});
		assertLastIs( new int[] {1,1,2});
	}
	
	@Test
	void test2211() {
		out = new PartitionedWordIterator(new Partition(new int[] {2,2} ));
		assertNextIs( new int[] {2,2,1,1});
		assertNextIs( new int[] {2,1,2,1});
		assertNextIs( new int[] {2,1,1,2});
		assertNextIs( new int[] {1,2,2,1});
		assertNextIs( new int[] {1,2,1,2});
		assertLastIs( new int[] {1,1,2,2});
	}
}
