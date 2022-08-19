package GradeSystemProjcect;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GradeSystemsTest2 {

	GradeSystems aGradeSystem = null;
	String aID = "975002039";
	String bID = "985002016";
	String nID = "123465789";
	String input = null;
	
	@Before
	public void setUp() throws Exception {
		aGradeSystem = new GradeSystems();
	}

	@After
	public void tearDown() throws Exception {
		aGradeSystem = null;
		// is.close();
	}
	@Test
	public final void testUpdateWeights_N() {
		String w1 = "10 12 38 15 25 N";
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		PrintStream ps = new PrintStream(baos);
		PrintStream old = System.out;
		System.setOut(ps);
		
		InputStream iold=System.in;
		InputStream is = new ByteArrayInputStream(w1.getBytes());
		System.setIn(is);
		//old.println(System.in.available());
		aGradeSystem.updateWeights();
		
		String res = "舊配分\r\n" + 
				"lab1           10%\r\n" + 
				"lab2           10%\r\n" + 
				"lab3           10%\r\n" + 
				"midTerm       30%\r\n" + 
				"finalTerm     40%\r\n" + 
				"輸入新配分\r\n" + 
				"\r\n" + 
				"lab1           \r\n" + 
				"lab2           \r\n" + 
				"lab3           \r\n" + 
				"midTerm       \r\n" + 
				"finalTerm     \r\n" + 
				"請確認新配分\r\n" + 
				"lab1           10%\r\n" + 
				"lab2           12%\r\n" + 
				"lab3           38%\r\n" + 
				"midTerm       15%\r\n" + 
				"finalTerm     25%\r\n" + 
				" 以上正確嗎? Y (Yes) 或 N (No)\r\n";
		
		System.out.flush();
		System.setOut(old);
		while(Main.scanner.hasNextLine()) Main.scanner.nextLine();
		System.setIn(iold);
		assertEquals(res, baos.toString());
	}
}
