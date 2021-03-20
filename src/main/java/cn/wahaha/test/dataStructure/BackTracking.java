package cn.wahaha.test.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 回溯法（back tracking）（探索与回溯法）是一种选优搜索法，又称为试探法，按选优条件向前搜索，以达到目标。
 * 但当探索到某一步时，发现原先选择并不优或达不到目标，就退回一步重新选择，这种走不通就退回再走的技术为回溯法，
 * 而满足回溯条件的某个状态的点称为“回溯点”。
 *
 * 白话：回溯法可以理解为通过选择不同的岔路口寻找目的地，一个岔路口一个岔路口的去尝试找到目的地。
 *  如果走错了路，继续返回来找到岔路口的另一条路，直到找到目的地。
 *
 *  穷举，是不是就是要用递归才行
 */
public class BackTracking {
    // 题目：电话号码的数字组合
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) {
            return combinations;
        }

        Map<Character, String> phoneMap = new HashMap<Character, String>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};

        backTrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    public void backTrack(List<String> combinations, Map<Character, String> phoneMap, String digits, int index, StringBuffer combination){
        if (index == digits.length()){
            // 回溯的终点
            combinations.add(combination.toString());
        } else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit);
            for (int i = 0; i < letters.length(); i++) {
                combination.append(letters.charAt(i));
                backTrack(combinations, phoneMap, digits, index + 1, combination);
                // 回退倒上一步(岔路口)
                combination.deleteCharAt(index);
            }
        }

    }






}
