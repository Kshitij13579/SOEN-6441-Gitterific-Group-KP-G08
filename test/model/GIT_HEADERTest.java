package model;

import model.GIT_HEADER;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for GIT_HEADER test enum 
 * @author Kshitij Yerande
 *@version 1.0
 *@since 2021-11-20
 */
public class GIT_HEADERTest {
    
	/**
	 * Test to validate enum values.
	 */
	@Test
	public void test() {
		assertEquals("ACCEPT",GIT_HEADER.ACCEPT.toString());
		assertEquals("CONTENT_TYPE",GIT_HEADER.CONTENT_TYPE.toString());
	}

}
