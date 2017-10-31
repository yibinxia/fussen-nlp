package com.fussen.nlp.util;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;


public class CommUtilTest {
	
	@Ignore
	public void allLongestSubsequencesTest() {
		int[] data = {1, 2, 1, 3, 4, 0, 5, 4, 6, 7};
		int[] res = {2, 1, 3, 2, 1, 2, 1, 3, 2, 1};
		assertTrue(java.util.Arrays.equals(res, CommUtil.allLongestSubsequences(data)));
	}
	@Ignore
	public void longestSubsequenceTest() {
		// need to fix
		int[] data = {1, 2, 1, 3, 4, 0, 5, 4, 6, 7};
		int res = 4;
		assertEquals(res, CommUtil.longestSubsequence(data));
	}
	@Test
	public void getMinimalCoinChangeTest() {
		int[] coins = {2, 3, 5};
		int sum = 0;
		int res = 0;
		assertEquals(res, CommUtil.getMinimalCoinChange(coins, sum));
	}
	@Test
	// coins = {1, 3, 5} and sum = 11
	// minimal coin number is 3 (5 + 5 + 1) 
	// DP(S) = min(DP(S-Ci)) + Ci
	public void getMinimalCoinChangeTest2() {
		int[] coins = {1, 3, 5};
		int sum = 16;
		int res = 4;
		assertEquals(res, CommUtil.getMinimalCoinChange(coins, sum));
	}
	
	@Test
	// citations: {3, 0, 6, 1, 5}
	// h-index = 3 as (3, 6, 5 >= 3) && (0, 1 < 3)
	public void hIndexTest() {
		int[] citations = {3, 0, 6, 1, 5};
		int res = 3;
		assertEquals(res, CommUtil.hIndex(citations));
		int[] citations2 = {};
		int res2 = 0;
		assertEquals(res2, CommUtil.hIndex(citations2));
	}
	
	@Test
	// {[1,3,1],
	//  [1,5,1],
	//  [4,2,1],
	//  [3,1,2]} grid[i,j], move down or right
	// result: 1->3->1->1->1->2 = 9
	public void minPathTest() {
		int[][] grid = {{1,3,1},{1,5,1},{4,2,1},{3,1,2}};
		int res = 9;
		assertEquals(res, CommUtil.minPath(grid));
	}
	@Test
	// {[1,3,1],
	//  [1,1,1],
	//  [4,2,1]} grid[i,j], move down or right
	// result: 1->1->1->1->1 = 5
	public void minPathTest2() {
		int[][] grid = {{1,3,1},{1,1,1},{4,2,1}};
		int res = 5;
		assertEquals(res, CommUtil.minPath(grid));
	}
	
	@Test
	public void twoSumTest() {
		int[] nums = {2, 7, 11, 15};
		int[] idx = CommUtil.twoSum(nums, 22);
		int[] res = {1, 3};
		assertTrue(java.util.Arrays.equals(idx, res));
	}
	
	@Test
	public void maxProfitTest() {
		int[] prices = {7, 1, 5, 3, 6, 4};
		int res = 5; // 6 - 1
		assertEquals(res, CommUtil.maxProfit(prices));
		int[] prices2 = {4, 3, 6, 1, 2, 2};
		int res2 = 3; // 6 - 3
		assertEquals(res2, CommUtil.maxProfit(prices2));
	}
	
	@Test
	public void fibonacchiTest() {
		int idx = 7;
		int res = 13;
		assertEquals(res, CommUtil.fibonacchi(idx));
		int idx2 = 20;
		int res2 = 6765;
		assertEquals(res2, CommUtil.fibonacchi(idx2));
		int idx3 = 40;
		int res3 = 102334155;
		assertEquals(res3, CommUtil.fibonacchi(idx3));
	}
}
