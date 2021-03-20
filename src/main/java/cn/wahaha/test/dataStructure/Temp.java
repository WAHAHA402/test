package cn.wahaha.test.dataStructure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Temp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        String string = scanner.nextLine();
//        String string = "*c*m*b*n*t*";
        StringBuilder stars = new StringBuilder();
        StringBuilder letters = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '*') {
                stars.append('*');
            } else {
                letters.append(string.charAt(i));
            }
        }

        System.out.println(stars.append(letters).toString());

    }


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






}
