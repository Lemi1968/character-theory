package defectCharacters;

import java.util.Arrays;

public class Partition implements Comparable<Partition> {
	int[] content;

	public Partition(int[] content) {
		this.content = content;
	}
	@Override
	public int hashCode() {
		return -Arrays.hashCode(this.content);
	}
	
	@Override
	public String toString() {
		String result = "";
		for (int i = content.length-1; i>=0;i--)
			for (int j = 0; j < content[i]; j++)
				result = result + (i+1) + " ";
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		return ( this.hashCode() == obj.hashCode() );
	}
	
	@Override
	public int compareTo(Partition o) {
		int thisLength 	= this.content.length;
		int otherLength = o.content.length;
		
		if ( thisLength != otherLength )
				return Integer.signum(thisLength-otherLength );
		else {
			for (int i = thisLength-1; i>=0; i--) {
		        int sign = Integer.signum(this.content[i]-o.content[i]);
		        if (sign != 0) {
		            return sign;
		        }
			}
		}
		return 0;
	}
}
