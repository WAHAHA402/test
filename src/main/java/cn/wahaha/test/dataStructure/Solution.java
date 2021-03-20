package cn.wahaha.test.dataStructure;

import java.util.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: Recrusion
 * @Author: zhangrenwei
 * @Date: 2019/12/29 9:29 下午
 */

public class Solution {
    //题目: 变态跳台阶
    //一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
    public int JumpFloorII(int target) {
        if (target <= 0) return -1;
        if (target == 1) return 1;
        return 2 * JumpFloorII(target - 1);
    }

    //求1+2+3+...+n，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
    //
    //解题思路：
    // 不能使用乘除法，但是要实现多次计算，好像也只有递归了。但是终止条件不能用关键字控制。
    //1.需利用逻辑与的短路特性实现递归终止。 2.当n==0时，(n>0)&&((sum+=Sum_Solution(n-1))>0)只执行前面的判断，为false，然后直接返回0；
    //3.当n>0时，执行sum+=Sum_Solution(n-1)，实现递归计算Sum_Solution(n)。
    public int Sum_Solution(int n) {
        int temp = n;
        // 后面这个判断条件其实无所谓，主要是为了递归计算结果
        boolean whateverHere = n > 0 && (temp += Sum_Solution(n - 1)) > 0;
        return temp;

    }

    //不用+ - * /做加法
    /*

        首先看十进制是如何做的： 5+7=12，三步走
        第一步：相加各位的值，不算进位，得到2。
        第二步：计算进位值，得到10. 如果这一步的进位值为0，那么第一步得到的值就是最终结果。

        第三步：重复上述两步，只是相加的值变成上述两步的得到的结果2和10，得到12。

        同样我们可以用三步走的方式计算二进制值相加： 5-101，7-111 第一步：相加各位的值，不算进位，得到010，二进制每位相加就相当于各位做异或操作，101^111。

        第二步：计算进位值，得到1010，相当于各位做与操作得到101，再向左移一位得到1010，(101&111)<<1。

        第三步重复上述两步， 各位相加 010^1010=1000，进位值为100=(010&1010)<<1。
        继续重复上述两步：1000^100 = 1100，进位值为0，跳出循环，1100为最终结果。
     */
    public int Add(int num1, int num2) {
        while (num2 != 0) {
            int temp = num1 ^ num2;
            num2 = (num1 & num2) << 1;
            num1 = temp;
        }
        return num1;
    }

