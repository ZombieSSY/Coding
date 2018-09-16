/*
34. Find First and Last Position of Element in Sorted Array

Given an array of integers nums sorted in ascending order, find the starting and ending position of a given target value.

Your algorithm's runtime complexity must be in the order of O(log n).

If the target is not found in the array, return [-1, -1].

Example 1:

Input: nums = [5,7,7,8,8,10], target = 8
Output: [3,4]
Example 2:

Input: nums = [5,7,7,8,8,10], target = 6
Output: [-1,-1]
*/

class Solution {
	public int[] searchRange(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
			return new int[]{-1, -1};
		}
		int[] res = new int[2];
		int left = 0;
		int right = nums.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				int prev = findLastSmaller(nums, mid - 1, target - 1);
				int next = findFirstLarger(nums, mid + 1, target + 1);
				if (nums[prev] == target && nums[next] == target) {
					return new int[]{prev, next};
				} else if (nums[prev] == target) {
					return new int[]{prev, next - 1};
				} else if (nums[next] == target) {
					return new int[]{prev + 1, next};
				} else {
					return new int[]{prev + 1, next - 1};
				}
			} else if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (nums[left] == target && nums[right] == target) {
			return new int[]{left, right};
		} else if (nums[left] == target) {
			return new int[]{left, left};
		} else if (nums[right] == target) {
			return new int[]{right, right};
		} else {
			return new int[]{-1, -1};
		}
	}
	
	private int findLastSmaller(int[] nums, int right, int target) {
		int left = 0;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] <= target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return nums[right] <= target ? right : left;
	}
	
	private int findFirstLarger(int[] nums, int left, int target) {
		int right = nums.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		return nums[left] >= target ? left : right;
	}
}

// Better solution:
class Solution {
	public int[] searchRange(int[] nums, int target) {
		int[] res = new int[]{-1, -1};
		if (nums == null || nums.length == 0) {
			return res;
		}
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2;
			if (nums[mid] < target) {
				left = mid + 1;
			} else {
				right = mid;
			}
		}
		if (nums[left] != target) {
			return res;
		} else {
			res[0] = left;
		}
		right = nums.length - 1;
		while (left < right) {
			int mid = left + (right - left) / 2 + 1;	// Make mid biased to the right
			if (nums[mid] > target) {
				right = mid - 1;
			}
			else {
				left = mid;
			}
		}
		res[1] = right;
		return res;
	}
}

/*
35. Search Insert Position

Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

You may assume no duplicates in the array.

Example 1:

Input: [1,3,5,6], 5
Output: 2
Example 2:

Input: [1,3,5,6], 2
Output: 1
Example 3:

Input: [1,3,5,6], 7
Output: 4
Example 4:

Input: [1,3,5,6], 0
Output: 0
*/

class Solution {
	public int searchInsert(int[] nums, int target) {
		if (nums == null || nums.length == 0 || target <= nums[0]) {
			return 0;
		} else if (nums[nums.length - 1] < target) {
			return nums.length;
		} 
		int left = 0;
		int right = nums.length - 1;
		while (left < right - 1) {
			int mid = left + (right - left) / 2;
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				left = mid;
			} else {
				right = mid;
			}
		}
		if (nums[left] >= target) {
			return left;
		} else if (nums[left] < target && nums[right] >= target) {
			return right;
		} else {
			return right + 1;
		}
	}
}

/*
39. Combination Sum

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Note:

All numbers (including target) will be positive integers.
The solution set must not contain duplicate combinations.
Example 1:

Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
Example 2:

Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
*/

class Solution {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (candidates == null || candidates.length == 0) {
			return res;
		}
		combination(res, new ArrayList<Integer>(), candidates, 0, target);
		return res;
	}
	
	private void combination(List<List<Integer>> res, List<Integer> temp, int[] candidates, int idx, int rest) {
		if (idx == candidates.length) {
			if (rest == 0) {
				res.add(new ArrayList<>(temp));
			}
			return;
		}
		for (int i = 0; i <= rest / candidates[idx]; i++) {
			for (int j = 0; j < i; j++) {
				temp.add(candidates[idx]);
			}
			combination(res, temp, candidates, idx + 1, rest - candidates[idx] * i);
			for (int j = 0; j < i; j++){
				temp.remove(temp.size() - 1);
			}
		}
	}
}


