package com.fussen.nlp.util;

import java.util.*;

public class CommUtil {
	private CommUtil() {}
	
	// digits[] = {1,1,1,2,2,3} with k=2
	// then the top 2 frequent digits = [1, 2]
	public static List<Integer> getTopKFreqentDigits(int[] digits, int k) {
		List<Integer> topDigits = new ArrayList<Integer>(k);
		
		return topDigits;
	}
	
	// Criteria: 1) increasing 2) max sum
	public static int[] allLongestSubsequences(int[] data) {
		int[] ll = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			ll[i] = 1;
			for (int j = i+1; j < data.length; j++) {
				if (data[i] < data[j]) {
					ll[i]++;
				}
				else {
					break;
				}
			}
		}
		return ll;
	}
	public static int longestSubsequence(int[] data) {
		int len = 0;
		if(data == null) {
			return 0;
		}
		int[] ll = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			ll[i] = 1;
			for (int j = i+1; j < data.length; j++) {
				if (data[i] < data[j]) {
					ll[i]++;
				}
				else {
					break;
				}
			}
		}
		for (int i = 0; i < ll.length; i++) {
			len = Math.max(len, ll[i]);
		}
		return len;
	}
	public static int allCoinChangeCombinations(int[] coins, int sum) {
		int allComb = 0;
		return allComb;
	}
	
	// Coins = {1, 3, 5} and sum = 11
	// minimal coin number is 3 (5 + 5 + 1) 
	// DP(S) = min(DP(S-Ci)) + 1
	public static int getMinimalCoinChange(int[] coins, int sum) {
		int[] dp = new int[sum + 1]; // default = 0
		for (int i = 0; i < coins.length && i < sum && coins[i] <= sum; i++) {
			dp[coins[i]] = 1;
		}
		if (dp[sum] == 0 && sum != 0) {
			dp[sum] = getDPMinCoins(coins, sum, dp);
		}
		return dp[sum];
	}
	private static int getDPMinCoins(int[] coins, int sum, int[] dp) {
		if (dp[sum] == 0 && sum != 0) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length && j <= sum && coins[j] <= sum; j++) {
				min = Math.min(min, getDPMinCoins(coins, sum - coins[j], dp));
			}
			if (min != Integer.MAX_VALUE) {
				dp[sum] = min + 1;
			}
		}
		return dp[sum];
	}
	/*
	//Do not suggest to use Singleton pattern as follows:
	private static CommUtil instance = null;
	public synchronized static CommUtil getInstance() {
		if (instance == null) {
			instance = new CommUtil();
		}
		return instance;
	}
	*/
	// citations: {3, 0, 6, 1, 5}
	// h-index = 3 as (3, 6, 5 >= 3) && (0, 1 < 3)
	public static int hIndex(int[] citations) {
		int idx = 0;
		Arrays.sort(citations);
		while (idx < citations.length && idx < citations[citations.length-1-idx]) {
			idx++;
		}
		return idx;
	}
	// {[1,3,1],
	//  [1,5,1],
	//  [4,2,1],
	//  [3,1,2]} grid[i,j], move down or right
	// result: 1->3->1->1->1->2 = 9
	public static int minPath(int[][] grid) {
		// O(i x j)
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length; j++) {
				if (i != 0 && j != 0) {
					grid[i][j] = grid[i][j] + Math.min(grid[i-1][j], grid[i][j-1]);
				}
				else if (i == 0 && j != 0) {
					grid[i][j] = grid[i][j] + grid[i][j-1];
				}
				else if (i!= 0 && j == 0) {
					grid[i][j] = grid[i][j] + grid[i-1][j];
				}
			}
		}
		return grid[grid.length-1][grid[0].length-1];
	}
	/*
	public static int minPath(int[][] grid) {
		// recursive
		return calculatePathSum(grid, 0, 0);
	}
	private static int calculatePathSum(int[][] grid, int i, int j) {
		// brute force: O(2 * exp(i+j))
		if (i == grid.length || j == grid[0].length) {
			// since seeking min, set max value for index out of boundary
			return Integer.MAX_VALUE;
		}
		if (i == grid.length - 1 && j == grid[0].length - 1) {
			// end point
			return grid[i][j];
		}
		return grid[i][j] + Math.min(calculatePathSum(grid, i+1, j), calculatePathSum(grid, i, j+1));
	}
	*/
	
	// nums [2, 7, 11, 15]; target: 22; sum of two: 7 + 15
	// return index of 1 and 3
	public static int[] twoSum(int [] nums, int target) {
		/*
		// brute force
		for (int i = 0; i < nums.length; i++) {
			for (int j = i+1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] {i, j};
				}
			}
		}
		*/
		// use hash with key = nums[index] & value = index
		Map<Integer, Integer> mValueIdx = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (mValueIdx.containsKey(complement)) {
				return new int[] {mValueIdx.get(complement), i};
			}
			mValueIdx.put(nums[i], i);
		}

		throw new IllegalArgumentException("No two sum solution found");
	}
	
	// array of stock price in the order of time
	// prices = [7, 1, 5, 3, 6, 4]; maxProfit = 6 - 1
	public static int maxProfit(int[] prices) {
		int maxprofit = 0;
		/*
		// brute force
		for (int i = 0; i < prices.length; i++) {
			for (int j = i; j < prices.length; j++) {
				int diff = prices[j] - prices[i];
				if (diff > maxprofit) {
					maxprofit = diff;
				}
			}
		}
		*/
		int minprice = Integer.MAX_VALUE;
		for (int i = 0; i < prices.length; i++) {
			if (prices[i] < minprice) {
				minprice = prices[i];
			}
			else if (prices[i] - minprice > maxprofit) {
				maxprofit = prices[i] - minprice;
			}
		}
		return maxprofit;
	}
	
	public class ListNode {
		int val;
		ListNode next;
		ListNode(int i) {val = i;}
	}
	
	public static ListNode addTwoNums(ListNode l1, ListNode l2) {
		int carry = 0;
		CommUtil util = new CommUtil();
		ListNode results = util.new ListNode(0);
		ListNode resN = results;
		ListNode tmp1 = l1;
		ListNode tmp2 = l2;
		while (tmp1 != null || tmp2 != null) {
			int v1 = tmp1 != null ? tmp1.val : 0;
			int v2 = tmp2 != null ? tmp2.val : 0;
			int sum = v1 + v2 + carry;
			carry = sum / 10;
			resN.next = util.new ListNode(sum % 10);
			resN = resN.next;
			if (tmp1 != null) 
				tmp1 = tmp1.next;
			if (tmp2 != null)
				tmp2 = tmp2.next;
		}
		if(carry > 0) {
			resN.next = util.new ListNode(carry);
		}
		return results.next;
	}
	
	public static int fibonacchi(int idx) {
		/*
		// Solution 1: recursive with O(2^n)
		if (idx == 0) {
			return 0;
		}
		else if (idx == 1) {
			return 1;
		}
		else {
		 	return fibonacchi(idx-1) + fibonacchi(idx-2);
		 }
		*/
			
		// dynamic programming with O(n)
		
		// Solution 2: do not need to use HashMap for idx & value to store or retrieve
		int[] fib = new int[idx+1];
		fib[0] = 0;
		fib[1] = 1;
		for (int i = 2; i < idx + 1; i++) {
			fib[i] = fib[i-1] + fib[i-2];
		}
		return fib[idx];
		
		/*
		// Solution 3: hybrid of Solution 1 & Solution 2
		int[] fib = new int[idx+1];
		return getDPFib(idx, fib);
		*/
	}
	
	private static int getDPFib(int idx, int[] fib) {
		if (idx == 0) {
			return 0;
		}
		else if (idx == 1) {
			return 1;
		}
		else {
			if (fib[idx] == 0) {
				fib[idx] = getDPFib(idx-1, fib) + getDPFib(idx-2, fib);
			}
			return fib[idx];

		}		
	}
}