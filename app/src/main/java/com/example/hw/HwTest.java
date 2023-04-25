package com.example.hw;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @description:
 * @author: huangyonghuang
 * @date: 2023/4/24
 */
public class HwTest {
    public static void main(String[] args) {
//        drink2();
//        deleteDuplicateOrder();

        //0x12F
        dexToTen();
    }


    /**
     * 汽水瓶问题
     * https://www.nowcoder.com/exam/test/68824155/submission?pid=1088888
     */
    private static void drink2() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int num = in.nextInt();
            int result = 0;
            //喝三瓶 空瓶算做一瓶 最后对二的特殊情况处理
            while (num >= 3) {
                num -= 3;
                num++;
                result++;
            }
            if (num == 2) {
                result++;
            }
            System.out.println(result);
        }
    }


    private static void deleteDuplicateOrder() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int number = sc.nextInt();
            int shuzu[] = new int[number];
            for (int i = 0; i < shuzu.length; i++) {
                shuzu[i] = sc.nextInt();
            }
            // 利用Set把重复的元素去掉
            Set<Object> set = new HashSet<>();
            for (int i = 0; i < shuzu.length; i++) {
                set.add(shuzu[i]);
            }
            // 遍历set集合 无序的
            // 转化成int数组
            int[] uArray = new int[set.size()];
            int count = 0;
            for (Object str : set) {
                // 将Object转化成 int
                uArray[count++] = Integer.parseInt(String.valueOf(str));
            }
            Arrays.sort(uArray);
            for (int i = 0; i < uArray.length; i++) {
                System.out.println(uArray[i]);
            }

        }
    }


    /**
     * 输入16进制，将他转为10进制
     */
    private static void dexToTen() {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            StringBuilder sb = new StringBuilder();
            sb.append(in.next());
            String str = sb.substring(2);
            char[] array = str.toCharArray();
            int sum = 0;
            for (int i = 0; i < array.length; i++) {
                if (array[i] >= 'A' && array[i] <= 'F') {
                    //pow是数学公式，求16的x次方，注意字符转换成整型
                    sum += ((int) array[i] - 55) * Math.pow(16, array.length - 1 - i);
                } else {
                    sum += ((int) array[i] - 48) * Math.pow(16, array.length - 1 - i);
                }
            }
            System.out.println(sum);
        }
        in.close();
    }

}
