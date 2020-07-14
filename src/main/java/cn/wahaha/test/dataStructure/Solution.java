package cn.wahaha.test.dataStructure;

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
    //1.需利用逻辑与的短路特性实现递归终止。 2.当n==0时，(n>0)&&((sum+=Sum_Solution(n-1))>0)只执行前面的判断，为false，然后直接返回0；
    //3.当n>0时，执行sum+=Sum_Solution(n-1)，实现递归计算Sum_Solution(n)。
    public int Sum_Solution(int n) {
        int temp = n;
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

    public int[] multiply(int[] A) {
        int n = A.length;
        int[] down = new int[n];
        down[0] = 1;
        int[] up = new int[n];
        up[n - 1] = 1;

        int[] B = new int[n];
        for (int i = 1; i <= n - 1; i++) {
            down[i] = down[i - 1] * A[i - 1];
        }
        for (int j = n - 2; j >= 0; j--) {
            up[j] = up[j + 1] * A[j + 1];
        }
        for (int i = 0; i <= n - 1; i++) {
            B[i] = down[i] * up[i];
        }
        return B;
    }

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

    //一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
    //解法一：递归，不可取
    public int JumpFloor(int target) {
        if (target <= 0) return 0;
        if (target == 1) return 1;
        if (target == 2) return 2;
        //重复计算次数太多，效率非常低，不可取
        return JumpFloor(target - 1) + JumpFloor(target - 2);
    }

    //解法二：动态规划 自底向上，使用迭代
    public int JumpFloorV2(int target) {
        if (target < 0) return -1;
        if (target == 0 || target == 1 || target == 2) return target;
        int one = 1, two = 2;
        int result = 0;
        for (int i = 3; i <= target; i++) {
            result = one + two;
            one = two;
            two = result;
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

    public int cutRope(int target) {
        if (target <= 1) return -1;
        if (target <= 3) return target - 1;
        if (target == 4) return 4;

        int x = target % 3;
        int y = target / 3;
        if (x == 0) {
            return (int) Math.pow(3, y);
        } else if (x == 1) {
            return (int) (4 * Math.pow(3, y - 1));
        } else {
            //x == 2
            return (int) (2 * Math.pow(3, y));
        }
    }


}
