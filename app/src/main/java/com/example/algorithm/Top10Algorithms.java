package com.example.algorithm;

import java.util.*;

/**
 * 互联网大厂高频算法题汇总（Java实现）
 * 覆盖：字节跳动、腾讯、阿里、快手等一线大厂面试常考题目
 * 
 * @author InterviewPrep
 * @date 2026-05-14
 */
public class Top10Algorithms {

    // ==================== 1. LRU 缓存机制 (LeetCode 146) ====================
    /**
     * 题目：设计和实现一个 LRU (最近最少使用) 缓存机制
     * 要求：get 和 put 操作时间复杂度均为 O(1)
     * 
     * 核心思路：HashMap + 双向链表
     * - HashMap: 存储 key -> Node 映射，实现 O(1) 查找
     * - 双向链表: 维护访问顺序，头部为最近使用，尾部为最久未使用
     * - 容量满时淘汰尾部节点
     */
    static class LRUCache {
        // 双向链表节点
        class Node {
            int key, value;
            Node prev, next;
            Node(int k, int v) { key = k; value = v; }
        }

        private final int capacity;
        private final Map<Integer, Node> map;  // key -> Node
        private final Node head, tail;         // 伪头部和伪尾部节点
        private int size;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.map = new HashMap<>();
            // 使用伪头部和伪尾部节点简化边界处理
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            this.size = 0;
        }

