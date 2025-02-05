package com.casa.eligibility.check.Controller;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TestJava {
    // E.g. Input: Automation, Output: noitamotuA
    // Please don't use method such as s.reverse()
    public static String reverseStringWithoutUsingStringMethod(String s) {
        char[] charArray = s.toCharArray();
        int left = 0;
        int right = charArray.length - 1;
        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;
            left++;
            right--;
        }
        return new String(charArray);
    }

    // Sort the integer in ASC order without using the built-in method such as ArrayUtils.sort
    public static Integer[] sortIntegers(Integer[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;
    }

    // Check if the given date is within the date range
    public static boolean isInDateRange(Date givenDate, Date startDate, Date endDate) {
        return !givenDate.before(startDate) && !givenDate.after(endDate);
    }

    // sort the given String in ASC order without using method like Arrays.sort
    public static char[] sortStringInAscOrder(String input) {
        char[] charArray = input.toCharArray();
        for (int i = 0; i < charArray.length - 1; i++) {
            for (int j = i + 1; j < charArray.length; j++) {
                if (charArray[i] > charArray[j]) {
                    char temp = charArray[i];
                    charArray[i] = charArray[j];
                    charArray[j] = temp;
                }
            }
        }
        return charArray;
    }

    // Given a Alphanumeric, please return a character with the lowest occurrence
    // E.g. AbcdAbc123123, the answer is d as it only occurs once
    // If there is more than 1 char with the same lowest occurrence, return the first char in ASC order
    public static char lowestOccurrence(String input) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (char c : input.toCharArray()) {
            charCountMap.put(c, charCountMap.getOrDefault(c, 0) + 1);
        }

        int minCount = Integer.MAX_VALUE;
        char resultChar = '\0';
        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            if (entry.getValue() < minCount || (entry.getValue() == minCount && entry.getKey() < resultChar)) {
                minCount = entry.getValue();
                resultChar = entry.getKey();
            }
        }
        return resultChar;
    }

    // Given the array of Integer, please apply the provided equations (+, -, x)
    // E.g. input: 1, 2, 3; equations: x*x, x*2-3, x*5+1
    // Answer: 1st equation: 1*1, 2*2, 3*3 = 1, 4, 9
    //         2nd equation: 1*2-3, 4*2-3, 9*2-3 = -1, 5, 15
    //         3rd equation: -1*5+1, 5*5+1, 15*5+1 = -4, 26, 76
    //         return { -4, 26, 76 }
    public static Integer[] solveEquations(Integer[] input, String[] equations) {
    Integer[] result = new Integer[input.length];

    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("JavaScript"); //JavaScript Engine should not be null

    try {
        for (int i = 0; i < input.length; i++) {
            int x = input[i];
            String equation = equations[i].replace("x", String.valueOf(x));

            try {
                Object evalResult = engine.eval(equation);
                result[i] = Integer.parseInt(evalResult.toString()); // Convert result to Integer
            } catch (Exception e) {
                result[i] = null; //  Ensure null does not cause NPE in return type
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return result;
}

    public static void main(String[] args)  {

        System.out.println("Test 1: " + reverseStringWithoutUsingStringMethod("Automation"));

        Integer[] intArray = new Integer[] { 10, 12, 54, 1, 2, -9, 8 };
        System.out.print("Test 2: ");
        intArray = sortIntegers(intArray);
        for (Integer i : intArray) {
            System.out.print(i + ", ");
        }

        System.out.println();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            Date startDate = sdf.parse("2024-12-01 13:09:22");
            Date endDate = sdf.parse("2025-01-09 20:10:12");
            Date givenDate = sdf.parse("2025-02-02 00:11:22");
            System.out.println("Test 3: " + isInDateRange(givenDate, startDate, endDate));
        } catch (Exception e) {
            System.out.println(e);
        }

        char[] sorted = sortStringInAscOrder("testingNG311");
        System.out.print("Test 4 :");
        for (char c: sorted) {
            System.out.print(c + ", ");
        }
        System.out.println();

        System.out.println("Test 5: " + lowestOccurrence("Abc1dd23affbc1ee23u3278"));

        System.out.print("Test 6: ");
        Integer[] equationArray = solveEquations(new Integer [] { 1, 2, 3 }, new String[] { "x*x", "x*3-5", "x+6-10" });
        for (Integer i : equationArray) {
            System.out.print(i  + ", ");
        }
    }
}
