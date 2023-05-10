package com.example.test.algorithm;

/**
 * @description: 动态规划相关问题
 * @author: huangyonghuang
 * @date: 2023/5/10
 */
public class DynamicPlan {


    /**
     * 最长连续子序列和
     * 1.确定状态 2.找到转移公式 3.确定初始条件以及边界条件
     * https://leetcode.cn/leetbook/read/top-interview-questions-easy/xn3cg3/
     */
    public int madSubArray(int[] num) {
        int length = num.length;
        int[] dp = new int[length];
        //初始状态
        dp[0] = num[0];
        int max = dp[0];
        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(dp[i - 1], 0) + num[i];
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 动态规划
     */
    public int climbStairs(int n) {
        if (n <= 1)
            return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

}
