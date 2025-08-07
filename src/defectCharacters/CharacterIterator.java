package defectCharacters;

import java.util.Iterator;

class CharacterIterator implements Iterator<DefectCharacter> {
	protected int n;
	protected int max;
	protected int current = 0;

	public CharacterIterator(int n) {
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