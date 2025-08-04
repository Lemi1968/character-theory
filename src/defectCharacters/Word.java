package defectCharacters;

import java.util.Arrays;

public class Word {
	
	private int[] letters;
	
	@Override
	public String toString() {
		String result = "";
		for (int i = 0; i < getLetters().length; i++)
			result = result + getLetters()[i] + " ";
		return result;
	}
	
	public Word(int[] letters) {
		this.setLetters(letters.clone());
	}

	public int signum() {
		int length = getLetters().length;
		for (int i = 0; i < ( length / 2); i++)
			if (getLetters()[i] > getLetters()[length - i - 1])
				return 1;
			else if (getLetters()[i] < getLetters()[length - i - 1])
				return -1;
		return 0;
	}
	
	public Word getMirrored( ) {
		int[] mirroredLetters = new int[getLetters().length];
		for (int i = 0; i<  getLetters().length; i++) {
			mirroredLetters[i] = getLetters()[ getLetters().length - i - 1];
		}
		return new Word( mirroredLetters);
	}

	public boolean isMirrorOf( Word word) {
		if (this.getLetters().length != word.getLetters().length)
			return false;
		for (int i = 0; i<  getLetters().length; i++) 
			if ( this.getLetters()[i] != word.getLetters()[ getLetters().length - i - 1 ] )
					return false;	
		return true;
	}
	
	public int getLastLetter( ) {
		return this.getLetters()[this.getLetters().length-1];
	}
	
	public Word getShortClone( ) {
		return new Word(Arrays.copyOf(this.getLetters(),this.getLetters().length-1));
	}
	
	public void increaseLastLetter( int increase ) {
		this.getLetters()[this.getLetters().length-1] = this.getLetters()[this.getLetters().length-1] + increase;
	}
	
	public Word getDenominatorWith(Word word) {
		int length = this.getLetters().length;
		if ((length != word.getLetters().length) 
				|| (this.getLetters()[0] != word.getLetters()[0]) 
				|| (this.getLetters()[length-1] != word.getLetters()[length-1]) )
			return null;
		else {
			int connection = this.getLetters()[0]+this.getLetters()[length-1];
			int position = 1;
			for (int i = 1; (i < length) 
							&& ( (this.getLetters()[i] == word.getLetters()[i])
//								|| (length % (i+1) != 0) 
								|| ( ( this.getLetters()[i] != connection ) 
										&& ( word.getLetters()[i] != connection ) ) )
								; i++)
				position = i+1;
			if (position == length-1)
				return null;
			int[] result = new int[position+1];
			for (int i = 0; i < position; i++)
				result[i] = this.getLetters()[i];
			result[position] = this.getLetters()[length-1];
			return new Word(result);
			//return new Word(new int[] {1,2});	
		}
	}

	int[] getLetters() {
		return letters;
	}

	void setLetters(int[] letters) {
		this.letters = letters;
	}
}
