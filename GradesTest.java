package GradeSystemProjcect;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradesTest {
	Grades aGrade = null;

	@Before
	public void setUp() throws Exception {
		aGrade = new Grades();
	}

	@After
	public void tearDown() throws Exception {
		aGrade = null;
	}

	@Test
	public final void testCalculateTotalGrade_1() {
		aGrade.lab1 = 55;
		aGrade.lab2 = 65;
		aGrade.lab3 = 75;
		aGrade.midTerm = 85;
		aGrade.finalTerm = 95;
		int[] weights = {10, 20, 10, 30, 30};
		aGrade.calculateTotalGrade(weights);
		assertEquals(80, aGrade.totalGrade);
	}

	@Test
	public final void testCalculateTotalGrade_2() {
		aGrade.lab1 = 71;
		aGrade.lab2 = 55;
		aGrade.lab3 = 84;
		aGrade.midTerm = 85;
		aGrade.finalTerm = 90;
		int[] weights = {10, 10, 10, 30, 40};
		aGrade.calculateTotalGrade(weights);
		assertEquals(83, aGrade.totalGrade);
	}
}
