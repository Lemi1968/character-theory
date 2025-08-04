package defectCharacters;

public class PrintLogger implements Loggable {

	@Override
	public void logFinding(String finding) {
		System.out.println(finding);
	}

}
