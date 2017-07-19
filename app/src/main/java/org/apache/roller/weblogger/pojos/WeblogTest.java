package org.apache.roller.weblogger.pojos;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.roller.weblogger.business.URLStrategy;
import org.junit.Test;

public class WeblogTest {
	
	private String mockGetComments(String entryComment, String tags) {
		String[] commentWordArray = entryComment.split(" ");

    	for (int i = 0; i < commentWordArray.length; ++i) {
        	if (commentWordArray[i].length() == 0) {
        		continue;
        	} else if (tags.contains(commentWordArray[i])) {
        		commentWordArray[i] = "MARK" + commentWordArray[i];
        	}
        }

        return String.join("", commentWordArray);
	}
	
	public URLStrategy urlStrategy;

	/**
	 * Tests a single matching word in a comment with a tag
	 */
	@Test
	public void test1() {
		String entryComment = "test";
		String tags = "tag1 tag2 test";
		int count = StringUtils.countMatches(mockGetComments(entryComment, tags), "MARK");
		assertEquals(1, count);
	}

	/**
	 * Tests empty tags
	 */
	@Test
	public void test2() {
		String entryComment = "test";
		String tags = "";
		int count = StringUtils.countMatches(mockGetComments(entryComment, tags), "MARK");
		assertEquals(0, count);
	}
	
	/**
	 * Tests a comment that does not have words matching any tags
	 */
	@Test
	public void test3() {
		String entryComment = "Dominic Farolino the dev";
		String tags = "test";
		int count = StringUtils.countMatches(mockGetComments(entryComment, tags), "MARK");
		assertEquals(0, count);
	}
	
	/**
	 * Test for multiple tags and comment word matches
	 */
	@Test
	public void test4() {
		String entryComment = "Dominic Farolino the dev";
		String tags = "the Farolino Dominic dev";
		int count = StringUtils.countMatches(mockGetComments(entryComment, tags), "MARK");
		assertEquals(4, count);
	}
	
	/**
	 * Tests with an empty comment and arbitrary number of tags
	 */
	@Test
	public void test5() {
		String entryComment = "";
		String tags = "test tags over here";
		int count = StringUtils.countMatches(mockGetComments(entryComment, tags), "MARK");
		assertEquals(0, mockGetComments(entryComment, tags));
	}

	
}