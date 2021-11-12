package model;

import model.GIT_HEADER;

import static org.junit.Assert.*;

import org.junit.Test;

public class GIT_HEADERTest {

	@Test
	public void test() {
		assertEquals("ACCEPT",GIT_HEADER.ACCEPT.toString());
		assertEquals("CONTENT_TYPE",GIT_HEADER.CONTENT_TYPE.toString());
	}

}
