package defectCharacters;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProcessRuntimeTest {
	ProcessRuntime out;

	@BeforeEach
	void setUp() throws Exception {
		out = new ProcessRuntime();
	}

	@Test
	void testStart() throws Exception {
		out.start();
		Exception exception = assertThrows(Exception.class, () -> out.start());
		assertTrue(exception.getMessage().contains("started"));
	}

	@Test
	void testStartStep() throws Exception {
		out.start("Test");
	}

	@Test
	void testStopNotStarted() {
		Exception exception = assertThrows(Exception.class, () -> out.stop());
		assertTrue(exception.getMessage().contains("not started"));
	}

	@Test
	void testStop() throws Exception {
		out.start();
		out.stop();
	}
	
	@Test
	void testGetSimpleAnalysis() throws Exception {
		Exception exception = assertThrows(Exception.class, () -> out.getAnalysis());
		assertTrue(exception.getMessage().contains("not started"));
		
		out.start();
		
		exception = assertThrows(Exception.class, () -> out.getAnalysis());
		assertTrue(exception.getMessage().contains("not stopped"));
		
		out.stop();
		assertNotNull(out.getAnalysis());
	}
	
	@Test
	void testGetAnalysis() throws Exception {
		String stepTitle = "StepTitle for testing";
		out.start(stepTitle);
		out.stop();
		String analysis = out.getAnalysis();
		assertTrue(analysis.contains(stepTitle));
	}
	
	@Test
	void testGetComplexAnalysis() throws Exception {
		String step1Title = "Title for step 1";
		out.start(step1Title);
		out.stop();
		
		String step2Title = "Title for step 2";
		out.start(step2Title);
		out.stop();
		
		String analysis = out.getAnalysis();
		assertTrue(analysis.contains(step1Title));
		assertTrue(analysis.contains(step2Title));
	}

}
