package com.fussen.nlp.util;

import java.util.*;

public class StringUtil {
	
	public StringUtil() {}
	
	class Node {
		String str;
		int idx;
		public Node(String s, int i) {
			str = s;
			idx = i;
		}
	}
	public String[] getTopKFrequentWords(String[] words, int k) {
		if (words == null || words.length == 0 || k < 1) {
			return null;
		}
		/*
		String[] topWords = new String[k];
		// use HashMap + PriorityQueue (using Node)
		Map<String, Node> mWordNodes = new HashMap<>();
		for (String s: words) {
			if (!mWordNodes.containsKey(s)) {
				mWordNodes.put(s, new Node(s, 1));
			} else {
				mWordNodes.get(s).idx++;
			}
		}
		Queue<Node> qNodes = new PriorityQueue<>(k, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n1.idx == n2.idx) {
					return n1.str.compareTo(n2.str);
				} else {
					return n2.idx - n1.idx;
				}
			}
		});
		for (String key : mWordNodes.keySet()) {
			qNodes.offer(mWordNodes.get(key));
		}
		for (int i = 0; i < k; i++) {
			if (i < mWordNodes.size()) {
				topWords[i] = qNodes.poll().str;
			} else {
				topWords[i] = null;
			}
		}
		*/
		TrieWords tw = new TrieWords();
		for (String s: words) {
			tw.put(s);
		}
		String[] topWords = tw.getTopKFrequentWords(k);
		return topWords;
	}
	
