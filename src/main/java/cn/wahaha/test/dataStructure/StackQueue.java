package cn.wahaha.test.dataStructure;

import java.util.Stack;

/**
 * @Description: StackQueue
 * @Author: zhangrenwei
 * @Date: 2019/12/8 11:15 下午
 */

public class StackQueue {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        if (stack2.size() != 0) {
            return stack2.pop();
        }
        while (stack1.size() != 0) {
            stack2.push(stack1.pop());
        }
        return stack2.pop();
    }
}
