package defectCharacters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
  

public class CharacterFactory { 

	public static CharacterFactory getInstance() {
		return new CharacterFactory();
	} 
	
	Iterator<DefectCharacter> getAll(int n) {
		return new CharacterIterator(n);
	}
	
	private void addCharactersWithContent(int[] letters, int position, int[] content, List<DefectCharacter> list) {
		boolean contentFound = false;
		position++;
		for (int i = content.length-1; i>=0; i--) {
			if (content[i] > 0) {
				contentFound = true;
				int[] nextLetters = letters.clone();
				nextLetters[position] = i+1;
				int[] nextContent = content.clone();
				nextContent[i]--;
				addCharactersWithContent(nextLetters, position, nextContent, list);
			}
		}
		if (!contentFound)
			list.add(new DefectCharacter(new Word(letters)));
	}
	
	Iterator<DefectCharacter> getForPartition(Partition p) {
		List<DefectCharacter> result = new ArrayList<DefectCharacter>();
		int pSum = 0;
		for (int i = 0; i < p.content.length; i++)
			pSum = pSum + p.content[i];
		
		addCharactersWithContent(new int[pSum], -1, p.content, result);
		
		return result.iterator();
	}
}

