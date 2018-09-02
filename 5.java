// 8.29 

/*
2 Integer to Roman
Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.

Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.

Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:

I can be placed before V (5) and X (10) to make 4 and 9. 
X can be placed before L (50) and C (100) to make 40 and 90. 
C can be placed before D (500) and M (1000) to make 400 and 900.
Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.

Example 1:

Input: 3
Output: "III"
Example 2:

Input: 4
Output: "IV"
Example 3:

Input: 9
Output: "IX"
Example 4:

Input: 58
Output: "LVIII"
Explanation: C = 100, L = 50, XXX = 30 and III = 3.
Example 5:

Input: 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.

*/
class Solution {
	public String intToRoman(int num) {
		StringBuilder sb = new StringBuilder();
        while (num > 0) {
            if (num >= 1000) {
                int digit = num / 1000;
                while (digit > 0) {
                    sb.append('M');
                    digit--;
                }
                num %= 1000;
            } else if (num >= 500) {
                if (num >= 900) {
                    sb.append("CM");
                    num -= 900;
                } else {
                    sb.append('D');
                    num -= 500;
                }
            } else if (num >= 100) {
                if (num >= 400) {
                    sb.append("CD");
                    num -= 400;
                } else {
                    int digit = num / 100;
                    while (digit > 0) {
                        sb.append('C');
                        digit--;
                    }
                    num %= 100;
                }
            } else if (num >= 50) {
                if (num >= 90) {
                    sb.append("XC");
                    num -= 90;
                } else {
                    sb.append('L');
                    num -= 50;
                }
            } else if (num >= 10) {
                if (num >= 40) {
                    sb.append("XL");
                    num -= 40;
                } else {
                    int digit = num / 10;
                    while (digit > 0) {
                        sb.append('X');
                        digit--;
                    }
                    num %= 10;
                }
            } else if (num >= 5) {
                if (num == 9) {
                    sb.append("IX");
                    num -= 9;
                } else {
                    sb.append('V');
                    num -= 5;
                }
            } else {
                if (num == 4) {
                    sb.append("IV");
                    num -= 4;
                } else {
                    while (num > 0) {
                        sb.append('I');
                        num--;
                    }
                }
            }
        }
        return sb.toString();
	}
}

/*
3 Merge Sorted Array
Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.

Note:

The number of elements initialized in nums1 and nums2 are m and n respectively.
You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
Example:

Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
*/

class Solution {
	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (nums1 == null || nums2 == null || n == 0) {
            return;
        }
        if (m == 0) {
            for (int l = 0; l < n; l++) {
                nums1[l] = nums2[l];
            }
        }
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums2[j] >= nums1[i]) {
                nums1[k--] = nums2[j--]; 
            } else {
                nums1[k--] = nums1[i--];
            }
        }
        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
	}
}

/*
4 Decode Ways
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given a non-empty string containing only digits, determine the total number of ways to decode it.

Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as "AB" (1 2) or "L" (12).
Example 2:

Input: "226"
Output: 3
Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).


*/

class Solution {
	public int numDecodings(String s) {
		if (s == null || s.length() == 0) {
            return 0;
        }
        int[] dp = new int[s.length() + 1];
        dp[s.length()] = 1;
        dp[s.length() - 1] = s.charAt(s.length() - 1) != '0' ? 1 : 0;
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                continue;
            } else {
                dp[i] = (Integer.parseInt(s.substring(i, i + 2)) <= 26) ? dp[i + 1] + dp[i + 2] : dp[i + 1];
            }
        }
        return dp[0];
	}
}

