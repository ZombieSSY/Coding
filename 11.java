/*
5. Longest Palindromic Substring

Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
*/

// Date structure: a 2D array to represent whether s(i ... j) can form a palindromic substring
// dp(i, j) == true when s(i) equals to s(j) and s(i+1 ... j-1) is a palindromic substring. 
// When we found a palindrome, check if it's the longest one. 
// Time complexity: O(n^2)
// Space complexity: O(n^2)

class Solution {
	public String longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		int n = s.length();
		String res = null;
		boolean[][] dp = new boolean[n][n];
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j < n; j++) {
				dp[i][j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || dp[i + 1][j - 1]);
				if (dp[i][j] && (res == null || (j - i + 1 > res.length()))) {
					res = s.substring(i, j + 1);
				}
			}
		}
		return res;
	}
}


/*
15. 3Sum

Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

Note:

The solution set must not contain duplicate triplets.

Example:

Given array nums = [-1, 0, 1, 2, -1, -4],

A solution set is:
[
  [-1, 0, 1],
  [-1, -1, 2]
]
*/

// Time: O(n^2) traversal + O(nlogn) sort
// Space: O(?) set
class Solution {
	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length < 3) {
			return res;
		}
		Arrays.sort(nums);
		Set<List<Integer>> set = new HashSet<>();
		for (int k = 0; k < nums.length; k++) {
			int i = k + 1;
			int j = nums.length - 1;
			while (i < j) {
				if (nums[k] + nums[i] + nums[j] == 0) {
					List<Integer> temp = new ArrayList<>();
					temp.add(nums[k]);
					temp.add(nums[i]);
					temp.add(nums[j]);
					if (!set.contains(temp)) {
						res.add(temp);
						set.add(temp);
					}
					while (i < nums.length && nums[i + 1] == nums[i]) {
						i++;
					}
					while (j >= 0 && nums[j - 1] == nums[j]) {
						j--;
					}
					i++;
					j--;
				} else if (nums[k] + nums[i] + nums[j] < 0) {
					i++;
				} else {
					j--;
				}
			}
		}
		return res;
	}
}

/*
17. Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.



Example:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.
*/

// Time: O(???)
// Space: O(digits.length())
class Solution {
	public List<String> letterCombinations(String digits) {
		String[] PHONE = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
		List<String> res = new ArrayList<>();
		if (digits == null || digits.length() == 0) {
			return res;
		}
		combination(res, "", digits, 0);
		return res;
	}
	
	private void combination(List<String> res, String prefix, String digits, int offset) {
		if (offset == digits.length()) {
			res.add(prefix);
			return;
		}
		String key = PHONE[digits.charAt(offset) - '0'];
		for (int i = 0; i < key.length(); i++) {
			combination(res, prefix + key.charAt(i), digits, offset + 1);
		}
	}
}

/*
22. Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/

class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if (n < 0) {
            return res;
        }
        StringBuilder sb = new StringBuilder();
        generate(res, 0, 0, n, sb);
        return res;
    }
    
    private void generate(List<String> res, int left, int right, int n, StringBuilder sb) {
        if (left == n && right == n) {
            res.add(sb.toString());
            return;
        }
        if (left < n) {
            sb.append('(');
            generate(res, left + 1, right, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
        if (right < left) {
            sb.append(')');
            generate(res, left, right + 1, n, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}