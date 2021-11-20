package model;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for GIT_PARAM test enum 
 * @author Kshitij Yerande
 *@version 1.0
 *@since 2021-11-20
 */
public class GIT_PARAMTest {
    
	/**
	 * Test to validate enum values.
	 */
	@Test
	public void test() {
		assertEquals("QUERY",GIT_PARAM.QUERY.toString());
		assertEquals("PER_PAGE",GIT_PARAM.PER_PAGE.toString());
		assertEquals("PAGE",GIT_PARAM.PAGE.toString());
	}

}