/*
48. Rotate Image

You are given an n x n 2D matrix representing an image.

Rotate the image by 90 degrees (clockwise).

Note:

You have to rotate the image in-place, which means you have to modify the input 2D matrix directly. DO NOT allocate another 2D matrix and do the rotation.

Example 1:

Given input matrix = 
[
  [1,2,3],
  [4,5,6],
  [7,8,9]
],

rotate the input matrix in-place such that it becomes:
[
  [7,4,1],
  [8,5,2],
  [9,6,3]
]
Example 2:

Given input matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

rotate the input matrix in-place such that it becomes:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
*/

/*
 * clockwise rotate
 * first reverse up to down, then swap the symmetry 
 * 1 2 3     7 8 9     7 4 1
 * 4 5 6  => 4 5 6  => 8 5 2
 * 7 8 9     1 2 3     9 6 3
*/
class Solution {
	public void rotate(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		int up = 0;
		int down = matrix.length - 1;
		while (up < down) {
			for (int i = 0; i < matrix[0].length; i++) {
				swap(matrix, up, down, i);
			}
			up++;
			down--;
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = i + 1; j < matrix[i].length; j++) {
				swap(matrix, i, j);
			}
		}
	}
	
	private void swap(int[][] matrix, int up, int down, int j) {
		int temp = matrix[up][j];
		matrix[up][j] = matrix[down][j];
		matrix[down][j] = temp;
	}
	
	private void swap(int[][] matrix, int i, int j) {
		int temp = matrix[i][j];
		matrix[i][j] = matrix[j][i];
		matrix[j][i] = temp;
	}
}

/*
53. Maximum Subarray

Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example:

Input: [-2,1,-3,4,-1,2,1,-5,4],
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.
Follow up:

If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.
*/

class Solution {
	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		int max = dp[0];
		for (int i = 1; i < nums.length; i++) {
			dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
			max = Math.max(max, dp[i]);
		}
		return max;
	}
}

/*
55. Jump Game

Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Example 1:

Input: [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum
			 jump length is 0, which makes it impossible to reach the last index.
*/

// Too slow
// Time: O(n ^ 2)
// Space: O(n)
class Solution {
	public boolean canJump(int[] nums) {
		if (nums == null || nums.length == 0) {
			return true;
		}
		boolean[] dp = new boolean[nums.length];
		dp[0] = true;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = false;
			for (int j = 0; j < i; j++) {
				if (dp[j] && nums[j] >= (i - j)) {
					dp[i] = true;
				}
			}
		}
		return dp[dp.length - 1];
	}
}

// Better solution
// Idea is to work backwards from the last index. Keep track of the smallest index that can "jump" to the last index. Check whether the current index can jump to this smallest index.
class Solution {
	public boolean canJump(int[] nums) {
		if (nums == null || nums.length == 0) {
			return true;
		}
		int last = nums.length - 1, i, j;
		for (i = nums.length - 2; i >= 0; i--){
			if (i + nums[i] >= last) {
				last = i;
			}
		}
		return last == 0;
	}
}

/*
56. Merge Intervals

Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [[1,3],[2,6],[8,10],[15,18]]
Output: [[1,6],[8,10],[15,18]]
Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
Example 2:

Input: [[1,4],[4,5]]
Output: [[1,5]]
Explanation: Intervals [1,4] and [4,5] are considerred overlapping.
*/

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
	public List<Interval> merge(List<Interval> intervals) {
		List<Interval> res = new ArrayList<>();
		if (intervals == null || intervals.size() == 0) {
			return res;
		}
		Collections.sort(intervals, new Comparator<Interval>(){
			@Override
			public int compare(Interval a, Interval b) {
				if (a.start == b.start) {
					return 0;
				}
				return a.start < b.start ? -1 : 1;
			}
		});
		int currStart = intervals.get(0).start;
		int currEnd = intervals.get(0).end;
		for (int i = 0; i < intervals.size() - 1; i++) {
			int nextStart = intervals.get(i + 1).start;
			int nextEnd = intervals.get(i + 1).end;
			if (nextStart > currEnd) {
				res.add(new Interval(currStart, currEnd));
				currStart = intervals.get(i + 1).start;
				currEnd = intervals.get(i + 1).end;
			} else if (nextStart <= currEnd && currEnd < nextEnd) {
				currEnd = nextEnd;
			}
		}
		res.add(new Interval(currStart, currEnd));
		return res;
	}
}