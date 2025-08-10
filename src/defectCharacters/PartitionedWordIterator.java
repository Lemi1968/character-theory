package defectCharacters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
/**
 * Iterates over word with the same content as a given partition, in lexicographic order
 */

public class PartitionedWordIterator implements Iterator<Word> {
	public Iterator<Word> iterator;

	private void addWords(int[] letters, int position, int[] content, List<Word> list) {
		boolean addWord = false;
		for (int i=content.length-1; i>=0; i--) {
			if (content[i] > 0) {
				addWord = true;
				letters[position] = i+1;
				content[i]--;
				addWords(letters, position+1, content, list);
				content[i]++;
			}
		}
		if (!addWord)
			list.add(new Word(letters));
	}
	public PartitionedWordIterator(Partition p) {
		List<Word> WordList = new ArrayList<Word>();

		
		if (p != null) {
			int sum = 0;
			for (int i = 0; i < p.content.length; i++)
				sum = sum + p.content[i];
			if (sum > 0)
				addWords(new int[sum], 0, p.content, WordList);			
		}
		iterator = WordList.iterator();
	}

	@Override
	public boolean hasNext() {
		return (iterator.hasNext());
	}

	@Override
	public Word next() throws NoSuchElementException {
		return (iterator.next());
	}
	

}
