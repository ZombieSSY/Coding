// 9.3

/*
1 Kth Largest Element in an Array

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element.

Example 1:

Input: [3,2,1,5,6,4] and k = 2
Output: 5
Example 2:

Input: [3,2,3,1,2,4,5,5,6] and k = 4
Output: 4
Note: 
You may assume k is always valid, 1 ≤ k ≤ array's length.

*/

class Solution {
	public int findKthLargest(int[] nums, int k) {
		if (nums == null || nums.length == 0) {
			return -1;
		}
		Queue<Integer> minHeap = new PriorityQueue<Integer>(k);
		for (int i = 0; i < k; i++) {
			minHeap.offer(nums[i]);
		}
		for (int i = k; i < nums.length; i++) {
			if (minHeap.peek() < nums[i]) {
				minHeap.poll();
				minHeap.offer(nums[i]);
			}
		}
		return minHeap.peek();
	}
}

/*
2 Insert Delete GetRandom O(1)
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example:

// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();

*/

class RandomizedSet {
	List<Integer> arr;
    Map<Integer, Integer> map;
    java.util.Random rand = new java.util.Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
        arr = new ArrayList<Integer>();
        map = new HashMap<Integer, Integer>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, arr.size());
        arr.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        int pos = map.get(val);
        if (pos < arr.size() - 1 ) { // not the last one than swap the last one with this val
            int lastElement = arr.get(arr.size() - 1 );
            arr.set(pos, lastElement);
            map.put(lastElement, pos);
        }
        map.remove(val);
        arr.remove(arr.size() - 1);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        return arr.get(rand.nextInt(arr.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


 /*
3 Count Primes

Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.

 */

class Solution {
	public int countPrimes(int n) {
		boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = i; i * j < n; j++) {
                    notPrime[i * j] = true;
                }
            }
        }
        return count;
	}
}


/*
4  Majority Element

Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

Example 1:

Input: [3,2,3]
Output: 3
Example 2:

Input: [2,2,1,1,1,2,2]
Output: 2

*/

class Solution {
	public int majorityElement(int[] nums) {
		if (nums == null || nums.length == 0) {
            return -1;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int checkTimes = nums.length / 2;
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (map.containsKey(curr)) {
                if (map.get(curr) + 1 > checkTimes) {
                    return curr;
                } else {
                    map.put(curr, map.get(curr) + 1);
                }
            } else {
                map.put(curr, 1);
            }
        }
        return -1;
	}
}