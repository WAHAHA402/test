package cn.wahaha.test.dataStructure;

import java.util.*;

public class StackPractice {
    // 用两个队列实现栈
    class MyStack {
        Queue<Integer> queue1;
        Queue<Integer> queue2;

        /** Initialize your data structure here. */
        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        /** Push element x onto stack. */
        public void push(int x) {
            queue2.offer(x);
            while(!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
            Queue tempQueue = queue1;
            queue1 = queue2;
            queue2 = tempQueue;

        }

        /** Removes the element on top of the stack and returns that element. */
        public int pop() {
            return queue1.poll();
        }

        /** Get the top element. */
        public int top() {
            return queue1.peek();
        }

        /** Returns whether the stack is empty. */
        public boolean empty() {
            return queue1.isEmpty();
        }

    }


    // 有效的括号
    public boolean isValid(String s) {
        int len = s.length();
        // 非偶数直接排除
        if(len % 2 == 1) {
            return false;
        }

        // 用映射保存括号对的关系
        Map<Character, Character> pairs = new HashMap(3){{
            put(')','(');
            put('}','{');
            put(']','[');
        }};

        Stack<Character> stack = new Stack<>();
        // 遍历整个字符串，如果是右括号，则栈顶需有个对应的左括号
        for(int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if(pairs.containsKey(ch)){
                if(stack.isEmpty() || stack.peek() != pairs.get(ch)) {
                    return false;
                }
                // 匹配成功，弹出左括号
                stack.pop();
            } else {
                // 是左括号，放入该左括号
                stack.push(ch);
            }
        }
        // 遍历结束，判断是否还有剩余左括号
        return stack.isEmpty();

    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }

    // 题目描述： 二叉树的锯齿形层序遍历
    // 给定一个二叉树，返回其节点值的锯齿形层序遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> ans = new LinkedList<List<Integer>>();
        if (root == null) {
            return ans;
        }

        Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
        nodeQueue.offer(root);
        boolean isOrderLeft = true;

        while (!nodeQueue.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<Integer>();
            int size = nodeQueue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode curNode = nodeQueue.poll();
                if (isOrderLeft) {
                    levelList.offerLast(curNode.val);
                } else {
                    levelList.offerFirst(curNode.val);
                }
                if (curNode.left != null) {
                    nodeQueue.offer(curNode.left);
                }
                if (curNode.right != null) {
                    nodeQueue.offer(curNode.right);
                }
            }
            ans.add(new LinkedList<Integer>(levelList));
            isOrderLeft = !isOrderLeft;
        }

        return ans;
    }


}
