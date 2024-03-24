package com.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/two-sum/description/
 */
public class TwoSum {

    public static final int BILLIARD = 1_000_000_000;

    public static int[] twoSum(int[] nums, int target) {

        if (nums.length < 2 || nums.length > 10_000
                || target < -BILLIARD || target > BILLIARD) {
            return null;
        }
        Set<Integer> allDistinct = Arrays.stream(nums).boxed().collect(Collectors.toSet());
        Set<Integer> alreadyChecked = new HashSet<>();
        for (int i = 0; i < nums.length - 1; i++) {
            int num = nums[i];

            if (num < -BILLIARD || num > BILLIARD) {
                return null;
            }

            if (alreadyChecked.contains(num)) {
                continue;
            } else {
                alreadyChecked.add(num);
            }

            if (!allDistinct.contains(target - num)) {
                continue;
            }

            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
//        int[] sum = twoSum(new int[]{2, 7, 11, 15}, 9);
        int[] sum = twoSum(new int[]{-1, -2, -3, -4, -5}, -8);
        System.out.println("[" + sum[0] + "," + sum[1] + "]");
    }
}