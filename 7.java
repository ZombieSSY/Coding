/*
1 Remove Nth Node From End of List
Given a linked list, remove the n-th node from the end of list and return its head.

Example:

Given linked list: 1->2->3->4->5, and n = 2.

After removing the second node from the end, the linked list becomes 1->2->3->5.
Note:

Given n will always be valid.

Follow up:

Could you do this in one pass?

*/

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || n == 0) {
			return head;
		}
		int temp = 0;
		ListNode first = head;
		ListNode second = head;
		while (second != null && temp < n) {
			second = second.next;
			temp++;
		}
		if (second == null) {
			return n == temp ? head.next : head;
		}
		while (second != null && second.next != null) {
			first = first.next;
			second = second.next;
		}
		first.next = first.next.next;
		return head;
	}
}


/*
2 Top K Frequent Elements

Given a non-empty array of integers, return the k most frequent elements.

Example 1:

Input: nums = [1,1,1,2,2,3], k = 2
Output: [1,2]
Example 2:

Input: nums = [1], k = 1
Output: [1]
Note:

You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

*/

class Solution {
	public List<Integer> topKFrequent(int[] nums, int k) {
		List<Integer> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}
		Map<Integer, Integer> map = new HashMap<>();
		for (int curr : nums) {
			if (map.containsKey(curr)) {
				map.put(curr, map.get(curr) + 1);
			} else {
				map.put(curr, 1);
			}
		}
		Queue<Map.Entry<Integer,Integer>> minHeap = new PriorityQueue(k, new Comparator<Map.Entry<Integer, Integer>>() {
			@Override
			public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
				if (entry1.getValue().equals(entry2.getValue())) {
					return 0;
				}
				return entry1.getValue() < entry2.getValue() ? -1 : 1;
			}
		});
		int idx = 0;
		for (Map.Entry<Integer,Integer> entry : map.entrySet()) {
			if (idx < k) {
				minHeap.offer(entry);
			} else {
				if (minHeap.peek().getValue() < entry.getValue()) {
					minHeap.poll();
					minHeap.offer(entry);
				}
			}
			idx++;
		}
		while (minHeap.size() > 0) {
			res.add(minHeap.poll().getKey());
		}
		return res;
	}
}


/*
3 ZigZag Conversion
The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

P   A   H   N
A P L S I I G
Y   I   R
And then read line by line: "PAHNAPLSIIGYIR"

Write the code that will take a string and make this conversion given a number of rows:

string convert(string s, int numRows);
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:

P     I    N
A   L S  I G
Y A   H R
P     I


*/
class Solution {
	public String convert(String s, int numRows) {
		if (s == null || s.length() == 0) {
			return "";
		}
		if (numRows == 1) {
			return s;
		}
		char[][] arr = new char[numRows][s.length()];
		int k = 0;
		for (int i = 0; i < arr[0].length; i++) {
			if (i % (numRows - 1) == 0) {
				for (int j = 0; j < numRows; j++) {
					if (k < s.length()) {
						arr[j][i] = s.charAt(k++);
					} else {
						break;
					}
				}
			} else {
				if (k < s.length()) {
					arr[numRows - 1 - (i % (numRows - 1))][i] = s.charAt(k++);
				} else {
					break;
				}
			}
		}
		StringBuilder sb = new StringBuilder();
		for (char[] row : arr) {
			for (char curr : row) {
				if (curr != 0) {
					sb.append(curr);
				}
			}
		}
		return sb.toString();
	}
}

/*
4 Validate Binary Search Tree

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than the node's key.
The right subtree of a node contains only nodes with keys greater than the node's key.
Both the left and right subtrees must also be binary search trees.
Example 1:

Input:
		2
	 / \
	1   3
Output: true
Example 2:

		5
	 / \
	1   4
		 / \
		3   6
Output: false
Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
						 is 5 but its right child's value is 4.


DS:
root: the current node when we go through the tree
max: all nodes value in cur subtree can not large than or equal to max
min: all nodes value in cur subtree can not smaller than or equal to min

private boolean ValidBST(TreeNode root, int max, int min)

AL:
each time we input the current subtree's root, and the max and the min boundary of this tree
base case:
if root == null, true
if root.value <= min || >= max, return false;
return ValidBST(root.left, min, root.value) && ValidBST(root.right, root.value, max)


time: O(n)
space: O(logn)



Key Point!!!
if we init min and max as Integer.MIN_VALUE and Integer.MAX_VALUE, how about [2147483647]???
so we need to init null.
*/

						 /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
	public boolean isValidBST(TreeNode root) {
		return isValidBSTHelper(root, null, null);
	}
	
	private boolean isValidBSTHelper(TreeNode root, Integer min, Integer max) {
		if (root == null) {
			return true;
		}
		if ((min != null && root.val <= min) || (max != null && root.val >= max)) {
			return false;
		}
		return isValidBSTHelper(root.left, min, root.val) && isValidBSTHelper(root.right, root.val, max);
	}
}