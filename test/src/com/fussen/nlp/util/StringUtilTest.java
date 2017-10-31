package com.fussen.nlp.util;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringUtilTest {

	@SuppressWarnings("deprecation")
	@Test
	public void getTopKFrequentWordsTest() {
		String[] words = {"yes", "hello", "world", "yes", "hello", "fussen", "yes"};
		int k = 5;
		String[] res = {"yes", "hello", "fussen", "world", null};
		StringUtil su = new StringUtil();
		assertEquals(res, su.getTopKFrequentWords(words, k));
	}
	@SuppressWarnings("deprecation")
	@Test
	public void getTopKFrequentWordsTest2() {
		String[] words = {};
		int k = 3;
		String[] res = null;
		StringUtil su = new StringUtil();
		assertEquals(res, su.getTopKFrequentWords(words, k));
	}
	@SuppressWarnings("deprecation")
	@Test
	public void getTopKFrequentWordsTest3() {
		String[] words = {"fussen"};
		int k = -1;
		String[] res = null;
		StringUtil su = new StringUtil();
		assertEquals(res, su.getTopKFrequentWords(words, k));
	}

	
	@Ignore
	public void dummyTest() {
		assertNotNull(null);
	}
	
	@Test
	public void lengthOfLongestSubStringTest() {
		String s = "abcabcbb";
		int res = 3; // "abc"
		assertEquals(res, StringUtil.lengthOfLongestSubString(s));
		String s2 = "pwwkew";
		int res2 = 3;
		assertEquals(res2, StringUtil.lengthOfLongestSubString(s2));
	}
	
	@Test
	public void groupAnagramsTest() {
		String[] strs = {"are", "bat", "ear", "code", "tab", "era"};
		// String[][] res = { {"are", "ear", "era"}, {"bat", "tab"}, {"code"}};
		List<List<String>> res = new ArrayList<List<String>>();
		res.add(Arrays.asList("are", "ear", "era"));
		res.add(Arrays.asList("bat", "tab"));
		res.add(Arrays.asList("code"));
		assertTrue(res.equals(StringUtil.groupAnagrams(strs)));
	}
	
	@Test
	public void areAnagramsTest() {
		String s1 = "army";
		String s2 = "mary";
		assertTrue(StringUtil.areAnagrams(s1, s2));
		String s3 = "iceman";
		String s4 = "cinema";
		assertTrue(StringUtil.areAnagrams(s3, s4));
		String s5 = "abcd";
		String s6 = "Abcd";
		assertFalse(StringUtil.areAnagrams(s5, s6));
	}
	
	@Test
	public void permutationTest() {
		ArrayList<String> results = StringUtil.permutate("abc");
		ArrayList<String> golden = new ArrayList<String>(Arrays.asList("abc", "acb", "bac", "bca", "cab", "cba"));
		assertEquals(golden, results);
		assertEquals(6, results.size());
	}
	
	@Test
	public void reverseSentenceTest() {
		String s = "This is the test, and I am good at it.";
		String r = StringUtil.reverseSentence(s);
		assertEquals(".it at good am I and ,test the is This", r);
		
	}
	
	@Test
	public void reserveWordsTest() {
		String s = "excellent skills and high spirit";
		String r = StringUtil.reverseWords(s);
		assertEquals("spirit high and skills excellent", r);
				
	}
	
	@Test
	public void longestPalindromeTest() {
		String s = "mysabcbastring";
		//String s = "ddddddddmyabcbastring";
		String r = StringUtil.longestPalindrome(s);
		assertEquals("sabcbas", r);
	}
	
	@Ignore
	public void hasPairedParenthesysTest() {
		//String s = "((*a((*)(*))";
		String s = "((*)(*))a((*";
		assertFalse(StringUtil.hasPairedParenthesys(s));
	}
}
