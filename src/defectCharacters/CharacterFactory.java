package defectCharacters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CharacterFactory { 

	public static CharacterFactory getInstance() {
		return new CharacterFactory();
	}
	
	class FilteredIterator implements Iterator<DefectCharacter> {

		public FilteredIterator(Iterator<DefectCharacter> sourceIterator) {
			
		}
		
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public DefectCharacter next() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	class IteratorForAll implements Iterator<DefectCharacter> {
		protected int n;
		protected int max;
		protected int current = 0;

		public IteratorForAll(int n) {
			this.n = n;
			this.max = (int) (Math.pow(2, n-1));
		}

		@Override
		public boolean hasNext() {
			return (current < max);
		}

		@Override
		public DefectCharacter next() {

			int[] nextLetters = new int[Integer.bitCount(current)+1];

			int lastPosition = -1;
			int index = 0;
			for (int i = 0; i <= n; i++) {
				if ((current & ( 1 << i) ) != 0) {
					nextLetters[index] = i - lastPosition;
					lastPosition = i;
					index++;
				}
			}
			nextLetters[nextLetters.length-1] = n - lastPosition - 1;
			current++;
			return new DefectCharacter(new Word(nextLetters));
		}

	}

	Iterator<DefectCharacter> getAll(int n) {
		return new IteratorForAll(n);
	}
	
	class IteratorOverPartition implements Iterator<DefectCharacter> {
		private Partition p;
		private int[] nextLetters;
		
		public IteratorOverPartition(Partition p) {
			this.p = p;
			
			int sum = 0;
			for (int i = 0; i < p.content.length; i++)
				sum=sum+p.content[i];
			if (sum==0)
				nextLetters = null;
			else {
				nextLetters = new int[sum];
			}
			
			int position = 0;
			for (int i = p.content.length-1; i >= 0; i--) {
				for (int j = 0;j < p.content[i]; j++) {
					nextLetters[position] = i+1;
					position++;
				}
			}
		}

		@Override
		public boolean hasNext() {
			return (nextLetters != null);
		}

		@Override
		public DefectCharacter next() {
			if (nextLetters == null)
				return null;
			DefectCharacter result = new DefectCharacter(new Word(nextLetters));

			// Determine letters of next character:
			// - identify first letter increase from back to front
			// - replace that letter with the last one
			// - distribute all remaining letters from highest to lowest
			if (nextLetters.length <= 1)
				nextLetters = null;
			else
			{		
				int position;
				int[] content = new int[nextLetters.length];
				for (position = nextLetters.length-2;
						(position>=0) && (nextLetters[position]<=nextLetters[position+1]);
						position--)
					content[nextLetters[position]]++;
				
				if (nextLetters[position] <= nextLetters[position+1])
					// This is the lowest word within this content
					nextLetters = null;
				else {
					int highest = nextLetters[position];
					nextLetters[position] = nextLetters[position+1];
					nextLetters[position+1] = highest;
					// Distribute the remaining content
					position = position + 2;
					for (int i = 0; i < content.length-1; i++)
						for (int j = 1; j<=content[i];j++) {
							nextLetters[position] = i;
							position++;
						}
					
				}
			}
			return result;
			
		}
		
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

