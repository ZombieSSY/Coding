/*
2 Climbing Stairs

Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

Note: Given n will be a positive integer.

Example 1:

Input: 2
Output: 2
Explanation: There are two ways to climb to the top.
1. 1 step + 1 step
2. 2 steps
Example 2:

Input: 3
Output: 3
Explanation: There are three ways to climb to the top.
1. 1 step + 1 step + 1 step
2. 1 step + 2 steps
3. 2 steps + 1 step

*/

class Solution {
	public int climbStairs(int n) {
		int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
	}
}

/*
3  Permutations

Given a collection of distinct integers, return all possible permutations.

Example:

Input: [1,2,3]
Output:
[
	[1,2,3],
	[1,3,2],
	[2,1,3],
	[2,3,1],
	[3,1,2],
	[3,2,1]
]

*/

class Solution {
	public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return res;
        }
        permuteHelper(nums, 0, res);
        return res;
    }

    private void permuteHelper(int[] nums, int idx, List<List<Integer>> res) {
        if (idx == nums.length) {
            List<Integer> curr = new ArrayList<>();
            for (int val : nums) {
                curr.add(val);
            }
            res.add(curr);
        }

        for (int i = idx; i < nums.length; i++) {
            swap(nums, i, idx);
            permuteHelper(nums, idx + 1, res);
            swap(nums, i, idx);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

/*
4 3Sum Closest

Given an array nums of n integers and an integer target, find three integers in nums such that the sum is closest to target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

Example:

Given array nums = [-1, 2, 1, -4], and target = 1.

The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

*/

class Solution {
	public int threeSumClosest(int[] nums, int target) {
		if (nums == null || nums.length == 0) {
            return -1;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int currSum = nums[i] + nums[j] + nums[k];
                int diff = target - currSum;
                if (diff > 0) {
                    j++;
                } else if (diff < 0){
                    k--;
                } else {
                    return currSum;
                }
                if (Math.abs(diff) < min) {
                    min = Math.abs(diff);
                    sum = currSum;
                }
            }
        }
        return sum;
	}
}
