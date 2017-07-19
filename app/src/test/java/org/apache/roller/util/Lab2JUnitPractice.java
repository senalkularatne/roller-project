package org.apache.roller.util;

import static org.junit.Assert.*;

import org.apache.roller.weblogger.pojos.WeblogEntry;
import org.junit.Test;

public class Lab2JUnitPractice {

	WeblogEntry entry = new WeblogEntry();
	
	@Test
	public void test() {
		try {
			entry.addTag("TAG");
		} catch (Exception e) {
			e.printStackTrace();
		}
		assertEquals("tags",entry.getTagsAsString());
	}

}
