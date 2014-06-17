package my.sample.project1;

import static org.junit.Assert.assertEquals;
import my.sample.project1.Utils;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;


public class UtilsTest {
	
	private Utils utils = null;
	
    @Before
    public void init() {
    	utils = new Utils();
    }
	
	@Test
    public void parseDateCharactersInString() {
		assertEquals("Wrong formatted date should return null", null, utils.parseDate("ffdsgfdggsdfsdfsdff"));
		assertEquals("Wrong formatted date should return null", null, utils.parseDate("2014/02/10f00:00:00"));
		assertEquals("Wrong formatted date should return null", null, utils.parseDate("2014/s2/10 00:00:00"));
	}
	
	@Test
    public void parseDateInvalidDate() {
		assertEquals("Wrong formatted date should return null", null, utils.parseDate("2014/02/29 00:00:00"));
		assertEquals("Wrong formatted date should return null", null, utils.parseDate("2014/99/10 00:00:00"));
		assertEquals("Wrong formatted date should return null", null, utils.parseDate("2014/02/99 00:00:00"));
		assertEquals("Wrong formatted date should return null", null, utils.parseDate("2014/02/10 99:00:00"));
		assertEquals("Wrong formatted date should return null", null, utils.parseDate("2014/02/10 00:99:00"));
		assertEquals("Wrong formatted date should return null", null, utils.parseDate("2014/02/10 00:00:99"));
	}
	
	@Test
    public void parseDateValidDate() {
		DateTime d = new DateTime(2014,2,10,1,2,3,0);
		assertEquals("Wrong formatted date should return null", d.toDate(), utils.parseDate("2014/02/10 01:02:03"));
		
	}
}
