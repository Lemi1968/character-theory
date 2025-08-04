package defectCharacters;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Test;

class VectorTest {

	@Test
	void contentToString() {
		int[] content = {1,0,3};
		assertEquals( "1 3 3 3 ", Vector.contentToString(content));
	}

	
	@Test
	void noFalsePositives() {
		Vector out 	= new Vector();
		out.add(new int[] {2,1,1,1});
		
		Vector different = new Vector();
		different.add(new int[] {2,1,1,1});
		
		out.add(new int[] {2,2,1});
		out.add(new int[] {2,2,1});
		out.add(new int[] {3,1,1});
		
		different.add(new int[] {2,2,1});
		different.add(new int[] {3,1,1});
		different.add(new int[] {3,1,1});
		assertNotEquals(out.hashCode(),different.hashCode());		
		
		out.add(new int[] {3,2});
		out.add(new int[] {3,2});
		out.add(new int[] {4,1});
		
		different.add(new int[] {4,1});
		different.add(new int[] {3,2});
		different.add(new int[] {4,1});
		assertNotEquals(out.hashCode(),different.hashCode());	
		
		out.add(new int[] {5});
		different.add(new int[] {5});
		assertNotEquals(out.hashCode(),different.hashCode());
		
	}
	
	@Test
	void assumptions() {
		HashMap<Integer,Integer> out = new HashMap<Integer,Integer>();
		out.put(579896160,1);
		out.put(10717665,2);
		out.put(-2122552865,1);
		out.put(-1086637471,1);
		out.put(-859930526,1);
		out.put(-1251904674,1);
		
		HashMap<Integer,Integer> out2 = new HashMap<Integer,Integer>();
		out2.put(579896160,1);
		out2.put(10717665,1);
		out2.put(-2122552865,1);
		out2.put(-1086637471,2);
		out2.put(-859930526,1);
		out2.put(-1251904674,2);
		
		assertNotEquals(out.hashCode(),out2.hashCode());
		
		Set<Entry<Integer, Integer>> entrySet  = out.entrySet();
		Set<Entry<Integer, Integer>> entrySet2 = out2.entrySet();
		
		entrySet.toArray();
		entrySet2.toArray();
		
		assertNotEquals(entrySet,out2.entrySet());
		assertNotEquals(entrySet.hashCode(), entrySet2.hashCode());
		
//		int sum1 = 0;
//		int sum2 = 0;
//		for (Entry<Integer, Integer> entry : entrySet) {
//			System.out.println("Set1: " + entry.hashCode());
//			sum1 += entry.hashCode();
//		}
//		for (Entry<Integer, Integer> entry : entrySet2) {
//			System.out.println("Set2: " + entry.hashCode());
//			sum2 += entry.hashCode();
//		}
//		
//		System.out.println("Sum Set1: " + sum1);
//		System.out.println("Sum Set2: " + sum2);	
		


	}
	
	@Test
	void multiplicity() {
		int[] content1 = {1,0,3};
		int[] content2 = {2,1,1};
		
		Vector out 	= new Vector();
		out.add(content1);
		out.add(content2);
		
		Vector same = new Vector();
		same.add(content2);
		same.add(content1);
		
		assertEquals(out.hashCode(),same.hashCode());
		
		Vector different = new Vector();
		different.add(content1);
		different.add(content2);
		different.add(content1);
		
		assertNotEquals(out.hashCode(),different.hashCode());
	}


}
