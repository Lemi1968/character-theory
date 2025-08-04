package defectCharacters;

public class DefectCharacter {
	static int n = 80;
	public Word word;
	public DefectCharacter(Word word) {
        this.word = word; 
	}
	public Word getWord( ) {
		return this.word;
	}
	
	@Override
	public String toString() {
		return this.word.toString();
	}
	@Override
	public int hashCode() {
		// The goal is to go recursively through all partitions of the characters word,
		// thereby collecting the content _and_ adding the vectors

		Vector vector = new Vector();		
		int[] content = new int[n];
		processPartitions(this.word, content, vector);
		return vector.hashCode();
//		return Arrays.hashCode(word.letters) 
//			 + Arrays.hashCode(this.word.getMirrored().letters);
	}
	@Override
	public boolean equals(Object obj) {
		return ( this.hashCode() == obj.hashCode() );
	}
	
	public Vector getVector() {
		Vector result = new Vector();		
		int[] content = new int[n];
		processPartitions(this.word, content, result);
		return result;
	}
	private void processPartitions(Word word, int[] content, Vector vector) {
		// The key idea is to do it letter by letter:
		// 1. process all partitions for a1 a2 ... (an-1 + an)
		// 2. process all partitions for a1 a2 ... an-1, passing an as additional content, and returning the populated content
		// 3. add the content to the vector
		// 4. return the hash code of the vector
		
		// Example: 1 3 2 = pp(13,2)+pp(15)
		//     			= pp(1,32)+pp(4,2)  +  pp(1,5)+pp(6)
		//              = 321+42+51+6
		int lastLetter = word.getLastLetter();
		if (word.getLetters().length == 1) {
			content[lastLetter-1]++;
			vector.add(content);			
		} else {
			
			Word partition = word.getShortClone();
			partition.increaseLastLetter(lastLetter);
			int[] partitionContent = content.clone();
			processPartitions(partition,partitionContent,vector);
			
			Word shortenedWord = word.getShortClone();
			content[lastLetter-1]++;
			processPartitions(shortenedWord,content,vector);

		}

	}
}
