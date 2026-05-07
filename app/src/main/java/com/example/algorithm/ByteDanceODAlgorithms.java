package com.example.algorithm;

import java.util.*;

/**
 * 字节OD面试高频算法题合集（6道）
 * 包含：有效的括号、爬楼梯、打家劫舍、二叉树层序遍历、合并区间、三数之和
 * <p>
 * 每道题都附带逐行注释，解释代码为什么这样写、背后的算法思想是什么、
 * 以及面试中容易踩的坑。
 */
public class ByteDanceODAlgorithms {

    // ==================== 5. 有效的括号 (LeetCode 20) ====================

    /**
     * 题目：给定一个只包含 '(' ')' '{' '}' '[' ']' 的字符串，判断字符串是否有效。
     * <p>
     * 核心思想 —— 栈（Stack）的"后进先出"特性：
     * 左括号相当于"打开一扇门"，右括号相当于"关闭一扇门"。
     * 规则：最后打开的门，必须最先关闭。这正好符合栈的特性。
     * <p>
     * 算法步骤：
     * 1. 遍历字符串，遇到左括号 '(' '{' '[' 就压入栈（记录"我打开了一扇门"）
     * 2. 遇到右括号，就从栈顶取出一个左括号，检查是否匹配
     * - 不匹配 → 字符串无效，直接返回 false
     * - 匹配 → 继续检查下一个字符
     * 3. 遍历结束后，如果栈为空 → 所有门都关上了，返回 true
     * 如果栈不为空 → 有门没关，返回 false
     * <p>
     * 面试易错点：
     * - 遇到右括号时，必须先判断栈是否为空。如果栈空却遇到右括号，说明多出了右括号。
     * - 最后一定要检查栈是否为空。如果栈里还有左括号，说明多出了左括号。
     */
    public boolean isValid(String s) {
        // 用 Stack 存储遇到的左括号。Java 的 Stack 类继承自 Vector，线程安全。
        // 面试时也可以用 Deque（双端队列）作为栈：Deque<Character> stack = new ArrayDeque<>();
        Stack<Character> stack = new Stack<>();

        // 逐个字符遍历字符串
        for (char c : s.toCharArray()) {
            // 如果是左括号，直接压栈。相当于"打开一扇门，记住它的类型"。
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                // 如果是右括号，但栈已经空了 → 没有对应的左括号可以匹配
                // 例如输入 ")("，第一个字符是 ')'，此时栈为空，直接无效
                if (stack.isEmpty()) return false;

                // 弹出栈顶的左括号，准备和当前右括号配对
                char top = stack.pop();

                // 检查是否匹配。三种合法配对：()  {}  []
                // 如果当前是 ')' 但栈顶不是 '(' → 不匹配
                // 如果当前是 '}' 但栈顶不是 '{' → 不匹配
                // 如果当前是 ']' 但栈顶不是 '[' → 不匹配
                if ((c == ')' && top != '(') ||
                        (c == '}' && top != '{') ||
                        (c == ']' && top != '[')) {
                    return false; // 配对失败，字符串无效
                }
            }
        }

