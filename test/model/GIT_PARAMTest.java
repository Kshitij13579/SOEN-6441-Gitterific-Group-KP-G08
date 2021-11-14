package model;

import static org.junit.Assert.*;

import org.junit.Test;

public class GIT_PARAMTest {

	@Test
	public void test() {
		assertEquals("QUERY",GIT_PARAM.QUERY.toString());
		assertEquals("PER_PAGE",GIT_PARAM.PER_PAGE.toString());
		assertEquals("PAGE",GIT_PARAM.PAGE.toString());
	
	}

}