        // 获取值，并将节点移到头部（最近使用）
        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            moveToHead(node);  // 访问后移到头部
            return node.value;
        }

        // 插入或更新值
        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                // 已存在：更新值并移到头部
                node.value = value;
                moveToHead(node);
            } else {
                // 不存在：创建新节点
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addToHead(newNode);
                size++;
                // 超出容量：淘汰尾部节点
                if (size > capacity) {
                    Node removed = removeTail();
                    map.remove(removed.key);
                    size--;
                }
            }
        }

        // 将节点移到头部（最近使用）
        private void moveToHead(Node node) {
            removeNode(node);
            addToHead(node);
        }

        // 在头部添加节点
        private void addToHead(Node node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }

        // 删除指定节点
        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        // 删除尾部节点（最久未使用）
        private Node removeTail() {
            Node node = tail.prev;
            removeNode(node);
            return node;
        }
    }

    // ==================== 2. 无重复字符的最长子串 (LeetCode 3) ====================
    /**
     * 题目：给定字符串，找出不含有重复字符的最长子串的长度
     * 
     * 核心思路：滑动窗口（双指针）
     * - left 和 right 维护一个窗口，窗口内无重复字符
     * - right 向右扩展，遇到重复字符时 left 跳到重复位置之后
     * - 使用 HashSet 或 HashMap 记录字符位置
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;

        Map<Character, Integer> charIndex = new HashMap<>();  // 字符 -> 最新位置
        int maxLen = 0;
        int left = 0;  // 窗口左边界

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            // 如果字符已存在且在窗口内，收缩左边界
            if (charIndex.containsKey(c) && charIndex.get(c) >= left) {
                left = charIndex.get(c) + 1;
            }
            // 更新字符最新位置
            charIndex.put(c, right);
            // 更新最大长度
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // ==================== 3. K 个一组翻转链表 (LeetCode 25) ====================
    /**
     * 题目：将链表每 k 个节点一组进行翻转，剩余不足 k 个不翻转
     * 
     * 核心思路：分段反转 + 连接
     * 1. 先遍历 k 个节点，确认够 k 个
     * 2. 反转这 k 个节点
     * 3. 将反转后的子链表与前一组尾节点和后一组头节点连接
     * 4. 递归或迭代处理后续节点
     */
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) return head;

        // 检查是否有 k 个节点
        ListNode tail = head;
        for (int i = 0; i < k; i++) {
            if (tail == null) return head;  // 不足 k 个，不反转
            tail = tail.next;
        }

        // 反转当前 k 个节点（head -> ... -> tail 的前一个）
        ListNode prev = null;
        ListNode curr = head;
        // tail 是下一组的起始节点，作为反转的终止条件
        while (curr != tail) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // head 变成了尾部，连接下一组
        head.next = reverseKGroup(tail, k);

        // prev 是新的头部
        return prev;
    }

    // ==================== 4. 数组中的第 K 个最大元素 (LeetCode 215) ====================
    /**
     * 题目：在未排序数组中找到第 k 个最大的元素
     * 
     * 核心思路：快速选择（Quick Select）- 快排 partition 的变形
     * - 每次 partition 后，枢轴位置即为排序后的最终位置
     * - 只递归处理包含第 k 大元素的那一半
     * - 平均时间复杂度 O(n)，最坏 O(n²)
     * 
     * 面试追问：
     * - 数据量极大怎么办？-> 使用堆（PriorityQueue），维护大小为 k 的小顶堆
     * - 要求 O(n) 且稳定？-> 使用计数排序/桶排序（数据范围有限时）
     */
    public int findKthLargest(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            throw new IllegalArgumentException("Invalid input");
        }
        // 第 k 大 = 升序排列后索引为 nums.length - k 的元素
        return quickSelect(nums, 0, nums.length - 1, nums.length - k);
    }

    private int quickSelect(int[] nums, int left, int right, int targetIndex) {
        if (left == right) return nums[left];

        // 随机选择枢轴，避免最坏情况
        int pivotIndex = left + new Random().nextInt(right - left + 1);
        pivotIndex = partition(nums, left, right, pivotIndex);

        if (pivotIndex == targetIndex) {
            return nums[pivotIndex];
        } else if (pivotIndex < targetIndex) {
            return quickSelect(nums, pivotIndex + 1, right, targetIndex);
        } else {
            return quickSelect(nums, left, pivotIndex - 1, targetIndex);
        }
    }

    // 三路划分 partition，返回枢轴最终位置
    private int partition(int[] nums, int left, int right, int pivotIndex) {
        int pivotValue = nums[pivotIndex];
        // 将枢轴移到末尾
        swap(nums, pivotIndex, right);

        int storeIndex = left;
        // 将小于枢轴的移到左边
        for (int i = left; i < right; i++) {
            if (nums[i] < pivotValue) {
                swap(nums, storeIndex, i);
                storeIndex++;
            }
        }
        // 将枢轴移到正确位置
        swap(nums, storeIndex, right);
        return storeIndex;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // 使用堆的解法（适合数据量极大或流式数据）
    public int findKthLargestByHeap(int[] nums, int k) {
        // 小顶堆，维护当前最大的 k 个元素
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();  // 弹出最小的，保留 k 个最大的
            }
        }
        return minHeap.peek();  // 堆顶就是第 k 大的
    }

    // ==================== 5. 反转链表 (LeetCode 206) ====================
    /**
     * 题目：反转一个单链表
     * 
     * 核心思路：三指针迭代法
     * - prev: 已反转部分的头节点
     * - curr: 当前待反转的节点
     * - next: 临时保存 curr.next，防止断链
     * 
     * 变种延伸：
     * - 反转前 N 个节点
     * - 反转从位置 m 到 n 的节点
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;   // 已反转部分
        ListNode curr = head;   // 当前节点

        while (curr != null) {
            ListNode next = curr.next;  // 暂存下一个节点
            curr.next = prev;           // 反转指针方向
            prev = curr;                // prev 前移
            curr = next;                // curr 前移
        }
        return prev;  // 新的头节点
    }

    // 递归解法（面试加分项）
    public ListNode reverseListRecursive(ListNode head) {
        // 递归终止条件：空节点或只有一个节点
        if (head == null || head.next == null) return head;

        // 递归反转后续链表
        ListNode newHead = reverseListRecursive(head.next);

        // 将当前节点接到反转后链表的尾部
        head.next.next = head;  // 原 head.next 现在是尾部，指向 head
        head.next = null;       // head 变成新尾部

        return newHead;
    }

    // ==================== 6. 二叉树的最近公共祖先 (LeetCode 236) ====================
    /**
     * 题目：给定二叉树和两个节点，找到它们的最近公共祖先（LCA）
     * 
     * 核心思路：后序遍历（自底向上）
     * - 如果当前节点是 p 或 q，返回当前节点
     * - 递归查找左右子树
     * - 如果左右子树都找到了，当前节点就是 LCA
     * - 如果只有一边找到，返回那一边的结果
     * 
     * 时间复杂度：O(n)，每个节点访问一次
     * 空间复杂度：O(h)，h 为树高，递归栈空间
     */
    static class TreeNode {
        int val;
        TreeNode left, right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // 递归终止条件
        if (root == null || root == p || root == q) {
            return root;  // 找到 p/q 或到达叶子节点的 null
        }

        // 后序遍历：先处理左右子树
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 情况1：左右子树各找到一个，当前节点就是 LCA
        if (left != null && right != null) {
            return root;
        }

        // 情况2：只有一边找到，返回那一边（可能 p 是 q 的祖先）
        return left != null ? left : right;
    }

    // 二叉搜索树版本的 LCA（利用 BST 特性优化到 O(h)）
    public TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        // 确保 p.val <= q.val
        if (p.val > q.val) {
            return lowestCommonAncestorBST(root, q, p);
        }

        while (root != null) {
            if (root.val > q.val) {
                root = root.left;   // p 和 q 都在左子树
            } else if (root.val < p.val) {
                root = root.right;  // p 和 q 都在右子树
            } else {
                return root;  // p.val <= root.val <= q.val，找到 LCA
            }
        }
        return null;
    }

    // ==================== 7. 三数之和 (LeetCode 15) ====================
    /**
     * 题目：给定数组，找出所有和为 0 的三元组，不能包含重复三元组
     * 
     * 核心思路：排序 + 双指针
     * 1. 先排序，方便去重和双指针
     * 2. 固定第一个数 nums[i]，在 [i+1, n-1] 范围内用双指针找两数之和为 -nums[i]
     * 3. 注意跳过重复元素（i、left、right 都要去重）
     * 
     * 时间复杂度：O(n²) - 外层 O(n)，内层双指针 O(n)
     * 空间复杂度：O(1) 或 O(logn)（排序栈空间）
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        Arrays.sort(nums);  // 先排序
        int n = nums.length;

        for (int i = 0; i < n - 2; i++) {
            // 去重：跳过相同的 nums[i]
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 剪枝：最小值大于 0，后面不可能有解
            if (nums[i] > 0) break;

            int left = i + 1;
            int right = n - 1;
            int target = -nums[i];

            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 去重：跳过相同的 left 和 right
                    while (left < right && nums[left] == nums[left + 1]) left++;
                    while (left < right && nums[right] == nums[right - 1]) right--;

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;  // 和太小，左指针右移增大
                } else {
                    right--; // 和太大，右指针左移减小
                }
            }
        }
        return result;
    }

    // ==================== 8. 岛屿数量 (LeetCode 200) ====================
    /**
     * 题目：给定二维网格，'1' 为陆地，'0' 为水域，计算岛屿数量
     * 
     * 核心思路：DFS / BFS
     * - 遍历整个网格，遇到 '1' 时启动 DFS/BFS
     * - 将连通的所有 '1' 标记为 '0'（或 visited），计数器 +1
     * 
     * 变种延伸：
     * - 岛屿最大面积（DFS 时计数）
     * - 岛屿周长（统计边界和水域相邻边）
     * - 被围绕的区域（边界 DFS + 内部翻转）
     */
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;  // 发现新岛屿
                    dfs(grid, i, j);  // 淹没整个岛屿
                }
            }
        }
        return count;
    }

    // DFS 淹没岛屿：将连通的所有 '1' 标记为 '0'
    private void dfs(char[][] grid, int row, int col) {
        // 边界检查 + 水域检查
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length 
            || grid[row][col] == '0') {
            return;
        }

        grid[row][col] = '0';  // 标记为已访问（淹没）

        // 四个方向扩散
        dfs(grid, row - 1, col);  // 上
        dfs(grid, row + 1, col);  // 下
        dfs(grid, row, col - 1);  // 左
        dfs(grid, row, col + 1);  // 右
    }

    // BFS 版本（面试追问"DFS 递归栈溢出怎么办"时使用）
    public int numIslandsBFS(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;

        int rows = grid.length, cols = grid[0].length;
        int count = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    grid[i][j] = '0';  // 标记为已访问

                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});

                    while (!queue.isEmpty()) {
                        int[] curr = queue.poll();
                        for (int[] dir : directions) {
                            int newRow = curr[0] + dir[0];
                            int newCol = curr[1] + dir[1];
                            if (newRow >= 0 && newRow < rows && newCol >= 0 && newCol < cols 
                                && grid[newRow][newCol] == '1') {
                                grid[newRow][newCol] = '0';
                                queue.offer(new int[]{newRow, newCol});
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    // ==================== 9. 合并 K 个升序链表 (LeetCode 23) ====================
    /**
     * 题目：合并 k 个有序链表，返回一个有序链表
     * 
     * 核心思路：优先队列（最小堆）
     * - 将所有链表的头节点放入最小堆
     * - 每次弹出最小节点接到结果链表
     * - 将弹出节点的 next 放入堆中
     * 
     * 时间复杂度：O(N log K)，N 为总节点数，K 为链表数
     * 空间复杂度：O(K)，堆的大小
     * 
     * 变种：合并两个有序数组（归并排序的子过程）
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        // 最小堆，按节点值排序
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
            (a, b) -> a.val - b.val
        );

        // 将所有链表头节点入堆
        for (ListNode node : lists) {
            if (node != null) {
                minHeap.offer(node);
            }
        }

        ListNode dummy = new ListNode(0);  // 哨兵节点
        ListNode tail = dummy;

        while (!minHeap.isEmpty()) {
            ListNode minNode = minHeap.poll();  // 弹出最小节点
            tail.next = minNode;
            tail = minNode;

            // 如果该节点还有下一个，将下一个入堆
            if (minNode.next != null) {
                minHeap.offer(minNode.next);
            }
        }

        return dummy.next;
    }

    // 分治法版本（面试追问"不用堆怎么做"时使用）
    public ListNode mergeKListsDivideConquer(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeRange(lists, 0, lists.length - 1);
    }

    private ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = mergeRange(lists, left, mid);
        ListNode l2 = mergeRange(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    // 合并两个有序链表（基础操作）
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    // ==================== 10. 买卖股票的最佳时机 (LeetCode 121) ====================
    /**
     * 题目：给定股票价格数组，只允许一次买卖，求最大利润
     * 
     * 核心思路：贪心 / 动态规划
     * - 遍历数组，维护 minPrice（历史最低买入价）
     * - 每天计算如果当天卖出的利润：price - minPrice
     * - 更新 maxProfit
     * 
     * 状态定义：
     * - dp[i] 表示前 i 天的最大利润
     * - dp[i] = max(dp[i-1], prices[i] - minPrice)
     * 
     * 变种延伸：
     * - 122: 可以多次买卖
     * - 123: 最多两笔交易
     * - 188: 最多 k 笔交易
     * - 309: 含冷冻期
     * - 714: 含手续费
     */
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int minPrice = prices[0];  // 历史最低买入价
        int maxProfit = 0;          // 最大利润

        for (int i = 1; i < prices.length; i++) {
            // 如果当天价格更低，更新最低买入价
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {
                // 计算当天卖出的利润，更新最大利润
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }
        return maxProfit;
    }

    // 通用 DP 框架（面试展示对变种题目的掌握）
    public int maxProfitDP(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int n = prices.length;
        // dp[i][0]: 第 i 天持有股票的最大利润
        // dp[i][1]: 第 i 天不持有股票的最大利润
        int hold = -prices[0];    // 第0天买入
        int notHold = 0;          // 第0天未买入

        for (int i = 1; i < n; i++) {
            // 今天持有 = max(昨天持有, 今天买入)
            hold = Math.max(hold, -prices[i]);
            // 今天不持有 = max(昨天不持有, 昨天持有 + 今天卖出)
            notHold = Math.max(notHold, hold + prices[i]);
        }
        return notHold;
    }

    // ==================== 辅助方法：打印链表 ====================
    public static void printList(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            if (head.next != null) sb.append(" -> ");
            head = head.next;
        }
        System.out.println(sb.toString());
    }

    // ==================== 辅助方法：创建链表 ====================
    public static ListNode createList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int num : arr) {
            tail.next = new ListNode(num);
            tail = tail.next;
        }
        return dummy.next;
    }

    // ==================== 主函数：测试所有算法 ====================
    public static void main(String[] args) {
        Top10Algorithms solution = new Top10Algorithms();

        System.out.println("========== 1. LRU 缓存测试 ==========");
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println("get(1): " + cache.get(1));  // 1
        cache.put(3, 3);  // 淘汰 key=2
        System.out.println("get(2): " + cache.get(2));  // -1

        System.out.println("\n========== 2. 无重复字符最长子串 ==========");
        System.out.println("abcabcbb: " + solution.lengthOfLongestSubstring("abcabcbb"));  // 3
        System.out.println("bbbbb: " + solution.lengthOfLongestSubstring("bbbbb"));      // 1

        System.out.println("\n========== 3. K个一组翻转链表 ==========");
        ListNode list3 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("原链表: ");
        printList(list3);
        ListNode reversed3 = solution.reverseKGroup(createList(new int[]{1, 2, 3, 4, 5}), 2);
        System.out.print("K=2翻转后: ");
        printList(reversed3);

        System.out.println("\n========== 4. 数组第K大元素 ==========");
        int[] nums4 = {3, 2, 1, 5, 6, 4};
        System.out.println("数组: " + Arrays.toString(nums4) + ", k=2, 第K大: " + 
            solution.findKthLargest(nums4.clone(), 2));  // 5
        System.out.println("堆解法 k=2: " + solution.findKthLargestByHeap(nums4.clone(), 2));

        System.out.println("\n========== 5. 反转链表 ==========");
        ListNode list5 = createList(new int[]{1, 2, 3, 4, 5});
        System.out.print("原链表: ");
        printList(list5);
        ListNode reversed5 = solution.reverseList(createList(new int[]{1, 2, 3, 4, 5}));
        System.out.print("反转后: ");
        printList(reversed5);

        System.out.println("\n========== 6. 二叉树最近公共祖先 ==========");
        TreeNode root6 = new TreeNode(3);
        root6.left = new TreeNode(5);
        root6.right = new TreeNode(1);
        root6.left.left = new TreeNode(6);
        root6.left.right = new TreeNode(2);
        root6.right.left = new TreeNode(0);
        root6.right.right = new TreeNode(8);
        TreeNode p6 = root6.left;      // 5
        TreeNode q6 = root6.right;     // 1
        TreeNode lca6 = solution.lowestCommonAncestor(root6, p6, q6);
        System.out.println("LCA of 5 and 1: " + (lca6 != null ? lca6.val : "null"));  // 3

        System.out.println("\n========== 7. 三数之和 ==========");
        int[] nums7 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result7 = solution.threeSum(nums7);
        System.out.println("三数之和为0的组合: " + result7);  // [[-1, -1, 2], [-1, 0, 1]]

        System.out.println("\n========== 8. 岛屿数量 ==========");
        char[][] grid8 = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println("岛屿数量(DFS): " + solution.numIslands(grid8));  // 3
        char[][] grid8b = {
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'},
            {'0', '0', '1', '0', '0'},
            {'0', '0', '0', '1', '1'}
        };
        System.out.println("岛屿数量(BFS): " + solution.numIslandsBFS(grid8b));  // 3

        System.out.println("\n========== 9. 合并K个升序链表 ==========");
        ListNode[] lists9 = new ListNode[]{
            createList(new int[]{1, 4, 5}),
            createList(new int[]{1, 3, 4}),
            createList(new int[]{2, 6})
        };
        ListNode merged9 = solution.mergeKLists(lists9);
        System.out.print("合并后: ");
        printList(merged9);  // 1 -> 1 -> 2 -> 3 -> 4 -> 4 -> 5 -> 6

        System.out.println("\n========== 10. 买卖股票最佳时机 ==========");
        int[] prices10 = {7, 1, 5, 3, 6, 4};
        System.out.println(" prices: " + Arrays.toString(prices10));
        System.out.println("最大利润: " + solution.maxProfit(prices10));  // 5 (1买6卖)
        System.out.println("DP解法利润: " + solution.maxProfitDP(prices10));
    }
}