        // 遍历结束后，栈必须为空。
        // 例如输入 "((()"，遍历完栈里还剩两个 '('，说明有左括号没被关闭，无效。
        return stack.isEmpty();
    }

    // ==================== 6. 爬楼梯 (LeetCode 70) ====================

    /**
     * 题目：假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。
     * 问有多少种不同的方法可以爬到楼顶？
     * <p>
     * 核心思想 —— 动态规划（Dynamic Programming）：
     * 大问题拆成重叠子问题。想想到达第 i 级台阶的最后一步，只可能是：
     * - 从 i-1 级跨 1 步上来
     * - 从 i-2 级跨 2 步上来
     * 所以：到达第 i 级的方法数 = 到达 i-1 级的方法数 + 到达 i-2 级的方法数
     * 即：dp[i] = dp[i-1] + dp[i-2]
     * <p>
     * 这其实就是斐波那契数列！
     * <p>
     * 边界条件：
     * - n = 1：只有1种方法（直接跨1步）
     * - n = 2：有2种方法（1+1，或者直接跨2步）
     * <p>
     * 空间优化：
     * 计算 dp[i] 时，只需要知道前两个值 dp[i-1] 和 dp[i-2]。
     * 不需要整个数组，用两个变量滚动更新即可，空间复杂度从 O(n) 降到 O(1)。
     * <p>
     * 面试追问：
     * - 递归写法？答：f(n) = f(n-1) + f(n-2)，但递归有大量重复计算，时间复杂度 O(2^n)。
     * - 怎么优化递归？答：记忆化搜索（Memoization），用 HashMap 缓存结果。
     */
    public int climbStairs(int n) {
        // 处理小值边界。n=1 返回 1，n=2 返回 2
        if (n <= 2) return n;

        // prev2 代表 dp[i-2]，初始是第1级的方法数（1种）
        // prev1 代表 dp[i-1]，初始是第2级的方法数（2种）
        int prev2 = 1, prev1 = 2;

        // 从第3级开始，一直算到第 n 级
        for (int i = 3; i <= n; i++) {
            // curr 就是 dp[i] = dp[i-1] + dp[i-2]
            int curr = prev1 + prev2;

            // 滚动更新：下一次循环时，prev2 变成原来的 prev1，prev1 变成 curr
            prev2 = prev1;
            prev1 = curr;
        }

        // 循环结束时，prev1 就是 dp[n]
        return prev1;
    }

    public int climbStairs1(int n) {
        if (n <= 2) {
            return n;
        }
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }


    // ==================== 7. 打家劫舍 (LeetCode 198) ====================

    /**
     * 题目：一排房屋，每间有现金。相邻房屋有连通防盗系统，不能连续偷两家。
     * 给定数组 nums，求能偷到的最大金额。
     * <p>
     * 核心思想 —— 动态规划，每个房子只有两种选择：偷 或 不偷。
     * <p>
     * 对于第 i 家房子：
     * 选择1：偷它
     * - 那么第 i-1 家绝对不能偷（会报警）
     * - 最大金额 = 前 i-2 家的最优金额 + nums[i]
     * - 即：dp[i-2] + nums[i]
     * <p>
     * 选择2：不偷它
     * - 那么最大金额保持为前 i-1 家的最优金额
     * - 即：dp[i-1]
     * <p>
     * 状态转移方程：dp[i] = max(dp[i-1], dp[i-2] + nums[i])
     * <p>
     * 为什么贪心不行？
     * 反例：nums = [2, 100, 1, 100]
     * 贪心选第一家 2，后面可选 1 或 100，最大 2+100=102
     * 但最优是选 100 + 100 = 200（隔一家偷）
     * 所以必须用动态规划，全局比较两种选择。
     * <p>
     * 边界处理：
     * - 没有房子：返回 0
     * - 只有1间：只能偷它，返回 nums[0]
     * - 只有2间：偷金额大的那家，返回 max(nums[0], nums[1])
     */
    public int rob(int[] nums) {
        // 防御性编程：空数组或 null，返回 0
        if (nums == null || nums.length == 0) return 0;

        // 只有一间房子，没得选，只能偷它
        if (nums.length == 1) return nums[0];

        // prev2 代表 dp[i-2]，初始为第0家的金额（偷第一家）
        int prev2 = nums[0];

        // prev1 代表 dp[i-1]，初始为 max(第一家, 第二家)
        // 两家相邻不能都偷，所以选金额大的那家
        int prev1 = Math.max(nums[0], nums[1]);

        // 从第3家（索引2）开始遍历到最后一家
        for (int i = 2; i < nums.length; i++) {
            // 两种选择取最大值：
            //   不偷当前家 → prev1（保持前 i-1 家的最优）
            //   偷当前家 → prev2 + nums[i]（前 i-2 家最优 + 当前家）
            int curr = Math.max(prev1, prev2 + nums[i]);

            // 滚动更新，为下一轮做准备
            prev2 = prev1;
            prev1 = curr;
        }

        // 最终 prev1 就是 dp[n-1]，即所有房子中的最大金额
        return prev1;
    }

    // ==================== 二叉树节点定义 ====================

    /**
     * 二叉树节点类。
     * val：节点存储的整数值
     * left：左子节点引用（可能为 null）
     * right：右子节点引用（可能为 null）
     */
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ==================== 8. 二叉树的层序遍历 (LeetCode 102) ====================

    /**
     * 题目：给定二叉树根节点，返回其节点值的层序遍历（逐层从左到右）。
     * <p>
     * 核心思想 —— BFS（广度优先搜索）+ 队列：
     * BFS 的特点是"先访问的节点，它的子节点也先被访问"，天然适合按层遍历。
     * <p>
     * 关键难点：如何区分"当前层"和"下一层"？
     * 不能混着处理，否则不知道结果该分成几个子列表。
     * <p>
     * 核心技巧 —— 在每一轮开始时，先记录当前队列长度 size：
     * - 这个 size 就是"当前层的节点总数"
     * - 循环 size 次，把这些节点全部出队、记录值、并把它们的子节点入队
     * - 此时队列里剩下的恰好全是"下一层"的节点
     * <p>
     * 面试易错点：
     * - for 循环不能用 queue.size() 动态判断，因为在循环内部会不断入队子节点，
     * 队列大小在变。必须在循环开始前"快照" int size = queue.size()。
     * - 空树直接返回空列表，不要抛异常或返回 null。
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 结果列表，每个元素是一层的节点值列表
        List<List<Integer>> result = new ArrayList<>();

        // 空树直接返回空列表。这是防御性编程，也是面试加分项。
        if (root == null) return result;

        // 用 LinkedList 实现队列（FIFO）。offer 入队，poll 出队。
        Queue<TreeNode> queue = new LinkedList<>();

        // 根节点入队，作为第一层唯一的节点
        queue.offer(root);

        // 当队列不为空时，说明还有节点没处理完
        while (!queue.isEmpty()) {
            // ★ 关键：先记录当前队列长度，这就是"当前层的节点个数"
            // 必须在循环开始前快照，因为循环中会不断入队子节点改变队列大小
            int size = queue.size();

            // 存储当前层所有节点的值
            List<Integer> level = new ArrayList<>();

            // 恰好处理 size 个节点，就是当前层的全部节点
            for (int i = 0; i < size; i++) {
                // 从队头取出节点（FIFO：先来的先处理）
                TreeNode node = queue.poll();

                // 记录当前节点的值
                level.add(node.val);

                // 如果左子节点存在，入队。它将在下一轮（下一层）被处理
                if (node.left != null) queue.offer(node.left);

                // 如果右子节点存在，入队
                if (node.right != null) queue.offer(node.right);
            }

            // 当前层处理完毕，把这一层的值列表加入总结果
            result.add(level);
        }

        return result;
    }

    // ==================== 9. 合并区间 (LeetCode 56) ====================

    /**
     * 题目：给定若干区间，合并所有重叠的区间，返回不重叠的区间数组。
     * <p>
     * 核心思想 —— 排序 + 贪心：
     * <p>
     * 为什么必须先排序？
     * 如果区间乱序，你无法判断当前区间是否和前面的重叠。
     * 按左端点升序排序后，所有可能重叠的区间都会变成相邻的，
     * 只需要和"最近的一个合并区间"比较即可。
     * <p>
     * 贪心策略：
     * 维护一个"当前合并区间" curr，逐个检查下一个区间：
     * - 能合并：下一个区间的左端点 <= curr 的右端点
     * → 扩展 curr 的右端点（取两者右端点的最大值）
     * 例如 [1,5] 和 [2,8] 合并为 [1,8]，而不是 [1,5]
     * - 不能合并：下一个区间的左端点 > curr 的右端点
     * → 把 curr 加入结果，curr 更新为下一个区间
     * <p>
     * 面试易错点：
     * - 排序比较器用 Integer.compare(a[0], b[0])，防止 a[0]-b[0] 整数溢出。
     * - 循环结束后，最后一个 curr 还在变量里，别忘了加入结果列表。
     */
    public int[][] merge(int[][] intervals) {
        // 如果只有0个或1个区间，直接返回，无需合并
        if (intervals.length <= 1) return intervals;

        // 按区间的左端点升序排序。
        // Lambda 表达式：(a, b) -> Integer.compare(a[0], b[0])
        // 不要用 a[0] - b[0]，当数值很大时会溢出。
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // 用 List 存储合并后的区间，因为不知道最终有几个
        List<int[]> merged = new ArrayList<>();

        // curr 代表"当前正在合并的区间"，初始化为第一个区间
        int[] curr = intervals[0];

        // 从第二个区间开始遍历
        for (int i = 1; i < intervals.length; i++) {
            // 检查当前区间 intervals[i] 是否能和 curr 合并
            // 条件：当前区间的左端点 <= curr 的右端点（有重叠或相邻）
            if (intervals[i][0] <= curr[1]) {
                // 可以合并，更新 curr 的右端点为两者最大值
                // 例如 curr=[1,3]，intervals[i]=[2,6] → 合并为 [1,6]
                // 例如 curr=[1,8]，intervals[i]=[2,5] → 合并为 [1,8]（curr 已经更大）
                curr[1] = Math.max(curr[1], intervals[i][1]);
            } else {
                // 不能合并（当前区间在 curr 右边，且不重叠）
                // 例如 curr=[1,6]，intervals[i]=[8,10]
                // 把 curr 加入结果，开始一个新的合并区间
                merged.add(curr);
                curr = intervals[i];
            }
        }

        // ★ 别忘了最后一个 curr！循环结束后它还在变量里，没有加入结果
        merged.add(curr);

        // 将 List<int[]> 转换为 int[][] 返回
        // new int[0][] 是 trick：让 JVM 自动推断并创建正确大小的数组
        return merged.toArray(new int[0][]);
    }

    // ==================== 10. 三数之和 (LeetCode 15) ====================

    /**
     * 题目：给定整数数组 nums，找出所有和为 0 且不重复的三元组。
     * <p>
     * 核心思想 —— 排序 + 双指针：
     * 暴力解法是三层循环 O(n³)，太慢。
     * 优化：先排序 O(n log n)，然后固定一个数，问题变成"两数之和"，用双指针 O(n)。
     * 总复杂度 O(n²)。
     * <p>
     * 算法步骤：
     * 1. 数组排序
     * 2. 固定第一个数 nums[i]，在其右侧用双指针找两数之和等于 -nums[i]
     * - left 指向 i+1（剩余数组的最左）
     * - right 指向数组末尾（最右）
     * - 三数之和 > 0 → 太大，right 左移（减小值）
     * - 三数之和 < 0 → 太小，left 右移（增大值）
     * - = 0 → 找到一组答案
     * 3. 去重：跳过相邻相等的数字，防止产生重复答案
     * <p>
     * 去重逻辑（最难的部分）：
     * - 固定数去重：if (i > 0 && nums[i] == nums[i-1]) continue;
     * 如果当前固定数和上一个相同，说明已经用这个数找过答案了，跳过。
     * - 双指针去重：找到答案后，left 右移跳过所有重复值，right 左移跳过所有重复值。
     * <p>
     * 面试易错点：
     * - 固定数去重时，判断 i > 0 防止数组越界。
     * - 找到答案后，left++ 和 right-- 必须同时执行，不能只移一个。
     * - 双指针去重要在记录答案之后做，否则可能漏解。
     */
    public List<List<Integer>> threeSum(int[] nums) {
        // 结果列表，存储所有不重复的三元组
        List<List<Integer>> result = new ArrayList<>();

        // 先排序。排序是双指针的前提，让数组有序才能通过移动指针控制大小。
        Arrays.sort(nums);

        // 固定第一个数 nums[i]。
        // 为什么到 nums.length - 2？因为后面至少还要留两个数给 left 和 right。
        for (int i = 0; i < nums.length - 2; i++) {

            // ★ 固定数去重：如果当前数和上一个数相同，跳过。
            // 例如排序后 [-4, -1, -1, 0, 1, 2]，i=1 固定 -1 时已经找过答案了，
            // i=2 又是 -1，直接 continue，避免重复答案。
            // 注意 i > 0 的判断，防止 i=0 时访问 nums[-1] 越界。
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // left 从 i 的下一个位置开始，指向剩余区间的最左边
            int left = i + 1;

            // right 指向数组末尾，最右边
            int right = nums.length - 1;

            // 当两个指针没有相遇时，继续寻找
            while (left < right) {
                // 计算三数之和
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // 找到一组答案，加入结果
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // ★ 双指针去重：跳过 left 后面重复的数字
                    // 例如 [-1, -1, 0, 0, 1, 1]，找到一组后，left 指向的 0 后面可能还有 0
                    while (left < right && nums[left] == nums[left + 1]) left++;

                    // ★ 双指针去重：跳过 right 前面重复的数字
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    // 去重完成后，两个指针同时向中间移动，继续寻找下一组
                    left++;
                    right--;

                } else if (sum < 0) {
                    // 三数之和小于 0，说明整体太小，需要增大。
                    // 因为数组有序，让 left 右移（指向更大的数）
                    left++;

                } else {
                    // 三数之和大于 0，说明整体太大，需要减小。
                    // 让 right 左移（指向更小的数）
                    right--;
                }
            }
        }

        return result;
    }

    // ==================== Main 测试入口 ====================
    public static void main(String[] args) {
        ByteDanceODAlgorithms sol = new ByteDanceODAlgorithms();

        System.out.println("========== 5. 有效的括号 ==========");
        System.out.println("核心：栈的后进先出，最后打开的门最先关");
        String[] bracketTests = {"()[]{}", "(]", "{[]}", ""};
        for (String s : bracketTests) {
            System.out.println("输入: '' + s + '' -> " + sol.isValid(s));
        }

        System.out.println(" ========== 6. 爬楼梯 ==========");
        System.out.println("核心：dp[i] = dp[i-1] + dp[i-2]，斐波那契数列");
        for (int n : new int[]{2, 3, 5, 10}) {
            System.out.println("n=" + n + " -> " + sol.climbStairs(n));
        }

        System.out.println(" ========== 7. 打家劫舍 ==========");
        System.out.println("核心：每家偷或不偷，dp[i] = max(不偷:dp[i-1], 偷:dp[i-2]+nums[i])");
        int[][] houseTests = {
                {1, 2, 3, 1},
                {2, 7, 9, 3, 1},
                {5}
        };
        for (int[] nums : houseTests) {
            System.out.println("输入: " + Arrays.toString(nums) + " -> " + sol.rob(nums));
        }

        System.out.println(" ========== 8. 二叉树的层序遍历 ==========");
        System.out.println("核心：BFS队列，每层开始前记录size快照");
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        List<List<Integer>> levels = sol.levelOrder(root);
        System.out.println("层序遍历结果: " + levels);

        System.out.println(" ========== 9. 合并区间 ==========");
        System.out.println("核心：按左端点排序，能合并就扩右边界，不能合并就存结果");
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merged = sol.merge(intervals);
        System.out.print("合并后: ");
        for (int[] interval : merged) {
            System.out.print("[" + interval[0] + "," + interval[1] + "] ");
        }
        System.out.println();

        System.out.println(" ========== 10. 三数之和 ==========");
        System.out.println("核心：排序后固定一个数，双指针夹逼，注意去重");
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> triplets = sol.threeSum(nums);
        System.out.println("输入: " + Arrays.toString(nums));
        System.out.println("结果: " + triplets);
    }
}
