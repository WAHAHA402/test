package cn.wahaha.test.dataStructure;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.omg.CORBA.INTERNAL;

import java.util.*;


public class Temp {
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//
//
//        String string = scanner.nextLine();
////        String string = "*c*m*b*n*t*";
//        StringBuilder stars = new StringBuilder();
//        StringBuilder letters = new StringBuilder();
//
//        for (int i = 0; i < string.length(); i++) {
//            if (string.charAt(i) == '*') {
//                stars.append('*');
//            } else {
//                letters.append(string.charAt(i));
//            }
//        }
//
//        System.out.println(stars.append(letters).toString());
//
//    }


//    public static void main(String[] args) {
//        //1.读取数据
//        Scanner scan = new Scanner(System.in);
//        String strs [] = scan.nextLine().split(" ");
//        //2.将数据存储在list中(保证不存在重复的数据)
//        ArrayList<Integer> list = new ArrayList<>();
//        for (int i = 0; i < strs.length; i++) {
//            list.add(Integer.parseInt(strs[i]));
//        }
//
//        // 3.将数据进行排序
//        Collections.sort(list);
//        int count = 0;
//        int sum = 0;
//        // 4.对数据进行判断
//
//        for (int i = 0; i < list.size() - 1; i++) {
//            int head = i;
//            int tail = list.size() - 1;
//
//            sum = list.get(head) + list.get(tail);
//
//            while (head < tail){
//                if (sum < 24){
//                    head++;
//                    sum = sum + list.get(head);
//                }
//                if(sum > 24) {
//                    sum = sum - list.get(tail);
//                    tail--;
//                    sum = sum + list.get(tail);
//                }
//                if (sum == 24) {
//                    count++;
//                    break;
//                }
//            }
//        }
//
//        System.out.println(count);
//        scan.close();
//    }

    ////        for (int i = 0; i < list.size()-1; i++) {
    ////            for (int j = list.size()-1; j >=i; j--) {
    ////                sum += list.get(i)+list.get(j);
    ////                if (sum < 24) {
    ////
    ////                    break;
    ////                }
    ////                if (list.get(i)+list.get(j)==24) {
    ////                    count++;
    ////                    break;
    ////                }
    ////            }
    ////
    ////        }

    public void getRandomPK() {
        int[] temp = new int[100];
        // 初始化略过
        int[] result = new int[100];

        int length = 100;

        for (int i = 99; i >= 0; i--) {
            int num = getRandomInteger(i);
            int temp1 = temp[num];
            temp[num] = temp[99 - i];
            result[i] = temp1;

        }
    }

    public static int getRandomInteger(int n) {
        return new Random().nextInt(n);
    }

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {
            this.val = val;
        }
    }

    List<List<Integer>> result = new ArrayList<>();
    boolean reverse = false;

    public List<List<Integer>> print(TreeNode root){
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                list.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }

            }
            if (reverse) {
                Collections.reverse(list);
            }
            result.add(list);
            reverse = !reverse;
        }


        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        Temp temp = new Temp();
        List<List<Integer>> list = temp.print(node1);
        for (int i = 0; i < list.size(); i++) {
            System.out.println(JSON.toJSONString(list.get(i)));
        }

    }

//    int index = -1;
//    TreeNode buildTree(int[] ints) {
//        index++;
////        int len = ints.length;
////        for (int i = 0; i < len; i++) {
////
////        }
//
//
//
//    }
//
//    TreeNode buildTree(TreeNode node){
//        buildTree(node.left)
//    }
    /**
     * 问题描述：一个青蛙,一次可以跳1级台阶,也可以跳2级,…也可以跳m级,总共有n级台阶,问青蛙总共有多少种跳法?
     */
    int jump(int n, int m){
        if(n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return 2*jump(n-1) - jump(n - m - 1);
    }

    int jump(int k){
        if (k == 1) {
            return 1;
        }
        if (k == 2) {
            return 2;
        }
        return jump(k -1) * 2;
    }






























}
