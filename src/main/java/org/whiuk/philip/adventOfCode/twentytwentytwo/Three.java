package org.whiuk.philip.adventOfCode.twentytwentytwo;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class Three {

    public static void partOne() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/3/input"), Charset.defaultCharset());
        int prioritySum = 0;
        for (String container: inputData) {
            char[] firstHalf = container.substring(0, container.length()/2).toCharArray();
            char[] secondHalf = container.substring(container.length()/2).toCharArray();
            char matchingChar = findMatch(firstHalf, secondHalf);
            prioritySum += calculatePriority(matchingChar);
        }
        System.out.println("Priority sum: "+prioritySum);
    }

    public static void partTwo() throws IOException {
        List<String> inputData = FileUtils.readLines(new File("input/2022/3/input"), Charset.defaultCharset());
        int prioritySum = 0;
        for (int i = 0; i < inputData.size(); i += 3) {
            System.out.println(inputData.get(i));
            char[] rucksack1 = inputData.get(i).toCharArray();
            char[] rucksack2 = inputData.get(i+1).toCharArray();
            char[] rucksack3 = inputData.get(i+2).toCharArray();
            char badge = calculateBadgeItem(rucksack1, rucksack2, rucksack3);
            System.out.println(badge);
            prioritySum += calculatePriority(badge);
        }
        System.out.println("Priority sum: "+prioritySum);
    }

    private static char calculateBadgeItem(char[] rucksack1, char[] rucksack2, char[] rucksack3) {
        for (int i = 0; i < rucksack1.length; i++) {
            for (int j = 0; j < rucksack2.length; j++) {
                if (rucksack1[i] == rucksack2[j]) {
                    for (int k = 0; k < rucksack3.length; k++) {
                        if (rucksack1[i] == rucksack3[k]) {
                            return rucksack1[i];
                        }
                    }
                }
            }
        }
        throw new RuntimeException();
    }

    private static char findMatch(char[] firstHalf, char[] secondHalf) {
        for (int i = 0; i < firstHalf.length; i++) {
            for (int j = 0; j < secondHalf.length; j++) {
                if (firstHalf[i] == secondHalf[j]) {
                    return firstHalf[i];
                }
            }
        }
        throw new RuntimeException();
    }

    private static int calculatePriority(char item) {
        if (item >= 'a' && item <= 'z') {
            return item - 'a' + 1;
        } else if (item >= 'A' && item <= 'Z') {
            return item - 'A' + 1 + 26;
        } else {
            throw new RuntimeException("Unexpected item: "+item);
        }
    }
}
