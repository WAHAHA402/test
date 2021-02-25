package cn.wahaha.test.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * @Description: LinkedList
 * @Author: zhangrenwei
 * @Date: 2019/11/17 6:26 下午
 */

public class LinkedListPractice {
    /**
     * 链表结点
     */
    public class ListNode {
        int val;
        ListNode next = null;

        public ListNode(int val) {
            this.val = val;
        }
    }

    // 输入两个链表，找出它们的第一个公共结点
    // 解法一： 蛮力法，循环便利，时间复杂度为O(m*n) 不推荐
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        while (pHead1 != null) {
            ListNode node = pHead2;
            while (node != null) {
                if (pHead1 == node) {
                    return pHead1;
                }
                node = node.next;
            }
            pHead1 = pHead1.next;
        }

        return null;
    }

    // 解法二： 求出两个链表的长度，长的先走多出的几步，然后开始同时走，如果有相同的结点，则一定会再相遇
    //          时间负责度是O(m + n)
    // java只有值引用
    public ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) {
            return null;
        }
        int pHead1Length = sumLinkedListLength(pHead1);
        int pHead2Length = sumLinkedListLength(pHead2);
        int stepDifference = Math.abs(pHead1Length - pHead2Length);
        if (pHead1Length > pHead2Length) {
            pHead1 = walkStep(pHead1, stepDifference);
        } else {
            pHead2 = walkStep(pHead2, stepDifference);
        }
        while (pHead1 != pHead2) {
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return pHead1;
    }

    int sumLinkedListLength(ListNode listNode) {
        if (listNode == null) {
            return 0;
        }
        int sum = 0;
//        ListNode tempNode = listNode;
        while (listNode != null) {
            sum += 1;
            listNode = listNode.next;
        }
        return sum;
    }

    ListNode walkStep(ListNode listNode, int stepNumber) {
        for (int i = 0; i < stepNumber; i++) {
            listNode = listNode.next;
        }
        return listNode;
    }

    /**
     * 这个解法跟上面那个方法是一个意思
     */
    public ListNode FindFirstCommonNode2(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null) return null;
        int lenth1 = 0;
        int lenth2 = 0;
        ListNode temp1 = pHead1;
        ListNode temp2 = pHead2;
        while (temp1 != null) {
            lenth1++;
            temp1 = temp1.next;
        }

        while (temp2 != null) {
            lenth2++;
            temp2 = temp2.next;
        }
        int stepDiff = Math.abs(lenth1 - lenth2);
        if (lenth1 > lenth2) {
            for (int i = 0; i < stepDiff; i++) {
                pHead1 = pHead1.next;
            }
        } else {
            for (int i = 0; i < stepDiff; i++) {
                pHead2 = pHead2.next;
            }
        }
        while (pHead1 != null && pHead2 != null) {
            if (pHead1 == pHead2) {
                return pHead1;
            }
            pHead1 = pHead1.next;
            pHead2 = pHead2.next;
        }
        return null;
    }

    /**
     * 解法三： 等于将两个链表串联起来了，两个指针要遍历的链表长度就一样了；
     *  注意： 这里终止条件是，找到相同的结点了；或者没有找到，两个列表的值同步变成空（这点是比较费脑的）
     *  看下面的链表例子：
     * 0-1-2-3-4-5-null
     * a-b-4-5-null
     * 下面代码里的的if语句，对于某个指针p1来说，其实就是让它跑了连接好的的链表，长度就变成一样了。
     * 如果有公共结点，那么指针一起走到末尾的部分，也就一定会重叠。看看下面指针的路径吧。
     * p1： 0-1-2-3-4-5-null-a-b-4-5-null
     * p2: a-b-4-5-null-0-1-2-3-4-5-null
     * 因此，两个指针所要遍历的链表就长度一样了！
     * 如果两个链表存在公共结点，那么p1就是该结点，如果不存在那么p1将会是null。
     */
    public ListNode FindFirstCommonNode3(ListNode pHead1, ListNode pHead2) {
        if(pHead1 == null || pHead2 == null) return null;

        ListNode head1 = pHead1;
        ListNode head2 = pHead2;
        while (head1 != head2) {
            head1 = head1.next;
            head2 = head2.next;

            if (head1 != head2) {
                if (head1 == null) head1 = pHead2;
                if (head2 == null) head2 = pHead1;
            }
        }

        return head1;
    }

    // 求链表中环的入口结点
    // 这题有个理论，直接看题解最好，简单明了，不值得花时间自己思考
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) return null;

        ListNode fast = pHead;
        ListNode slow = pHead;

        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }

        if (fast == null || fast.next == null) return null;

        fast = pHead;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }

        return fast;

    }

    //输入一个链表，反转链表后，输出新链表的表头。
    // 解法1：反转的时候容易丢失指针，导致链表断裂，所以需要额外的指针保存会断裂的指针
    //同时，需要知道前一个结点的位置； 参考哨兵结点
    public ListNode ReverseList1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode nextNode = head.next;
        ListNode currentNode = head;
        ListNode previousNode = null;
        while (nextNode != null) {
            nextNode = currentNode.next;
            if (nextNode == null) {
                head = currentNode;
            }
            currentNode.next = previousNode;
            previousNode = currentNode;
            currentNode = nextNode;
        }

        return head;
    }

    // 解法2： 这个解法相对于上面那个更加简单一些，不需要多保存一个当前结点；
    //      核心思路在于，画图，保存好改变指向会丢失的下一个结点，再遍历就行，仅此而已
    public ListNode ReverseList2(ListNode head) {
       if (head == null || head.next == null) {
           return head;
       }

       ListNode previous = null;
       ListNode next = head.next;

       while(head != null) {
           next = head.next;
           head.next = previous;
           previous = head;
           head = next;
       }

       return previous;
    }

    //解法2： 递归解法
    public ListNode ReverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode reversedList = ReverseList(head.next);
        head.next.next = head;
        head.next = null;
        return reversedList;
    }

    // 合并两个单调递增的链表，同时保证合并后的链表满足单调不减原则
    public ListNode Merge(ListNode list1, ListNode list2) {
        // 有没有发现，这段代码，在方法末尾其实重复了，所以这里可以去掉，但是这个第一步验证的思想还是要有
//        if (list1 == null) {
//            return list2;
//        }
//        if (list2 == null) {
//            return list1;
//        }
        //此处设置一个哨兵结点
        ListNode guard = new ListNode(-1);
        ListNode current = guard;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                current = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                current = list2;
                list2 = list2.next;
            }
        }
        //利用两个链表已经排好序的特点
        if (list1 == null) {
            current.next = list2;
        }
        if (list2 == null) {
            current.next = list1;
        }
        return guard.next;

    }

    //解法二：递归版本
    public ListNode Merge2(ListNode list1, ListNode list2) {
//        if (list1 == null) {
//            return list2;
//        }
//        if (list2 == null) {
//            return list1;
//        }
//        if (list1.val <= list2.val) {
//            list1.next = Merge2(list1.next, list2);
//            return list1;
//        } else {
//            list2.next = Merge2(list1, list2.next);
//            return list2;
//        }
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        if (list1.val < list2.val) {
            list1.next = Merge2(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge2(list2.next, list1);
            return list2;
        }

    }

    //输入一个链表，按链表从尾到头的顺序返回一个ArrayList

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> integers = new ArrayList<>();
        if (listNode == null) return integers;

        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.empty()) {
            integers.add(stack.pop());
        }

        return integers;
    }

    public class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    //输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
    // 返回结果为复制后复杂链表的head。
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }
        RandomListNode currentNode = pHead;
        // 1、新增每个节点，插入原节点与其next节点之间，如原来是A->B->C 变成A->A'->B->B'->C->C'
        while (currentNode != null) {
            RandomListNode newNode = new RandomListNode(currentNode.label);
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            currentNode = newNode.next;
        }

        currentNode = pHead;
        // 2、为每个新增的节点复制random节点
        while (currentNode != null) {
            if (currentNode.random != null) {
                currentNode.next.random = currentNode.random.next;
            }
            currentNode = currentNode.next.next;
        }

        // 3、拆分链表
        currentNode = pHead;
        RandomListNode newHead = pHead.next;
        RandomListNode currentNewNode = newHead;
        while (currentNode != null) {
            currentNode.next = currentNewNode.next;
            currentNode = currentNewNode.next;

            if (currentNewNode.next != null) {
                currentNewNode.next = currentNewNode.next.next;
                currentNewNode = currentNewNode.next;
            }
        }

        return newHead;
    }

    // 相较于上面那个最优但是最难写出来的解，下面这个方法，利用了hashmap保存映射关系，增加了一个空间复杂度O(n)，但是很好写啊！！！
    public RandomListNode Clone_V2(RandomListNode pHead) {
        if (pHead == null) {
            return null;
        }

        RandomListNode current = pHead;
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();

        while(current != null) {
            RandomListNode node = new RandomListNode(current.label);
            map.put(current, node);
            current = current.next;
        }

        current = pHead;
        while (current != null) {
            RandomListNode node = map.get(current);
            if (current.next != null){
                node.next = map.get(current.next);
            }
            if (current.random != null) {
                node.random = map.get(current.random);
            }

            current = current.next;
        }

        return map.get(pHead);

    }

    // 输入一个链表，输出该链表中倒数第k个结点
    // 这道题，最容易想到的一种解法是，遍历整个列表，获取长度为n，然后正数第n-k就是那个结点，时间复杂度为O(n)+O(n-k)
    // 第二种解法，快慢指针法： 第一：快指针先走k步；第二：快慢指针开始每次同时走一步，当快指针走完时，慢指针所在结点即为倒数第k个结点；
    // 相较于第一种解法，时间复杂度较小；下面是第二种解法的实现
    public ListNode FindKthToTail(ListNode head, int k) {
//        if (k <= 0 || head == null) {
//            return null;
//        }
//        ListNode ahead = head;
//        ListNode behind = head;
//        for (int i = 0; i < k - 1; i++) {
//            if (ahead.next != null) {
//                ahead = ahead.next;
//            } else {
//                return null;
//            }
//        }
//        while (ahead.next != null) {
//            ahead = ahead.next;
//            behind = behind.next;
//        }
//        return behind;

        if(k <= 0 || head == null) return null;
        ListNode fast = head;
        ListNode slow = head;

        for (int i = 0; i < k; i++) {
            if (fast == null) {
                return null;
            }
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    //在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
    //例如，链表-1->2->3->3->3->4->4->5 处理后为 1->2->5
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;
        //添加哨兵结点，以方便碰到第一个，第二个节点就相同的情况
        ListNode guard = new ListNode(-1);
        guard.next = pHead;

        ListNode pre = guard;
        ListNode last = guard.next;
        while (last != null) {
            if (last.next != null && last.val == last.next.val) {
                while (last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                pre.next = last.next;
                last = last.next;
            } else {
                pre = pre.next;
                last = last.next;
            }
        }

        return guard.next;
    }

    public ListNode deleteDuplicationV2(ListNode pHead) {
        if (pHead == null || pHead.next == null) return pHead;

        ListNode guard = new ListNode(-1);
        guard.next = pHead;

        ListNode pre = guard;
        ListNode last = guard.next;
        while (last != null) {
            if (last.next != null && last.val == last.next.val) {
                while (last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                pre.next = last.next;
                last = last.next;
            } else {
                pre = pre.next;
                last = last.next;
            }
        }

        return guard.next;
    }

    public static void main(String[] args) {
        System.out.println(-3 % 2);
        System.out.println(3 % -2);

        System.out.println(-2 % 12);
        System.out.println(-20 % 12);
    }


}
