// 8.28

/*
1 Reverse String

Write a function that takes a string as input and returns the string reversed.

Example 1:

Input: "hello"
Output: "olleh"
Example 2:

Input: "A man, a plan, a canal: Panama"
Output: "amanaP :lanac a ,nalp a ,nam A"


*/

class Solution {
	public String reverseString(String s) {
		if (s == null || s.length() == 0) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() - 1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
}

/*
2 Most Common Word
Given a paragraph and a list of banned words, 
return the most frequent word that is not in the list of banned words.  
It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation. 
 Words in the paragraph are not case sensitive.  The answer is in lowercase.

Example:
Input: 
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]
Output: "ball"
Explanation: 
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), 
so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
 

Note:

1 <= paragraph.length <= 1000.
1 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
Different words in paragraph are always separated by a space.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.

*/

class Solution {
	public String mostCommonWord(String paragraph, String[] banned) {
		if (paragraph == null || paragraph.length() == 0) {
			return "";
		}
		Set<String> ban = new HashSet<>(Arrays.asList(banned));
		Map<String, Integer> count = new HashMap<>();
		String[] words = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");
		for (String w : words) {
			if (!ban.contains(w)) {
				count.put(w, count.getOrDefault(w, 0) + 1);
			}
		}
		return Collections.max(count.entrySet(), Map.Entry.comparingByValue()).getKey();			
	}
}

/*
3 Group Anagrams
Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
	["ate","eat","tea"],
	["nat","tan"],
	["bat"]
]
Note:

All inputs will be in lowercase.
The order of your output does not matter.

*/

class Solution {
	public List<List<String>> groupAnagrams(String[] strs) {
		if (strs == null || strs.length == 0) {
			return new ArrayList<List<String>>();
		}
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		for (String s : strs) {
			char[] charArr = s.toCharArray();
			Arrays.sort(charArr);
			String keyStr = String.valueOf(charArr);
			if (!map.containsKey(keyStr)) {
				map.put(keyStr, new ArrayList<String>());
			}
			map.get(keyStr).add(s);
		}
		return new ArrayList<List<String>>(map.values());
	}
}

/*
4 Word Break
Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
determine if s can be segmented into a space-separated sequence of one or more dictionary words.

Note:

The same word in the dictionary may be reused multiple times in the segmentation.
You may assume the dictionary does not contain duplicate words.
Example 1:

Input: s = "leetcode", wordDict = ["leet", "code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple", "pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
						 Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
Output: false

*/

class Solution {
	public boolean wordBreak(String s, List<String> wordDict) {
		if (s == null || s.length() == 0) {
			return false;
		}
		boolean[] dp = new boolean[s.length() + 1];
		dp[0] = true;
		for(int i = 1; i <= s.length(); i++) {
			for(int j = 0; j < i; j++) {
				if (dp[j] && wordDict.contains(s.substring(j,i))){
					dp[i] = true;
					break;
				}
			}
		}
		return dp[s.length()];
	}
}
