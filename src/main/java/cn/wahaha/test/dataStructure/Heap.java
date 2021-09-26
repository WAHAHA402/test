package cn.wahaha.test.dataStructure;

import java.util.PriorityQueue;

public class Heap {
    public int lastStoneWeight(int[] stones) {
        // 按照从大到小的顺序排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones) {
            priorityQueue.offer(stone);
        }

        while (priorityQueue.size() > 1) {
            int a = priorityQueue.poll();
            int b = priorityQueue.poll();
            if(a > b) {
                priorityQueue.offer(a - b);
            }
        }

        return priorityQueue.isEmpty() ? 0 : priorityQueue.poll();
    }
}
