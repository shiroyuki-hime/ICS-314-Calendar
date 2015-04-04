import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class FreeTimeCalEventTest {
	private FreeTimeCalEvent cal;
	
	//Runs before all of the @Test methods
	@Before
	public void setUp() throws Exception {
		cal = new FreeTimeCalEvent();
	}
	
	//Checks that version is 2.0
	public void testVer() throws Exception{
		assertEquals(2.0, cal.getVer());
	}
	
	//Checks classification
	public void testCl() throws Exception{
		assertNotNull(cal.getCl());
	}

	//Checks location
	@Test
	public void testLocation() throws Exception{
		assertNotNull(cal.getLocation());
	}
	
	//Checks summary
	@Test
	public void testSummary() throws Exception{
		assertNotNull(cal.getSummary());
	}
	
	//Checks priority
	@Test
	public void testPriority() throws Exception{
		assertNotNull(cal.getPriority());
	}
	
	//Checks DTSTART
	@Test
	public void testDtStart() throws Exception{
		assertNotNull(cal.getDtStart());
	}
	
	//Checks DTEND
	@Test
	public void testDtEnd() throws Exception{
		assertNotNull(cal.getDtEnd());
	}

	//Checks Time Zone
	@Test
	public void testTimeZone() throws Exception{
		assertNotNull(cal.getTimeZone());
	}
}