	public static int lengthOfLongestSubString(String s) {
		int n = s.length();
		int len = 0;
		Map<Character, Integer> mCharIdx = new HashMap<Character, Integer>();
		for (int i = 0, j = 0; j < n; j++) {
			Character key = s.charAt(j);
			if (mCharIdx.containsKey(key)) {
				i = mCharIdx.get(key);
			}
			len = Math.max(len, j - i + 1);
			mCharIdx.put(key, j+1);
		}
		return len;
	}
	/*
	public static int lengthOfLongestSubString(String s) {
		// use sliding window [i, j)
		int n = s.length();
		Set<Character> hs = new HashSet<>();
		int len =0;
		int iWindow = 0, jWindow = 0;
		while (iWindow < n && jWindow < n) {
			Character c = s.charAt(jWindow);
			if (!hs.contains(c)) {
				hs.add(c);
				jWindow++; // slide right index
				len = Math.max(len, jWindow - iWindow);
			}
			else {
				hs.remove(s.charAt(iWindow));
				iWindow++; // slide left index
			}
		}
		return len;
	}
	// string = "abcabcbb": len = 3 with "abc"
	// string = "pwwkew": len =3 with "wke"
	// brute force: O(n*n*n);
	public static int lengthOfLongestSubString(String s) {
		int n = s.length();
		int len = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i+1; j < n; j++) {
				if(allUnique(s, i, j)) {
					len = Math.max(len, j-i);
				}
			}
		}
		return len;
	}
	private static boolean allUnique(String s, int start, int end) {
		// use Hash for uniqueness check
		Set<Character> hs = new HashSet<>();
		for (int i = start; i < end; i++) {
			Character c = s.charAt(i);
			if (hs.contains(c)) {
				return false;
			}
			hs.add(c);
		}
		return true;
	}
	*/
	public static List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> m = new HashMap<String, List<String>>();
		/*
		// brute force
		for (int i = 0; i < strs.length; i++) {
			String s = strs[i];
			char[] chars = s.toCharArray();
			Arrays.sort(chars);
			String sorteds = String.valueOf(chars);
			System.out.println(sorteds);
			if (!m.containsKey(sorteds)) {
				m.put(sorteds, new ArrayList<String>());
			}
			m.get(sorteds).add(s);
			System.out.println("s: " + s);
		}
		*/
		// count characters in each string (no special characters)
		// strings with same char counts are anagrams
		int[] counts = new int[26];
		for (int i = 0; i < strs.length; i++) {
			Arrays.fill(counts, 0);
			char[] chars = strs[i].toCharArray();
			for (int j = 0; j < chars.length; j++) {
				counts[chars[j]-'a']++;
			}
			StringBuilder sb = new StringBuilder("");
			for (int n = 0; n < 26; n++) {
				sb.append(counts[n]);
			}
			String key = sb.toString();
			if(!m.containsKey(key)) {
				m.put(key, new ArrayList());
			}
			m.get(key).add(strs[i]);
		}
		return new ArrayList(m.values());
	}
	
	public static boolean areAnagrams(String s1, String s2) {
		// does not handle upper/lower cases, such as Mary & army
		if (s1.length() != s2.length()) {
			return false;
		}
		for (int i = 0; i < s1.length(); i++) {
			char c = s1.charAt(i);
			int index = s2.indexOf(c);
			if (index == -1) {
				return false;
			}
			s2 = s2.substring(0, index) + s2.substring(index+1);
		}
		return s2.isEmpty();
	}
	
	public static ArrayList<String> permutate(String s) {
		// abc, acb, bac, bca, cab, cba with the number of all combinations n!
		ArrayList<String> results = new ArrayList<String>();
		return permutate("", s, results);
	}
	
	private static ArrayList<String> permutate(String head, String tail, ArrayList<String> results) {
		if (tail.isEmpty()) {
			results.add(head);
		}
		else {
			for (int i = 0; i < tail.length(); i++ ) {
				String newHead = head + tail.charAt(i);
				String newTail = tail.substring(0, i) + tail.substring(i+1, tail.length());
				results = permutate(newHead, newTail, results);
			}
		}
		return results;
	}
	
	// how to handle all the punctuation?
	public static String reverseSentence(String s) {
		// only one sentence with ',' in the middle.
		s = s.trim();
		if (s.isEmpty()) {
			return null;
		}
		if (s.charAt(s.length()-1) == '.') {
			s = s.substring(0, s.length()-1);
		}
		String newSent = "";
		ArrayList<String> phrases = new ArrayList<String>(Arrays.asList(s.split(",")));
		for (int i = phrases.size() - 1; i >= 0; i--) {
			if (i == phrases.size() - 1) {
				newSent += '.';
			}
			else {
				newSent += " ,";
			}
			newSent += reverseWords(phrases.get(i));
		}
		return newSent;
	}
	
	// we love travel => travel love we
	public static String reverseWords(String s) {
		if (s.trim().isEmpty()) {
			return null;
		}
		String newStr = "";
		ArrayList<String> words = new ArrayList<String>(Arrays.asList(s.split("\\s")));
		for (int i = words.size() - 1; i >= 0; i--) {
			newStr += words.get(i) + " ";
		}
		return newStr.trim();
	}
	
	// Palindrome: sabcbas (symmetric) in the string mysabcbastring
	// dynamic programming: given a string XXXCiXXXXXCjXXX
	// P(i,j) = (P(i+1, j-1) and Ci==Cj)  
	// bases: P(i,i) & P(i,i+1) = (Ci == Ci+1)
	public static String longestPalindrome(String s) {
		int i = 0;
		int j = 0;
		for (int n = 0; n < s.length(); n++) {
			int len1 = symExpandAtCenter(s, n, n);
			int len2 = symExpandAtCenter(s, n, n+1);
			int len = Math.max(len1, len2);
			if (len > j - i) {
				i = n - (len - 1) / 2;
				j = n + len / 2;
			}
		}
		return s.substring(i, j+1);
	}
	private static int symExpandAtCenter(String s, int left, int right) {
		int l = left;
		int r = right;
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return r - l -1;
	}
	/*
	public static void main() {
		String s = "abc{}de{";
		boolean status = hasPairedParenthesys(s);
		System.out.print(status);
	}
	*/
	public static boolean hasPairedParenthesys(String s) {
		// empty is ok; {} is ok; {*} {* and *} is also ok. } cannot be in front of {
		boolean status = true;
		int lowBound = 0;
		int upperBound = 0;
		for (int i=0; i<s.length(); i++) {
			char c = s.charAt(i);
			System.out.print(c);
			switch(c) {
			case '(': lowBound++; upperBound++; break;
			case ')': lowBound--; upperBound--; break;
			case '*': lowBound--; upperBound++; break;
			default: break;
			}
			if (upperBound < 0) {
				status = false;
				break;
			}
			System.out.println(lowBound);
			System.out.println(upperBound);
		}
		if (lowBound > 0) {
			status = false;
		}
		return status;
	}
}
