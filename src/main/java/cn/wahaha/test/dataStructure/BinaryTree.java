package cn.wahaha.test.dataStructure;

import java.util.*;

/**
 * @Description: BinaryTree
 * @Author: zhangrenwei
 * @Date: 2019-10-18 23:37
 */
public class BinaryTree {
    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    //求二叉树深度
    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftSonDepth = treeDepth(root.left);
        int rightSonDepth = treeDepth(root.right);
        return leftSonDepth >= rightSonDepth ? leftSonDepth + 1 : rightSonDepth + 1;
    }

    //给定一个二叉树，将其变为它的镜像
    //https://www.nowcoder.com/practice/564f4c26aa584921bc75623e48ca3011?tpId=13&tqId=11171&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
    public void mirror(TreeNode root) {
        if (root != null) {
            TreeNode tmp = root.right;
            root.right = root.left;
            root.left = tmp;
            mirror(root.left);
            mirror(root.right);
        }

    }

    //判断是否是平衡二叉树
    //https://www.nowcoder.com/practice/8b3b95850edb4115918ecebdf1b4d222?tpId=13&tqId=11192&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
    //这种做法有很明显的问题，在判断上层结点的时候，会多次重复遍历下层结点，增加了不必要的开销。如果改为从下往上遍历，如果子树是平衡二叉树，则返回子树的高度；
    // 如果发现子树不是平衡二叉树，则直接停止遍历，这样至多只对每个结点访问一次。参见更好的解法： isBalanced_Solution_V2
    public boolean isBalanced_Solution(TreeNode root) {
        if (root == null) {
            return true;
        }
        int balanceFactor = treeDepth(root.left) - treeDepth(root.right);
        int bf = Math.abs(balanceFactor);
        return (bf == 0 || bf == 1) && isBalanced_Solution(root.left) && isBalanced_Solution(root.right);
    }

    public boolean isBalanced_Solution_V2(TreeNode root) {

        return getDepth(root) == -1;
    }

    private int getDepth(TreeNode root) {
        if (root == null) return 0;
        int left = getDepth(root.left);
        //这里很关键，底部探测到为-1则一路返回到跟，不再进行任何操作
        if (left == -1) return -1;
        int right = getDepth(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) + 1;
    }

    //判断两个二叉树是否是对称的
    boolean isSymmetrical(TreeNode root) {
        //下面这个解法永远为真，应该是地址引用的问题
//        TreeNode root1 = root;
//        mirror(root);
//        return root == root1;
        /*思路：首先根节点以及其左右子树，左子树的左子树和右子树的右子树相同
         * 左子树的右子树和右子树的左子树相同即可，采用递归
         * 非递归也可，采用栈或队列存取各级子树根节点
         */
        if (root == null) return true;
        return isLeftAndRightEquals(root.left, root.right);
    }

    boolean isLeftAndRightEquals(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return left.val == right.val && isLeftAndRightEquals(left.left, right.right) && isLeftAndRightEquals(left.right, right.left);
    }

    //从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行
    /**
     * 这个方法复杂低效，利用队列逻辑就会清楚很多
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        Integer treeDepth = treeDepth(pRoot);
        ArrayList<ArrayList<Integer>> treeValueList = new ArrayList<ArrayList<Integer>>(treeDepth);
        if (treeDepth == 0) return treeValueList;

        for (int i = 0; i < treeDepth; i++) {
            treeValueList.add(new ArrayList<Integer>());
        }
        printALine(pRoot, 0, treeValueList, treeDepth);

        return treeValueList;
    }

    void printALine(TreeNode root, int layer, ArrayList<ArrayList<Integer>> treeValueList, int treeDepth) {
        if (layer >= treeDepth || root == null) {
            return;
        }
        ArrayList<Integer> thisLine = treeValueList.get(layer);
        thisLine.add(root.val);

        printALine(root.left, layer + 1, treeValueList, treeDepth);
        printALine(root.right, layer + 1, treeValueList, treeDepth);
    }
     **/
    /**
     * 这道题要用队列来做，先进先出
     * @param pRoot
     * @return
     */
    ArrayList<ArrayList<Integer> > Print(TreeNode pRoot) {
        //返回的整数集合的集合
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null) {
            return arrayLists;
        }
        //利用队列来做到先进先出
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);

        //每次会将各个节点不为空的子节点加入队列，如果队列不空，则需要继续打印
        while (!queue.isEmpty()) {
            //这个size是要遍历的当前层的节点数的多少
            int size = queue.size();
            ArrayList<Integer> integers = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                //每次取出一个元素，循环结束，则当前层的节点已经全部遍历结束
                TreeNode node = queue.poll();

                integers.add(node.val);

                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }

            arrayLists.add(integers);
        }

        return arrayLists;
    }

    //给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return null;
        // 1 右节点不为空情况，中序遍历下一节点为右节点的最左节点
        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        // 2 右节点为空，（不包含跟节点）父节点不为空，当前节点是父节点的左节点时，下一节点为父节点
        if (pNode.next != null && pNode == pNode.next.left) {
            return pNode.next;
        }
        // 3 右节点为空，（不包含跟节点）父节点不为空，当前节点为父节点的右结点，向上遍历找到父结点为其本人父节点的左节点，此时下一结点为其本身的父结点
        if (pNode.next != null) {
            pNode = pNode.next;
            while (pNode.next != null && pNode == pNode.next.left) {
                pNode = pNode.next;
            }
            return pNode.next;
        }

        return null;
    }

    /**
     * 这个方法是上面那个方法的简化版
     * @param pNode
     * @return
     */
    public TreeLinkNode GetNext2(TreeLinkNode pNode) {
        if(pNode == null) return null;
        if(pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while(pNode.next != null){
            if(pNode == pNode.next.left){
                return pNode.next;
            }
            pNode = pNode.next;
        }

        return null;
    }

    public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    //从上往下打印出二叉树的每个节点，同层节点从左至右打印。这个方法借助队列是最好的方法，其他方法都很低效（说多了都是泪）
    //返回一个整数的list集合

    public ArrayList<Integer> PrintFromTopToBottomV2(TreeNode root) {
        ArrayList<Integer> integers = new ArrayList<>();
        //if (root == null) return integers;
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        while (treeNodes.size() != 0) {
            TreeNode treeNode = treeNodes.remove(0);
            integers.add(treeNode.val);
            if (treeNode.left != null) {
                treeNodes.add(treeNode.left);
            }
            if (treeNode.right != null) {
                treeNodes.add(treeNode.right);
            }
        }
        return integers;
    }

    // 拓展二
    public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (root == null) return integers;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            integers.add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

        return integers;
    }


    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (root == null) return arrayLists;
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(root.val);

        sumAPathValue(root, integers, arrayLists, target);
        //Collections.sort(arrayLists, Comparator.comparingInt(a -> a.size()));
        return arrayLists;
    }

    //输入一颗二叉树的跟节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径
    public void sumAPathValue(TreeNode node, ArrayList<Integer> integers, ArrayList<ArrayList<Integer>> arrayLists, int target) {

        if (node.left == null && node.right == null) {
            int sum = 0;
            for (int i = 0; i < integers.size(); i++) {
                sum += integers.get(i);
            }
            if (sum == target) {
                arrayLists.add(integers);
            }
            //return;
        }

        ArrayList<Integer> leftPath;
        ArrayList<Integer> rightPath;
        if (node.left != null) {
            leftPath = new ArrayList<>(integers);
            leftPath.add(node.left.val);
            sumAPathValue(node.left, leftPath, arrayLists, target);
        }
        if (node.right != null) {
            rightPath = new ArrayList<>(integers);
            rightPath.add(node.right.val);
            sumAPathValue(node.right, rightPath, arrayLists, target);
        }

    }

    private ArrayList<ArrayList<Integer>> result = new ArrayList<>();
    private ArrayList<Integer> list = new ArrayList<>();
    // 这道题还是得用递归做法
    public ArrayList<ArrayList<Integer>> FindPathV2(TreeNode root, int target) {
        if (root == null) return result;

        list.add(root.val);
        target -= root.val;

        if (target == 0 && root.left == null && root.right == null) {
            result.add(new ArrayList<>(list));
        }

        FindPathV2(root.left, target);
        FindPathV2(root.right, target);
        list.remove(list.size() - 1);

        return result;
    }

    /**
     * 请实现一个函数按照"之"字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     * 解： 终于可以战胜这类题了，无论是多层打印、按"之"字形打印，其本质都是二叉树的层序遍历；借助其他的数据结构来辅助完成是比较好的选择，目前来看队列是最简单的实现方式
     *      这道题，相较于按照每层从左到右打印，无非是多了一个反转。
     *
     */
    public ArrayList<ArrayList<Integer>> Print_之(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null) return arrayLists;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(pRoot);
        boolean reverse = false;

        while (!queue.isEmpty()) {
            ArrayList<Integer> integers = new ArrayList<>();

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                // 如果使用下面注释掉的这个部分，for循环下面那个if方法就可以不用了；但是两者的效率哪个高还要对比下，都涉及到list数组的移动
//                if(reverse){
//                    integers.add(0, node.val);
//                } else {
                    integers.add(node.val);
//                }

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            if (reverse){
                Collections.reverse(integers);
            }
            arrayLists.add(integers);
            reverse = !reverse;
        }

        return arrayLists;
    }



    //输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    // 例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，则重建二叉树并返回。
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

        return reConstructBinaryTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode reConstructBinaryTree(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) return null;

        TreeNode root = new TreeNode(pre[startPre]);
        //310ms 22556k
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + i - startIn, in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, startPre + i - startIn + 1, endPre, in, i + 1, endIn);
                break;
            }
        }

        return root;
    }

    public TreeNode reConstructBinaryTreeV2(int[] pre, int startPre, int endPre, int[] in, int startIn, int endIn) {
        if (startPre > endPre || startIn > endIn) return null;
        TreeNode root = new TreeNode(pre[startPre]);
        for (int i = startIn; i <= endIn; i++) {
            if (in[i] == pre[startPre]) {
                root.left = reConstructBinaryTree(pre, startPre + 1, startPre + (i - startIn), in, startIn, i - 1);
                root.right = reConstructBinaryTree(pre, startPre + (i - startIn) + 1, endPre, in, i + 1, endIn);
                break;
            }
        }

        return root;
    }

    public TreeNode reConstructBinaryTreeV3(int[] pre, int[] in) {
        if (pre.length == 0 || in.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        // 在中序中找到前序的根
        for (int i = 0; i < in.length; i++) {
            if (in[i] == pre[0]) {
                // 左子树，注意 copyOfRange 函数，左闭右开
                root.left = reConstructBinaryTreeV3(Arrays.copyOfRange(pre, 1, i + 1), Arrays.copyOfRange(in, 0, i));
                // 右子树，注意 copyOfRange 函数，左闭右开
                root.right = reConstructBinaryTreeV3(Arrays.copyOfRange(pre, i + 1, pre.length), Arrays.copyOfRange(in, i + 1, in.length));
                break;
            }
        }
        return root;
    }

    //请实现两个函数，分别用来序列化和反序列化二叉树；序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#）。
    String Serialize(TreeNode root) {
        //先序遍历
        StringBuilder stringBuilder = new StringBuilder();
        Serialize(root, stringBuilder);
        return stringBuilder.toString();

    }

    void Serialize(TreeNode root, StringBuilder stringBuilder) {
        if (root == null) {
            stringBuilder.append("#,");
            return;
        }
        stringBuilder.append(root.val + ",");
        Serialize(root.left, stringBuilder);
        Serialize(root.right, stringBuilder);
    }

    int index = -1;

    TreeNode Deserialize(String str) {
        index++;
        String[] strings = str.split(",");
        //注意这里没有判断数组越界问题，因为不会发生
        if (strings[index].equals("#")) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.parseInt(strings[index]));
        treeNode.left = Deserialize(str);
        treeNode.right = Deserialize(str);
        return treeNode;
    }

    String SerializeV2(TreeNode root) {
        StringBuilder stringBuilder = new StringBuilder();
        if (root == null) {
            stringBuilder.append("#,");
            return stringBuilder.toString();
        }
        stringBuilder.append(root.val + ",");
        stringBuilder.append(Serialize(root.left));
        stringBuilder.append(Serialize(root.right));
        return stringBuilder.toString();
    }

    int index2 = -1;

    TreeNode DeserializeV2(String str) {
        index2++;
        String[] strings = str.split(",");
        if (strings[index2].equals("#")) {
            return null;
        }
        TreeNode treeNode = new TreeNode(Integer.valueOf(strings[index2]));
//        index2++;
        treeNode.left = DeserializeV2(str);
        treeNode.right = DeserializeV2(str);

        return treeNode;
    }

    // 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
    // 这个题目要画个图，思考下就一目了然了（我真棒！）
    // 递归思路。
    // 第一步：左子树转成排序的双向链表（返回链表头），
    // 第二步：右子树转成双向链表（返回链表头）；
    // 第三步：将左子树的链表尾部和根结点连接，将右子树的链表头跟根结点连接
    // PS: 二叉搜索树：左子树比根结点值小，右子树比根结点值大；左右子树各自右都是二叉搜索树

    public TreeNode Convert(TreeNode pRootOfTree) {
        if(pRootOfTree == null || (pRootOfTree.left == null && pRootOfTree.right == null)) {
            return pRootOfTree;
        }
        // 第一步
        TreeNode left = Convert(pRootOfTree.left);
        // 第二步
        TreeNode right = Convert(pRootOfTree.right);
        // 第三步
        TreeNode temp = left; // left 要保存起来返回
        while (temp != null && temp.right != null) {
            temp = temp.right;
        }
        if (temp != null) {
            temp.right = pRootOfTree;
            pRootOfTree.left = temp;
        }

        if (right != null) {
            right.left = pRootOfTree;
            pRootOfTree.right = right;
        }

        return left == null ? pRootOfTree : left;
    }

    public TreeNode Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree == null || (pRootOfTree.left == null && pRootOfTree.right == null)) {
            return pRootOfTree;
        }

        TreeNode left = Convert(pRootOfTree.left);
        TreeNode p = left;
        while (p != null && p.right != null) {
            p = p.right;
        }
        if (left != null) {
            p.right = pRootOfTree;
            pRootOfTree.left = p;
        }

        TreeNode right = Convert(pRootOfTree.right);
        if (right != null) {
            pRootOfTree.right = right;
            right.left = pRootOfTree;
        }

        return left == null ? pRootOfTree : left;
    }

    /**
     * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true,否则返回false。假设输入的数组的任意两个数字都互不相同。
     * 解答：
     *      这道题其实比较好理解，画个图，回顾后序遍历的做法；利用递归，和上面那道根据前序遍历和中序遍历重建二叉树的题思路类似
     */
    public boolean helpVerify(int [] sequence, int start, int root){
        if(start >= root)return true;
        int key = sequence[root];
        int i;
        //找到左右子数的分界点
        for(i=start; i < root; i++)
            if(sequence[i] > key)
                break;
        //在右子树中判断是否含有小于root的值，如果有返回false
        for(int j = i; j < root; j++)
            if(sequence[j] < key)
                return false;
        return helpVerify(sequence, start, i-1) && helpVerify(sequence, i, root-1);
    }
    public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence == null || sequence.length == 0)return false;
        return  helpVerify(sequence, 0, sequence.length-1);

    }

    public boolean VerifySquenceOfBSTV2(int [] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }

        if (sequence.length == 1) {
            return true;
        }

        int root = sequence[sequence.length - 1];
        int i;
        for (i = 0; i < sequence.length - 1; i++) {
            if (sequence[i] > root) {
                break;
            }
        }

        for (int j = i; j < sequence.length - 1; j++) {
            if (sequence[j] < root) {
                return false;
            }
        }

        boolean left = true, right = true;
        if (i > 0) {
            left = VerifySquenceOfBSTV2(Arrays.copyOfRange(sequence, 0, i));
        }
        if (i < sequence.length - 1) {
            right = VerifySquenceOfBSTV2(Arrays.copyOfRange(sequence, i, sequence.length - 1));
        }

        return left && right;
    }



    /**
     * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
     * 这题看似简单，其实有点绕，得好好理解
     */

    //遍历大树
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        // 1、当两棵树有一颗为空，或者两棵树遍历完了，至少有一颗为空的时候，均为false;
        if (root1 == null || root2 == null) {
            return false;
        }

        // 2、当有一个值开始相等时，才开始判断这两棵树的子树的值是不是也都一一相等；否则，递归找到第一个相等的值，
        //  如果找不到，当递归进行到第一步时，就结束了。
        if (root1.val == root2.val) {
            // 这里不能直接 return judge(root1, root2); 的原因是，题目没有说明A，B两棵树的值不可重复
            if (judge(root1, root2)) {
                return true;
            }
        }

        return HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }

    // 判断是否是子结构
    public boolean judge(TreeNode root, TreeNode subtree) {
        // 子结构循环完毕，期间没有返回false，说明全部匹配
        if (subtree == null) {
            return true;
        }

        // 大树已经循环完毕，而子树还有结点，说明没有匹配成功
        if (root == null) {
            return false;
        }

        if (root.val == subtree.val) {
            // 相等后判断左右孩子结点
            return judge(root.left, subtree.left) && judge(root.right, subtree.right);
        }
        return false;
    }

    /**
     * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）中，按结点数值大小顺序第三小结点的值为4。
     * 解： 二叉搜索树： 左子树都比根小，右子树都比根大；中序遍历打印一下，就是排好序的树结点值大小；
     */

    List<TreeNode> integers = new ArrayList<>();
    TreeNode KthNode(TreeNode pRoot, int k)
    {
        OrderTraversal(pRoot);
        if (k > integers.size() || k < 1){
            return null;
        }
        return integers.get(k - 1);
    }

    // 中序遍历
    public void OrderTraversal(TreeNode node) {
        if (node == null) return;
        OrderTraversal(node.left);
        integers.add(node);
        OrderTraversal(node.right);
    }

    public static void main(String[] args) {
//        int [] ints = new int[]{3,6,7,5};
//
//        int i= 0;
//        int root = ints[ints.length - 1];
//        for (i = 0; i < ints.length - 1; i++) {
//            if (ints[i] > root)
//                break;
//        }
//        System.out.println(i);

        List<Integer> integers = new LinkedList<>();
        integers.add(1);
        integers.add(2);
        integers.add(3);
        integers.add(4);
        // 队列 先进先出
        System.out.println(integers.remove(0));
    }

}
