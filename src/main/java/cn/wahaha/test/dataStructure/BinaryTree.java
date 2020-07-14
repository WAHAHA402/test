package cn.wahaha.test.dataStructure;

import java.util.ArrayList;
import java.util.Stack;

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
        if (root == null) {
            return true;
        }
        return isLeftAndRightEquals(root.left, root.right);
    }

    public boolean isLeftAndRightEquals(TreeNode left, TreeNode right) {
        if (left == null && right == null) return true;
        if (left == null || right == null) return false;

        return (left.val == right.val) && isLeftAndRightEquals(left.left, right.right) && isLeftAndRightEquals(left.right, right.left);
    }

    //从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行
    ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        Integer treeDepth = treeDepth(pRoot);
        ArrayList<ArrayList<Integer>> treeValueList = new ArrayList<ArrayList<Integer>>(treeDepth);
        if (treeDepth == 0) return null;

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

    //给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null) return null;

        if (pNode.right != null) {
            pNode = pNode.right;
            while (pNode.left != null) {
                pNode = pNode.left;
            }
            return pNode;
        }
        while (pNode.next != null) {
            if (pNode == pNode.next.left) {
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

    //从上往下打印出二叉树的每个节点，同层节点从左至右打印。
    //返回一个整数的list集合
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {

        ArrayList<Integer> integers = new ArrayList<>();

        if (root == null) return integers;
        ArrayList<ArrayList<Integer>> intArrayLists = new ArrayList<>();
        printNode(root, 1, intArrayLists);

        for (int i = 0; i < intArrayLists.size(); i++) {
            integers.addAll(intArrayLists.get(i));
        }
        return integers;
    }

    void printNode(TreeNode treeNode, int depth, ArrayList<ArrayList<Integer>> intArrayLists) {
        if (treeNode == null) return;
        if (depth > intArrayLists.size()) {
            intArrayLists.add(new ArrayList<>());
        }
        intArrayLists.get(depth - 1).add(treeNode.val);

        printNode(treeNode.left, depth + 1, intArrayLists);
        printNode(treeNode.right, depth + 1, intArrayLists);
    }

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

    //拓展二：二叉树的层次遍历，借助一个队列就可以了
    //返回一个list的list集合
    public ArrayList<ArrayList<Integer>> PrintFromTopToBottom_multiple_lines(TreeNode root) {
        //存放结果
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (root == null) return arrayLists;
        //使用队列，先进先出
        ArrayList<TreeNode> queue = new ArrayList();
        //存放每行的列表
        ArrayList<Integer> arrayList = new ArrayList<>();
        //记录本层打印了多少个
        int start = 0;
        //记录下层要打印几个
        int end = 1;
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.remove(0);
            //添加到本行的arrayList
            arrayList.add(treeNode.val);
            start++;
            //每打印一个节点，就把此几点的下层左右节点放入队列
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
            //判断本层是否打印完成
            if (start == end) {
                //此时的queue中存储的都是下一层的节点，即end即为queue的大小
                end = queue.size();
                start = 0;
                arrayLists.add(arrayList);
                //重置arrayList
                arrayList = new ArrayList<>(end);
            }
        }

        return arrayLists;
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

    public ArrayList<ArrayList<Integer>> FindPathV2(TreeNode root, int target) {
        if (root == null) return result;
        list.add(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            result.add(new ArrayList<>(list));
        }
        FindPathV2(root.left, target);
        FindPathV2(root.right, target);
        list.remove(list.size() - 1);
        return result;
    }

    //请实现一个函数按照"之"字形打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
    public ArrayList<ArrayList<Integer>> Print_之(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null) {
            return arrayLists;
        }

        printByDepth(pRoot, 1, arrayLists);

        //讲偶数层反转，面试时会说海量数据，根本行不通
        for (int i = 0; i < arrayLists.size(); i++) {
            if ((i - 1) % 2 == 0) {
                ArrayList<Integer> integers = new ArrayList<>(arrayLists.get(i));
                for (int j = 0; j < integers.size(); j++) {
                    arrayLists.get(i).set(j, integers.get(integers.size() - 1 - j));
                }
            }
        }

        return arrayLists;
    }

    void printByDepth(TreeNode node, int depth, ArrayList<ArrayList<Integer>> arrayLists) {
        if (node == null) return;
        if (depth > arrayLists.size()) {
            arrayLists.add(new ArrayList<>());
        }
        ArrayList<Integer> thisLayerNumber = arrayLists.get(depth - 1);
        thisLayerNumber.add(node.val);

        printByDepth(node.left, depth + 1, arrayLists);
        printByDepth(node.right, depth + 1, arrayLists);
    }

    public ArrayList<ArrayList<Integer>> Print_之_V2(TreeNode pRoot) {

        ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
        if (pRoot == null) return arrayLists;
        Stack<TreeNode> oldStack = new Stack<>();
        Stack<TreeNode> evenStack = new Stack<>();
        int layer = 1;
        oldStack.push(pRoot);
        while (!(oldStack.isEmpty() && evenStack.isEmpty())) {
            ArrayList<Integer> arrayList = new ArrayList<>();
            if (layer % 2 != 0) {
                while (!oldStack.isEmpty()) {
                    TreeNode treeNode = oldStack.pop();
                    if (treeNode != null) {
                        arrayList.add(treeNode.val);
                        evenStack.push(treeNode.left);
                        evenStack.push(treeNode.right);
                    }
                }
                if (!arrayList.isEmpty()) {
                    arrayLists.add(arrayList);
                    layer++;
                }
            } else {
                while (!evenStack.isEmpty()) {
                    TreeNode treeNode = evenStack.pop();
                    if (treeNode != null) {
                        arrayList.add(treeNode.val);
                        oldStack.push(treeNode.right);
                        oldStack.push(treeNode.left);
                    }
                }
                if (!arrayList.isEmpty()) {
                    arrayLists.add(arrayList);
                    layer++;
                }

            }
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
        TreeNode treeNode = new TreeNode(Integer.valueOf(strings[index]));
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

    //将二叉树转换成一个双向链表
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree == null) {
            return null;
        }
        if (pRootOfTree.left == null && pRootOfTree.right == null) {
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

}
