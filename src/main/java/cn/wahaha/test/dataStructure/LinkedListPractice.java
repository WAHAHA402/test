package cn.wahaha.test.dataStructure;

import java.util.ArrayList;
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

    //输入两个链表，找出它们的第一个公共结点
    //解法一： 蛮力法，循环便利，时间复杂度为O(m*n)
    public ListNode FindFirstCommonNode1(ListNode pHead1, ListNode pHead2) {
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

    //解法二： java只有值引用
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
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

    //求链表中环的入口结点
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null) {
            return null;
        }
        ListNode fast = pHead;
        ListNode slow = pHead;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
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

    //合并两个单调递增的链表，同时保证合并后的链表满足单调不减原则
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
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
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        if (list1.val <= list2.val) {
            list1.next = Merge2(list1.next, list2);
            return list1;
        } else {
            list2.next = Merge2(list1, list2.next);
            return list2;
        }

    }

    //输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
    ArrayList<Integer> arrayList = new ArrayList<>();

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty()) {
            arrayList.add(stack.pop());
        }

        return arrayList;
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
        //新增每个节点，插入原节点与其next节点之间，如原来是A->B->C 变成A->A'->B->B'->C->C'
        while (currentNode != null) {
            RandomListNode newNode = new RandomListNode(currentNode.label);
            newNode.next = currentNode.next;
            currentNode.next = newNode;
            currentNode = newNode.next;
        }

        currentNode = pHead;
        //为每个新增的节点复制random节点
        while (currentNode != null) {
            if (currentNode.random != null) {
                currentNode.next.random = currentNode.random.next;
            }
            currentNode = currentNode.next.next;
        }

        //拆分链表
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

    //输入一个链表，输出该链表中倒数第k个结点。
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null || k <= 0) return null;
        ListNode fast = head;
        ListNode slow = head;
        while (k - 1 > 0) {
            if (fast.next != null) {
                fast = fast.next;
                k--;
            } else {
                return null;
            }
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }

    public ListNode FindKthToTailV2(ListNode head, int k) {
        if (k <= 0 || head == null) {
            return null;
        }
        ListNode ahead = head;
        ListNode behind = head;
        for (int i = 0; i < k - 1; i++) {
            if (ahead.next != null) {
                ahead = ahead.next;
            } else {
                return null;
            }
        }
        while (ahead.next != null) {
            ahead = ahead.next;
            behind = behind.next;
        }
        return behind;
    }

    //在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
    //例如，链表-1->2->3->3->3->4->4->5 处理后为 1->2->5
    public ListNode deleteDuplication(ListNode pHead) {
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

    public ListNode deleteDuplicationV2(ListNode pHead) {
        if (pHead == null || pHead.next == null) {
            return pHead;
        }
        //添加哨兵结点，以方便碰到第一个，第二个节点就相同的情况
        ListNode guard = new ListNode(-1);
        guard.next = pHead;

        ListNode pre = guard;
        ListNode last = pHead;
        while (last != null) {
            if (last.next != null && last.val == last.next.val) {
                while (last.next != null && last.val == last.next.val) {
                    last = last.next;
                }
                pre.next = last.next;
                last = last.next;
            } else {
                last = last.next;
                pre = pre.next;

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
