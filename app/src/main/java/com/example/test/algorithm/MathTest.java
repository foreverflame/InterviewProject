package com.example.test.algorithm;

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/5/19
 */
public class MathTest {

    public static void main(String[] args) {
        int result = mySqrt(3);
        System.out.println(result);
    }


    /**
     * 求x的平方根
     */
    public static int mySqrt(int x) {
        int l = 0, r = x, ans = -1;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if ((long) mid * mid <= x) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }

}
