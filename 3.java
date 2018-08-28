// 8.28

/*
1 Search in Rotated Sorted Array
Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.

(i.e., [0,1,2,4,5,6,7] might become [4,5,6,7,0,1,2]).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.

Your algorithm's runtime complexity must be in the order of O(log n).

Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1

*/

class Solution {
	public int search(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        // Find the smallest element
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        int pivot = left;
        left = 0;
        right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int realMid = (mid + pivot) % nums.length;
            if (nums[realMid] == target) {
                return realMid;
            } else if (nums[realMid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
	}
}

/*
2 Longest Common Prefix
Write a function to find the longest common prefix string amongst an array of strings.

If there is no common prefix, return an empty string "".

Example 1:

Input: ["flower","flow","flight"]
Output: "fl"
Example 2:

Input: ["dog","racecar","car"]
Output: ""
Explanation: There is no common prefix among the input strings.
Note:

All given inputs are in lowercase letters a-z.

*/

class Solution {
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }
        Arrays.sort(strs, (a, b) -> {
            if (a.length() == b.length()) {
                return a.compareTo(b);
            }
            return a.length() < b.length() ? -1 : 1;
        });
        String check = strs[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < strs.length; i++) {
            String curr = strs[i];
            if (i == 1) {
                for (int j = 0; j < check.length(); j++) {
                    if (check.charAt(j) == curr.charAt(j)) {
                        sb.append(check.charAt(j));
                    } else {
                        break;
                    }
                }
            } else {
                for (int j = 0; j < sb.length(); j++) {
                    if (sb.charAt(j) != curr.charAt(j)) {
                        if (j == 0) {
                            return "";
                        } else {
                            sb = sb.delete(j, sb.length());
                            break;
                        }
                    }
                }
            }
        }
        return sb.toString();
	}
}


/*
3 Palindrome Number
Determine whether an integer is a palindrome. An integer is a palindrome when it reads the same backward as forward.

Example 1:

Input: 121
Output: true
Example 2:

Input: -121
Output: false
Explanation: From left to right, it reads -121. From right to left, it becomes 121-. Therefore it is not a palindrome.
Example 3:

Input: 10
Output: false
Explanation: Reads 01 from right to left. Therefore it is not a palindrome.
Follow up:

Coud you solve it without converting the integer to a string?

*/

class Solution {
	public boolean isPalindrome(int x) {
		if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }
        return x == reverse || x == reverse / 10;
	}
}

/*
4 Reverse Words in a String

Given an input string, reverse the string word by word.

Example:  

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.

*/

public class Solution {
	public String reverseWords(String s) {
		if (s == null || s.length() == 0) {
            return "";
        }
        String res = "";
        StringBuilder sb =  new StringBuilder();
        int i = s.length() - 1;
        while (i >= 0) {
            if (s.charAt(i) != ' ') {
                int end = i;
                while (i >= 0 && s.charAt(i) != ' ') {
                    i--;
                }
                for (int k = i + 1; k <= end; k++) {
                    sb.append(s.charAt(k));
                }
                sb.append(' ');
            }
            i--;
        }
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == ' ') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
	}
}

/*
5 Reverse Integer

Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
*/

class Solution {
	public int reverse(int x) {
		long reverse = 0;
        boolean positive = true;
        if (x < 0) {
            positive = false;
            x *= -1;
        }
        while (x > 0) {
            reverse = reverse * 10 + x % 10;
            x = x / 10;
        }
        if (!positive) {
            reverse *= -1;
        }
        return reverse > Integer.MAX_VALUE || reverse < Integer.MIN_VALUE ? 0 : (int) reverse;
	}
}

/*
6  Trapping Rain Water

Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.


The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

Example:

Input: [0,1,0,2,1,0,1,3,2,1,2,1]
Output: 6
*/

class Solution {
	public int trap(int[] height) {
		public int trap(int[] height) {
        if (height == null || height.length <= 1) {
            return 0;
        }
        int i = 0;
        int j = height.length - 1;
        int res = 0;
        int maxLeft = height[i];
        int maxRight = height[j];
        while (i <= j) {
            maxLeft = Math.max(maxLeft, height[i]);
            maxRight = Math.max(maxRight, height[j]);
            if (maxLeft < maxRight) {
                res += (maxLeft - height[i]);
                i++;
            } else {
                res += (maxRight - height[j]);
                j--;
            }
        }
        return res;
    }
	}
}