    // 给定一个数组A[0,1,...,n-1],请构建一个数组B,其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
    // 不能使用除法。（注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
    // 解法： 第一种解法：遍历，每次乘积；缺点是时间复杂度过高O(n2);
    //      第二种解法：分成上下两个乘积；最终的结果为两个乘积相乘；一定要画图，对于理清思路非常重要！
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] up = new int[n];
        int[] down = new int[n];
        int[] B = new int[n];

        up[n - 1] = down[0] = 1;
        // 计算下三角
        for (int i = 1; i < n; i++) {
            down[i] = down[i - 1] * A[i - 1];
        }
        // 计算上三角
        for (int i = n - 2; i >= 0; i--) {
            up[i] = up[i + 1] * A[i + 1];
        }
        // 合并
        for (int i = 0; i < n; i++) {
            B[i] = up[i] * down[i];
        }

        return B;
    }

    public int[] multiply_V2(int[] A) {
        int length = A.length;
        int[] B = new int[length];
        //边界
        if (A == null || A.length <= 1) {
            return null;
        }
        //计算下三角
        //初始化第一行
        B[0] = 1;
        for (int i = 1; i < length; i++) {
            B[i] = B[i - 1] * A[i - 1];
        }
        //计算上三角
        //初始化最后一行
        int temp = 1;
        for (int i = length - 1; i >= 0; i--) {
            B[i] = temp * B[i];
            temp = A[i] * temp;
        }
        return B;
    }

    // 输入一个整型数组，数组里有正数也有负数。数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。要求时间复杂度为 O(n).
    // 解法： 这是典型的动态规划题目 动态规划两个特点(我自己临时概括的)：1、大问题能分解成可以直接求解的小问题 2、大问题和小问题有某种关联关系
    // 具体思路：
    // 求最大连续子数组和 可以转化为 求以每一个元素结尾的最大子数组和的最大值，而以当前元素结尾的最大子数组和可以由以上一个元素结尾的最大子数组和求得，则可使用动态规划求解
    // 设n为当前遍历元素下标，e为当前元素，f(n)为以当前元素结尾的子数组和最大值，则f(n) = MAX(f(n - 1) + e, e)，则最大子数组和为计算出的f(n)的最大值
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int total = array[0], maxSum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (total >= 0) {
                total += array[i];
            } else {
                total = array[i];
            }
            if (total > maxSum) {
                maxSum = total;
            }
        }

        return maxSum;
    }

    public int FindGreatestSumOfSubArray_V2(int[] array) {
        if (array.length == 0) return 0;

        int maxSum = array[0];
        int nowMaxSum = array[0];

        for (int i = 1; i < array.length; i++) {
            nowMaxSum = Math.max(array[i] + nowMaxSum, array[i]);
            maxSum = Math.max(maxSum, nowMaxSum);
        }

        return maxSum;
    }

    // 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
    // 解法一：递归，效率较低
    public int JumpFloor(int target) {
        if (target <= 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        // 存在重复计算，斐波那契数列
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }

    // 解法二：动态规划 自底向上，使用迭代
    public int JumpFloorV2(int target) {

        if (target <= 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;

        int result = 0;
        int oneSubStep = 1;
        int twoSubStep = 2;
        for (int i = 3; i <= target; i++) {
            result = oneSubStep + twoSubStep;
            oneSubStep = twoSubStep;
            twoSubStep = result;
        }

        return result;
    }

    //输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
    public int NumberOf1(int n) {
        int count = 0;
        while (n != 0) {
            n = n & (n - 1);
            count++;
        }
        return count;
    }

    /**
     * 题目： 旋转数组的最小数字
     * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
     * 输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
     * NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     */
    // 这题可以用两种做法，一种暴力解法，时间复杂度O(n)，另一种就是二分法的变种 O(logn)
    // 得分三种情况：
    //  用中间值和端点值比（这里例如用数组索引第一的低位），确定中间值处于递增、
    //  ，还是其他（非递减排序的数组下这里又分为两种可能，所以不能确定，只能缩短范围，将端点逻辑上往后（前）移）
    public int minNumberInRotateArray(int[] array) {
//        if (array.length == 0) return 0;
//        int min = array[0];
//        for (int i = 1; i < array.length; i++) {
//            if (array[i] <= min) min = array[i];
//        }
//        return min;
        if (array.length == 0) return 0;
        int low = 0, high = array.length - 1;
        int mid;
        while (low < high) {
            mid = low + (high - low) / 2;
            if (array[mid] > array[high]) {
                low = mid + 1;
            } else if (array[mid] == array[high]) {
                high -= 1;
            } else {
                high = mid;
            }
        }
        return array[low];

    }

    public int minNumberInRotateArray_V2(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int low = 0;
        int high = array.length - 1;
        int mid;

        while (low < high) {
            // 这个是这种解法（跟低位端点比）的一个坑点， 数组是非递减的数组，10111
            // 如果是上面第一种的跟高位端点比，就不需要这个if
            if (array[low] < array[high]) {
                return array[low];
            }
            mid = (high + low) / 2;
            if (array[mid] > array[high]) {
                low = mid + 1;
            } else if (array[mid] < array[high]) {
                high = mid;
            } else {
                high--;
            }

        }

        return array[low];
    }

    //这道题最最舒服的写法
    public int minNumberInRotateArray_V3(int[] array) {
        if (array.length == 0) {
            return 0;
        }

        int low = 0;
        int high = array.length - 1;
        int mid;

        while (low < high) {
            mid = (high + low) / 2;
            if (array[mid] < array[high]) {
                high = mid;
            } else if (array[mid] > array[high]) {
                low = mid + 1;
            } else {
                high--;
            }
        }

        return array[low];
    }

    // 剪绳子
    // 这题啊，最后的一个结论是，应该多减出长度为3的绳子，至少剩余长度为4的时候，剪成2*2；
    public int cutRope(int target) {
        if (target < 2) return 0;

        int n = target % 3;
        int m = target / 3;
        switch (n) {
            case 0:
                return (int) Math.pow(3, m);
            case 1:
                return (int) Math.pow(3, m - 1) * 4;
            case 2:
                return (int) Math.pow(3, m) * 2;
            default:
                return 0;
        }
    }


    public int Fibonacci(int n) {
        int[] numbers = new int[40];
        numbers[0] = 0;
        numbers[1] = 1;

        for (int i = 2; i <= n; i++) {
            numbers[i] = numbers[i - 1] + numbers[i - 2];
        }
        return numbers[n];
    }

    // 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方
    // 这个点考虑以下几点： 底数为0时、指数为0时、指数为负时
    // PS:细节是魔鬼
    public double Power(double base, int exponent) {
//        if (base == 0.0){
//            return 0.0;
//        }
//        // 前置结果设为1.0，即当exponent=0 的时候，就是这个结果
//        double result = 1.0d;
//        // 获取指数的绝对值
//        int e = exponent > 0 ? exponent : -exponent;
//        // 根据指数大小，循环累乘
//        for(int i = 1 ; i <= e; i ++){
//            result *= base;
//        }
//        // 根据指数正负，返回结果
//        return exponent > 0 ? result : 1 / result;
        if (base == 0.0) return 0.0;
        if (exponent == 0) return 1.0;

        double result = 1.0;
        int e = exponent > 0 ? exponent : -exponent;

        for (int i = 0; i < e; i++) {
            result *= base;
        }

        return exponent > 0 ? result : 1 / result;
    }

    // 字符串循环左移
    // 汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，就是用字符串模拟这个指令的运算结果。
    // 对于一个给定的字符序列S，请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,要求输出循环左移3位后的结果，即“XYZdefabc”。
    // 是不是很简单？OK，搞定它！
    public String LeftRotateString(String str, int n) {
        if (str == null || n > str.length()) {
            return str;
        }

        return str.substring(n) + str.substring(0, n);
    }

    /**
     * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
     * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
     * 下面是两个要求实现的方法
     */
    Map<Character, Integer> map = new LinkedHashMap<>();

    //Insert one char from stringstream
    public void Insert(char ch) {
        if (map.containsKey(ch)) {
            map.put(ch, map.get(ch) + 1);
        } else {
            map.put(ch, 1);
        }

    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        // LinkedHashMap是双向链表，记录了顺序；所以在遍历中，找到第一个为1的就是答案
        for (char ch : map.keySet()) {
            if (map.get(ch) == 1) {
                return ch;
            }
        }

        return '#';
    }

    class ListNode {

        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    // 题目：圆圈中最后剩下的数（又叫 约瑟夫环）
    //  这道题下面有三种解法
    //  这是第一种： 利用链表自己组一个环，这个方法不难，可以写出来
    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }

        ListNode head = new ListNode(0);
        ListNode node = head;
        // 第一步：构建一个环
        for (int i = 1; i < n; i++) {
            node.next = new ListNode(i);
            node = node.next;
        }
        // 这里要注意，尾-首相连；现在node是尾巴，按题目的要求，遍历要从第一个数开始，所以node从尾巴开始就很合适了
        node.next = head;

        // 第二步：开始循环遍历
        int k = 0;
        while (node.next != node) {
            k++;
            // 如果这一步后移的结点就是那个结点，则删掉这个结点；注意，这里的node还是上一步的结点，没有移动；k重新置为0
            if (k == m) {
                node.next = node.next.next;
                k = 0;
            } else {
                node = node.next;
            }
        }

        return node.val;

    }


    // 第二种：其实跟第一种差不多意思
    //  每次成为一个环形链表。例如 输入 5 3 即：0 1 2 3 4，每次移动标尺3。为 （3 - 1） % 5 = 2；第一次去除2，
    //  变成新数组 0 1 3 4 这是新删除的坐标位置不再直接是3，而是前一次的坐标位置加上移动标尺3再求余。
    //  即（2 + 3 - 1）% 4 = 0；即删除0坐标元素。一次递推，直到数组大小为1。
    //  整个过程：输入（n,m）
    //  迭代公式为：
    //    pre = (pre + m - 1) % n--; //前一个坐标初始化为0 记pre
    public int LastRemaining_Solution_V2(int n, int m) {
        if (n <= 0 || m <= 0) return -1;

        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            integers.add(i);
        }

        int pre = 0;
        while (integers.size() != 1) {
            pre = (pre + m - 1) % integers.size();
            integers.remove(pre);
        }

        return integers.get(0);

    }

    // 第三种： 这个就啰嗦了；是剑指offer书上写的：不利用高级函数，纯找规律；太费劲了，直接贴答案
    public int LastRemaining_SolutionV3(int n, int m) {
        if (n <= 0 || m <= 0) return -1;
        if (n == 1) return 0;

        return (LastRemaining_SolutionV3(n - 1, m) + m) % n;
    }

    // 第三种： 或者这种写法
    public int LastRemaining_SolutionV4(int n, int m) {

        if (n <= 0 || m <= 0) return -1;

        int p = 0;
        for (int i = 2; i <= n; i++) {
            p = (p + m) % i;
        }

        return p;
    }

    // 统计一个数字在升序数组中出现的次数
    // 例： 输入 [1,2,3,3,3,3,4,5],3  返回 4
    // 题解： 二分
    // 第一种解法： 这里是鄙人自己写的，先判断位置，与中间值比较；若小，则在左；若大，则在右；若等，则左右横跳
    public int GetNumberOfK(int [] array , int k) {
        if (array == null || array.length == 0) return 0;

        int len = array.length;
        int low = 0;
        int high = len - 1;
        int count = 0;
        int mid;

        while (low <= high) {
            mid = (high + low) / 2;
            if (array[mid] < k) {
                low = mid + 1;
            } else if (array[mid] > k) {
                high = mid - 1;
            } else {
//                count++;
                for (int i = mid; i >= low ; i--) {
                    if (array[i] < k) {
                        break;
                    }
                    count++;
                }
                for (int i = mid + 1; i <= high; i++) {
                    if (array[i] > k) {
                        break;
                    }
                    count++;
                }
                return count;
            }
        }


        return count;
    }

    // 第二种解法：也是利用二分，但是为的是确定第一个等于k的值的位置；和最后一个等于k的值的位置 两者相减加1就是答案，费劲，
    // 说是比上面那个时间复杂度高；上面那个方法时间复杂度最后还是O(n)，而这个是O(logN)
    //
    public int GetNumberOfK_V2(int [] array , int k) {
        // 费劲，淦！

        return -1;
    }

    // 题目： 数组中只出现一次的数字： 一个整型数组里除了两个数字之外，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
    // 这个题目解法有很多：比较优秀的解肯定是书上的先拆分两个子数组，再异或；（一个数异或它本身得到的值就是0）；
    // 还有两种比较简单但是时间/空间复杂度高点的；第一种，利用map，记录次数；第二种，利用个arraylist，下面就是这种
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        if(array.length < 2){
            return;
        }
        ArrayList<Integer> list = new ArrayList();
        for(int i = 0;i<array.length;i++){
            if(list.contains(array[i])){
                // 这里他娘的要注意啊，这个remove同名方法有两个，这个是移除对象
                list.remove(new Integer(array[i]));
            }else{
                list.add(array[i]);
            }
        }
        num1[0] = list.get(0);
        num2[0] = list.get(1);
    }

    // 题目：和为S的两个数字
    // 描述： 输入一个递增排序的数组和一个数字S，在数组中查找两个数，使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
    // 思路：有序数组，使用双指针法;
    // 因为数组是有序的，所以可以用双指针，指向数组的首尾，具体步骤如下：
    //  1.初始化：指针i指向数组首， 指针j指向数组尾部
    //  2. 如果arr[i] + arr[j] == sum , 说明是可能解
    //  3. 否则如果arr[i] + arr[j] > sum, 说明和太大，所以--j
    //  4. 否则如果arr[i] + arr[j] < sum, 说明和太小，所以++i
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (array == null || array.length == 0) return integers;

        int head = 0;
        int tail = array.length - 1;
        while (head < tail) {
            int result = array[head] + array[tail];
            if (result == sum) {
                integers.add(array[head]);
                integers.add(array[tail]);
                break;
            }
            if (result < sum) {
                head++;
            }
            if (result > sum) {
                tail--;
            }
        }
        return integers;
    }

    // 题目：第一个只出现一次的字符
    // 描述：在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,并返回它的位置,
    // 如果没有则返回 -1（需要区分大小写）.
    public int FirstNotRepeatingChar(String str) {

        if (str == null || str.length() == 0) return -1;
        // todo 这个知识点要补一下 数组字母做索引，这里好像是ACII码转换啥的
        int[] count = new int[256];

        int len = str.length();
        for (int i = 0; i < len; i++) {
            // 可以直接写作 count[str.charAt(i)++;
            count[str.charAt(i)] = count[str.charAt(i)] + 1;
        }

        for (int i = 0; i < len; i++) {
            if (count[str.charAt(i)] == 1) {
                return i;
            }
        }

        return -1;

    }

    // 找出数组中第一个重复的数字
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        Set<Integer> set = new HashSet<>(length);
        for (int i = 0; i < length; i++) {
            if (set.contains(numbers[i])) {
                duplication[0] = numbers[i];
                return true;
            } else {
                set.add(numbers[i]);
            }
        }
        return false;
    }

    // 题目： 把数组排成最小的数
    // 描述： 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。
    //      例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
    // 解析： 看一下解析，比较关键的一句话 所以在这里自定义一个比较大小的函数，比较两个字符串s1, s2大小的时候，先将它们拼接起来，比较s1+s2,和s2+s1哪个大，
    //      如果s1+s2大，那说明s2应该放前面，所以按这个规则，s2就应该排在s1前面。 。
    public String PrintMinNumber(int [] numbers) {
        if (numbers == null || numbers.length == 0) {
            return "";
        }

        int len = numbers.length;

        // 自定义的排序规则
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int sum1 = Integer.parseInt(numbers[i] + "" + numbers[j]);
                int sum2 = Integer.parseInt(numbers[j] + "" + numbers[i]);
                if (sum1 > sum2) {
                    int temp = numbers[j];
                    numbers[j] = numbers[i];
                    numbers[i] = temp;
                }
            }
        }

        StringBuilder string = new StringBuilder();
        for (int i = 0; i < len; i++) {
            string.append(numbers[i]);
        }

        return string.toString();
    }

    // 题目：数组中出现次数超过一半的数字
    // 描述： 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
    // 例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
    public int MoreThanHalfNum_Solution(int [] array) {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.pop();
        stack.peek();
        stack.empty();
        stack.isEmpty();


        return 0;
    }

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();




    }






}