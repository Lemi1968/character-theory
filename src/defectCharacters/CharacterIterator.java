package defectCharacters;

import java.util.Iterator;
/**
 * Iterates over all Characters following the boolean-hierarchical order
 * Can be used to generate streams
 */
class CharacterIterator implements Iterator<DefectCharacter> {
	private Iterator<Word> wordIterator;
	protected int n;
	protected int max;
	protected int current = 0;

	public CharacterIterator(int n) {
		this.wordIterator = new WordIterator(n);
	}

	@Override
	public boolean hasNext() {
		return (this.wordIterator.hasNext());
	}

	@Override
	public DefectCharacter next() {
		return new DefectCharacter(this.wordIterator.next());
	}

}