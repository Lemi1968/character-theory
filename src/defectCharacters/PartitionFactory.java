package defectCharacters;

public class PartitionFactory {
	public Partition getLargest( int sum ) {
		if (sum <= 0) return null;
		int[] content = new int[sum];
		content[sum-1] = 1;
		return new Partition(content);
	}
	public Partition getNext( Partition p ) {
		// Return the next lexicographically smaller partition
		// Examples:
		// - 2 => 11
		// - 42 => 411
		// - 8 => 71 => 62 => 611 => 53 => 521 => 511 => 44 => 431 => 422 => 4211 => ...
		
		if (p.content.length == 1) return null;		
		
		// The first non-zero content bigger than 1 determines the necessary break-down
		int nonZeroIndex = 0;
		do nonZeroIndex++; 
		while ( nonZeroIndex<p.content.length && p.content[nonZeroIndex] == 0 ) ;
		
		int[] resultContent;
		
		// The highest number of the next content might be reduced by 1

		if (nonZeroIndex < p.content.length-1 || p.content[nonZeroIndex]>1) {
			resultContent = new int[p.content.length];	
			resultContent[nonZeroIndex] = p.content[nonZeroIndex]-1;
		}
		
		else
			resultContent = new int[p.content.length-1];
		
		// The first part of the next partition remains
		for (int j = p.content.length-1;j>nonZeroIndex;j--)
			resultContent[j] = p.content[j];

		
		// The rest needs to be distributed in a lexicographically maximal way
		int distribute = nonZeroIndex+1 + p.content[0];
		for (int j = nonZeroIndex-1; j>=0; j--) {
			resultContent[j] = distribute / (j+1);
			distribute = distribute % (j+1);
		}		

		return new Partition(resultContent);
	}
}
