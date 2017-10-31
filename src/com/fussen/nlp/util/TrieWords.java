package com.fussen.nlp.util;

import java.util.*;

public class TrieWords {
	// 26 characters in ASCII table convert 'A' (=65) into 'a' (=97)
	private static final int SIZE = 26;
	public static class Node {
		private String str;
		private int freq = 0;
		private Node[] next = new Node[SIZE];
	}
	private Node root;
	private int size;
	
	public TrieWords() {
	}
	
	public int size() {
		return size;
	}
	
	private Node get(Node node, String word, int depth) {
		if (node == null) {
			return null;
		}
		if (depth == word.length()) {
			return node;
		}
		char c = Character.toLowerCase(word.charAt(depth));
		if (c > 'z' || c < 'a') {
			throw new IllegalArgumentException(word + " contains unsupported characters");
		}
		return get(node.next[c-'a'], word, depth+1);
	}
	
	public Node get(String word) {
		if (word == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		Node n = get(root, word, 0);
		return n;
	}
	
	public int getFrequency(String word) {
		if (word == null) {
			throw new IllegalArgumentException("argument to get() is null");
		}
		Node n = get(root, word, 0);
		if (n == null) {
			return 0;
		}
		return n.freq;
	}
	
	public boolean contains(String word) {
		if (word == null) {
			throw new IllegalArgumentException("argument to containword() is null");
		}
		return get(word) != null;
	}
	
	private Node put(Node node, String word, int depth) {
		if (node == null) {
			node = new Node();
		}
		if (depth == word.length()) {
			if (node.str == null) {
				size++;
			}
			node.str = word;
			node.freq++;
			return node;
		}
		char c = Character.toLowerCase(word.charAt(depth));
		if (c > 'z' || c < 'a') {
			throw new IllegalArgumentException(word + " contains unsupported characters");
		}
		node.next[c-'a'] = put(node.next[c-'a'], word, depth+1);
		return node;
	}
	
	public void put(String word) {
		if (word == null) {
			throw new IllegalArgumentException("first argument to put() is null");
		}
		root = put(root, word, 0);
	}
	
	private void collect(Node node, StringBuilder prefix, Queue<String> results) {
		if (node == null) {
			return;
		}
		if (node.str != null) {
			results.add(prefix.toString());
		}
		for (int i = 0; i < SIZE; i++) {
			prefix.append((char)(i+'a'));
			collect(node.next[i], prefix, results);
			prefix.deleteCharAt(prefix.length() - 1);
		}
	}
	
	public Iterable<String> wordsWithPrefix(String prefix) {
		Queue<String> results = new LinkedList<String>();
		Node node = get(root, prefix, 0);
		collect(node, new StringBuilder(prefix), results);
		return results;
	}
	
	public Iterable<String> words() {
		return wordsWithPrefix("");
	}
	
	private Node delete(Node node, String word, int depth) {
		if (node == null) {
			return null;
		}
		if (depth == word.length()) {
			if (node.str != null) {
				size--;
			}
			node.str = null;
			node.freq = 0;
		}
		else {
			char c = Character.toLowerCase(word.charAt(depth));
			node.next[c-'a'] = delete(node.next[c-'a'], word, depth+1);
		}
		if (node.str != null) {
			return node;
		}
		for (int i = 0; i < SIZE; i++) {
			if (node.next[i] != null) {
				return node;
			}
		}
		return null;
	}
	
	public void delete(String word) {
		if (word == null) {
			throw new IllegalArgumentException("argument to delete() is null");
		}
		root = delete(root, word, 0);
	}
	
	public String[] getTopKFrequentWords(int k) {
		Queue<Node> nodes = new PriorityQueue<Node>(size, new Comparator<Node>() {
			public int compare(Node n1, Node n2) {
				if (n2.freq == n1.freq) {
					return n1.str.compareTo(n2.str);
				}
				return n2.freq - n1.freq;
			}
		});
		for (String word : words()) {
			nodes.offer(get(root, word, 0));
		}
		String[] results = new String[k];
		for (int i = 0; i < k; i++) {
			if (i < size) {
				results[i] = nodes.poll().str;
			}
			else {
				results[i] = null;
			}
		}
		return results;
	}
	
	/*
	public static void main(String[] args) {
		TrieWords st = new TrieWords();
		Scanner in = new Scanner(System.in);
		for (int i = 0; i< 10; i++) {
			String word = in.next();
			st.put(word);
		}
		System.out.println("Size: " + st.size);
//		st.delete("every");
		if (st.size() < 100) {
			System.out.println("word:");
			for (String word : st.words()) {
				System.out.println(word + " " + st.getFrequency(word));
			}
		}
		for (String s : st.wordsWithPrefix("a")) {
			System.out.println(s);
		}
		System.out.println("-------------");
		String[] tops = st.getTopKFrequentWords(3);
		for (String s : tops) {
			System.out.println(s);
		}
	}
	*/
}
